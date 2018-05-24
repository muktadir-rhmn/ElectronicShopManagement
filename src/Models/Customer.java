package Models;

import Database.DataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MUKTADIR
 */
public class Customer {
    private String name = null;
    private String father = null;
    private String mobile = null;
    private String address = null;
    
    public static int insert(String name, String father, String mobile, String address) {
        String sql = "INSERT INTO CUSTOMERS(NAME, FATHER, MOBILE, ADDRESS)" +
                "VALUES(?, ?, ?, ?)";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, father);
            stmt.setString(3, mobile);
            stmt.setString(4, address);
            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static ArrayList<Customer> getCustomers(){
        ArrayList<Customer> list = new ArrayList<>();

        String sql =
                "SELECT NAME, FATHER, MOBILE, ADDRESS " +
                        "FROM CUSTOMERS";
        DataAccess da = DataAccess.getDataAccess();

        try {
            PreparedStatement stmt  = da.getStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Customer t = new Customer();
                t.name = rs.getString("NAME");
                t.father = rs.getString("FATHER");
                t.mobile = rs.getString("MOBILE");
                t.address = rs.getString("ADDRESS");

                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public String getName() {
        return name;
    }

    public String getFather() {
        return father;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

}
