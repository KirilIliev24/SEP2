package Jinder.ViewModel.LoginReg.RegistrateNeutral;

import Jinder.model.LoginModel;
import Jinder.view.ViewHandler;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import java.time.LocalDate;

public class RegistrateNeutralVM
{
    private ViewHandler viewHandler;
    private LoginModel loginModel;
    private StringProperty onUserNameId;
    private StringProperty onPasswordId;
    private StringProperty onPasswordRepId;
    private StringProperty onFirstNameId;
    private StringProperty onLastNameId;
    private Property<LocalDate> onDobId;

    public RegistrateNeutralVM(LoginModel loginModel, ViewHandler vh)
    {

        this.loginModel = loginModel;
        this.viewHandler = vh;

        onUserNameId = new SimpleStringProperty();
        onPasswordId = new SimpleStringProperty();
        onPasswordRepId = new SimpleStringProperty();
        onFirstNameId = new SimpleStringProperty();
        onLastNameId = new SimpleStringProperty();
        onDobId = new SimpleObjectProperty<LocalDate>();
    }

    public void setValues()
    {
        if (loginModel.getUser() == null)
        {
            onUsernameId().setValue(null);
            onPasswordRepId().setValue(null);
            onPasswordId().setValue(null);
            onLastNameId().setValue(null);
            onFirstNameId().setValue(null);
            onDobId().setValue(null);
        }
    }

    public void openLoginScreen()
    {
        loginModel.setUserNull();
        viewHandler.openLoginScreen();

    }

    public void openCompanyReg()
    {
        if (loginModel.checkExistance(onUserNameId.getValue()))
        {
            String type = "Company";
            loginModel.regNewUser(onUserNameId.getValue(), onPasswordId.getValue(), type, onFirstNameId.getValue(), onLastNameId.getValue(), onDobId.getValue());
            viewHandler.openCompanyReg();
        } else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeight(100);
            alert.setTitle("ERROR");
            alert.setHeaderText("Username exists!");
            alert.setContentText("The username is used by someone else, choose another one!");
            alert.showAndWait();
        }
    }

    public void openCandidateReg()
    {
        if (loginModel.checkExistance(onUserNameId.getValue()))
        {
            String type = "Candidate";

            loginModel.regNewUser(onUserNameId.getValue(), onPasswordId.getValue(), type, onFirstNameId.getValue(), onLastNameId.getValue(), onDobId.getValue());
            viewHandler.openCandidateReg();
        } else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeight(100);
            alert.setTitle("ERROR");
            alert.setHeaderText("Username exists!");
            alert.setContentText("The username is used by someone else, choose another one!");
            alert.showAndWait();
        }
    }

    public StringProperty onUsernameId()
    {
        return onUserNameId;
    }

    public StringProperty onPasswordId()
    {
        return onPasswordId;
    }

    public StringProperty onPasswordRepId()
    {
        return onPasswordRepId;
    }

    public StringProperty onFirstNameId()
    {
        return onFirstNameId;
    }

    public StringProperty onLastNameId()
    {
        return onLastNameId;
    }

    public Property<LocalDate> onDobId()
    {
        return onDobId;
    }
}
