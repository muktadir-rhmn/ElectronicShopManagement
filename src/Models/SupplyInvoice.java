/**
 * @author MUKTADIR
 */
package Models;


import Database.DataAccess;

import java.sql.*;
import java.util.ArrayList;

public class SupplyInvoice extends Invoice{
    private int invoiceId;
    private String date;
    private int total;
    private int discount;
    private int netTotal;
    private int transportationCost;
    private String userName;
    private String userMobile;
    private String supplierName;
    private String supplierId;
    private ArrayList<Orderline> orderlines;

    public static int[] getTotalBoughtTransportCost(String month, String year){
        int[] ret = new int[2];
        String sql = "SELECT SUM(TOTAL - DISCOUNT) TOTAL_BOUGHT, SUM(TRANSPORTATION_COST) TRANSPORTATION_COST\n" +
                "FROM INVOICES JOIN SUPPLY_INVOICES USING (INVOICE_ID)\n"+
                "WHERE TO_CHAR(\"DATE\", 'MMYYYY') = ? || ?";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, month);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            ret[0] = rs.getInt("TOTAL_BOUGHT");
            ret[1] = rs.getInt("TRANSPORTATION_COST");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static int addSupplyInvoice(String user, int total, int discount, int transportationCost, int supplierId){
        String sql = "{? = call ADD_SUPPLY_INVOICE(?, ?, ?, ?, ?)}";
        DataAccess da = DataAccess.getDataAccess();
        try {
            CallableStatement stmt = da.getCallableStatement(sql);
            stmt.registerOutParameter(1, Types.DECIMAL);
            stmt.setString(2, user);
            stmt.setInt(3, total);
            stmt.setInt(4, discount);
            stmt.setInt(5, transportationCost);
            stmt.setInt(6, supplierId);
            stmt.execute();

            return stmt.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static SupplyInvoice getInvoice(String invoiceId){
        SupplyInvoice invoice = null;
        String sql = "SELECT INVOICE_ID, TO_CHAR(\"DATE\", 'DD-MM-YYYY') \"DATE\", TOTAL, DISCOUNT, NET_TOTAL, USER_NAME, USER_MOBILE, SUPPLIER_NAME, SUPPLIER_ID, TRANSPORTATION_COST " +
                "FROM SUPPLY_INVOICES_DERIVED " +
                "WHERE INVOICE_ID = ?";
        DataAccess da = DataAccess.getDataAccess();
        try {

            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, invoiceId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                invoice = new SupplyInvoice();
                invoice.invoiceId = rs.getInt("INVOICE_ID");
                invoice.date = rs.getString("DATE");
                invoice.total = rs.getInt("TOTAL");
                invoice.discount = rs.getInt("DISCOUNT");
                invoice.netTotal = rs.getInt("NET_TOTAL");
                invoice.userName  = rs.getString("USER_NAME");
                invoice.userMobile = rs.getString("USER_MOBILE");
                invoice.supplierName  = rs.getString("SUPPLIER_NAME");
                invoice.supplierId  = rs.getString("SUPPLIER_ID");
                invoice.transportationCost = rs.getInt("TRANSPORTATION_COST");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(invoice != null) invoice.orderlines = Orderline.getOrderlines(invoiceId);
        return invoice;
    }

    public static ArrayList<SupplyInvoice> getInvoices(String month, String year){
        ArrayList<SupplyInvoice> invoices = new ArrayList<>();
        String sql = "SELECT INVOICE_ID, TO_CHAR( \"DATE\", 'DD Month YYYY') \"DATE\", TOTAL, DISCOUNT, NET_TOTAL, USER_NAME, USER_MOBILE, SUPPLIER_NAME, SUPPLIER_ID, TRANSPORTATION_COST " +
                "FROM SUPPLY_INVOICES_DERIVED \n";
        if(month != null && year != null) sql += "WHERE TO_CHAR(\"DATE\", 'MMYYYY') = ? || ? ";

        DataAccess da = DataAccess.getDataAccess();
        try {

            PreparedStatement stmt = da.getStatement(sql);
            if(month != null && year != null){
                stmt.setString(1, month);
                stmt.setString(2, year);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                SupplyInvoice invoice = new SupplyInvoice();
                invoice.invoiceId = rs.getInt("INVOICE_ID");
                invoice.date = rs.getString("DATE");
                invoice.total = rs.getInt("TOTAL");
                invoice.discount = rs.getInt("DISCOUNT");
                invoice.netTotal = rs.getInt("NET_TOTAL");
                invoice.userName  = rs.getString("USER_NAME");
                invoice.userMobile = rs.getString("USER_MOBILE");
                invoice.supplierName  = rs.getString("SUPPLIER_NAME");
                invoice.supplierId  = rs.getString("SUPPLIER_ID");
                invoice.transportationCost = rs.getInt("TRANSPORTATION_COST");

                //invoice.orderlines = Orderline.getOrderlines(invoice.invoiceId + "");
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

    public String getUserName() {
        return userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public int getTransportationCost() {
        return transportationCost;
    }



    public ArrayList<Orderline> getOrderlines() {
        return orderlines;
    }
}
