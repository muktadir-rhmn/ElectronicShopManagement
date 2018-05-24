/**
 * @author MUKTADIR
 */

package Servlets.Administration;

import Models.Product;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="AddProduct", urlPatterns={"/Administration/AddProduct.do"})
public class AddProduct extends HttpServlet {
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            out.print("You must login to see this page...");
            return;
        }
        User user = ((User)session.getAttribute("user"));

        String category = req.getParameter("category");
        String model = req.getParameter("model");
        String company = req.getParameter("company");
        String extraInfo = req.getParameter("extraInfo");
        int costPrice;
        int sellingPrice;
        try{
            costPrice = Integer.parseInt(req.getParameter("costPrice"));
            sellingPrice = Integer.parseInt(req.getParameter("sellingPrice"));
        }
        catch (NumberFormatException e){//if invalid number is given.
            e.printStackTrace();
            return;
        }


        /*validation code should be added here*/

        int result  = Product.insert(category, model, company, extraInfo, costPrice, sellingPrice);

        if(result == 1) {
            session.setAttribute("msg", company + " " + category + " " + model + " successfully added to the database.");
            res.sendRedirect("../index.jsp");
        }
        else{
            session.setAttribute("msg", "Failed to insert " + company + " " + category + " " + model);
            req.getRequestDispatcher("addProduct.jsp").forward(req, res);
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
