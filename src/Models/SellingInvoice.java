/**
 * @author MUKTADIR
 */
package Models;


import Database.DataAccess;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class SellingInvoice {
    private int invoiceId;
    private String date;
    private int total;
    private int discount;
    private int netTotal;
    private int profit;
    private String userName;
    private String userMobile;
    private String customerName;
    private String customerMobile;
    private ArrayList<Orderline> orderlines;

    public static int[] getTotalSaleProfit(String month, String year){
        int[] ret = new int[2];
        String sql = "SELECT SUM(TOTAL - DISCOUNT) TOTAL_SOLD, SUM(PROFIT) PROFIT\n" +
                "FROM INVOICES JOIN SELLING_INVOICES USING(INVOICE_ID)\n" +
                "WHERE TO_CHAR(\"DATE\", 'MMYYYY') = ? || ?";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, month);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            ret[0] = rs.getInt("TOTAL_SOLD");
            ret[1] = rs.getInt("PROFIT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static int addSellingInvoice(String user, String customer, int total, int discount, int profit, int isOnetime, int nInstallment, String firstInstallmentDate, int interval){
        String sql = "{? = call ADD_SELLING_INVOICE(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        DataAccess da = DataAccess.getDataAccess();
        try {
            CallableStatement stmt = da.getCallableStatement(sql);
            stmt.registerOutParameter(1, Types.DECIMAL);
            stmt.setString(2, user);
            stmt.setString(3, customer);
            stmt.setInt(4, total);
            stmt.setInt(5, discount);
            stmt.setInt(6, profit);
            stmt.setInt(7, isOnetime);
            stmt.setInt(8, nInstallment);
            stmt.setString(9, firstInstallmentDate);
            stmt.setInt(10, interval);
            System.out.println("one + " + isOnetime);
            stmt.execute();

            return stmt.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static SellingInvoice getInvoice(String invoiceId){
        SellingInvoice invoice = null;
        String sql = "SELECT INVOICE_ID, TO_CHAR( \"DATE\", 'DD Month YYYY') \"DATE\", TOTAL, DISCOUNT, NET_TOTAL, " +
                "USER_NAME, USER_MOBILE, CUSTOMER_NAME, CUSTOMER_MOBILE, PROFIT " +
                "FROM SELLING_INVOICES_DERIVED " +
                "WHERE INVOICE_ID = ?";
        DataAccess da = DataAccess.getDataAccess();
        try {

            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, invoiceId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                invoice = new SellingInvoice();
                invoice.invoiceId = rs.getInt("INVOICE_ID");
                invoice.date = rs.getString("DATE");
                invoice.total = rs.getInt("TOTAL");
                invoice.discount = rs.getInt("DISCOUNT");
                invoice.netTotal = rs.getInt("NET_TOTAL");
                invoice.userName= rs.getString("USER_NAME");
                invoice.userMobile = rs.getString("USER_MOBILE");
                invoice.customerName  = rs.getString("CUSTOMER_NAME");
                invoice.customerMobile  = rs.getString("CUSTOMER_MOBILE");
                invoice.profit = rs.getInt("PROFIT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(invoice != null) invoice.orderlines = Orderline.getOrderlines(invoiceId);
        return invoice;
    }

    public static ArrayList<SellingInvoice> getInvoices(){
        ArrayList<SellingInvoice> invoices = new ArrayList<>();
        String sql = "SELECT INVOICE_ID, TO_CHAR( \"DATE\", 'DD Month YYYY') \"DATE\", TOTAL, DISCOUNT, NET_TOTAL, " +
                "USER_NAME, USER_MOBILE, CUSTOMER_NAME, CUSTOMER_MOBILE,PROFIT " +
                "FROM SELLING_INVOICES_DERIVED " ;
        DataAccess da = DataAccess.getDataAccess();
        try {

            PreparedStatement stmt = da.getStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                SellingInvoice invoice = new SellingInvoice();
                invoice.invoiceId = rs.getInt("INVOICE_ID");
                invoice.date = rs.getString("DATE");
                invoice.total = rs.getInt("TOTAL");
                invoice.discount = rs.getInt("DISCOUNT");
                invoice.netTotal = rs.getInt("NET_TOTAL");
                invoice.userName= rs.getString("USER_NAME");
                invoice.userMobile = rs.getString("USER_MOBILE");
                invoice.customerName  = rs.getString("CUSTOMER_NAME");
                invoice.customerMobile  = rs.getString("CUSTOMER_MOBILE");
                invoice.profit = rs.getInt("PROFIT");
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public String getDate() {
        return date;
    }

    public int getTotal() {
        return total;
    }

    public int getDiscount() {
        return discount;
    }

    public int getNetTotal() {
        return netTotal;
    }


    public int getProfit() {
        return profit;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public ArrayList<Orderline> getOrderlines() {
        return orderlines;
    }
}
