/**
 * @author MUKTADIR
 */

package Servlets.Customer;

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


@WebServlet(name="SellProduct", urlPatterns={"/Customer/SellProduct.do"})
public class SellProduct extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            out.print("You must login to see this page...");
            return;
        }
        User user = (User) session.getAttribute("user");


        try {
            String orderlines = req.getParameter("orderlines");
            int total = Integer.parseInt(req.getParameter("total"));
            int discount = Integer.parseInt(req.getParameter("discount").equals("")?"0": req.getParameter("discount"));
            int profit = Integer.parseInt(req.getParameter("profit"));
            int isOnetime = req.getParameter("paymentType").equals("onetime") ? 1 : 0;
            String mobile = req.getParameter("mobile").equals("") ? null : req.getParameter("mobile");


            int nInstallments = 0;
            String firstInstallmentDate = "";
            int interval = 0;
            if(isOnetime == 0) {
                try {
                    nInstallments = Integer.parseInt(req.getParameter("nInstallments"));
                    firstInstallmentDate = req.getParameter("firstInstallmentDate");
                    interval = Integer.parseInt(req.getParameter("interval"));
                }
                catch (NumberFormatException e){
                    session.setAttribute("msg", "Invalid Number....");
                    res.sendRedirect("SellProduct.jsp");
                    e.printStackTrace();
                    return;
                }
            }

            /*add validation code*/
            String msg = "";
            if(discount > total){
                msg += "Discount > total\n";
            }
            if(mobile == null){
                msg += "Enter customer\n";
            }

            int invoiceId = SellingInvoice.addSellingInvoice(user.getMobile(), mobile, total, discount, profit, isOnetime, nInstallments, firstInstallmentDate, interval );

            if(invoiceId <= 0){
                msg += "Enter valid information\n";
            }
            if(!msg.equals("")){
                session.setAttribute("msg", msg);
                res.sendRedirect("SellProduct.jsp");
                return;
            }

            JSONArray jsonArray = new JSONArray(orderlines);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int productId = jsonObject.getInt("productId");
                int qty = jsonObject.getInt("quantity");
                Orderline.addSellingOrderline(invoiceId, productId, qty);
            }

        }
        catch (NumberFormatException e){
            session.setAttribute("msg", "Invalid Number....");
            res.sendRedirect("SellProduct.jsp");
            e.printStackTrace();
            return;

        }

        session.setAttribute("msg", " successfully added to the database. " + req.getParameter("orderlines"));
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
