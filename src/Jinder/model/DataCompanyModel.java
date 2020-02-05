package Jinder.model;

import Jinder.client.CompanyClient;
import Jinder.client.CompanyClientInt;
import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class DataCompanyModel implements CompanyModel
{
    private CompanyClientInt companyClient;
    private User thisUser;
    private ArrayList<Job> yourJobs;
    private Job selectedJob;
    private ArrayList<User> appliedUsers;
    private ArrayList<User> connectedTo;
    private User selectedUser;
    private Conversation conversation;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public DataCompanyModel()
    {
        companyClient = new CompanyClient(this);
    }

    @Override
    public void initialize(User user)
    {
        thisUser = user;
        connectedTo = companyClient.getConnectedTo(thisUser);
        yourJobs = companyClient.getJobsCreatedBy(thisUser);
        appliedUsers = new ArrayList<>();
        selectedUser = null;
        conversation = null;
        companyClient.setUserConnection(thisUser);
    }

    @Override
    public void cleanCache()
    {
        thisUser = null;
        connectedTo = null;
        yourJobs = null;
        appliedUsers = null;
        selectedUser = null;
        conversation = null;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener)
    {
        if (eventName == null || "".equals(eventName))
        {
            support.addPropertyChangeListener(listener);
        } else
        {
            support.addPropertyChangeListener(eventName, listener);
        }
    }

    @Override
    public ArrayList<User> getUsersConnectedTo()
    {
        connectedTo = companyClient.getConnectedTo(thisUser);
        return connectedTo;
    }

    @Override
    public ArrayList<Job> getJobsMadeByUser()
    {
        return yourJobs;
    }

    @Override
    public User getUserToReview()
    {
        loadAppliedUsers(selectedJob);
        if (appliedUsers == null || appliedUsers.isEmpty())
            return null;
        return appliedUsers.get(0);
    }

    @Override
    public void respondToUserApplication(String status)
    {
        if (appliedUsers != null && appliedUsers.isEmpty() == false)
        {
            companyClient.sendResponseToApplication(selectedJob, appliedUsers.get(0), status);
            loadAppliedUsers(selectedJob);
        }
    }

    @Override
    public int getApplicantAmount()
    {
        if (appliedUsers != null)
        {
            return appliedUsers.size();
        } else return 0;
    }

    @Override
    public void postNewJob(String jobName, String requirements, String description, String category)
    {
        Job job = new Job(thisUser, jobName, requirements, description, category);
        companyClient.postNewJob(job);
        yourJobs = companyClient.getJobsCreatedBy(thisUser);
    }


    //called when pressed send button
    @Override
    public void sendMessage(String message)
    {
        if (selectedUser != null && message != null)
            companyClient.newMessage(thisUser.getUsername(), selectedUser.getUsername(), "" + thisUser.getFirstName() + ": " + message);
    }

    public Conversation showConversation(int selectedUser)
    {
        this.selectedUser = connectedTo.get(selectedUser);
        if (this.selectedUser != null)
            loadConversation();
        return conversation;
    }

    @Override
    public Job getJobsByIndex(Integer number2)
    {
        selectedJob = yourJobs.get(number2.intValue());
        if (selectedJob != null)
            loadAppliedUsers(selectedJob);
        return yourJobs.get(number2.intValue());
    }

    @Override
    public void fireConversationUpdate(Conversation conversation)
    {
        if (thisUser.getUsername().equals(conversation.getRecentReceiver()) && selectedUser != null && selectedUser.getUsername().equals(conversation.getRecentSender()))
        {
            this.conversation = conversation;
            support.firePropertyChange("UpdateConversation", null, conversation);
        }
    }

    public Conversation loadExistingConversation()
    {
        loadConversation();
        return conversation;
    }

    @Override
    public void clearConversationData()
    {
        selectedUser = null;
        conversation = new Conversation();
    }

    private void loadConversation()
    {
        if (selectedUser != null)
            conversation = companyClient.getConversation(thisUser.getUsername(), selectedUser.getUsername());
    }

    private void loadAppliedUsers(Job job)
    {
        if (selectedJob != null)
        {
            ArrayList<User> userArrayList = companyClient.getAppliedUsers(selectedJob);
            appliedUsers = userArrayList;
        }
    }
}
