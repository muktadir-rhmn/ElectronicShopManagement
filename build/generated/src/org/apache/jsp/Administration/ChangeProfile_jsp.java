package org.apache.jsp.Administration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ChangeProfile_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Resources/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("title", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Add Employee", request.getCharacterEncoding()), out, false);
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');

    String mobile = request.getParameter("mobile");
    if(mobile == null){
        return;
    }

      out.write("\r\n");
      out.write("<form action=\"AddEmployee.do\" method=\"post\">\r\n");
      out.write("    <table>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='name'>Name</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='name' id='name' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='father'>Father</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='father' id='father' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='mobile'>Mobile</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='mobile' id='mobile' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='password'>Password</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='password' name='password' id='password' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='salary'>Salary</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='salary' id='salary' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='address'>Address</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='address' id='address' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='submit' class='btn btn-success btn-block' value='Add Employee' />    <td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    </table>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write('\r');
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
