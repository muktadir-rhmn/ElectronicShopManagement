/**
 * @author MUKTADIR
 */

package Servlets.Supplier;

import Models.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="BuyProduct", urlPatterns={"/Supplier/BuyProduct.do"})
public class BuyProduct extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            out.print("You must login to see this page...");
            return;
        }
        User user = ((User)session.getAttribute("user"));

        try {
            String orderlines = req.getParameter("orderlines");
            int total = Integer.parseInt(req.getParameter("total"));
            int discount = Integer.parseInt(req.getParameter("discount").equals("") ? "0" : req.getParameter("discount"));
            int transportationCost = Integer.parseInt(req.getParameter("transportationCost").equals("")? "0": req.getParameter("transportationCost"));
            int supplierId = Integer.parseInt(req.getParameter("supplierId"));
            System.out.println("sup id: " + supplierId);
            int invoiceId = SupplyInvoice.addSupplyInvoice(user.getMobile(), total, discount, transportationCost, supplierId);

            System.out.println(invoiceId);
            JSONArray jsonArray = new JSONArray(orderlines);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int productId = jsonObject.getInt("productId");
                int qty = jsonObject.getInt("quantity");
                Orderline.addSupplyOrderline(invoiceId, productId, qty);
                System.out.println("Invoice id:" + invoiceId);
                System.out.println("Product id: " + productId);
                System.out.println("qty " + qty);
            }
        }catch (NumberFormatException e){
                System.out.println("invalid number");
                e.printStackTrace();
            }

            session.setAttribute("msg", " successfully added to the database.");
        res.sendRedirect("../index.jsp");
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Project Management System by muktadir rahman";
    }

}
