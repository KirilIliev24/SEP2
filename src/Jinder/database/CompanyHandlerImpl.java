package Jinder.database;

import Jinder.sharedFiles.Company;
import Jinder.sharedFiles.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyHandlerImpl implements CompanyHandler
{

    private DBConn dbConn;

    public CompanyHandlerImpl()
    {
        dbConn = DBConn.getInstance();
    }

    private ArrayList<Object[]> getData(String sql)
    {
        ResultSet rs = null;
        ArrayList<Object[]> result = null;
        try
        {
            rs = dbConn.executeSelectQuery(sql);
            result = new ArrayList<>();
            while (rs.next())
            {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++)
                {
                    row[i] = rs.getObject(i + 1);
                }
                result.add(row);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isNotExisting(String username)
    {
        String sql = "select*from \"Jinder\".Employeraccount  where username = '" + username + "';";
        ArrayList<Object[]> result = getData(sql);
        PreparedStatement checkIfExistsStatement = null;
        try
        {
            checkIfExistsStatement = dbConn.getPreparedStatement(sql);
            ResultSet resultSet = checkIfExistsStatement.executeQuery();
            checkIfExistsStatement.close();
            resultSet.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        if (result.isEmpty())
        {
            return true;
        } else
            return false;


    }


    @Override
    public void createCompany(String username, String password, String fname, String lname,
                              Date dateOfBirth, String accountType, String companyName, String companyDesc)
    {
        String sql = "insert into \"Jinder\".Employeraccount (username,password,fname,lname,dateOfBirth,accountType,companyName,companyDescription) values (?,?,?,?,?,?,?,?)";
        PreparedStatement employerStatement = null;
        if (isNotExisting(username))
        {
            try
            {
                employerStatement = dbConn.getPreparedStatement(sql);
                employerStatement.setString(1, username);
                employerStatement.setString(2, password);
                employerStatement.setString(3, fname);
                employerStatement.setString(4, lname);
                employerStatement.setDate(5, dateOfBirth);
                employerStatement.setString(6, accountType);
                employerStatement.setString(7, companyName);
                employerStatement.setString(8, companyDesc);
                employerStatement.execute();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        } else
        {
            System.out.println("User already exist!");
        }


    }

    @Override
    public User getCompanyInfo(String username)
    {
        String sql = "select*from \"Jinder\".Employeraccount  where username = '" + username + "';";
        ArrayList<Object[]> result = getData(sql);
        ResultSet rs = null;
        User user = null;
        if (!(result.isEmpty()))
        {
            Object[] row = result.get(0);
            Date date = (Date) row[4];
            user = new User((String) row[0], (String) row[1], (String) row[5], (String) row[2], (String) row[3], date.toLocalDate());
            user.setAdvancedInfo(new Company((String) row[6], (String) row[7]));
        }
        return user;
    }

    @Override
    public User getCompanyInfo(String userName, String password)
    {
        String sql = "select*from \"Jinder\".Employeraccount  where (username = '" + userName + "' and password = '" + password + "' );";
        ArrayList<Object[]> result = getData(sql);
        ResultSet rs = null;
        User user = null;
        if (!(result.isEmpty()))
        {
            Object[] row = result.get(0);
            Date date = (Date) row[4];
            user = new User((String) row[0], (String) row[1], (String) row[5], (String) row[2], (String) row[3], date.toLocalDate());
            user.setAdvancedInfo(new Company((String) row[6], (String) row[7]));
        }
        return user;
    }

}
