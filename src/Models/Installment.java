/**
 * @author MUKTADIR
 */
package Models;


import Database.DataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Installment extends Payment{
    private int invoiceId;
    private int paymentId;
    private int scheduleId;
    private String date;
    private int ammount;
    private String customerName;
    private String customerMobile;
    private String userName;
    private String userMobile;

    public static int getTotalWithdrawn(String month, String year){
        String sql = "SELECT ROUND(SUM(AMMOUNT), 2) TOTAL_WITHDRAWN\n" +
                "FROM PAYMENTS JOIN INSTALLMENTS USING(PAYMENT_ID)\n"+
                "WHERE TO_CHAR(\"DATE\", 'MMYYYY') = ? || ?";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, month);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("TOTAL_WITHDRAWN");

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static ArrayList<Installment> getInstallments(String month, String year){
        ArrayList<Installment> list = new ArrayList<>();
        String sql = "SELECT INVOICE_ID, PAYMENT_ID, SCHEDULE_ID, TO_CHAR( \"DATE\", 'DD Month YYYY') \"DATE\", AMMOUNT, CUSTOMER_NAME, CUSTOMER_MOBILE, USER_NAME, USER_MOBILE\n" +
                "FROM INSTALLMENT_LOG IL\n";
        if(month != null && year != null) sql += "WHERE TO_CHAR(\"DATE\", 'MMYYYY') = ? || ? ";
        sql += "ORDER BY IL.\"DATE\"\n";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            if(month != null && year != null){
                stmt.setString(1, month);
                stmt.setString(2, year);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Installment t = new Installment();
                t.invoiceId = rs.getInt("INVOICE_ID");
                t.paymentId = rs.getInt("PAYMENT_ID");
                t.scheduleId = rs.getInt("SCHEDULE_ID");
                t.date = rs.getString("DATE");
                t.ammount = rs.getInt("AMMOUNT");
                t.customerName = rs.getString("CUSTOMER_NAME");
                t.customerMobile = rs.getString("CUSTOMER_MOBILE");
                t.userName = rs.getString("USER_NAME");
                t.userMobile = rs.getString("USER_MOBILE");

                t.userMobile  = rs.getString("USER_MOBILE");
                t.userName = rs.getString("USER_NAME");
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public String getDate() {
        return date;
    }

    public int getAmmount() {
        return ammount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMobile() {
        return userMobile;
    }
}
