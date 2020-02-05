package Jinder.ViewModel.LoginReg.LoginScreen;

import Jinder.model.LoginModel;
import Jinder.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

public class LoginScreenVM
{
    public StringProperty loginUsername;
    public StringProperty loginPass;
    private ViewHandler viewHandler;
    private LoginModel loginModel;

    public LoginScreenVM(LoginModel loginModel, ViewHandler vh)
    {
        this.loginModel = loginModel;
        this.viewHandler = vh;
        loginUsername = new SimpleStringProperty();
        loginPass = new SimpleStringProperty();
    }

    public void openUser()
    {
        String strUsername = (String) loginUsername.getValue();
        String strPass = (String) loginPass.getValue();
        String type = loginModel.getAccountType(strUsername, strPass);
        if (type.equals("Company"))
        {
            loginModel.setUserNull();
            viewHandler.openCompanyMain();
        } else if (type.equals("Candidate"))
        {
            loginModel.setUserNull();
            viewHandler.openCandidateMain();
        } else if (type.equals("null"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Login error!");
            alert.setContentText("User doesn't not exist, or you misspelled the username or the password!");
            alert.showAndWait();

        } else if (type.equals("error"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Login error!");
            alert.setContentText("An error occurred during the login process!");
            alert.showAndWait();

        } else
        {
            System.out.println(type);
        }
        loginUsername.setValue(null);
        loginPass.setValue(null);
    }


    public void openRegistrateNeutral()
    {

        viewHandler.openRegistrateNeutral();
        loginUsername.setValue(null);
        loginPass.setValue(null);

    }

    public StringProperty loginUsername()
    {
        return loginUsername;
    }

    public StringProperty loginPass()
    {
        return loginPass;
    }

    public void logging()
    {
        if (loginPass.getValue() == null || loginUsername.getValue() == null || loginPass.getValue().equals("") || loginUsername.getValue().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Fill out all the fields!");
            alert.setContentText("You haven't filled out the username, and the password fields!");
            alert.showAndWait();
        } else if (loginPass.getValue() != null & loginUsername.getValue() != null)
        {
            openUser();
        }
    }
}
