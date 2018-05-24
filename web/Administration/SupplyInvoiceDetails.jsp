
<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Orderline" %>
<%@ page import="Models.SupplyInvoice" %>
<%@ page import="Models.SellingInvoice" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Invoice Details" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    //url : *.jsp?invoiceId=123
    String invoiceId = request.getParameter("invoiceId");
    SupplyInvoice invoice = SupplyInvoice.getInvoice(invoiceId);

%>

<table>
    <tr>
        <td>Invoice Id</td>
        <td>
            <%out.println(invoiceId);%>
        </td>
    </tr>
    <tr>
        <td>
            Date
        </td>

        <td>
            <%out.println(invoice.getDate());%>
        </td>
    </tr>
    <tr>
        <td>
            Supplier
        </td>
        <td>
            <%out.println(invoice.getSupplierName());%>
        </td>
    </tr>
    <tr>
        <td>
            Total
        </td>
        <td>
            <%out.println(invoice.getTotal());%>
        </td>
    </tr>
    <tr>
        <td>
            Discount
        </td>
        <td>
            <%out.println(invoice.getDiscount());%>
        </td>
    </tr>
    <tr>
        <td>
            Net Total
        </td>
        <td>
            <%out.println(invoice.getNetTotal());%>
        </td>
    </tr>
    <tr>
        <td>
            Bought by
        </td>
        <td>
            <%out.println(invoice.getUserName() + "(" + invoice.getUserMobile() + ")");%>
        </td>
    </tr>
</table>

<h2>Orderlines</h2>
<table id="orderlines" class="table table-hover">
    <thead>
    <th>Product</th>
    <th>Qty</th>
    </thead>
    <%
        ArrayList<Orderline> orderlines = invoice.getOrderlines();
        for(Orderline t : orderlines){
            out.println(String.format("<tr><td>%s</td><td>%d</td></tr>", t.getProduct(), t.getQuantity()));
        }
    %>
</table>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>