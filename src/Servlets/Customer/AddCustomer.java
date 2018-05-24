/**
 * @author MUKTADIR
 */

package Servlets.Customer;

import Models.Customer;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="AddCustomer", urlPatterns={"/Customer/AddCustomer.do"})
public class AddCustomer extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            out.print("You must login to see this page...");
            return;
        }
        User user = ((User)session.getAttribute("user"));

        String name = req.getParameter("name");
        String father = req.getParameter("father");
        String mobile = req.getParameter("mobile");
        String address = req.getParameter("address");

        /*add validation code here*/


        int x = Customer.insert(name, father, mobile, address);
        if(x == 1) {
            session.setAttribute("msg", name + " successfully added to the database.");
            res.sendRedirect("../index.jsp");
        }
        else{
            session.setAttribute("msg", name +" was failed to add to the database.");
            req.getRequestDispatcher("AddCustomer.jsp").forward(req, res);
        }
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Project Management System by muktadir rahman";
    }

}
