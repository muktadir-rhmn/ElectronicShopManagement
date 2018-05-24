/**
 * @author MUKTADIR
 */
package Models;


import Database.DataAccess;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentSchedule {
    private int scheduleId = -1;
    private String date = null;
    private int amount = -1;
    private int invoiceId = -1;
    private String customerName = null;
    private String customerMobile = null;
    private String status;

    public static PaymentSchedule getSchedule(String scheduleId){
       PaymentSchedule t = null;
        String sql = "SELECT  SCHEDULE_ID, TO_CHAR(\"DATE\", 'DD-MON-YYYY') \"DATE\", AMMOUNT, INVOICE_ID, C.\"NAME\" CUSTOMER_NAME, C.MOBILE CUSTOMER_MOBILE, STATUS " +
                "FROM PAYMENT_SCHEDULES P_S JOIN SELLING_INVOICES S_I USING(INVOICE_ID) " +
                "JOIN CUSTOMERS C ON S_I.CUSTOMER =  C.MOBILE " +
                "WHERE SCHEDULE_ID = ? " ;
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, scheduleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                t = new PaymentSchedule();
                t.scheduleId = rs.getInt("SCHEDULE_ID");
                t.date = rs.getString("DATE");
                t.amount = rs.getInt("AMMOUNT");
                t.invoiceId = rs.getInt("INVOICE_ID");
                t.customerName = rs.getString("CUSTOMER_NAME");
                t.customerMobile = rs.getString("CUSTOMER_MOBILE");
                t.status = rs.getString("STATUS");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
    public static ArrayList<PaymentSchedule> getSchedules(){
        ArrayList<PaymentSchedule> list = new ArrayList<>();
        String sql = "SELECT  SCHEDULE_ID, TO_CHAR(\"DATE\", 'DD-MON-YYYY') \"DATE\", AMMOUNT, INVOICE_ID, C.\"NAME\" CUSTOMER_NAME, C.MOBILE CUSTOMER_MOBILE, STATUS " +
                "FROM PAYMENT_SCHEDULES P_S JOIN SELLING_INVOICES S_I USING(INVOICE_ID) " +
                "JOIN CUSTOMERS C ON S_I.CUSTOMER =  C.MOBILE " +
                "ORDER BY P_S.\"DATE\"";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                PaymentSchedule t = new PaymentSchedule();
                t.scheduleId = rs.getInt("SCHEDULE_ID");
                t.date = rs.getString("DATE");
                t.amount = rs.getInt("AMMOUNT");
                t.invoiceId = rs.getInt("INVOICE_ID");
                t.customerName = rs.getString("CUSTOMER_NAME");
                t.customerMobile = rs.getString("CUSTOMER_MOBILE");
                t.status = rs.getString("STATUS");
                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<PaymentSchedule> getSchedules(String month, String year){
        ArrayList<PaymentSchedule> list = new ArrayList<>();
        String sql = "SELECT  SCHEDULE_ID, TO_CHAR(\"DATE\", 'DD-MON-YYYY') \"DATE\", AMMOUNT, INVOICE_ID, C.\"NAME\" CUSTOMER_NAME, C.MOBILE CUSTOMER_MOBILE, STATUS " +
                "FROM PAYMENT_SCHEDULES P_S JOIN SELLING_INVOICES S_I USING(INVOICE_ID) " +
                "JOIN CUSTOMERS C ON S_I.CUSTOMER =  C.MOBILE " +
                "WHERE TO_CHAR(\"DATE\", 'MMYYYY') = ? || ? " +
                "ORDER BY P_S.\"DATE\" ";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, month);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                PaymentSchedule t = new PaymentSchedule();
                t.scheduleId = rs.getInt("SCHEDULE_ID");
                t.date = rs.getString("DATE");
                t.amount = rs.getInt("AMMOUNT");
                t.invoiceId = rs.getInt("INVOICE_ID");
                t.customerName = rs.getString("CUSTOMER_NAME");
                t.customerMobile = rs.getString("CUSTOMER_MOBILE");
                t.status = rs.getString("STATUS");
                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean payInstallment(String scheduleId, String user){
        String sql = "{call PAY_INSTALLMENT(?, ?)}";
        DataAccess da = DataAccess.getDataAccess();
        try {
            CallableStatement stmt = da.getCallableStatement(sql);
            stmt.setInt(1, Integer.parseInt(scheduleId));
            stmt.setString(2, user);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getScheduleId() {
        return scheduleId;
    }


    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public boolean isPaid(){
        return status.equals("P");
    }
}
