/**
 * @author MUKTADIR
 * Just to handle the request, not actually updating the database.
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


@WebServlet(name="ChangeProfile", urlPatterns={"/Administration/ChangeProfile.do"})
public class ChangeProfile extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();

        if(req.getSession().getAttribute("user") == null){
            out.print("You must login to see this page...");
            return;
        }
        String mobile = req.getParameter("mobile");
        if(mobile == null){
            out.print("Wrong URL");
            return;
        }

        User user = ((User)session.getAttribute("user"));
        if(!user.getMobile().equals(mobile) && !user.isAdmin()){
            out.print("you are not allowed to change this profile");
        }

        User userToChangeProfile = User.getUser(mobile);
        if(userToChangeProfile == null){
            out.print("No  Mobile found...");
            return;
        }
        if(req.getParameter("removeAccess") != null){
            User.removeAccess(userToChangeProfile.getMobile());
            session.setAttribute("msg", " successfully removed access.");
            res.sendRedirect("../index.jsp");
            return;
        }
        session.setAttribute("userToChangeProfile", userToChangeProfile);
        res.sendRedirect("ChangeProfile.jsp");

    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 
}
