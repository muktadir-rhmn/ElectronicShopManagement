<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Add Supplier" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
%>
<%
    String msg = (String) session.getAttribute("msg");
    if(msg != null){
        out.print(
                "<div class='alert alert-danger'>" + msg +"</div>"
        );
        session.removeAttribute("msg");
    }
%>

<form action="AddSupplier.do" method="post">
    <table>
        <tr>
            <td>
                <label for='name'>Name</label>
            </td>
            <td>
                <input type='text' name='name' id='name' class='form-control'/>
            </td>
        </tr>
        <tr>
            <td>
                <label for='proprietor'>Proprietor</label>
            </td>
            <td>
                <input type='text' name='proprietor' id='proprietor' class='form-control'/>
            </td>
        </tr>
        <tr>
            <td>
                <label for='address'>Address</label>
            </td>
            <td>
                <input type='text' name='address' id='address' class='form-control'/>
            </td>
        </tr>
        <tr>
            <td>
                <label for='mobile'>Mobile</label>
            </td>
            <td>
                <input type='text' name='mobile' id='mobile' class='form-control'/>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <input type='submit' class='btn btn-success btn-block' value='Add Supplier' />    <td>
        </tr>

    </table>
</form>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>