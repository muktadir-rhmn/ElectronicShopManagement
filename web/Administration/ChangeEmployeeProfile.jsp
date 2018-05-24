<%@ page import="Models.User" %>
<%@ page import="java.util.ArrayList" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Edit Employee Profile" />
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


    ArrayList<User> users =  User.getAllUsers();
%>

<script>
    var users = [

        <%
            for ( User s: users) {
                if (!s.getMobile().equals(user.getMobile()))
                out.println(String.format("{ label: '%s', value: '%s' },", s.getName() + " " + s.getAddress() + " "+s.getMobile() , s.getMobile()));
            }
        %>
    ];
    $(function () {
        $("#employeeMobile").autocomplete({
            source : users
        });


    })
</script>
<form action="ChangeProfile.do" method="get">
    <table>
        <tr>
            <td></td>
            <td>
                <input type="checkbox" name="removeAccess" id="removeAccess"/> <label for="removeAccess">Remove Access</label>
            </td>
        </tr>
        <tr>
            <td>
                Employee
            </td>
            <td>
                <input type="text" name="mobile" id="employeeMobile" class="form-control"/>
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