package Jinder.network;

import Jinder.client.ClientRemote;
import Jinder.database.*;
import Jinder.sharedFiles.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class RMIServer implements IServer
{
    private DBConn dbConn;
    private CandidateHandler candidateHandler;
    private CompanyHandler companyHandler;
    private JobHandler jobHandler;
    private CandidateApplicationHandler applicationHandler;
    private MessageHandler messageHandler;
    private ArrayList<ClientRemote> clientRemotes;

    public RMIServer()
    {
        try
        {
            dbConn = DBConn.getInstance();
            candidateHandler = new CandidateHandlerImpl();
            companyHandler = new CompanyHandlerImpl();
            jobHandler = new JobHandlerImpl();
            applicationHandler = new CandidateApplicationHandlerImpl();
            messageHandler = new MessageHandlerImpl();
            clientRemotes = new ArrayList<>();
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public User typeOfAccount(String username, String pass)
    {
        User user = companyHandler.getCompanyInfo(username, pass);

        if (user == null)
            user = candidateHandler.getCandidateByAccount(username, pass);


        return user;
    }

    @Override
    public void setClientConnection(ClientRemote ccr)
    {
        clientRemotes.add(ccr);
    }

    @Override
    public void newCandidateAccount(User newCandidateUser)
    {
        Candidate cand = (Candidate) newCandidateUser.getTypeClass();
        candidateHandler.createCandidate(newCandidateUser.getUsername(), newCandidateUser.getPassword(),
                newCandidateUser.getFirstName(), newCandidateUser.getLastName(), java.sql.Date.valueOf(newCandidateUser.getDateOfBirth()), newCandidateUser.getType(), cand.getEducation(),
                cand.getExperience(), cand.getSkills(), cand.getSoftwareS(), cand.getLicenses(), cand.getComments());
    }

    @Override
    public void newCompanyAccount(User newCompanyUser) throws RemoteException
    {
        Company comp = (Company) newCompanyUser.getTypeClass();
        companyHandler.createCompany(newCompanyUser.getUsername(), newCompanyUser.getPassword(),
                newCompanyUser.getFirstName(), newCompanyUser.getLastName(), java.sql.Date.valueOf(newCompanyUser.getDateOfBirth()), newCompanyUser.getType(), comp.getCompanyName(), comp.getCompanyDesc());
    }

    @Override
    public void newJobPost(Job job) throws RemoteException
    {
        jobHandler.postJob(job.getUsername(), job.getJobName(), job.getRequirements(), job.getDescription(), job.getCategory());
    }

    @Override
    public boolean checkExisting(String username) throws RemoteException
    {
        if (candidateHandler.isNotExisting(username) && companyHandler.isNotExisting(username))
        {
            return true;
        } else
        {
            return false;
        }

    }

    @Override
    public ArrayList<User> companyConnectedTo(User thisUser)
    {
        ArrayList<Job> jobs = jobHandler.getJob(thisUser.getUsername());
        ArrayList<User> usersConnected = new ArrayList<>();
        ArrayList<User> tempList;

        for (int i = 0; i < jobs.size(); i++)
        {
            int jobId = jobHandler.getId(jobs.get(i).getUsername(), jobs.get(i).getJobName(), jobs.get(i).getRequirements(), jobs.get(i).getDescription(), jobs.get(i).getCategory());
            tempList = applicationHandler.getAcceptedCandidates(jobId);
            if (tempList != null)
                for (int j = 0; j < tempList.size(); j++)
                {
                    if (!usersConnected.contains(tempList.get(j)))
                    {
                        usersConnected.add(tempList.get(j));
                    }
                }
        }
        return usersConnected;
    }

    @Override
    public ArrayList<Job> getJobCreatedByUser(User thisUser) throws RemoteException
    {
        return jobHandler.getJob(thisUser.getUsername());
    }

    @Override
    public ArrayList<User> getUsersAppliedToJob(Job job) throws RemoteException
    {
        int jobId = jobHandler.getId(job.getUsername(), job.getJobName(), job.getRequirements(), job.getDescription(), job.getCategory());
        ArrayList<User> userArrayList = null;
        userArrayList = applicationHandler.getWaitingCandidates(jobId);
        return userArrayList;
    }

    @Override
    public void setCompanyResponse(Job job, User user, String response) throws RemoteException
    {
        int jobId = jobHandler.getId(job.getUsername(), job.getJobName(), job.getRequirements(), job.getDescription(), job.getCategory());
        if (response.equals("Accepted"))
        {
            applicationHandler.changeCandidateState(jobId, user.getUsername(), "Accepted");
            String msg = "You have been accepted by " + job.getEmpName() + " from " + job.getCompName() + " to work at their offered \"" + job.getJobName() + "\" position. You can discuss further details here.";
            messageHandler.createMessage(job.getUsername(), user.getUsername(), msg);
            User company = companyHandler.getCompanyInfo(job.getUsername());
            updateConnectedTo(user.getUsername(), company);
        } else applicationHandler.changeCandidateState(jobId, user.getUsername(), "Declined");
    }

    @Override
    public ArrayList<Job> getJobsForUser(String username, String category) throws RemoteException
    {
        ArrayList<Job> jobsForUser = jobHandler.getJobByCategory(category);
        if (jobsForUser != null)
            for (int i = 0; i < jobsForUser.size(); i++)
            {
                int jobId = jobHandler.getId(jobsForUser.get(i).getUsername(), jobsForUser.get(i).getJobName(), jobsForUser.get(i).getRequirements(),
                        jobsForUser.get(i).getDescription(), jobsForUser.get(i).getCategory());
                if (applicationHandler.isNotExistingOnJobId(username, jobId) == false)
                {
                    jobsForUser.remove(i);
                    i--;
                }
            }
        return jobsForUser;
    }

    @Override
    public void userReactedToJob(String username, String status, Job job) throws RemoteException
    {
        int jobId = jobHandler.getId(job.getUsername(), job.getJobName(), job.getRequirements(), job.getDescription(), job.getCategory());
        if (status.equals("Accepted"))
            applicationHandler.createApplication(jobId, username, "WAITING");
        else applicationHandler.createApplication(jobId, username, "Declined");
    }

    @Override
    public ArrayList<User> CandidateConnectedTo(User thisUser) throws RemoteException
    {
        ArrayList<User> tempEmpList = applicationHandler.getEmployersForAcceptedCandidate(thisUser);
        return tempEmpList;
    }

    @Override
    public void sendNewMessage(String sender, String reciever, String message) throws RemoteException
    {
        messageHandler.createMessage(sender, reciever, message);
        Conversation conversation = new Conversation();
        conversation.setMessages(messageHandler.getMessages(sender, reciever));
        updateConversation(reciever, conversation);
    }

    @Override
    public Conversation getConversation(String sender, String receiver) throws RemoteException
    {
        Conversation conversation = new Conversation();
        conversation.setMessages(messageHandler.getMessages(sender, receiver));
        return conversation;
    }

    private void updateConversation(String receiver, Conversation conversation)
    {
        if (clientRemotes.isEmpty() == false)
            for (int i = 0; i < clientRemotes.size(); i++)
            {
                try
                {
                    if (clientRemotes.get(i).getUsername().equals(receiver))
                    {
                        try
                        {
                            clientRemotes.get(i).updateConversation(conversation);
                        } catch (RemoteException e)
                        {
                            clientRemotes.remove(i);
                            i--;
                        }
                        break;
                    }
                } catch (RemoteException | NullPointerException e)
                {
                    clientRemotes.remove(i);
                    i--;
                }
            }
    }

    private void updateConnectedTo(String receiver, User company)
    {
        if (clientRemotes.isEmpty() == false)
            for (int i = 0; i < clientRemotes.size(); i++)
            {
                try
                {
                    if (clientRemotes.get(i).getUsername().equals(receiver))
                    {
                        try
                        {
                            clientRemotes.get(i).updateUsersConnected(company);
                        } catch (RemoteException e)
                        {
                            clientRemotes.remove(i);
                            i--;
                        }
                        break;
                    }
                } catch (RemoteException | NullPointerException e)
                {
                    clientRemotes.remove(i);
                    i--;
                }
            }
    }
}

