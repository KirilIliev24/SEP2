package Jinder.ViewModel.Company.CompanySeek;

import Jinder.model.CompanyModel;
import Jinder.sharedFiles.User;
import Jinder.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CompanySeekVM
{
    private ViewHandler viewHandler;
    private CompanyModel companyModel;
    private StringProperty onCandCVId;

    public CompanySeekVM(CompanyModel companyModel, ViewHandler vh)
    {
        this.companyModel = companyModel;
        this.viewHandler = vh;
        onCandCVId = new SimpleStringProperty();
    }


    public void showUserInfo()
    {
        User user = companyModel.getUserToReview();
        if (user != null)
        {
            onCandCVId.setValue(user.toString());
        } else onCandCVId.setValue("\n\n          No more users has applied to this job so far");
    }

    public void response(String response)
    {
        companyModel.respondToUserApplication(response);
    }

    public void openCompanyMain()
    {
        viewHandler.openCompanyMain();
    }

    public StringProperty onCandCVId()
    {
        return onCandCVId;
    }

    public void openCompanyRev()
    {
        viewHandler.openCompanySeekJobRev();
    }
}
