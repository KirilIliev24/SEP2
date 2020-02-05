package Jinder.model;

import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface CompanyModel
{
    void initialize(User user);

    void cleanCache();

    void addListener(String eventName, PropertyChangeListener listener);

    ArrayList<User> getUsersConnectedTo();

    ArrayList<Job> getJobsMadeByUser();

    User getUserToReview();

    void respondToUserApplication(String status);

    int getApplicantAmount();

    void postNewJob(String jobName, String requirements, String description, String category);

    void sendMessage(String message);

    Conversation showConversation(int selectedUser);

    Conversation loadExistingConversation();

    void clearConversationData();

    void fireConversationUpdate(Conversation conversation);

    Job getJobsByIndex(Integer number2);


}
