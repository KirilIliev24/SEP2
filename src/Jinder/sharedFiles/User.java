package Jinder.sharedFiles;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class User implements Serializable
{
    private String username;
    private String password;
    private String type;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private Company company;
    private Candidate candidate;

    public User(String username, String password, String type, String firstName, String lastName, LocalDate dateOfBirth)
    {
        this.username = username;
        this.password = password;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;

        //
        company = null;
        candidate = null;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getType()
    {
        return type;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAdvancedInfo(Company company)
    {
        this.company = new Company(company);
    }

    public void setAdvancedInfo(Candidate candidate)
    {
        this.candidate = new Candidate(candidate);
    }

    public Object getTypeClass()
    {
        if (type.equals("Company"))
        {
            return company;
        } else if (type.equals("Candidate"))
        {
            return candidate;
        } else return null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof User))
            return false;

        User user = (User) o;
        return username.equals(user.username) &&
                password.equals(user.password) &&
                type.equals(user.type) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                dateOfBirth.equals(user.dateOfBirth) &&
                getTypeClass().equals(((User) o).getTypeClass());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(username, password, type, firstName, lastName, dateOfBirth, company, candidate);
    }

    public String toString()
    {
        String str = firstName.toUpperCase() + " " + lastName.toUpperCase() + "\nBorn: " + dateOfBirth.toString();
        if (company != null)
        {
            str += "\n\n" + company.toString();
        } else if (candidate != null)
        {
            str += "\n\n" + candidate.toString();
        } else
        {
            str += " typeNotSet!";
        }
        return str;
    }
}
