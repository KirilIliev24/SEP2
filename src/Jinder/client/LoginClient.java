package Jinder.client;

import Jinder.network.IServer;
import Jinder.sharedFiles.User;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoginClient implements LoginClientInt
{
    private IServer server;

    public LoginClient()
    {
        try
        {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            server = (IServer) reg.lookup("Server");
        } catch (RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public User typeOfAccount(String email, String pass)
    {
        User account = null;
        try
        {
            account = server.typeOfAccount(email, pass);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void newCandidateAcc(User newUser)
    {
        try
        {
            server.newCandidateAccount(newUser);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void newCompanyAcc(User newUser)
    {
        try
        {
            server.newCompanyAccount(newUser);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isNotExisting(String username)
    {

        boolean boo = false;
        try
        {

            if (server.checkExisting(username))
            {
                boo = true;
            } else
            {
                boo = false;
            }
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return boo;
    }
}
