/**
 * @author MUKTADIR
 */
package Models;


import Database.DataAccess;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class Orderline {
    private int invoiceId = -1;
    private String product;
    private int quantity = -1;


    public static int addSellingOrderline(int invoiceId, int productId, int quantity){
        String sql = "INSERT INTO ORDERLINES(INVOICE_ID, PRODUCT_ID, QUANTITY)\n" +
                "VALUES(?, ?, ?)";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setInt(1, invoiceId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return  -1;
        }
    }

    public static int addSupplyOrderline(int invoiceId, int productId, int quantity){
        String sql = "INSERT INTO ORDERLINES(INVOICE_ID, PRODUCT_ID, QUANTITY)\n" +
                "VALUES(?, ?, ?)";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setInt(1, invoiceId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static ArrayList<Orderline> getOrderlines(String invoiceId){
        ArrayList<Orderline> list = new ArrayList<>();
        String sql = "SELECT INVOICE_ID, \n" +
                "(SELECT (COMPANY || ' ' || CATEGORY || ' ' || MODEL) FROM PRODUCTS P WHERE O.PRODUCT_ID = P.PRODUCT_ID ) PRODUCT,\n" +
                "QUANTITY\n" +
                "FROM ORDERLINES O\n" +
                "WHERE INVOICE_ID = ?";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, invoiceId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Orderline t = new Orderline();
                t.invoiceId = rs.getInt("INVOICE_ID");
                t.product = rs.getString("PRODUCT");
                t.quantity = rs.getInt("QUANTITY");
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

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
