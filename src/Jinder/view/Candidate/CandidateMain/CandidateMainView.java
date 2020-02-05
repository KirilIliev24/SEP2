package Jinder.view.Candidate.CandidateMain;

import Jinder.ViewModel.Candidate.CandidateMain.CandidateMainVM;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeEvent;


public class CandidateMainView
{
    @FXML
    private TextArea chatArea;
    @FXML
    private JFXTextField newMessageField;
    @FXML
    private JFXListView<String> listOfCompanies;
    @FXML
    private ImageView log1;
    @FXML
    private ImageView log2;
    @FXML
    private AnchorPane anchor;

    private CandidateMainVM viewModel;

    public void init(CandidateMainVM vm)
    {
        this.viewModel = vm;
        chatArea.textProperty().bindBidirectional(viewModel.getChatArea());
        newMessageField.textProperty().bindBidirectional(viewModel.getNewMsgField());

        listOfCompanies.setItems(viewModel.getItems());
        listOfCompanies.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2)
            {
                viewModel.showConversation(((Integer) number2).intValue());
            }
        });

        viewModel.addListener("SetCarret", this::setCarret);
        newMessageField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {
                    case ENTER:
                        viewModel.sendMessage();
                        newMessageField.requestFocus();
                        chatArea.positionCaret(chatArea.getLength());

                }
            }
        });
    }

    private void setCarret(PropertyChangeEvent propertyChangeEvent)
    {
        Platform.runLater(() -> chatArea.positionCaret(chatArea.getLength()));
    }

    public void onSendMsgButton(ActionEvent actionEvent)
    {
        viewModel.sendMessage();
        newMessageField.requestFocus();
        chatArea.positionCaret(chatArea.getLength());
    }

    public void candSeek(ActionEvent actionEvent)
    {
        viewModel.openCandidateSeek();
    }


    public void pressed(MouseEvent event)
    {
        if (event.getTarget() == anchor)
        {
            log2.setVisible(true);
            log1.setVisible(false);
        }
    }

    public void released(MouseEvent event)
    {
        if (event.getTarget() == anchor)
        {
            log2.setVisible(false);
            log1.setVisible(true);
        }
    }

    public void handleLogout(MouseEvent event)
    {
        if (event.getTarget() == anchor)
        {
            viewModel.openLoginScreen();
        }
    }

}
