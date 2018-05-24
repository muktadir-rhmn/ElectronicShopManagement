
import Database.DataAccess;
import Models.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
/**
 * @author MUKTADIR
 */



public class Test {
    public static void main(String[] ar) throws SQLException {
        String s = " [{\"productId\":1,\"quantity\":2},{\"productId\":3,\"quantity\":3}]";

        JSONArray jsonArray = new JSONArray(s);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int productId = jsonObject.getInt("productId");
            int qty = jsonObject.getInt("quantity");
            System.out.println("pid: " + productId + " qty: " + qty);
        }

    }
    
    static void testDB() throws SQLException {

        DataAccess da = DataAccess.getDataAccess();
        PreparedStatement stmt = da.getStatement("select * from users");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            System.out.println(rs.getString("name"));
        }

    }
    static void generateCode(){
        HtmlCreator t = new HtmlCreator();
        String textBox = t.text("Quantity") +
                t.text("nInstallments")+
                t.text("interval")+
                t.text("discount")+
                t.submit("Add Customer");
        t.copyToClipBoard(textBox);
    }
   
}
