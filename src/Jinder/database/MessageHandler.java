package Jinder.database;

import Jinder.sharedFiles.Message;
import java.util.ArrayList;

public interface MessageHandler
{
    void createMessage(String sender, String reciever, String message);

    ArrayList<Message> getMessages(String sender, String reciever);
}
