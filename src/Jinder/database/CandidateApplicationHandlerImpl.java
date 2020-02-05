package Jinder.database;

import Jinder.sharedFiles.Candidate;
import Jinder.sharedFiles.Company;
import Jinder.sharedFiles.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CandidateApplicationHandlerImpl implements CandidateApplicationHandler
{

    private DBConn dbConn;

    public CandidateApplicationHandlerImpl()
    {
        dbConn = DBConn.getInstance();
    }

    @Override
    public void createApplication(int jobId, String username, String status)
    {
        if (isNotExistingOnJobId(username, jobId))
        {
            String sql = "insert into \"Jinder\".CandidateApplication(username,jobid,status) values (?,?,?)";
            PreparedStatement statement = null;
            try
            {
                statement = dbConn.getPreparedStatement(sql);
                statement.setString(1, username);
                statement.setInt(2, jobId);
                statement.setString(3, status);
                statement.execute();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        } else
        {
            System.out.println("Application already exist! You can only apply once per offer!");
        }
    }

    @Override
    public ArrayList<User> getWaitingCandidates(int jobId)
    {
        String sql = "select*from \"Jinder\".CandidateAccount where username in (select username from \"Jinder\".Candidateapplication where (jobid='" + jobId + "' and status ='WAITING'))";
        ArrayList<Object[]> result = getData(sql);
        ArrayList<User> users;
        if (result.isEmpty())
        {
            return null;
        } else
        {
            User user = null;
            users = new ArrayList<User>();
            for (int i = 0; i < result.size(); i++)
            {
                Object[] row = result.get(0);
                Date date = (Date) row[4];
                user = new User((String) row[0], (String) row[1], (String) row[5], (String) row[2], (String) row[3], date.toLocalDate());
                user.setAdvancedInfo(new Candidate((String) row[7], (String) row[8], (String) row[6], (String) row[9], (String) row[10], (String) row[11]));
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public ArrayList<User> getAcceptedCandidates(int jobId)
    {
        String sql = "select*from \"Jinder\".CandidateAccount where username in (select username from \"Jinder\".Candidateapplication where (jobid='" + jobId + "' and status ='Accepted'))";
        ArrayList<Object[]> result = getData(sql);
        ArrayList<User> users;
        if (result.isEmpty())
        {
            return null;
        } else
        {
            User user = null;
            users = new ArrayList<User>();
            for (int i = 0; i < result.size(); i++)
            {
                Object[] row = result.get(i);
                Date date = (Date) row[4];
                user = new User((String) row[0], (String) row[1], (String) row[5], (String) row[2], (String) row[3], date.toLocalDate());
                user.setAdvancedInfo(new Candidate((String) row[7], (String) row[8], (String) row[6], (String) row[9], (String) row[10], (String) row[11]));
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public ArrayList<User> getEmployersForAcceptedCandidate(User user)
    {
        String sql = "select*from \"Jinder\".EmployerAccount where username in (select username from \"Jinder\".Job where jobid in (select jobid from \"Jinder\".candidateapplication where (username='" + user.getUsername() + "' and status ='Accepted')))";
        ArrayList<Object[]> result = getData(sql);
        ArrayList<User> users = new ArrayList<User>();
        ;
        if (result.isEmpty())
        {
            return users;
        } else
        {
            User tempUser = null;
            for (int i = 0; i < result.size(); i++)
            {
                Object[] row = result.get(i);
                Date date = (Date) row[4];
                tempUser = new User((String) row[0], (String) row[1], (String) row[5], (String) row[2], (String) row[3], date.toLocalDate());
                tempUser.setAdvancedInfo(new Company((String) row[6], (String) row[7]));
                users.add(tempUser);
            }
        }
        return users;
    }

    @Override
    public void changeCandidateState(int jobId, String username, String status)
    {
        String sql = "update \"Jinder\".CandidateApplication set status = '" + status + "' where (username='" + username + "' and jobId='" + jobId + "')";
        PreparedStatement statement = null;

        try
        {
            statement = dbConn.getPreparedStatement(sql);
            statement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isNotExistingOnJobId(String username, int jobId)
    {
        String sql = "select*from \"Jinder\".CandidateApplication  where (username = '" + username + "' and jobid = '" + jobId + "');";
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
