package Jinder.database;

import Jinder.sharedFiles.User;

import java.sql.Date;

public interface CandidateHandler
{
    void createCandidate(String username, String password, String fname, String lname, Date dateOfBirth, String type, String education,
                         String experience, String skills, String softwareSkills,
                         String licenses, String comments);

    User getCandidateByAccount(String username, String password);

    boolean isNotExisting(String username);

}
