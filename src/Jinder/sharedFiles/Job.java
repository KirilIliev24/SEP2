package Jinder.sharedFiles;

import java.io.Serializable;

public class Job implements Serializable
{
    private User postedBy;
    private String jobName;
    private String requirements;
    private String description;
    private String category;

    public Job(User postedBy, String jobName, String requirements, String description, String category)
    {
        this.postedBy = postedBy;
        this.jobName = jobName;
        this.requirements = requirements;
        this.description = description;
        this.category = category;
    }

    public String getUsername()
    {
        return postedBy.getUsername();
    }

    public String getEmpName()
    {
        return postedBy.getFirstName() + " " + postedBy.getLastName();
    }

    public String getCompName()
    {
        Company comp = (Company) postedBy.getTypeClass();
        return comp.getCompanyName();
    }

    public void setPostedBy(User postedBy)
    {
        this.postedBy = postedBy;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getRequirements()
    {
        return requirements;
    }

    public void setRequirements(String requirements)
    {
        this.requirements = requirements;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        Company company = (Company) postedBy.getTypeClass();
        return company.getCompanyName().toUpperCase() + "\n\n" +
                "Company Description:\n" + company.getCompanyDesc() + "\n\n" +
                "Position: " + jobName + "\n" +
                "Requirements:\n" + requirements + "\n" +
                "Description of Position:\n" + description + "\n";
    }
}
