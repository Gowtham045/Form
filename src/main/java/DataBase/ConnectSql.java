package DataBase;

import CommonConstants.CommonConstants;
import Contexts.CommonContext;
import Contexts.UserContext;
import org.apache.commons.chain.Context;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectSql {
   private static Connection con;
    private static Connection getConnection() {
       if(con!=null) return con;
        try {
           Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/delta","root","");
            return con;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    public static boolean addUser(Context context) throws SQLException {
        UserContext user=(UserContext)context.get(CommonConstants.USER) ;
        String sql="INSERT INTO `delta`.`User` (`FIRST_NAME`, `LAST_NAME`, `EMAIL`, `CONTACT`, `HASH`, `SALT`) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement prst=getConnection().prepareStatement(sql);
        prst.setString(1, user.getFirstName());
        prst.setString(2, user.getLastName());
        prst.setString(3, user.getEmail());
        prst.setLong(4,user.getContact());
        prst.setString(5,(String)context.get(CommonConstants.HASH));
        prst.setString(6,(String)context.get(CommonConstants.SALT));
        boolean result= prst.execute();
        return  result;
    }
    public static void getUsers(Context context) throws SQLException {
        List<UserContext> Users = new ArrayList<>();
        String sql = "SELECT ID,FIRST_NAME,LAST_NAME,EMAIL,CONTACT FROM User;";
        PreparedStatement prst = getConnection().prepareStatement(sql);
        ResultSet result = prst.executeQuery();
        while (result.next()) {
            UserContext user = new UserContext();
            user.setId(result.getInt(1));
            user.setFirstName(result.getString(2));
            user.setLastName(result.getString(3));
            user.setEmail(result.getString(4));
            user.setContact(result.getLong(5));
            Users.add(user);
        }
        context.put(CommonConstants.USERS_LIST,Users);
    }
    public static void getUserById(Context context) throws SQLException {
        UserContext user = new UserContext();
        String sql = "SELECT ID,FIRST_NAME,LAST_NAME,EMAIL,CONTACT FROM User WHERE ID=?;";
        PreparedStatement prst = getConnection().prepareStatement(sql);
        prst.setInt(1,(Integer)context.get(CommonConstants.ID));
        ResultSet result = prst.executeQuery();
        while (result.next()) {
            user.setId(result.getInt(1));
            user.setFirstName(result.getString(2));
            user.setLastName(result.getString(3));
            user.setEmail(result.getString(4));
            user.setContact(result.getLong(5));
        }
        if(user.getId()==-1){
            context.put(CommonConstants.USER,null);
        }else
          context.put(CommonConstants.USER,user);
    }
    public static void main(String args[]) throws SQLException {
        Context context=new CommonContext();
        UserContext user=new UserContext();
        user.setFirstName("Gow");
        user.setLastName("S");
        user.setContact(90956L);
        user.setPassword("qwertyu");
        context.put(CommonConstants.USER,user);
        context.put(CommonConstants.HASH,"sadfghjk");
        context.put(CommonConstants.SALT,"sdfghj");
        System.out.println(new ConnectSql().addUser(context));
    }
}
