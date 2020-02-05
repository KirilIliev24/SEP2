package Jinder.client;

import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CompanyClientRemoteImpl implements ClientRemote
{
    CompanyClient companyClient;

    public CompanyClientRemoteImpl(CompanyClient client)
    {

        try
        {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        companyClient = client;
    }

    @Override
    public String getUsername() throws RemoteException
    {
        return companyClient.getUser();
    }

    @Override
    public void updateConversation(Conversation conversation) throws RemoteException
    {
        companyClient.pushConversation(conversation);
    }

    @Override
    public void updateUsersConnected(User company) throws RemoteException
    {
        //Live updates for rewiev button could be implemented in the future at this method
        System.out.println("This shouldn't be called >>> CompanyClientRemoteImpl/line:32");
    }
}
