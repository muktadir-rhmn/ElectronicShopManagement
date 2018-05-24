/**
 * @author MUKTADIR
 * Update the database based on the form input.
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
import javax.servlet.http.HttpSession;


@WebServlet(name="UpdateProfile", urlPatterns={"/Administration/UpdateProfile.do"})
public class UpdateProfile extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        if(req.getSession().getAttribute("user") == null){
            out.print("You must login to see this page...");
            return;
        }
        User user = ((User)session.getAttribute("user"));
        User userToChangeProfile  = (User)session.getAttribute("userToChangeProfile");

        String password = req.getParameter("password");
        String address = req.getParameter("address");
        String salary;
        if(user.isAdmin())  salary = req.getParameter("salary");
        else salary = userToChangeProfile.getSalary();

        /*input validation code should added here*/

        userToChangeProfile.update(password, address, salary);

        session.removeAttribute("userToChangeProfile");

        req.getSession().setAttribute("msg", "Profile updated...");
        res.sendRedirect("../index.jsp");


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
