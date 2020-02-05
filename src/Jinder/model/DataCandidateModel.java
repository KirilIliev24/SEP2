package Jinder.model;

import Jinder.client.CandidateClient;
import Jinder.client.CandidateClientInt;
import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class DataCandidateModel implements CandidateModel
{
    private CandidateClientInt candidateClient;
    private User thisUser;

    private ArrayList<User> connectedTo;
    private User selectedUser;
    private Conversation conversation;

    private ArrayList<Job> jobsToDisplay;
    private String currentChosenCategory;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);


    public DataCandidateModel()
    {
        candidateClient = new CandidateClient(this);
    }

    @Override
    public void initialize(User user)
    {
        thisUser = user;
        jobsToDisplay = new ArrayList<>();
        currentChosenCategory = "";
        connectedTo = candidateClient.getConnectedEmployers(thisUser);
        selectedUser = null;
        conversation = null;
        candidateClient.setUserConnection(thisUser);

    }

    @Override
    public void cleanCache()
    {
        thisUser = null;
        jobsToDisplay = null;
        currentChosenCategory = "";
        connectedTo = null;
        selectedUser = null;
        conversation = null;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener)
    {
        if (eventName == null || "".equals(eventName))
        {
            support.addPropertyChangeListener(listener);
        } else
        {
            support.addPropertyChangeListener(eventName, listener);
        }
    }

    @Override
    public Job getJobInfo(String category)
    {
        loadJobsToDisplay(category);
        if (jobsToDisplay.isEmpty())
            return null;
        return jobsToDisplay.get(0);
    }

    @Override
    public void reactToJob(String status)
    {
        if (jobsToDisplay != null && jobsToDisplay.isEmpty() == false)
        {
            candidateClient.usersResponseToJob(thisUser.getUsername(), status, jobsToDisplay.get(0));
            jobsToDisplay.remove(0);
        }
    }

    @Override
    public ArrayList<User> loadEmployersConnected()
    {
        connectedTo = candidateClient.getConnectedEmployers(thisUser);
        return connectedTo;
    }

    @Override
    public void sendMessage(String message)
    {
        if (selectedUser != null && message != null)
            candidateClient.newMessage(thisUser.getUsername(), selectedUser.getUsername(), "" + thisUser.getFirstName() + ": " + message);
    }

    @Override
    public Conversation showConversation(int intValue)
    {
        this.selectedUser = connectedTo.get(intValue);
        if (this.selectedUser != null)
            loadConversation();
        return conversation;
    }

    @Override
    public void fireConversationUpdate(Conversation conversation)
    {
        if (thisUser.getUsername().equals(conversation.getRecentReceiver()) && selectedUser.getUsername().equals(conversation.getRecentSender()))
        {
            this.conversation = conversation;
            support.firePropertyChange("UpdateConversation", null, conversation);
        }
    }

    @Override
    public void fireUsersConnectedUpdate(User company)
    {
        if (company != null && connectedTo.contains(company) == false)
        {
            connectedTo.add(company);
            support.firePropertyChange("UpdateUsersConnected", null, connectedTo);
        }
    }

    @Override
    public Conversation loadExistingConversation()
    {
        loadConversation();
        return conversation;
    }

    @Override
    public void clearConversationData()
    {
        selectedUser = null;
        conversation = new Conversation();
    }

    @Override
    public String getUser()
    {
        return thisUser.getUsername();
    }

    private void loadJobsToDisplay(String category)
    {
        ArrayList<Job> tempJobList = candidateClient.getJobsForUser(thisUser.getUsername(), category);
        if (currentChosenCategory != null && currentChosenCategory.equals(category))
        {
            jobsToDisplay = tempJobList;
        } else
        {
            jobsToDisplay = tempJobList;
            currentChosenCategory = category;
        }
    }

    private void loadConversation()
    {
        if (selectedUser != null)
            conversation = candidateClient.getConversation(thisUser.getUsername(), selectedUser.getUsername());
    }
}
