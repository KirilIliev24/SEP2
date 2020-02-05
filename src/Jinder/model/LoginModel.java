package Jinder.model;

import Jinder.sharedFiles.User;
import java.time.LocalDate;

public interface LoginModel
{
    String getAccountType(String email, String pass);

    void regNewUser(String username, String password, String type, String firstName, String lastName, LocalDate dateOfBirth);

    void regTypeCompany(String companyName, String companyDesc);

    void regTypeCandidate(String education, String experience, String skills, String softwareS, String licenses, String comments);

    User getUser();

    void setUserNull();

    boolean checkExistance(String username);
}
