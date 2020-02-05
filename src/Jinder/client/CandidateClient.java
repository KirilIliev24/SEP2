package Jinder.client;

import Jinder.model.CandidateModel;
import Jinder.network.IServer;
import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class CandidateClient implements CandidateClientInt
{
    private IServer server;
    private ClientRemote clientRemote;
    private CandidateModel candidateModel;
    private User clientsUser;

    public CandidateClient(CandidateModel cModel)
    {
        try
        {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            server = (IServer) reg.lookup("Server");
        } catch (RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
        clientRemote = new CandidateClientRemoteImpl(this);
        candidateModel = cModel;
    }

    @Override
    public ArrayList<Job> getJobsForUser(String username, String category)
    {
        ArrayList<Job> jobs = null;
        try
        {
            jobs = server.getJobsForUser(username, category);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public ArrayList<User> getConnectedEmployers(User user)
    {
        ArrayList<User> tempList = new ArrayList<>();
        try
        {
            tempList = server.CandidateConnectedTo(user);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return tempList;
    }

    @Override
    public void usersResponseToJob(String username, String status, Job job)
    {
        try
        {
            server.userReactedToJob(username, status, job);
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
    public Conversation getConversation(String sender, String reciever)
    {
        Conversation conversation = new Conversation();
        try
        {
            conversation = server.getConversation(sender, reciever);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return conversation;
    }

    @Override
    public void setUserConnection(User user)
    {
        clientsUser = user;
        try
        {
            server.setClientConnection(clientRemote);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public void pushConversation(Conversation conversation)
    {
        candidateModel.fireConversationUpdate(conversation);
    }

    public String getUser()
    {
        return clientsUser.getUsername();
    }

    public void pushUsersConnected(User company)
    {
        candidateModel.fireUsersConnectedUpdate(company);
    }
}
