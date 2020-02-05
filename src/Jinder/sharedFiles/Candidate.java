package Jinder.sharedFiles;

import java.io.Serializable;

public class Candidate implements Serializable
{
    private String education;
    private String experience;
    private String skills;
    private String softwareS;
    private String licenses;
    private String comments;

    public Candidate(String education, String experience, String skills, String softwareS, String licences, String comments)
    {
        this.education = education;
        this.experience = experience;
        this.skills = skills;
        this.softwareS = softwareS;
        this.licenses = licences;
        this.comments = comments;
    }

    public Candidate(Candidate candidate)
    {
        education = candidate.education;
        experience = candidate.experience;
        skills = candidate.skills;
        softwareS = candidate.softwareS;
        licenses = candidate.licenses;
        comments = candidate.comments;
    }

    public String getEducation()
    {
        return education;
    }

    public void setEducation(String education)
    {
        this.education = education;
    }

    public String getExperience()
    {
        return experience;
    }

    public void setExperience(String experience)
    {
        this.experience = experience;
    }

    public String getSkills()
    {
        return skills;
    }

    public void setSkills(String skills)
    {
        this.skills = skills;
    }

    public String getSoftwareS()
    {
        return softwareS;
    }

    public void setSoftwareS(String softwareS)
    {
        this.softwareS = softwareS;
    }

    public String getLicenses()
    {
        return licenses;
    }

    public void setLicenses(String licenses)
    {
        this.licenses = licenses;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public Candidate getCopy()
    {
        return new Candidate(education, experience, skills, softwareS, licenses, comments);
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Candidate))
            return false;

        Candidate other = (Candidate) o;
        return education.equals(other.education) &&
                experience.equals(other.experience) &&
                skills.equals(other.skills);
    }

    @Override
    public String toString()
    {
        return "Education: " + education + "\n" +
                "Experiences: " + experience + "\n" +
                "Skills: " + skills + "\n" +
                "Software skills: " + softwareS + "\n" +
                "Licenses: " + licenses + "\n" +
                "Additional comments: " + comments;

    }
}
