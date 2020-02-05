package Jinder.database;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBConn
{
    private static Connection con = null;
    private static DBConn instance;
    private Statement statement;
    private static Lock lock = new ReentrantLock();

    private DBConn()
    {
        if (con == null)
        {
            ConfigureConnection();
        }
    }

    private void ConfigureConnection()
    {
        try
        {
            Class.forName("org.postgresql.Driver");

            String dbUser = "postgres";
            String dbPass = "1234";
            String SCHEMA = "JINDER";

            con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", dbUser, dbPass);
            Statement stmnt = con.createStatement();
            stmnt.execute("SET SEARCH_PATH TO " + SCHEMA);
            stmnt.close();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static DBConn getInstance()
    {
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new DBConn();
                }
            }
        }
        return instance;
    }

    public ResultSet executeSelectQuery(String sql) throws SQLException
    {
        if (con == null) return null;
        Statement statement = con.createStatement();
        return statement.executeQuery(sql);
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException
    {
        return con.prepareStatement(sql);
    }
}
