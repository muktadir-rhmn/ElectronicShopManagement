/**
 * @author MUKTADIR
 */

package Models;

import Database.DataAccess;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class User {
    private String type = "EM";
    private String name = null;
    private String father = null;
    private String mobile = null;
    private String password = null;
    private String active;

    public String getName() {
        return name;
    }

    public String getFather() {
        return father;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public String getSalary() {
        return salary;
    }

    private String address = null;
    private String joinDate = null;
    private String salary;

    public String getAddress() {
        return address;
    }

    public static User getUser(String mobile){
        User user = null;
        
        String sql = "SELECT type, name, father, mobile, password, address, TO_CHAR(JOIN_DATE, 'DD MON YYYY') joinDate, NVL(salary, 0) sal"
                + " FROM Users WHERE mobile = ?";

        try {
            DataAccess da = DataAccess.getDataAccess();
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, mobile);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                user = new User();
                user.type = rs.getString("type");
                System.out.println(user.name = rs.getString("name"));
                user.father = rs.getString("father");
                user.mobile = rs.getString("mobile");
                user.password = rs.getString("password");
                user.address = rs.getString("address");
                user.joinDate = rs.getString("joinDate");
                user.salary =  rs.getString("sal");;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName())
                    .log(Level.SEVERE,
                    null,
                    ex);
        }
        return user;
    }
    
    /**
     * I have overloaded this method for 2 reasons:
     * 1. If the user has given a wrong password, we do not need to fetch data 
     *    from the database.
     * 2. For login we do not need father...etc. So they need not to be fetched 
     *      from database. We only need other attributes only when the user 
     *      want to update his account, or admin wants to see them.
     * 
     */
    public static User getUser(String mobile, String password){
        User user = null;
        
        String sql ="SELECT type, name, mobile, active"
               + " FROM Users WHERE mobile = ? AND password = ?";
        
        try {
            
            DataAccess da = DataAccess.getDataAccess();
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, mobile);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                user = new User();
                user.type = rs.getString("type");
                user.name = rs.getString("name");
                user.mobile = rs.getString("mobile");
                user.active = rs.getString("active");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    public static ArrayList<User> getAllUsers(){
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT TYPE, NAME, FATHER, MOBILE, ADDRESS, SALARY, PASSWORD, TO_CHAR(JOIN_DATE, 'DD Month YYYY') JOIN_DATE, ACTIVE " +
                "FROM USERS";
        try {
            DataAccess da = DataAccess.getDataAccess();
            PreparedStatement stmt = da.getStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                User user = new User();
                user.type = rs.getString("TYPE");
                System.out.println(user.name = rs.getString("NAME"));
                user.father = rs.getString("FATHER");
                user.mobile = rs.getString("MOBILE");
                user.password = rs.getString("PASSWORD");
                user.address = rs.getString("ADDRESS");
                user.joinDate = rs.getString("JOIN_DATE");
                user.salary =  rs.getString("SALARY");;
                user.active = rs.getString("ACTIVE");
                list.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName())
                    .log(Level.SEVERE,
                            null,
                            ex);
        }
        return list;
    }

    public static int removeAccess(String userId){
        String sql = "UPDATE USERS\n" +
                "SET ACTIVE='N'\n" +
                "WHERE MOBILE = ?";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, userId);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public boolean isAdmin(){
        return type.equals("AD");
    }
    public boolean isActive(){return active.equals("Y");}
    
    /**
     *
     * @return 
     *      1 if inserted
     *      0 if not inserted
     */
    public static int insert(String type, String name, String father, String mobile, String password, String address, String salary){
        String sql =
                    "INSERT INTO USERS(TYPE, NAME, FATHER, MOBILE, ADDRESS, PASSWORD, SALARY)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            DataAccess da = DataAccess.getDataAccess();
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, type);
            stmt.setString(2, name);
            stmt.setString(3, father);
            stmt.setString(4, mobile);
            stmt.setString(5, address);
            stmt.setString(6, password);
            stmt.setString(7, salary);  //string will automatically converted to number
            return stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public int update(String password,String address, String salary) {
        String sql = "UPDATE USERS SET PASSWORD = ?, ADDRESS = ?, SALARY = ? WHERE MOBILE = ?";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, address);
            stmt.setString(3, salary);
            stmt.setString(4, this.mobile);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
}
