package Jinder;

import Jinder.model.*;
import Jinder.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        CandidateModel candidateModel = new DataCandidateModel();
        CompanyModel companyModel = new DataCompanyModel();
        LoginModel loginModel = new DataLoginModel(candidateModel, companyModel);
        ViewHandler vh = new ViewHandler(stage, loginModel, candidateModel, companyModel);
        vh.start();
    }
}
