package Jinder.database;

import Jinder.sharedFiles.User;
import java.sql.Date;

public interface CompanyHandler
{
    void createCompany(String username, String password, String fname, String lname, Date dateOfBirth, String acountType, String companyName, String companyDesc);

    boolean isNotExisting(String username);

    User getCompanyInfo(String userName, String password);

    User getCompanyInfo(String username);
}
