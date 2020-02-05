package Jinder.database;

import Jinder.sharedFiles.Company;
import Jinder.sharedFiles.Job;
import Jinder.sharedFiles.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobHandlerImpl implements JobHandler
{
    private DBConn dbConn;

    public JobHandlerImpl()
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

    @Override
    public void postJob(String username, String jobName, String requirements, String description, String category)
    {
        String sql = "insert into \"Jinder\".Job (username,jobName,requirements,description,category) values (?,?,?,?,?)";
        PreparedStatement jobStatement = null;

        try
        {
            jobStatement = dbConn.getPreparedStatement(sql);
            jobStatement.setString(1, username);
            jobStatement.setString(2, jobName);
            jobStatement.setString(3, requirements);
            jobStatement.setString(4, description);
            jobStatement.setString(5, category);
            jobStatement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Job> getJob(String username)
    {
        String sql = "select*from \"Jinder\".Job  where (username = '" + username + "');";
        User userTemp = getEmployerInfo(username);
        ArrayList<Object[]> result = getData(sql);
        ArrayList<Job> jobs = new ArrayList<>();
        Job job = null;
        if (!(result.isEmpty()))
        {
            for (int i = 0; i < result.size(); i++)
            {

                Object[] row = result.get(i);
                job = new Job(userTemp, (String) row[2], (String) row[3], (String) row[4], (String) row[5]);
                jobs.add(job);
            }
        }
        return jobs;
    }

    @Override
    public int getId(String username, String jobName, String requirements, String description, String category)
    {
        String sql = "select jobId from \"Jinder\".Job  where (username = '" + username + "' and " + "jobName = '" + jobName + "' and "
                + " requirements = '" + requirements + "' and " + " description = '" + description + "' and " + " category = '" + category + "');";
        ArrayList<Object[]> result = getData(sql);
        int id = 0;
        if ( result == null || !(result.isEmpty()))
        {
            Object[] row = result.get(0);
            id = (int) row[0];
        }
        return id;
    }

    @Override
    public ArrayList<Job> getJobByCategory(String category)
    {
        String sql = "select*from \"Jinder\".Job  where (category = '" + category + "');";
        ArrayList<Object[]> result = getData(sql);
        ArrayList<Job> jobs = new ArrayList<>();
        Job job = null;
        if (!(result.isEmpty()))
        {
            for (int i = 0; i < result.size(); i++)
            {
                Object[] row = result.get(i);
                User userTemp = getEmployerInfo((String) row[1]);
                job = new Job(userTemp, (String) row[2], (String) row[3], (String) row[4], (String) row[5]);
                jobs.add(job);
            }
        }
        return jobs;
    }

    @Override
    public String getPoster(int id)
    {
        String sql = "select username from \"Jinder\".Job  where (jobId = '" + id + "');";
        ArrayList<Object[]> result = getData(sql);
        String username = "";
        if (!(result.isEmpty()))
        {
            Object[] row = result.get(0);
            username = (String) row[0];
        }
        return username;
    }

    private User getEmployerInfo(String username)
    {
        String sql = "select*from \"Jinder\".Employeraccount  where username = '" + username + "';";
        ArrayList<Object[]> result = getData(sql);
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
