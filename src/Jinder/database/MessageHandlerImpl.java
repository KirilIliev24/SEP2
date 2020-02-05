package Jinder.database;

import Jinder.sharedFiles.Message;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MessageHandlerImpl implements MessageHandler
{
    private DBConn dbConn;

    public MessageHandlerImpl()
    {
        dbConn = DBConn.getInstance();
    }

    @Override
    public void createMessage(String sender, String reciever, String message)
    {
        inputTimeMessage(sender, reciever);
        putMessage(sender, reciever, message);
    }

    @Override
    public ArrayList<Message> getMessages(String sender, String reciever)
    {
        String sql = "select*from \"Jinder\".Message where (sender = '" + sender + "' and reciever = '" + reciever + "' ) or (sender = '" + reciever + "' and reciever = '" + sender + "' );";
        ArrayList<Object[]> result = getData(sql);
        Message message = null;
        ArrayList<Message> messages = new ArrayList<>();


        if (!result.isEmpty())
        {
            for (int i = 0; i < result.size(); i++)
            {
                Object[] row = result.get(i);
                message = new Message((String) row[0], (String) row[1], (String) row[2], (Timestamp) row[3]);
                messages.add(message);
            }
        }
        return messages;
    }

    private void inputTimeMessage(String sender, String receiver)
    {
        ArrayList<Message> arrayList = getMessages(sender, receiver);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String frmToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestamp);
        String frmToHours = new SimpleDateFormat("HH:mm").format(timestamp);
        if (arrayList == null || arrayList.isEmpty())
        {
            putMessage(sender, receiver, "                                                             " + frmToDate);
        } else if ((timestamp.getTime() - arrayList.get(arrayList.size() - 1).getTimestamp().getTime()) > 60000)
        {
            putMessage(sender, receiver, "\n                                                                      " + frmToHours);
        }
    }

    private void putMessage(String sender, String reciever, String message)
    {
        String sql = "insert into \"Jinder\".message(sender,reciever,message,mtimestamp) values (?,?,?,?)";
        PreparedStatement statement = null;
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        try
        {
            statement = dbConn.getPreparedStatement(sql);
            statement.setString(1, sender);
            statement.setString(2, reciever);
            statement.setString(3, message);
            statement.setTimestamp(4, ts);
            statement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
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
