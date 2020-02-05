package Jinder.model;

import Jinder.client.LoginClient;
import Jinder.client.LoginClientInt;
import Jinder.sharedFiles.Candidate;
import Jinder.sharedFiles.Company;
import Jinder.sharedFiles.User;
import java.time.LocalDate;

public class DataLoginModel implements LoginModel
{
    private LoginClientInt loginClientInt;
    private CandidateModel candidateModel;
    private CompanyModel companyModel;
    private User newUser;

    public DataLoginModel(CandidateModel candidateM, CompanyModel employerM)
    {
        this.candidateModel = candidateM;
        this.companyModel = employerM;
        loginClientInt = new LoginClient();
    }

    @Override
    public String getAccountType(String username, String pass)
    {
        User accInfo = loginClientInt.typeOfAccount(username, pass);
        if (accInfo == null)
        {
            return "null";
        } else if (accInfo.getType().equals("Company"))
        {
            companyModel.initialize(accInfo);
            return accInfo.getType();
        } else if (accInfo.getType().equals("Candidate"))
        {
            candidateModel.initialize(accInfo);
            return accInfo.getType();
        } else
        {
            return "error";
        }
    }

    @Override
    public void regNewUser(String username, String password, String type, String firstName, String lastName, LocalDate dateOfBirth)
    {
        newUser = new User(username, password, type, firstName, lastName, dateOfBirth);
    }

    @Override
    public void regTypeCompany(String companyName, String companyDesc)
    {
        newUser.setAdvancedInfo(new Company(companyName, companyDesc));
        loginClientInt.newCompanyAcc(newUser);
        User accInfo = loginClientInt.typeOfAccount(newUser.getUsername(), newUser.getPassword());
        companyModel.initialize(accInfo);
        setUserNull();
    }

    @Override
    public void regTypeCandidate(String education, String experience, String skills, String softwareS, String licenses, String comments)
    {
        newUser.setAdvancedInfo(new Candidate(education, experience, skills, softwareS, licenses, comments));
        loginClientInt.newCandidateAcc(newUser);
        User accInfo = loginClientInt.typeOfAccount(newUser.getUsername(), newUser.getPassword());
        candidateModel.initialize(accInfo);
        setUserNull();
    }

    @Override
    public User getUser()
    {
        return newUser;
    }

    @Override
    public void setUserNull()
    {
        newUser = null;
    }

    @Override
    public boolean checkExistance(String username)
    {
        if (loginClientInt.isNotExisting(username))
        {
            return true;

        } else
        {
            return false;
        }
    }
}
