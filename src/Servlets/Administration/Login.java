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
import javax.servlet.http.HttpSession;


@WebServlet(name="Login", urlPatterns={"/Administration/Login.do"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        
        String userName = req.getParameter("mobile");
        String password = req.getParameter("password");
        User user = User.getUser(userName, password);
        
        HttpSession session = req.getSession();
        if(user == null){ //failed to login
            session.setAttribute("msg", "Please enter valid mobile and password....");
            req.getRequestDispatcher("Login.jsp").forward(req, res);
        }
        else if(!user.isActive()){
            session.setAttribute("msg", "You are not authorized to access this application...");
            res.sendRedirect("Login.jsp");
        }
        else{
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(60000);
            res.sendRedirect("../index.jsp");
        }
    } 


    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

}
