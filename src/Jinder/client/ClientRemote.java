package Jinder.client;

import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.User;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRemote extends Remote
{
    String getUsername() throws RemoteException;

    void updateConversation(Conversation conversation) throws RemoteException;

    void updateUsersConnected(User company) throws RemoteException;
}
