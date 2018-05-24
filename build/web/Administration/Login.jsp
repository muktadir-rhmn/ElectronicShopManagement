<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Login" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    String msg = (String) session.getAttribute("msg");
    if(msg != null){
        out.print(
                "<div class='alert alert-danger'>" + msg +"</div>"
        );
        session.removeAttribute("msg");
    }
%>

<form action="Login.do" method="post">
<table>
    <tr>
        <td>
            <label for="mobile" > Mobile:</label>
        </td>
        <td>
            <input type="text" id="mobile" name="mobile" class="form-control"/>
        </td>
    </tr>
    <tr>
        <td>
            <label for="password" > Password:</label>
        </td>
        <td>
            <input type="password" id="password" name="password" class="form-control"/>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <input type="submit" value="Login" class="btn btn-success btn-block"/>
        </td>
    </tr>
</table>
</form>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>