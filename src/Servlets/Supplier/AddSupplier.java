/**
 * @author MUKTADIR
 */

package Servlets.Supplier;

import Models.Supplier;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="AddSupplier", urlPatterns={"/Supplier/AddSupplier.do"})
public class AddSupplier extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            out.print("You must login to see this page...");
            return;
        }
        User user = ((User)session.getAttribute("user"));

        String name = req.getParameter("name");
        String proprietor = req.getParameter("proprietor");
        String address = req.getParameter("address");
        String mobile = req.getParameter("mobile");

        /*validation code should be added*/

        int x = Supplier.insert(name, proprietor, address, mobile);
        if(x == 1) {
            session.setAttribute("msg", name + " successfully added to the database.");
            req.getRequestDispatcher("../index.jsp").forward(req, res);
        }
        else{
            session.setAttribute("msg", name +" was failed to add to the database.");
            res.sendRedirect("../index.jsp");
        }
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
