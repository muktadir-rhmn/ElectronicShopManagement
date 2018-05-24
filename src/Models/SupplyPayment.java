package Models;

import Database.DataAccess;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by MUKTADIR on 12/14/2016.
 */
public class SupplyPayment extends Payment{
    private int paymentId;
    private int supplierId;
    private String supplierName;
    private int ammount;
    private String date;
    private String userName;
    private String userMobile;

    public static int totalPaidToSuppliers(String month, String year){
        String sql = "SELECT ROUND(SUM(AMMOUNT), 2) PAID_TO_SUPPLIERS\n" +
                "FROM PAYMENTS JOIN SUPPLIER_PAYMENTS USING(PAYMENT_ID)\n"+
                "WHERE TO_CHAR(\"DATE\", 'MMYYYY') = ? || ?";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, month);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("PAID_TO_SUPPLIERS");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public static boolean paySupplier(int supplierId, int ammount, String user){
        String sql = "{call PAY_SUPPLIER(?, ?, ?) }";
        DataAccess da = DataAccess.getDataAccess();
        try {
            CallableStatement stmt = da.getCallableStatement(sql);
            stmt.setInt(1, supplierId);
            stmt.setInt(2, ammount);
            stmt.setString(3, user);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<SupplyPayment> getSupplyPayments(String month, String year){
        ArrayList<SupplyPayment> list = new ArrayList<>();
        String sql = "SELECT PAYMENT_ID, TO_CHAR( \"DATE\", 'DD Month YYYY') \"DATE\", AMMOUNT, SUPPLIER_ID, S.NAME SUPPLIER_NAME, U.NAME USER_NAME, U.MOBILE USER_MOBILE\n" +
                "FROM PAYMENTS P JOIN SUPPLIER_PAYMENTS S_P USING(PAYMENT_ID) JOIN SUPPLIERS S USING(SUPPLIER_ID)\n" +
                "JOIN USERS U ON U.MOBILE = P.\"USER\"\n";
        if(month != null && year != null) sql += "WHERE TO_CHAR(\"DATE\", 'MMYYYY') = ? || ? \n";

        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            if(month != null && year != null){
                stmt.setString(1, month);
                stmt.setString(2, year);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                SupplyPayment payment = new SupplyPayment();
                payment.paymentId = rs.getInt("PAYMENT_ID");
                payment.supplierId = rs.getInt("SUPPLIER_ID");
                payment.supplierName = rs.getString("SUPPLIER_NAME");
                payment.ammount = rs.getInt("AMMOUNT");
                payment.date = rs.getString("DATE");
                payment.userMobile  = rs.getString("USER_MOBILE");
                payment.userName = rs.getString("USER_NAME");
                list.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public int getAmmount() {
        return ammount;
    }

    public String getDate() {
        return date;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMobile() {
        return userMobile;
    }
}
