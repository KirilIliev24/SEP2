package Jinder.network;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain
{
    public static void main(String[] args)
    {
        try
        {
            Registry registry = LocateRegistry.createRegistry(1099);
            IServer server = new RMIServer();
            registry.bind("Server", server);
            System.out.println("Server started...");
        } catch (RemoteException e)
        {
            e.printStackTrace();
        } catch (AlreadyBoundException e)
        {
            e.printStackTrace();
        }
    }
}
