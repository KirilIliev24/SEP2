package Jinder.client;

import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CandidateClientRemoteImpl implements ClientRemote
{
    CandidateClient candidateClient;

    public CandidateClientRemoteImpl(CandidateClient client)
    {

        try
        {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        candidateClient = client;
    }

    @Override
    public String getUsername() throws RemoteException
    {
        return candidateClient.getUser();
    }

    @Override
    public void updateConversation(Conversation conversation) throws RemoteException
    {
        candidateClient.pushConversation(conversation);
    }

    @Override
    public void updateUsersConnected(User company) throws RemoteException
    {
        candidateClient.pushUsersConnected(company);
    }
}
