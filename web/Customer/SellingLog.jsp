<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.SupplyInvoice" %>
<%@ page import="Models.SellingInvoice" %>
<%@ page import="Models.User" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Selling Log" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    User user = (User) session.getAttribute("user");
    if(user == null){
        out.print("You must login to see this page...");
        return;
    }
    if(!user.isAdmin()){
        session.setAttribute("msg", "You are not authorized to access this page...");
        response.sendRedirect("../index.jsp");
    }


    ArrayList<SellingInvoice> invoices = SellingInvoice.getInvoices();

%>

<table  class="table table-bordered">
    <tr  class="active">
    <th>Invoice Id</th>
    <th>Date</th>
    <th>Supplier</th>
    <th>Total</th>
    <th>Discount</th>
    <td>NetTotal</td>
    <th>Profit</th>
    <th>By</th>
    </tr>
    <%
        for(SellingInvoice i : invoices){

    %>
    <tr>
        <td><%out.print(String.format(
                "<a href='../Administration/SellingInvoiceDetails.jsp?invoiceId=%s' >%s</a>",
                i.getInvoiceId(), i.getInvoiceId()
        ));%></td>
        <td><%out.print(i.getDate());%></td>
        <td><%out.print(i.getCustomerName());%></td>
        <td><%out.print(i.getTotal());%></td>
        <td><%out.print(i.getDiscount());%></td>
        <td><%out.print(i.getNetTotal());%></td>
        <td><%out.print(i.getProfit());%></td>
        <td><%out.print(i.getUserName());%></td>
    </tr>
    <%
        }
    %>
</table>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>