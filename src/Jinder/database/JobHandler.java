package Jinder.database;

import Jinder.sharedFiles.Job;

import java.util.ArrayList;

public interface JobHandler
{
    void postJob(String username, String jobName, String requirements, String description, String category);

    ArrayList<Job> getJob(String username);

    int getId(String username, String jobName, String requirements, String description, String category);

    ArrayList<Job> getJobByCategory(String category);

    String getPoster(int id);
}
