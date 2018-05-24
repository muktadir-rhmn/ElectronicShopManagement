package org.apache.jsp.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Models.Product;
import java.util.ArrayList;

public final class SellProduct_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../Resources/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("title", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Buy Product", request.getCharacterEncoding()), out, false);
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');

    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    ArrayList<String> categories = Product.getCategories();
    ArrayList<String> companies = Product.getCompanies();
    ArrayList<Product> products = Product.getProducts();

      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    var products = []; //to store the stockQty, company, costPrice, sellingPrice of a product.\r\n");
      out.write("    var models = []; //an array that stores the models of a category\r\n");
      out.write("    var orderlines = []; //the orderlines currently added to the table.\r\n");
      out.write("    var orderlinePrice = [];\r\n");
      out.write("    var totalPrice = 0;\r\n");
      out.write("    ");

        for (String cat : categories) {
            out.println(String.format("models[\"%s\"] = [];", cat));
            out.println(String.format("products[\"%s\"] = [];", cat));
        }

       for (Product p : products) {
           out.println(String.format("models[\"%s\"].push(\"%s\");", p.getCategory(), p.getModel()));
           out.print(String.format(
                   "var t = {productId: %s, company: \"%s\", extraInfo: \"%s\", costPrice: %s, sellingPrice: %s, stockQty:%s};",
                             p.getProductId(), p.getCompany(), p.getExtraInfo(), p.getCostPrice(), p.getSellingPrice(), p.getStockQty()
                   ));
           out.println(String.format("products[\"%s\"][\"%s\"] = t;", p.getCategory(), p.getModel()));
       }
    
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    $(\"document\").ready(function () {\r\n");
      out.write("        //models changing whenever category changing\r\n");
      out.write("        $(\"#category\").change(function () {\r\n");
      out.write("            var cat = $(\"#category\").val();\r\n");
      out.write("\r\n");
      out.write("            $(\"#nAvailable\").text(\"\");\r\n");
      out.write("            $(\"#costPrice\").text(\"\");\r\n");
      out.write("            $(\"#sellingPrice\").text(\"\");\r\n");
      out.write("            $(\"#model\").html(\"<option value='' disabled selected>Select category</option>\");\r\n");
      out.write("\r\n");
      out.write("            for(var i =0; i < models[cat].length; i++){\r\n");
      out.write("                var t = models[cat][i];\r\n");
      out.write("                $(\"#model\").append($(\"<option value=\"+ t +\"> \"+ t +\"</option>\"))\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        $(\"#model\").change(function () {\r\n");
      out.write("            var cat = $(\"#category\").val();\r\n");
      out.write("            var mod = $(\"#model\").val();\r\n");
      out.write("            $(\"#nAvailable\").text(products[cat][mod].stockQty);\r\n");
      out.write("            $(\"#costPrice\").text(products[cat][mod].costPrice);\r\n");
      out.write("            $(\"#sellingPrice\").text(products[cat][mod].sellingPrice);\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        $(\"#addOrderline\").click(function () {\r\n");
      out.write("            var category = $(\"#category\").val();\r\n");
      out.write("            var model = $(\"#model\").val();\r\n");
      out.write("            var qty = parseInt($(\"#quantity\").val());\r\n");
      out.write("            var p = products[category][model];\r\n");
      out.write("\r\n");
      out.write("            /*validity of qty & duplicate category-model checking code here*/\r\n");
      out.write("\r\n");
      out.write("            //add to \"orderlines\" that will be sent to the server\r\n");
      out.write("            var t = {productId: p.productId, quantity: qty};\r\n");
      out.write("            orderlines.push(t);\r\n");
      out.write("\r\n");
      out.write("            //add into the table\r\n");
      out.write("            /*<th>Product</th>\r\n");
      out.write("             <th>Rate</th>\r\n");
      out.write("             <th>Qty</th>\r\n");
      out.write("             <th>Price</th>\r\n");
      out.write("             <th>Action</th>*/\r\n");
      out.write("            var tr = $(\"<tr>\");\r\n");
      out.write("            tr.attr(\"id\", p.productId + \"\");\r\n");
      out.write("            tr.append( //product\r\n");
      out.write("                $(\"<td>\").text(p.company + \" \" + category + \" \" + model)\r\n");
      out.write("            ); //eg. Walton TV WI-121\r\n");
      out.write("            tr.append(//rate\r\n");
      out.write("                $(\"<td>\").text(p.sellingPrice)\r\n");
      out.write("            );\r\n");
      out.write("            tr.append(//Qty\r\n");
      out.write("                $(\"<td>\").text(qty)\r\n");
      out.write("            );\r\n");
      out.write("            tr.append(//price\r\n");
      out.write("                $(\"<td>\").text(qty * p.sellingPrice)\r\n");
      out.write("            );\r\n");
      out.write("            tr.append( //Action\r\n");
      out.write("                $(\"<td>\").html(\r\n");
      out.write("                    \"<a href='#' onclick='removeOrderline(\" + p.productId + \")'>\" +\r\n");
      out.write("                    \"<span class='glyphicon glyphicon-trash'></span>\" +\r\n");
      out.write("                    \"</a>\"\r\n");
      out.write("                )\r\n");
      out.write("            );\r\n");
      out.write("\r\n");
      out.write("            $(\"#trTotal\").before(tr);\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            //total text box update\r\n");
      out.write("            orderlinePrice[p.productId] = qty * p.sellingPrice;\r\n");
      out.write("            totalPrice = totalPrice + orderlinePrice[p.productId];\r\n");
      out.write("            $(\"#total\").val(totalPrice);\r\n");
      out.write("            var discount = parseInt($(\"#discount\").val());\r\n");
      out.write("            $(\"#netTotal\").val(totalPrice - (isNaN(discount)  ? 0:discount));\r\n");
      out.write("\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        $(\"#discount\").change(function () {\r\n");
      out.write("            var discount = parseInt($(\"#discount\").val());\r\n");
      out.write("            $(\"#netTotal\").val(totalPrice - (isNaN(discount)  ? 0:discount));\r\n");
      out.write("        })\r\n");
      out.write("        $(\"#paymentTypeInstallment\").click(function () {\r\n");
      out.write("            $(\".installmentDetails\").show(\"slow\");\r\n");
      out.write("        })\r\n");
      out.write("\r\n");
      out.write("        $(\"#paymentTypeOnetime\").click(function () {\r\n");
      out.write("            $(\".installmentDetails\").hide(\"slow\");\r\n");
      out.write("        })\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        $(\"#firstInstallmentDate\").datepicker(\r\n");
      out.write("            { dateFormat: \"dd-M-yy\" }\r\n");
      out.write("        )\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    function removeOrderline(productId) {\r\n");
      out.write("\r\n");
      out.write("        totalPrice = totalPrice - orderlinePrice[productId];\r\n");
      out.write("        $(\"#total\").val(totalPrice);\r\n");
      out.write("        var discount = parseInt($(\"#discount\").val());\r\n");
      out.write("        $(\"#netTotal\").val(totalPrice - (isNaN(discount)  ? 0:discount));\r\n");
      out.write("        $(\"#\" + productId).remove();\r\n");
      out.write("\r\n");
      out.write("        for(var i=0; i < orderlines.length ; i++){\r\n");
      out.write("            if(orderlines[i].productId == productId){\r\n");
      out.write("                orderlines = orderlines.splice(i, 1);\r\n");
      out.write("                return;\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<form action=\"BuyProduct.do\" method=\"post\">\r\n");
      out.write("    <table>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for=\"category\">Categories</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <select name=\"category\" id=\"category\" >\r\n");
      out.write("                    <option value=\"\" disabled selected>Select category</option>\r\n");
      out.write("                    ");

                        for(String s:categories){
                            out.print(String.format("<option value='%s'>%s</option>", s, s));
                        }
                    
      out.write("\r\n");
      out.write("                </select>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td style=\"padding-left:60px\">\r\n");
      out.write("                # Available\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <span id=\"nAvailable\"></span>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td >\r\n");
      out.write("                <label for='model'>Model</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <select name=\"model\" id=\"model\" >\r\n");
      out.write("                    <option value=\"\" disabled selected>Select Model</option>\r\n");
      out.write("\r\n");
      out.write("                </select>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td style=\"padding-left:60px\">\r\n");
      out.write("                Cost Price\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <span id=\"costPrice\"></span>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='quantity'>Quantity</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='quantity' id='quantity' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td style=\"padding-left:60px\">\r\n");
      out.write("                Selling Price\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <span id=\"sellingPrice\"></span>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <button type=\"button\" id=\"addOrderline\" class=\"btn btn-success btn-block\">\r\n");
      out.write("                    <span class=\"glyphicon glyphicon-plus\">Add</span>\r\n");
      out.write("                </button>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("    <br/>\r\n");
      out.write("    <br/>\r\n");
      out.write("    <h2>Products</h2>\r\n");
      out.write("    <table id=\"orderlines\" class=\"table table-hover\">\r\n");
      out.write("        <thead>\r\n");
      out.write("        <th>Product</th>\r\n");
      out.write("        <th>Rate</th>\r\n");
      out.write("        <th>Qty</th>\r\n");
      out.write("        <th>Price</th>\r\n");
      out.write("        <th>Action</th>\r\n");
      out.write("        </thead>\r\n");
      out.write("\r\n");
      out.write("        <tr id=\"trTotal\">\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td></td>\r\n");
      out.write("            <td style=\"background: greenyellow\">Total</td>\r\n");
      out.write("            <td style=\"background: greenyellow\">\r\n");
      out.write("                <input type=\"text\" name=\"total\" id=\"total\" disabled=\"true\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("\r\n");
      out.write("        </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("    <br/>\r\n");
      out.write("    <br/>\r\n");
      out.write("    <h2>Payment</h2>\r\n");
      out.write("    <table>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='discount'>Discount</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='discount' id='discount' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='netTotal'>Net Total</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='netTotal' id='netTotal' class='form-control' disabled=\"true\"/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("                Payment Type\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"radio\" name=\"paymentType\" id=\"paymentTypeOnetime\" value=\"onetime\"/><label for=\"paymentTypeOnetime\">Onetime</label>\r\n");
      out.write("                <input type=\"radio\" name=\"paymentType\" id=\"paymentTypeInstallment\" value=\"installment\"/><label for=\"paymentTypeInstallment\">Installments</label>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <tr class=\"installmentDetails\">\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='nInstallments'># Installments</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='nInstallments' id='nInstallments' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr class=\"installmentDetails\">\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='firstInstallmentDate'>First Instal. Date</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='firstInstallmentDate' id='firstInstallmentDate' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr class=\"installmentDetails\">\r\n");
      out.write("            <td>\r\n");
      out.write("                <label for='interval'>Interval</label>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='text' name='interval' id='interval' class='form-control'/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type='submit' class='btn btn-success btn-block' value='Buy Products' />    <td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\r\n");
      out.write("    </table>\r\n");
      out.write("</form>\r\n");
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
