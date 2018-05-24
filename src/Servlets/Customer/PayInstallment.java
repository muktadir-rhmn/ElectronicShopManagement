/**
 * @author MUKTADIR
 */

package Servlets.Customer;

import Models.PaymentSchedule;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="PayInstallment", urlPatterns={"/Customer/PayInstallment.do"})
public class PayInstallment extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            out.print("You must login to see this page...");
            return;
        }
        User user = ((User)session.getAttribute("user"));

        String scheduleId = req.getParameter("scheduleId");
        if(scheduleId == null){
            out.print("you must provide some schedule id");
            return;
        }

        PaymentSchedule.payInstallment(scheduleId, user.getMobile());
        
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
