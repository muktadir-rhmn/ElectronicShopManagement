<%@ page import="Models.PaymentSchedule" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Pay Installment" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    String scheduleId = request.getParameter("scheduleId");
    if(scheduleId == null){
        out.print("You must give some scheduleId");
        return;
    }
    PaymentSchedule schedule = PaymentSchedule.getSchedule(scheduleId);
    if(schedule.isPaid()){
        out.print("The installment on " + schedule.getDate() + " by " + schedule.getCustomerName() +" is already paid");
        return;
    }

%>

<form action="PayInstallment.do" method="POST">
    <input type="hidden" name="scheduleId" value="<%out.print(scheduleId);%>"/>
    <table>
        <tr>
            <td>
                Schedule Id
            </td>
            <td>
                <% out.print(schedule.getScheduleId());%>
            </td>
        </tr>
        <tr>
            <td>
                Customer Name
            </td>
            <td>
                <% out.print(schedule.getCustomerName());%>
            </td>
        </tr>
        <tr>
            <td>
                Customer Mobile
            </td>
            <td>
                <% out.print(schedule.getCustomerMobile());%>
            </td>
        </tr>
        <tr>
            <td>
                Expected Date
            </td>
            <td>
                <% out.print(schedule.getDate());%>
            </td>
        </tr>
        <tr>
            <td>
                Amount
            </td>
            <td>
                <% out.print(schedule.getAmount());%>
            </td>
        </tr>
        <tr>
            <td>

            </td>
            <td>
                <input type="submit" value="Confirm Payment" class="btn btn-primary"/>
            </td>
        </tr>
    </table>
</form>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>