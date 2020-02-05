package Jinder.sharedFiles;

import java.io.Serializable;
import java.util.Objects;

public class Company implements Serializable
{
    private String companyName;
    private String companyDesc;

    public Company(String companyName, String companyDesc)
    {
        this.companyName = companyName;
        this.companyDesc = companyDesc;
    }

    public Company(Company company)
    {
        companyName = company.companyName;
        companyDesc = company.companyDesc;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyDesc()
    {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc)
    {
        this.companyDesc = companyDesc;
    }

    public Company getCopy()
    {
        return new Company(companyName, companyDesc);
    }

    @Override
    public String toString()
    {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", companyDesc='" + companyDesc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Company))
            return false;

        Company other = (Company) o;
        return companyName.equals(other.companyName) &&
                companyDesc.equals(other.companyDesc);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(companyName, companyDesc);
    }
}
