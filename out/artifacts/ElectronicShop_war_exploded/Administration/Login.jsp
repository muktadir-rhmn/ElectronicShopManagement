<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Login" />
</jsp:include>
<%-- ******************************************************** --%>
<% 
    Boolean hasFailedLogin = (Boolean) session.getAttribute("hasFailedLogin");
    if(hasFailedLogin != null && hasFailedLogin == true){
         session.setAttribute("hasFailedLogin", false);
        out.println(
                "<div class='alert alert-danger'>" +
                    "Please enter valid Mobile and Password." +
                    "</div>"
        );
    }
    if(session.getAttribute("user") != null){ //if the user is already logged in
        request.getRequestDispatcher("../Home.jsp").forward(request, response);
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