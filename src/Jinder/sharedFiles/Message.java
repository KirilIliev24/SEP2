package Jinder.sharedFiles;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable
{
    private String sender;
    private String reciever;
    private String message;
    private Timestamp timestamp;

    public Message(String sender, String reciever, String message, Timestamp timestamp)
    {
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSender()
    {
        return sender;
    }

    public String getReciever()
    {
        return reciever;
    }

    public String getMessage()
    {
        return message;
    }

    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public void setReciever(String reciever)
    {
        this.reciever = reciever;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", reciever='" + reciever + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public void setTimestamp(Timestamp timestamp)
    {
        this.timestamp = timestamp;
    }
}
