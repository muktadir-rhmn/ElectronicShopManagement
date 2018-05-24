<%@ page import="Models.*" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Monthly Report" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    User user = (User) session.getAttribute("user");
    if(user == null){
        out.print("You must login to see this page...");
        return;
    }
    if(!user.isAdmin()){
        session.setAttribute("msg", "You are not authorized to access this application...");
        response.sendRedirect("../index.jsp");
    }

%>
<%

    String month = request.getParameter("month");
    String year = request.getParameter("year");

    int sale = 0;
    int profit = 0;
    int withdrawn = 0;
    int bought = 0;
    int transportationCost = 0;
    int paidToSuppliers = 0;
    if(month != null || year != null){
        int t[] = new int[2];
        t = SellingInvoice.getTotalSaleProfit(month, year);
        sale = t[0];
        profit = t[1];
        withdrawn = Installment.getTotalWithdrawn(month, year);
        t = SupplyInvoice.getTotalBoughtTransportCost(month, year);
        bought = t[0];
        transportationCost = t[1];
        paidToSuppliers = SupplyPayment.totalPaidToSuppliers(month,year);
    }
%>
<br>
<form action="Report.jsp" method="get">
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

<h2><%if(month != null )out.print(month +" - "+year); else out.print("Select month year");%></h2>
<table class="table table-bordered">
    <tr>
        <td>
            Sale
        </td>
        <td>
            <%out.print(sale);%>
        </td>
    </tr>
    <tr>
        <td>
            Profit
        </td>
        <td>
            <%out.print(profit);%>
        </td>
    </tr>
    <tr>
        <td>
            Installment Withdrawn
        </td>
        <td>
            <%out.print(withdrawn);%>
        </td>
    </tr>
    <tr>
        <td>
            Bought
        </td>
        <td>
            <%out.print(bought);%>
        </td>
    </tr>
    <tr>
        <td>
            Transportation Cost
        </td>
        <td>
            <%out.print(transportationCost);%>
        </td>
    </tr>
    <tr>
        <td>
            Paid to Suppliers
        </td>
        <td>
            <%out.print(paidToSuppliers);%>
        </td>
    </tr>
</table>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>