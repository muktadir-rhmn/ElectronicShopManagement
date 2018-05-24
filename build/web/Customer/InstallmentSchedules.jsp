<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.PaymentSchedule" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Installment Schedules" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    ArrayList<PaymentSchedule> schedules;
    String month = request.getParameter("month");
    String year = request.getParameter("year");
    if(month != null && year != null){
        schedules = PaymentSchedule.getSchedules(month, year);
    }
    else schedules = PaymentSchedule.getSchedules();
%>
<br>
<form action="InstallmentSchedules.jsp" method="get">
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
<br>
<br><br>
<table class="table table-bordered">
    <tr  class="active">
        <th>Id</th>
        <th>InvoiceId</th>
        <th>Date</th>
        <th>Amount</th>
        <th>Customer</th>
        <th>Action</th>
    </tr>
    <%
        for(PaymentSchedule t : schedules){
    %>
    <tr class="<%if (t.isPaid()) out.print("success");%>">
        <td><%  out.print(t.getScheduleId());  %></td>
        <td><% out.print(String.format(
                "<a href='../Administration/SellingInvoiceDetails.jsp?invoiceId=%s'>%s</a>"
                , t.getInvoiceId(), t.getInvoiceId()
        )); %></td>
        <td><%  out.print(t.getDate());  %></td>
        <td><%  out.print(t.getAmount());  %></td>

        <td><%  out.print(t.getCustomerName() + "(" + t.getCustomerMobile() + ")");  %></td>
        <td>
            <%
                if (!t.isPaid())out.print(String.format(
                        "<a href='PayInstallment.jsp?scheduleId=%s' class='btn btn-primary'>Pay</a>",
                        t.getScheduleId()
                ));
                else out.print("<span class='btn btn-success'>Paid</span>");

            %>

        </td>
    </tr>
    <%
        }
    %>
</table>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>