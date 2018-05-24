package Models;

import Database.DataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by MUKTADIR on 12/14/2016.
 */
public class Product {
    private int productId = -1;
    private String category = null;
    private String model= null;
    private String company= null;
    private String extraInfo= null;
    private int costPrice = -1;
    private int sellingPrice = -1;
    private int stockQty = -1;

    public static ArrayList<String> getCategories(){
        ArrayList<String> list = new ArrayList<>();

        String sql =
                "SELECT DISTINCT(CATEGORY) CATEGORY " +
                        "FROM PRODUCTS";
        DataAccess da = DataAccess.getDataAccess();

        try {
            PreparedStatement stmt  = da.getStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                list.add(rs.getString("CATEGORY"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getCompanies(){
        ArrayList<String> list = new ArrayList<>();

        String sql =
                "SELECT DISTINCT(company) company " +
                        "FROM PRODUCTS";
        DataAccess da = DataAccess.getDataAccess();

        try {
            PreparedStatement stmt  = da.getStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                list.add(rs.getString("company"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Product> getProducts(){
        ArrayList<Product> list = new ArrayList<>();

        String sql =
                "SELECT PRODUCT_ID, CATEGORY, MODEL, COMPANY, EXTRA_INFO, COST_PRICE, SELLING_PRICE, STOCK_QTY " +
                        "FROM PRODUCTS\n" +
                        "ORDER BY CATEGORY, MODEL, PRODUCT_ID";
        DataAccess da = DataAccess.getDataAccess();

        try {
            PreparedStatement stmt  = da.getStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Product t = new Product();
                t.productId = rs.getInt("PRODUCT_ID");
                t.category = rs.getString("CATEGORY");
                t.model = rs.getString("MODEL");
                t.company = rs.getString("COMPANY");
                t.extraInfo = rs.getString("EXTRA_INFO");
                t.costPrice = rs.getInt("COST_PRICE");
                t.sellingPrice = rs.getInt("SELLING_PRICE");
                t.stockQty = rs.getInt("STOCK_QTY");

                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int insert(String category, String model, String company, String extraInfo, int costPrice, int sellingPrice) {
        String sql =
                "INSERT INTO PRODUCTS(PRODUCT_ID, CATEGORY, MODEL, COMPANY, EXTRA_INFO, COST_PRICE, SELLING_PRICE)" +
                        "VALUES(PRODUCT_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        DataAccess da = DataAccess.getDataAccess();
        try {
            PreparedStatement stmt = da.getStatement(sql);
            stmt.setString(1, category);
            stmt.setString(2,model);
            stmt.setString(3,company);
            stmt.setString(4,extraInfo);
            stmt.setInt(5,costPrice);
            stmt.setInt(6,sellingPrice);
            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getProductId() {
        return productId;
    }

    public String getCategory() {
        return category;
    }

    public String getModel() {
        return model;
    }

    public String getCompany() {
        return company;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public int getCostPrice() {
        return costPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public int getStockQty() {
        return stockQty;
    }
}
