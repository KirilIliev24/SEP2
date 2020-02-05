package Jinder.client;

import Jinder.sharedFiles.User;

public interface LoginClientInt
{
    User typeOfAccount(String email, String pass);

    void newCandidateAcc(User newUser);

    void newCompanyAcc(User newUser);

    boolean isNotExisting(String username);
}
