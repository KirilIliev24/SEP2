package Jinder.ViewModel.Candidate.CandidateMain;

import Jinder.model.CandidateModel;
import Jinder.sharedFiles.Conversation;
import Jinder.sharedFiles.User;
import Jinder.view.ViewHandler;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CandidateMainVM
{
    private StringProperty chatArea;
    private StringProperty newMessageField;
    private ObservableList<String> items;
    private ViewHandler viewHandler;
    private CandidateModel candidateModel;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);


    public CandidateMainVM(CandidateModel candidateModel, ViewHandler vh)
    {
        this.candidateModel = candidateModel;
        this.viewHandler = vh;
        chatArea = new SimpleStringProperty();
        newMessageField = new SimpleStringProperty();
        items = FXCollections.observableArrayList();
        this.candidateModel.addListener("UpdateConversation", this::updateConv);
        this.candidateModel.addListener("UpdateUsersConnected", this::updateItems);
    }

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

    private void updateItems(PropertyChangeEvent propertyChangeEvent)
    {
        Platform.runLater(() ->
        {
            items.clear();
            ArrayList<User> connectedUsers = (ArrayList<User>) propertyChangeEvent.getNewValue();
            if (connectedUsers.size() > 0)
                for (int i = 0; i < connectedUsers.size(); i++)
                {
                    String temp = connectedUsers.get(i).getFirstName() + " " + connectedUsers.get(i).getLastName();
                    items.add(temp);
                }
        });
    }

    private void updateConv(PropertyChangeEvent evt)
    {
        Platform.runLater(() -> chatArea.setValue(candidateModel.loadExistingConversation().toString()));
        support.firePropertyChange("SetCarret", null, null);
    }

    public StringProperty getChatArea()
    {
        return chatArea;
    }

    public StringProperty getNewMsgField()
    {
        return newMessageField;
    }

    public ObservableList<String> getItems()
    {
        ArrayList<User> connectedUsers = candidateModel.loadEmployersConnected();
        items.clear();
        if (connectedUsers.size() > 0)
            for (int i = 0; i < connectedUsers.size(); i++)
            {
                String temp = connectedUsers.get(i).getFirstName() + " " + connectedUsers.get(i).getLastName();
                items.add(temp);
            }
        return items;
    }

    public void openCandidateSeek()
    {
        candidateModel.clearConversationData();
        viewHandler.openCandidateSeek();
    }

    public void openLoginScreen()
    {
        candidateModel.cleanCache();
        viewHandler.openLoginScreen();
    }

    public void showConversation(int intValue)
    {
        if (intValue >= 0)
        {
            chatArea.setValue(candidateModel.showConversation(intValue).toString());
        } else chatArea.setValue("");
        support.firePropertyChange("SetCarret", null, null);
    }

    public void sendMessage()
    {
        candidateModel.sendMessage(newMessageField.getValue());
        Conversation conv = candidateModel.loadExistingConversation();
        if (conv != null)
            chatArea.setValue(conv.toString());
        else chatArea.setValue(null);
        newMessageField.setValue(null);
    }
}
