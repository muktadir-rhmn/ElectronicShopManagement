package org.apache.jsp.Administration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Resources/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("title", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Login", request.getCharacterEncoding()), out, false);
      out.write('\n');
      out.write('\n');

    String msg = (String) session.getAttribute("msg");
    if(msg != null){
        out.print(
                "<div class='alert alert-danger'>" + msg +"</div>"
        );
        session.removeAttribute("msg");
    }

      out.write("\n");
      out.write("\n");
      out.write("<form action=\"Login.do\" method=\"post\">\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            <label for=\"mobile\" > Mobile:</label>\n");
      out.write("        </td>\n");
      out.write("        <td>\n");
      out.write("            <input type=\"text\" id=\"mobile\" name=\"mobile\" class=\"form-control\"/>\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            <label for=\"password\" > Password:</label>\n");
      out.write("        </td>\n");
      out.write("        <td>\n");
      out.write("            <input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\"/>\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td></td>\n");
      out.write("        <td>\n");
      out.write("            <input type=\"submit\" value=\"Login\" class=\"btn btn-success btn-block\"/>\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("</table>\n");
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
