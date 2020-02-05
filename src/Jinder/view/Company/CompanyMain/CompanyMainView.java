package Jinder.view.Company.CompanyMain;

import Jinder.ViewModel.Company.CompanyMain.CompanyMainVM;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeEvent;


public class CompanyMainView
{
    @FXML
    private TextArea chatArea;
    @FXML
    private JFXTextField newMsgField;
    @FXML
    private JFXListView<String> listOfApplicants;
    @FXML
    private ImageView log1;
    @FXML
    private ImageView log2;
    @FXML
    private AnchorPane anchor;

    private CompanyMainVM viewModel;

    public void init(CompanyMainVM vm)
    {
        this.viewModel = vm;
        chatArea.textProperty().bindBidirectional(viewModel.getChatArea());
        newMsgField.textProperty().bindBidirectional(viewModel.getNewMsgArea());

        listOfApplicants.setItems(viewModel.getItems());
        listOfApplicants.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2)
            {
                viewModel.showConversation(((Integer) number2).intValue());

            }
        });
        viewModel.addListener("SetCarret", this::setCarret);

        newMsgField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {
                    case ENTER:
                        viewModel.sendMessage();
                        newMsgField.requestFocus();
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
        newMsgField.requestFocus();
        chatArea.positionCaret(chatArea.getLength());

    }


    public void companySeekJobRev(ActionEvent actionEvent)
    {
        viewModel.openCompanySeekJobRev();
    }

    public void compPostJob(ActionEvent actionEvent)
    {
        viewModel.openPostJob();
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
