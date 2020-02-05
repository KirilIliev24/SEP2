package Jinder.client;

import Jinder.model.CompanyModel;
import Jinder.network.IServer;
import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class CompanyClient implements CompanyClientInt
{
    private IServer server;
    private ClientRemote clientRemote;
    private CompanyModel companyModel;
    private User clientsUser;

    public CompanyClient(CompanyModel companyModel)
    {
        try
        {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            server = (IServer) reg.lookup("Server");
        } catch (RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
        clientRemote = new CompanyClientRemoteImpl(this);
        this.companyModel = companyModel;
    }


    @Override
    public void postNewJob(Job job)
    {
        try
        {
            server.newJobPost(job);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getConnectedTo(User thisUser)
    {
        ArrayList<User> userArrayList = null;
        try
        {
            userArrayList = server.companyConnectedTo(thisUser);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return userArrayList;
    }

    @Override
    public ArrayList<Job> getJobsCreatedBy(User thisUser)
    {
        ArrayList<Job> userJobList = null;
        try
        {
            userJobList = server.getJobCreatedByUser(thisUser);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return userJobList;
    }

    @Override
    public ArrayList<User> getAppliedUsers(Job job)
    {
        ArrayList<User> userArrayList = new ArrayList<>();
        try
        {
            userArrayList = server.getUsersAppliedToJob(job);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return userArrayList;
    }

    @Override
    public void sendResponseToApplication(Job job, User user, String status)
    {
        try
        {
            server.setCompanyResponse(job, user, status);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void newMessage(String sender, String receiver, String message)
    {
        try
        {
            server.sendNewMessage(sender, receiver, message);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Conversation getConversation(String sender, String receiver)
    {
        Conversation conversation = new Conversation();
        try
        {
            conversation = server.getConversation(sender, receiver);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return conversation;
    }

    @Override
    public void setUserConnection(User thisUser)
    {
        clientsUser = thisUser;
        try
        {
            server.setClientConnection(clientRemote);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public String getUser()
    {
        return clientsUser.getUsername();
    }

    public void pushConversation(Conversation conversation)
    {
        companyModel.fireConversationUpdate(conversation);
    }
}
