package Jinder.ViewModel;

import Jinder.ViewModel.Candidate.CandidateMain.CandidateMainVM;
import Jinder.ViewModel.LoginReg.CandidateReg.CandidateRegVM;
import Jinder.ViewModel.Candidate.CandidateSeek.CandidateSeekVM;
import Jinder.ViewModel.Company.CompanyMain.CompanyMainVM;
import Jinder.ViewModel.Company.CompanyPost.CompanyPostJobVM;
import Jinder.ViewModel.LoginReg.CompanyReg.CompanyRegVM;
import Jinder.ViewModel.Company.CompanySeek.CompanySeekJobRevVM;
import Jinder.ViewModel.Company.CompanySeek.CompanySeekVM;
import Jinder.ViewModel.LoginReg.LoginScreen.LoginScreenVM;
import Jinder.ViewModel.LoginReg.RegistrateNeutral.RegistrateNeutralVM;
import Jinder.model.*;
import Jinder.view.ViewHandler;

public class ViewModelProvider
{

    private CompanyMainVM companyMainVM;
    private LoginScreenVM loginScreenVM;
    private CompanySeekJobRevVM companySeekJobRevVM;
    private CandidateMainVM candidateMainVM;
    private CandidateRegVM candidateRegVM;
    private CandidateSeekVM candidateSeekVM;
    private CompanyPostJobVM companyPostJobVM;
    private CompanyRegVM companyRegVM;
    private CompanySeekVM companySeekVM;
    private RegistrateNeutralVM registrateNeutralVM;
    private CompanyModel companyModel;
    private CandidateModel candidateModel;

    public ViewModelProvider(LoginModel loginModel, CandidateModel candidateM, CompanyModel employerM, ViewHandler vh)
    {
        companyModel = employerM;
        candidateModel = candidateM;

        companyMainVM = new CompanyMainVM(companyModel, vh);
        companySeekJobRevVM = new CompanySeekJobRevVM(companyModel, vh);
        companyPostJobVM = new CompanyPostJobVM(companyModel, vh);
        companySeekVM = new CompanySeekVM(companyModel, vh);

        loginScreenVM = new LoginScreenVM(loginModel, vh);
        companyRegVM = new CompanyRegVM(loginModel, vh);
        candidateRegVM = new CandidateRegVM(loginModel, vh);
        registrateNeutralVM = new RegistrateNeutralVM(loginModel, vh);

        candidateMainVM = new CandidateMainVM(candidateModel, vh);
        candidateSeekVM = new CandidateSeekVM(candidateModel, vh);


    }

    public LoginScreenVM getLoginScreenVM()
    {
        return loginScreenVM;
    }

    public CompanyMainVM getCompanyMainVM()
    {
        return companyMainVM;
    }

    public CompanySeekJobRevVM getCompanySeekJobRevVM()
    {
        return companySeekJobRevVM;
    }

    public CandidateMainVM getCandidateMainVM()
    {
        return candidateMainVM;
    }

    public CandidateRegVM getCandidateRegVM()
    {
        return candidateRegVM;
    }

    public CandidateSeekVM getCandidateSeekVM()
    {
        return candidateSeekVM;
    }

    public CompanyPostJobVM getCompanyPostJobVM()
    {
        return companyPostJobVM;
    }

    public CompanyRegVM getCompanyRegVM()
    {
        return companyRegVM;
    }

    public CompanySeekVM getCompanySeekVM()
    {
        return companySeekVM;
    }

    public RegistrateNeutralVM getRegistrateNeutralVM()
    {
        return registrateNeutralVM;
    }
}
