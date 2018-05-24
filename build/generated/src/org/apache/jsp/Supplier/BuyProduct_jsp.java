package org.apache.jsp.Supplier;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Models.Product;
import java.util.ArrayList;
import Models.CategoryModel;

public final class BuyProduct_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Resources/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("title", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("", request.getCharacterEncoding()), out, false);
      out.write('\n');
      out.write('\n');

    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    ArrayList<String> categories = Product.getCategories();
    ArrayList<String> companies = Product.getCompanies();
    ArrayList<CategoryModel> models = Product.getModels();

      out.write("\n");
      out.write("<script>\n");
      out.write("    var model = {};\n");
      out.write("    ");

        for (String cat : categories) {
            out.println(String.format("model[%s] = {};", cat));
        }
    
      out.write("\n");
      out.write("\n");
      out.write("    ");

       for (CategoryModel cm : models) {
           out.println(String.format("model[%s].push(\"%s\");", cm.category, cm.model));
       }
    
      out.write("\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<form action=\"BuyProduct.do\" method=\"post\">\n");
      out.write("    <table>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for=\"category\">Categories</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <select name=\"category\" id=\"category\" >\n");
      out.write("                    <option value=\"\" disabled selected>Select category</option>\n");
      out.write("                </select>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for='model'>Model</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <select name=\"model\" id=\"model\" >\n");
      out.write("                    <option value=\"\" disabled selected>Select Model</option>\n");
      out.write("\n");
      out.write("                </select>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for='Quantity'>Quantity</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <input type='text' name='Quantity' id='Quantity' class='form-control'/>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for='nInstallments'>NInstallments</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <input type='text' name='nInstallments' id='nInstallments' class='form-control'/>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for='interval'>Interval</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <input type='text' name='interval' id='interval' class='form-control'/>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for='discount'>Discount</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <input type='text' name='discount' id='discount' class='form-control'/>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <input type='submit' class='btn btn-success btn-block' value='Buy Product' />    <td>\n");
      out.write("        </tr>\n");
      out.write("\n");
      out.write("    </table>\n");
      out.write("</form>\n");
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Resources/footer.jsp", out, false);
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
