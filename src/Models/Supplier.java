package Models;

import Database.DataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by MUKTADIR on 12/14/2016.
 */
public class Supplier {
    private int supplierId = -1;
    private String name = null;
    private String proprietor = null;
    private String address = null;
    private String mobile = null;
    private int totalDebit = -1;

    public static int insert(String name, String proprietor, String address, String mobile) {
        String sql = "INSERT INTO SUPPLIERS(SUPPLIER_ID, NAME, PROPRIETOR, ADDRESS, MOBILE)" +
                "VALUES(SUPPLIER_ID_SEQ.NEXTVAL, ?, ?, ?, ?)";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, proprietor);
            stmt.setString(3, address);
            stmt.setString(4, mobile);
            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Supplier getSupplier(String supplierId){
      Supplier t = null;

        String sql =
                "SELECT SUPPLIER_ID, NAME, PROPRIETOR, ADDRESS, MOBILE, TOTAL_DEBIT  " +
                        "FROM SUPPLIERS " +
                        "WHERE SUPPLIER_ID = ? ";
        DataAccess da = DataAccess.getDataAccess();

        try {
            PreparedStatement stmt  = da.getStatement(sql);
            stmt.setInt(1, Integer.parseInt(supplierId));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                t = new Supplier();
                t.supplierId = rs.getInt("SUPPLIER_ID");
                t.name = rs.getString("NAME");
                t.proprietor = rs.getString("PROPRIETOR");
                t.address = rs.getString("ADDRESS");
                t.mobile = rs.getString("MOBILE");
                t.totalDebit = rs.getInt("TOTAL_DEBIT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static ArrayList<Supplier> getSuppliers(){
        ArrayList<Supplier> list = new ArrayList<>();

        String sql =
                "SELECT SUPPLIER_ID, NAME, PROPRIETOR, ADDRESS, MOBILE, TOTAL_DEBIT  " +
                        "FROM SUPPLIERS";
        DataAccess da = DataAccess.getDataAccess();

        try {
            PreparedStatement stmt  = da.getStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Supplier t = new Supplier();
                t.supplierId = rs.getInt("SUPPLIER_ID");
                t.name = rs.getString("NAME");
                t.proprietor = rs.getString("PROPRIETOR");
                t.address = rs.getString("ADDRESS");
                t.mobile = rs.getString("MOBILE");
                t.totalDebit = rs.getInt("TOTAL_DEBIT");

                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public int getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public String getProprietor() {
        return proprietor;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public int getTotalDebit() {
        return totalDebit;
    }
}
