<%@ page import="Models.Supplier" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Pay Supplier" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    String supplierId = request.getParameter("supplierId");
    if(supplierId == null){
        out.print("Provide supplier id....");
        return;
    }
    Supplier supplier = Supplier.getSupplier(supplierId);
%>

<form action="Pay.do" method="POST">
    <input type="hidden" name="supplierId" value="<%out.print(supplierId);%>"/>
    <table>
        <tr>
            <td>
                Supplier Id
            </td>
            <td>
                <% out.print(supplier.getSupplierId()); %>
            </td>
        </tr>
        <tr>
            <td>
                Supplier Name
            </td>
            <td>
                <% out.print(supplier.getName()); %>
            </td>
        </tr>
        <tr>
            <td>
                Address
            </td>
            <td>
                <% out.print(supplier.getAddress()); %>
            </td>
        </tr>
        <tr>
            <td>
                Total Debit
            </td>
            <td>
                <% out.print(supplier.getTotalDebit()); %>
            </td>
        </tr>
        <tr>
            <td>
                Paying Amount
            </td>
            <td>
                <input type="text" name="payingAmmount" class="form-control"/>
            </td>
        </tr><tr>
        <td>

        </td>
        <td>
            <input type="submit" value="Pay" class="btn btn-primary"/>
        </td>
    </tr>



    </table>
</form>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>