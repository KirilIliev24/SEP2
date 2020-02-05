package Jinder.client;

import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;
import java.util.ArrayList;

public interface CandidateClientInt
{
    ArrayList<Job> getJobsForUser(String username, String category);

    ArrayList<User> getConnectedEmployers(User user);

    void usersResponseToJob(String username, String status, Job job);

    void newMessage(String sender, String receiver, String message);

    Conversation getConversation(String sender, String reciever);

    void setUserConnection(User user);

}
