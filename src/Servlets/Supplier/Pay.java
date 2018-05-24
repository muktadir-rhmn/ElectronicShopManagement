/**
 * @author MUKTADIR
 */

package Servlets.Supplier;

import Models.SupplyPayment;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="Pay", urlPatterns={"/Supplier/Pay.do"})
public class Pay extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            out.print("You must login to see this page...");
            return;
        }
        User user = ((User)session.getAttribute("user"));

        try {
            int supplierId = Integer.parseInt(req.getParameter("supplierId"));
            int ammount = Integer.parseInt(req.getParameter("payingAmmount"));

            SupplyPayment.paySupplier(supplierId, ammount, user.getMobile());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            out.print("Please enter valid supplier id and ammount");
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
