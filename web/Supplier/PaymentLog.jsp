<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.SupplyPayment" %>
<%@ page import="Models.User" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Payment Log" />
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

%>
<%


    String month = request.getParameter("month");
    String year = request.getParameter("year");

    ArrayList<SupplyPayment> payments;
    if(month != null && year != null) payments = SupplyPayment.getSupplyPayments(month, year);
    else payments = SupplyPayment.getSupplyPayments(null, null);
%>


<br>
<form action="PaymentLog.jsp" method="get">
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

<h2><%if(month != null )out.print(month +  " - " + year);%></h2>
<table class="table table-bordered">
    <tr  class="active">
        <th>Payment Id</th>
        <th>Date</th>
        <th>Ammount</th>
        <th>Supplier</th>
        <th>By</th>
    </tr>
    <%
        for (SupplyPayment p : payments){
    %>
        <tr>
            <td><% out.print(p.getPaymentId()); %></td>
            <td><% out.print(p.getDate()); %></td>
            <td><% out.print(p.getAmmount()); %></td>
            <td><% out.print(p.getSupplierName()); %></td>
            <td><% out.print(p.getUserName()); %></td>
        </tr>
    <%
        }
    %>
</table>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>