package Jinder.sharedFiles;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable
{
    ArrayList<Message> messages;

    public Conversation()
    {
        messages = new ArrayList<>();
    }

    public void setMessages(ArrayList<Message> messages)
    {
        if (messages != null)
            this.messages = messages;
        else this.messages.clear();
    }

    public String getRecentReceiver()
    {
        return messages.get(messages.size() - 1).getReciever();
    }

    public String getRecentSender()
    {
        return messages.get(messages.size() - 1).getSender();
    }

    public String toString()
    {
        String str = "";
        for (int i = 0; i < messages.size(); i++)
        {
            str += "" + messages.get(i).getMessage() + "\n";
        }
        return str;
    }
}
