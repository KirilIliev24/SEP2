package Jinder.database;

import Jinder.sharedFiles.User;

import java.util.ArrayList;

public interface CandidateApplicationHandler
{
    void createApplication(int jobId, String username, String status);

    ArrayList<User> getWaitingCandidates(int jobId);

    ArrayList<User> getAcceptedCandidates(int jobId);

    ArrayList<User> getEmployersForAcceptedCandidate(User user);

    void changeCandidateState(int jobId, String username, String status);

    boolean isNotExistingOnJobId(String username, int jobId);
}
