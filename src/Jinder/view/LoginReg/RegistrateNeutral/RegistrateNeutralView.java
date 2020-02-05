package Jinder.view.LoginReg.RegistrateNeutral;

import Jinder.ViewModel.LoginReg.RegistrateNeutral.RegistrateNeutralVM;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

public class RegistrateNeutralView
{
    private RegistrateNeutralVM viewModel;
    @FXML
    private JFXTextField usernameId;
    @FXML
    private JFXPasswordField passwordId;
    @FXML
    private JFXPasswordField passwordRepId;
    @FXML
    private JFXTextField firstNameId;
    @FXML
    private JFXTextField lastNameId;
    @FXML
    private JFXDatePicker dobId;
    @FXML
    private RadioButton choiceCompany;
    @FXML
    private RadioButton choiceCandidate;
    @FXML
    private ToggleGroup category;

    private JFXPanel dialogStage;

    public void init(RegistrateNeutralVM vm)
    {
        this.viewModel = vm;

        usernameId.textProperty().bindBidirectional(viewModel.onUsernameId());
        passwordId.textProperty().bindBidirectional(viewModel.onPasswordId());
        passwordRepId.textProperty().bindBidirectional(viewModel.onPasswordRepId());
        firstNameId.textProperty().bindBidirectional(viewModel.onFirstNameId());
        lastNameId.textProperty().bindBidirectional(viewModel.onLastNameId());
        dobId.valueProperty().bindBidirectional(viewModel.onDobId());

        category = new ToggleGroup();

        choiceCompany.setToggleGroup(category);
        choiceCandidate.setToggleGroup(category);
        viewModel.setValues();
        dialogStage = new JFXPanel();

        usernameId.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {
                    case ENTER:
                        passwordId.requestFocus();
                }
            }
        });
        passwordId.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {
                    case ENTER:
                        passwordRepId.requestFocus();
                }
            }
        });
        passwordRepId.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {
                    case ENTER:
                        firstNameId.requestFocus();
                }
            }
        });
        firstNameId.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {
                    case ENTER:
                        lastNameId.requestFocus();
                }
            }
        });
        lastNameId.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {
                    case ENTER:
                        dobId.requestFocus();
                }
            }
        });
    }

    public void backToLogin(ActionEvent actionEvent)
    {
        viewModel.openLoginScreen();

    }

    public void registrate(ActionEvent actionEvent)
    {
        if ((!(choiceCompany.isSelected()) && !(choiceCandidate.isSelected())) || usernameId.getText() == null || usernameId.getText().equals("")
                || passwordId.getText() == null || passwordId.getText().equals("") || passwordRepId.getText() == null || passwordRepId.getText().equals("")
                || firstNameId.getText() == null || firstNameId.getText().equals("") || lastNameId.getText() == null || lastNameId.getText().equals("")
                || dobId.getValue() == null || dobId.getValue().equals(""))
        {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeight(300);
            alert.setTitle("ERROR");
            alert.setHeaderText("Fill out all the fields!");
            alert.setContentText("You forgot to fill out all the fields, that are required, for continuing the registration process!");
            alert.showAndWait();
        } else
        {
            if (!(passwordId.getText().equals(passwordRepId.getText())))
            {
                System.out.println("asdasd");
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeight(150);
                alert.setTitle("ERROR");
                alert.setHeaderText("Passwords does not match!");
                alert.setContentText("You need to write similar passwords, for the registration!");
                alert.showAndWait();
            } else if (choiceCompany.isSelected())
            {
                viewModel.openCompanyReg();
            } else if (choiceCandidate.isSelected())
            {
                viewModel.openCandidateReg();
            }
        }
    }

}
