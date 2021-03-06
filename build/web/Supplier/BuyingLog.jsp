<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.SupplyInvoice" %>
<%@ page import="Models.User" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Buying Log" />
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


    String month = request.getParameter("month");
    String year = request.getParameter("year");

    ArrayList<SupplyInvoice> invoices;
    if(month != null && year != null) invoices = SupplyInvoice.getInvoices(month, year);
    else invoices = SupplyInvoice.getInvoices(null, null);

%>

<br>
<form action="BuyingLog.jsp" method="get">
    <select name="month">
        <option value="01"> January</option>
        <option value="02"> February</option>
        <option value="03"> March</option>
        <option value="04"> April</option>
        <option value="05"> May</option>
        <option value="06"> June</option>
        <option value="07"> July</option>
        <option value="08"> August</option>
        <option value="09"> September</option>
        <option value="10"> October</option>
        <option value="11"> November</option>
        <option value="12"> December</option>
    </select>
    <select name="year">
        <option value="2016">2016</option>
        <option value="2017">2017 </option>
        <option value="2018">2018 </option>

    </select>
    <input type="submit" value="Go" class="btn btn-primary"/>
</form>
<table  class="table table-bordered">
    <tr  class="active">
        <th>Invoice Id</th>
        <th>Date</th>
        <th>Supplier</th>
        <th>Total</th>
        <th>Discount</th>
        <td>NetTotal</td>
        <th>Xport. Cost</th>
        <th>By</th>
    </tr>
    <%
        for(SupplyInvoice i : invoices){

    %>
    <tr>
        <td><%out.print(String.format(
                "<a href='../Administration/SupplyInvoiceDetails.jsp?invoiceId=%s' >%s</a>",
                i.getInvoiceId(), i.getInvoiceId()
        ));%></td>
        <td><%out.print(i.getDate());%></td>
        <td><%out.print(i.getSupplierName());%></td>
        <td><%out.print(i.getTotal());%></td>
        <td><%out.print(i.getDiscount());%></td>
        <td><%out.print(i.getNetTotal());%></td>
        <td><%out.print(i.getTransportationCost());%></td>
        <td><%out.print(i.getUserName());%></td>
    </tr>
    <%
        }
    %>
</table>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>