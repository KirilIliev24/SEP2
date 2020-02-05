package Jinder.network;

import Jinder.client.ClientRemote;
import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServer extends Remote
{
    boolean checkExisting(String username) throws RemoteException;

    User typeOfAccount(String username, String pass) throws RemoteException;//will need pass also

    void setClientConnection(ClientRemote ccr) throws RemoteException;

    void newCandidateAccount(User newCandidateUser) throws RemoteException;

    void newCompanyAccount(User newCompanyUser) throws RemoteException;

    void newJobPost(Job job) throws RemoteException;

    ArrayList<User> companyConnectedTo(User thisUser) throws RemoteException;

    ArrayList<Job> getJobCreatedByUser(User thisUser) throws RemoteException;

    ArrayList<User> getUsersAppliedToJob(Job job) throws RemoteException;

    void setCompanyResponse(Job job, User user, String response) throws RemoteException;

    ArrayList<Job> getJobsForUser(String username, String category) throws RemoteException;

    void userReactedToJob(String username, String status, Job job) throws RemoteException;

    ArrayList<User> CandidateConnectedTo(User thisUser) throws RemoteException;

    void sendNewMessage(String sender, String reciever, String message) throws RemoteException;

    Conversation getConversation(String sender, String receiver) throws RemoteException;
}
