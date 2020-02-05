package Jinder.model;

import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface CandidateModel
{
    void initialize(User user);

    void cleanCache();

    void addListener(String eventName, PropertyChangeListener listener);

    Job getJobInfo(String category);

    void reactToJob(String status);

    ArrayList<User> loadEmployersConnected();

    void sendMessage(String message);

    Conversation showConversation(int intValue);

    void fireConversationUpdate(Conversation conversation);

    Conversation loadExistingConversation();

    void clearConversationData();

    String getUser();

    void fireUsersConnectedUpdate(User company);
}
