package Jinder.ViewModel.Company.CompanyMain;

import Jinder.model.CompanyModel;
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

public class CompanyMainVM
{
    private StringProperty chatArea;
    private StringProperty newMsgField;

    private ObservableList<String> items;

    private ViewHandler viewHandler;
    private CompanyModel companyModel;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CompanyMainVM(CompanyModel companyModel, ViewHandler vh)
    {
        this.companyModel = companyModel;
        this.viewHandler = vh;
        chatArea = new SimpleStringProperty();
        newMsgField = new SimpleStringProperty();
        items = FXCollections.observableArrayList();
        this.companyModel.addListener("UpdateConversation", this::updateConv);
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

    private void updateConv(PropertyChangeEvent propertyChangeEvent)
    {
        Platform.runLater(() -> chatArea.setValue(companyModel.loadExistingConversation().toString()));
        support.firePropertyChange("SetCarret", null, null);

    }

    public StringProperty getChatArea()
    {
        return chatArea;
    }

    public StringProperty getNewMsgArea()
    {
        return newMsgField;
    }

    public ObservableList<String> getItems()
    {
        ArrayList<User> connectedUsers = companyModel.getUsersConnectedTo();
        items.clear();
        if (connectedUsers.size() > 0)
            for (int i = 0; i < connectedUsers.size(); i++)
            {
                String temp = connectedUsers.get(i).getFirstName() + " " + connectedUsers.get(i).getLastName();
                items.add(temp);
            }
        return items;
    }


    public void openCompanySeekJobRev()
    {
        companyModel.clearConversationData();
        viewHandler.openCompanySeekJobRev();
    }

    public void openPostJob()
    {
        viewHandler.openPostJob();
    }

    public void openLoginScreen()
    {
        companyModel.cleanCache();
        viewHandler.openLoginScreen();
    }

    public void showConversation(int intValue)
    {
        if (intValue >= 0)
            chatArea.setValue(companyModel.showConversation(intValue).toString());
        else chatArea.setValue("");
        support.firePropertyChange("SetCarret", null, null);

    }

    public void sendMessage()
    {
        companyModel.sendMessage(newMsgField.getValue());
        Conversation conv = companyModel.loadExistingConversation();
        if (conv != null)
            chatArea.setValue(conv.toString());
        else chatArea.setValue(null);
        newMsgField.setValue(null);
    }
}


