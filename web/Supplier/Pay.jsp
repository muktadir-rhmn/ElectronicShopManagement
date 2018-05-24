<%@ page import="Models.Supplier" %>
<%@ page import="java.util.ArrayList" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Pay Supplier" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    ArrayList<Supplier> suppliers = Supplier.getSuppliers();
%>
<script>
    var suppliers = [

        <%
            for ( Supplier s: suppliers) {
                out.println(String.format("{ label: '%s', value: '%s' },", s.getSupplierId() + " " + s.getName() + " "+s.getMobile() + " " +s.getAddress(), s.getSupplierId()));
            }
        %>
    ];

    $(function () {
        $("#supplierId").autocomplete({
            source : suppliers
        });
    })
</script>

<form action="Pay2.jsp" method="get">
    <table>
        <tr>
            <td>
                Supplier
            </td>
            <td>
                <input type="text" name="supplierId" id="supplierId" class="form-control"/>
            </td>
        </tr>
        <tr>
            <td>

            </td>
            <td>
                <input type="submit" value="Go" class="btn btn-primary"/>
            </td>
        </tr>
    </table>
</form>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>