/**
 * @author MUKTADIR
 */

package Servlets.Administration;

import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="AddEmployee", urlPatterns={"/Administration/AddEmployee.do"})
public class AddEmployee extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String father = req.getParameter("father");
        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");
        String address =  req.getParameter("address");
        String joinDate = req.getParameter("joinDate");
        String salary = req.getParameter("salary");
        
        /*validation code here*/
        
        int x = User.insert("EM", name, father, mobile, password, address, salary);
        if(x != 1){ //not inserted
            req.getSession().setAttribute("errorAlreadyExists", true);
            req.getRequestDispatcher("AddEmployee.jsp").forward(req, res);
        }
        else{
            req.getSession().setAttribute("msg", "Employee " + name +" successfully added to the database.");
            res.sendRedirect("../index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

}
