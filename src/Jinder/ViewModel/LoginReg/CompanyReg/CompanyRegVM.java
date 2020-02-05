package Jinder.ViewModel.LoginReg.CompanyReg;

import Jinder.model.LoginModel;
import Jinder.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CompanyRegVM
{
    private ViewHandler viewHandler;
    private LoginModel loginModel;
    private StringProperty onCompanyNameId;
    private StringProperty onCompanyDescriptionId;

    public CompanyRegVM(LoginModel loginModel, ViewHandler vh)
    {
        this.loginModel = loginModel;
        this.viewHandler = vh;

        onCompanyNameId = new SimpleStringProperty();
        onCompanyDescriptionId = new SimpleStringProperty();
    }

    public void openRegistrateNeutral()
    {
        viewHandler.openRegistrateNeutral();
    }

    public void openCompanyMain()
    {
        loginModel.regTypeCompany(onCompanyNameId.getValue(), onCompanyDescriptionId.getValue());
        viewHandler.openCompanyMain();
    }

    public StringProperty onCompanyNameId()
    {
        return onCompanyNameId;
    }

    public StringProperty onCompanyDescriptionId()
    {
        return onCompanyDescriptionId;
    }

    public void setFields()
    {
        onCompanyNameId.setValue(null);
        onCompanyDescriptionId.setValue(null);
    }
}
