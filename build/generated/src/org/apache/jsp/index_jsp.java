package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Models.User;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<script type=\"text/javascript\" src=\"Resources/jQuery/jquery-3.1.1.min.js\"></script>\n");
      out.write("<link rel=\"stylesheet\" href=\"Resources/Bootstrap/css/bootstrap.min.css\">\n");
      out.write("<script src=\"Resources/Bootstrap/js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("<style>\n");
      out.write("    td {\n");
      out.write("        padding: 10px;\n");
      out.write("    }\n");
      out.write("    body{\n");
      out.write("        padding: 20px;\n");
      out.write("    }\n");
      out.write("</style>\n");
      out.write("<title> Home - Electronic Shop Management System</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<center>\n");
      out.write("\n");
      out.write("\n");

    User user = (User) session.getAttribute("user");
    if(user == null){
        out.print("You must login to see this page...");
        return;
    }

      out.write("\n");
      out.write("<nav class=\"navbar navbar-inverse\">\n");
      out.write("    <div class=\"container-fluid\">\n");
      out.write("\n");
      out.write("        <ul class=\"nav navbar-nav\">\n");
      out.write("            <li class=\"active\"><a href=\"/index.jsp\">Home</a></li>\n");
      out.write("            <li class=\"dropdown\">\n");
      out.write("                <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">Customer\n");
      out.write("                    <span class=\"caret\"></span></a>\n");
      out.write("                <ul class=\"dropdown-menu\">\n");
      out.write("                    <li><a href=\"Customer/SellProduct.jsp\">Sell Product</a></li>\n");
      out.write("                    <li><a href=\"Customer/AddCustomer.jsp\">Add Customer</a></li>\n");
      out.write("                    <li><a href=\"Customer/SellingLog.jsp\">SellingLog</a></li>\n");
      out.write("                    <li><a href=\"Customer/InstallmentSchedules.jsp\">Installment Schedule</a></li>\n");
      out.write("                    <li><a href=\"Customer/InstallmentLog.jsp\">Installment Log</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li class=\"dropdown\">\n");
      out.write("                <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">Supplier\n");
      out.write("                    <span class=\"caret\"></span></a>\n");
      out.write("                <ul class=\"dropdown-menu\">\n");
      out.write("                    <li><a href=\"Supplier/BuyProduct.jsp\">Buy Product</a></li>\n");
      out.write("                    <li><a href=\"Supplier/AddSupplier.jsp\">Add Supplier</a></li>\n");
      out.write("                    <li><a href=\"Supplier/BuyingLog.jsp\">Buying Log</a></li>\n");
      out.write("                    <li><a href=\"Supplier/Pay.jsp\">Pay Supplier</a></li>\n");
      out.write("                    <li><a href=\"Supplier/PaymentLog.jsp\">Payment Log</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li class=\"dropdown\">\n");
      out.write("                <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">Administration\n");
      out.write("                    <span class=\"caret\"></span></a>\n");
      out.write("                <ul class=\"dropdown-menu\">\n");
      out.write("                    <li><a href=\"Administration/AddProduct.jsp\">Add Product</a></li>\n");
      out.write("                    <li><a href=\"Administration/AddEmployee.jsp\">Add Employee</a></li>\n");
      out.write("                    <li><a href=\"Administration/ChangeProfile.do?mobile=");
out.print(((User)session.getAttribute("user")).getMobile());
      out.write("\">Edit Profile</a></li>\n");
      out.write("                    <li><a href=\"Administration/ChangeEmployeeProfile.jsp\">Edit Employee Profile</a></li>\n");
      out.write("                    <li><a href=\"Administration/Report.jsp\">Report</a></li>\n");
      out.write("                    <li><a href=\"Administration/ViewStock.jsp\">View Stock</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"Administration/Logout.jsp\">Log Out</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </div>\n");
      out.write("</nav>\n");
      out.write("    <h1>Home</h1>\n");
      out.write("    <h2>Welcome ");
out.print(user.getName());
      out.write(" !</h2>\n");
      out.write("        ");

    String msg = (String) session.getAttribute("msg");
    if(msg != null){
        out.print(
                "<div class='alert alert-success'>" + msg +"</div>"
        );
        session.removeAttribute("msg");
    }

      out.write('\n');
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
