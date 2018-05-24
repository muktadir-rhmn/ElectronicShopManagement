package org.apache.jsp.Administration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Models.Product;
import java.util.ArrayList;

public final class AddProduct_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Resources/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("title", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Add Product", request.getCharacterEncoding()), out, false);
      out.write('\n');
      out.write('\n');

    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    ArrayList<String> categories = Product.getCategories();
    ArrayList<String> companies = Product.getCompanies();

      out.write("\n");
      out.write("\n");
      out.write("<form action=\"/Administration/AddProduct.do\" method=\"post\">\n");
      out.write("    <table>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for=\"category\">Categories</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <select name=\"category\" id=\"category\">\n");
      out.write("                    ");

                        for(String s:categories){
                            out.print(String.format("<option value='%s'>%s</option>", s, s));
                        }
                    
      out.write("\n");
      out.write("                </select>\n");
      out.write("                <button type=\"button\" class=\"btn btn-info btn-sm\">\n");
      out.write("                    <span class=\"glyphicon glyphicon-plus\"></span>\n");
      out.write("                </button>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for='model'>Model</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <input type='text' name='model' id='model' class='form-control'/>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                Company\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <select name=\"company\" id=\"company\">\n");
      out.write("                    ");

                        for(String s:companies){
                            out.print(String.format("<option value='%s'>%s</option>", s, s));
                        }
                    
      out.write("\n");
      out.write("                </select>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for='extraInfo'>Extra Info</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <input type='text' name='extraInfo' id='extraInfo' class='form-control'/>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for='costPrice'>Cost Price</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <input type='text' name='costPrice' id='costPrice' class='form-control'/>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("            <td>\n");
      out.write("                <label for='sellingPrice'>Selling Price</label>\n");
      out.write("            </td>\n");
      out.write("            <td>\n");
      out.write("                <input type='text' name='sellingPrice' id='sellingPrice' class='form-control'/>\n");
      out.write("            </td>\n");
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
