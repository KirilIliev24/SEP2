package Jinder.view;

import Jinder.ViewModel.ViewModelProvider;
import Jinder.model.CandidateModel;
import Jinder.model.CompanyModel;
import Jinder.model.LoginModel;
import Jinder.view.Candidate.CandidateMain.CandidateMainView;
import Jinder.view.LoginReg.CandidateReg.CandidateRegView;
import Jinder.view.Candidate.CandidateSeek.CandidateSeekView;
import Jinder.view.Company.CompanyMain.CompanyMainView;
import Jinder.view.Company.CompanyPost.CompanyPostJobView;
import Jinder.view.LoginReg.CompanyReg.CompanyRegView;
import Jinder.view.Company.CompanySeek.CompanySeekJobRevView;
import Jinder.view.Company.CompanySeek.CompanySeekView;
import Jinder.view.LoginReg.LoginScreen.LoginScreenView;
import Jinder.view.LoginReg.RegistrateNeutral.RegistrateNeutralView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ViewHandler
{
    private Stage mainStage;
    private Stage jobStage;
    private ViewModelProvider viewModelProvider;

    public ViewHandler(Stage stage, LoginModel loginModel, CandidateModel candidateModel, CompanyModel companyModel)
    {
        viewModelProvider = new ViewModelProvider(loginModel, candidateModel, companyModel, this);
        mainStage = stage;
        jobStage = new Stage();
        mainStage.setResizable(false);
        jobStage.setResizable(false);
        mainStage.getIcons().add(new Image("Jinder\\images\\JinderIcon.png"));
        mainStage.setOnCloseRequest((WindowEvent event1) ->
        {
            System.exit(0);
        });
        jobStage.getIcons().add(new Image("Jinder\\images\\JinderIcon.png"));
    }

    public void start()
    {
        openLoginScreen();
    }

    public void openLoginScreen()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("LoginReg/LoginScreen/LoginScreen.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        LoginScreenView view = loader.getController();
        view.init(viewModelProvider.getLoginScreenVM());
        mainStage.setTitle("Jinder | Login");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

        if (jobStage.isShowing())
        {
            System.out.println("asdasd");
            jobStage.close();
        }
    }

    public void openCompanyMain()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Company/CompanyMain/CompanyMain.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        CompanyMainView view = loader.getController();
        view.init(viewModelProvider.getCompanyMainVM());
        mainStage.setTitle("Jinder | Company Client");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }


    public void openCompanySeekJobRev()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Company/CompanySeek/CompanySeekJobRev.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        CompanySeekJobRevView view = loader.getController();
        view.init(viewModelProvider.getCompanySeekJobRevVM());
        mainStage.setTitle("Jinder | Job Review");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }

    public void openCompanySeek()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Company/CompanySeek/CompanySeek.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        CompanySeekView view = loader.getController();
        view.init(viewModelProvider.getCompanySeekVM());
        mainStage.setTitle("Jinder | Job Review");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openPostJob()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Company/CompanyPost/CompanyPostJob.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        CompanyPostJobView view = loader.getController();
        view.init(viewModelProvider.getCompanyPostJobVM());
        jobStage.setTitle("Jinder | Post a Job");

        Scene scene = new Scene(root);
        jobStage.setScene(scene);
        jobStage.show();
    }


    public void openRegistrateNeutral()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("LoginReg/RegistrateNeutral/RegistrateNeutral.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        RegistrateNeutralView view = loader.getController();
        view.init(viewModelProvider.getRegistrateNeutralVM());
        mainStage.setTitle("Jinder | Registrate");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openCompanyReg()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("LoginReg/CompanyReg/CompanyReg.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        CompanyRegView view = loader.getController();
        view.init(viewModelProvider.getCompanyRegVM());
        mainStage.setTitle("Jinder | Registrate");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openCandidateReg()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("LoginReg/CandidateReg/CandidateReg.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        CandidateRegView view = loader.getController();
        view.init(viewModelProvider.getCandidateRegVM());
        mainStage.setTitle("Jinder | Registrate");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openCandidateMain()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Candidate/CandidateMain/CandidateMain.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        CandidateMainView view = loader.getController();
        view.init(viewModelProvider.getCandidateMainVM());
        mainStage.setTitle("Jinder | Candidate Client");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openCandidateSeek()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Candidate/CandidateSeek/CandidateSeek.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        CandidateSeekView view = loader.getController();
        view.init(viewModelProvider.getCandidateSeekVM());
        mainStage.setTitle("Jinder | Job Search");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
