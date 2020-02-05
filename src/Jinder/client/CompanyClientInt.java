package Jinder.client;

import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;

import java.util.ArrayList;

public interface CompanyClientInt
{
    void postNewJob(Job job);

    ArrayList<User> getConnectedTo(User thisUser);

    ArrayList<Job> getJobsCreatedBy(User thisUser);

    ArrayList<User> getAppliedUsers(Job job);

    void sendResponseToApplication(Job job, User user, String status);

    void newMessage(String sender, String receiver, String message);

    Conversation getConversation(String sender, String receiver);

    void setUserConnection(User thisUser);
}
