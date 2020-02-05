package Jinder.view.LoginReg.LoginScreen;

import Jinder.ViewModel.LoginReg.LoginScreen.LoginScreenVM;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class LoginScreenView
{
    @FXML
    private JFXTextField usernameId;
    @FXML
    private JFXPasswordField passId;

    private LoginScreenVM viewModel;

    public void init(LoginScreenVM vm)
    {
        this.viewModel = vm;
        usernameId.textProperty().bindBidirectional(viewModel.loginUsername());
        passId.textProperty().bindBidirectional(viewModel.loginPass());

        usernameId.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {
                    case ENTER:
                        passId.requestFocus();
                    case DOWN:
                        passId.requestFocus();
                }
            }
        });

        passId.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                switch (keyEvent.getCode())
                {
                    case ENTER:
                        viewModel.logging();
                    case UP:
                        usernameId.requestFocus();

                }
            }
        });
    }


    public void onLogin(ActionEvent actionEvent)
    {
        viewModel.logging();
    }

    public void onRegistrate(ActionEvent actionEvent)
    {
        viewModel.openRegistrateNeutral();
    }
}

