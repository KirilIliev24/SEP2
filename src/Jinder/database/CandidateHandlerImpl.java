package Jinder.database;

import Jinder.sharedFiles.Candidate;
import Jinder.sharedFiles.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class CandidateHandlerImpl implements CandidateHandler
{
    private DBConn dbConn;

    public CandidateHandlerImpl()
    {
        dbConn = DBConn.getInstance();

    }

    @Override
    public void createCandidate(String username, String password, String fname,
                                String lname, Date dateOfBirth, String type, String education,
                                String experience, String skills, String softwareSkills,
                                String licences, String comment)
    {

        if (isNotExisting(username))
        {
            String sql = "insert into \"Jinder\".Candidateaccount (username,password,fname,lname,dateOfBirth,accountType,skills,education," +
                    "expirience,softwareSkills,licences,comment) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement candidateStatement = null;

            try
            {
                candidateStatement = dbConn.getPreparedStatement(sql);
                candidateStatement.setString(1, username);
                candidateStatement.setString(2, password);
                candidateStatement.setString(3, fname);
                candidateStatement.setString(4, lname);
                candidateStatement.setDate(5, dateOfBirth);
                candidateStatement.setString(6, type);
                candidateStatement.setString(7, skills);
                candidateStatement.setString(8, education);
                candidateStatement.setString(9, experience);
                candidateStatement.setString(10, softwareSkills);
                candidateStatement.setString(11, licences);
                candidateStatement.setString(12, comment);

                candidateStatement.execute();


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
    public User getCandidateByAccount(String username, String password)
    {
        String sql = "select*from \"Jinder\".Candidateaccount  where (username = '" + username + "' and password = '" + password + "' );";
        ArrayList<Object[]> result = getData(sql);
        User user = null;
        try
        {
            PreparedStatement getCandidateStatement = dbConn.getPreparedStatement(sql);
            ResultSet resultSet = getCandidateStatement.executeQuery();
            if ((!result.isEmpty()))
            {
                Object[] row = result.get(0);
                Date date = (Date) row[4];
                user = new User((String) row[0], (String) row[1], (String) row[5], (String) row[2], (String) row[3], date.toLocalDate());
                user.setAdvancedInfo(new Candidate((String) row[7], (String) row[8], (String) row[6], (String) row[9], (String) row[10], (String) row[11]));
            }
            getCandidateStatement.close();
            resultSet.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean isNotExisting(String username)
    {
        String sql = "select*from \"Jinder\".Candidateaccount  where username = '" + username + "';";
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


}