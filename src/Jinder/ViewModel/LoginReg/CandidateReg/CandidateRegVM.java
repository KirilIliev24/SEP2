package Jinder.ViewModel.LoginReg.CandidateReg;

import Jinder.model.LoginModel;
import Jinder.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CandidateRegVM
{
    private ViewHandler viewHandler;
    private LoginModel loginModel;
    private StringProperty onEducationId, onExperiencesId, onSkillsId, onSoftSkillsId, onLicensesId, onCommentsId;

    public CandidateRegVM(LoginModel loginModel, ViewHandler vh)
    {
        this.loginModel = loginModel;
        this.viewHandler = vh;

        onEducationId = new SimpleStringProperty();
        onExperiencesId = new SimpleStringProperty();
        onSkillsId = new SimpleStringProperty();
        onSoftSkillsId = new SimpleStringProperty();
        onLicensesId = new SimpleStringProperty();
        onCommentsId = new SimpleStringProperty();
    }

    public void openRegistrateNeutral()
    {
        viewHandler.openRegistrateNeutral();
    }

    public void openCandidateMain()
    {
        if (onSoftSkillsId.getValue() == null) onSoftSkillsId.setValue("NO_INFO");
        if (onLicensesId.getValue() == null) onLicensesId.setValue("NO_INFO");
        if (onCommentsId.getValue() == null) onCommentsId.setValue("NO_INFO");
        loginModel.regTypeCandidate(onEducationId.getValue(), onExperiencesId.getValue(), onSkillsId.getValue(), onSoftSkillsId.getValue(), onLicensesId.getValue(), onCommentsId.getValue());
        viewHandler.openCandidateMain();
    }

    public StringProperty onEducationId()
    {
        return onEducationId;
    }

    public StringProperty onExperiencesId()
    {
        return onExperiencesId;
    }

    public StringProperty onSkillsId()
    {
        return onSkillsId;
    }

    public StringProperty onSoftSkillsId()
    {
        return onSoftSkillsId;
    }

    public StringProperty onLicensesId()
    {
        return onLicensesId;
    }

    public StringProperty onCommentsId()
    {
        return onCommentsId;
    }

    public void setFields()
    {
        onEducationId.setValue(null);
        onExperiencesId.setValue(null);
        onSkillsId.setValue(null);
        onSoftSkillsId.setValue(null);
        onLicensesId.setValue(null);
        onCommentsId.setValue(null);


    }
}
