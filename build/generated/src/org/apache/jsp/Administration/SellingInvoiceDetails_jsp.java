package org.apache.jsp.Administration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Models.SellingInvoice;
import java.util.ArrayList;
import Models.Orderline;

public final class SellingInvoiceDetails_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Resources/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("title", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Invoice Details", request.getCharacterEncoding()), out, false);
      out.write('\n');
      out.write('\n');

    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    //url : *.jsp?invoiceId=123
    String invoiceId = request.getParameter("invoiceId");
    SellingInvoice invoice = SellingInvoice.getInvoice(invoiceId);


      out.write("\n");
      out.write("\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <td>Invoice Id</td>\n");
      out.write("        <td>\n");
      out.write("            ");
out.println(invoiceId);
      out.write("\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            Date\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            ");
out.println(invoice.getDate());
      out.write("\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            Customer\n");
      out.write("        </td>\n");
      out.write("        <td>\n");
      out.write("            ");
out.println(invoice.getCustomer());
      out.write("\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            Total\n");
      out.write("        </td>\n");
      out.write("        <td>\n");
      out.write("            ");
out.println(invoice.getTotal());
      out.write("\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            Discount\n");
      out.write("        </td>\n");
      out.write("        <td>\n");
      out.write("            ");
out.println(invoice.getDiscount());
      out.write("\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            Net Total\n");
      out.write("        </td>\n");
      out.write("        <td>\n");
      out.write("            ");
out.println(invoice.getNetTotal());
      out.write("\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("        <td>\n");
      out.write("            Sold by\n");
      out.write("        </td>\n");
      out.write("        <td>\n");
      out.write("            ");
out.println(invoice.getUser());
      out.write("\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("<h2>Orderlines</h2>\n");
      out.write("<table id=\"orderlines\" class=\"table table-hover\">\n");
      out.write("    <thead>\n");
      out.write("    <th>Product</th>\n");
      out.write("    <th>Qty</th>\n");
      out.write("    </thead>\n");
      out.write("    ");

        ArrayList<Orderline> orderlines = invoice.getOrderlines();
        for(Orderline t : orderlines){
            out.println(String.format("<tr><td>%s</td><td>%d</td></tr>", t.getProduct(), t.getQuantity()));
        }
    
      out.write("\n");
      out.write("</table>\n");
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
