<%@ page import="Models.User" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Edit Profile" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    User userToChangeProfile  =(User)session.getAttribute("userToChangeProfile");
    if(userToChangeProfile == null){
        out.print("No user selected.");
        return;
    }
    User user = (User)session.getAttribute("user");

%>
<form action="UpdateProfile.do" method="post">
    <table>
        <tr>
            <td>
                Name
            </td>
            <td>
               <% out.println(userToChangeProfile.getName());%>
            </td>
        </tr>
        <tr>
            <td>
                Father
            </td>
            <td>
                <% out.print(userToChangeProfile.getFather()); %>
            </td>
        </tr>
        <tr>
            <td>
                Mobile
            </td>
            <td>
                <%out.print(userToChangeProfile.getMobile()); %>
            </td>
        </tr>
        <tr>
            <td>
                <label for='password'>Password</label>
            </td>
            <td>
                <input type='password' name='password' id='password' class='form-control' value="<%out.print(userToChangeProfile.getPassword());%>"/>
            </td>
        </tr>
        <%
            if(user.isAdmin()) { //only admin can change the salary
                System.out.println("Admin");

        %>
        <tr>
            <td>
                <label for='salary'>Salary</label>
            </td>
            <td>
                <input type='text' name='salary' id='salary' class='form-control' value="<%out.print(userToChangeProfile.getSalary());%>"/>
            </td>
        </tr>
        <%
            }
        %>


        <tr>
            <td>
                <label for='address'>Address</label>
            </td>
            <td>
                <input type='text' name='address' id='address' class='form-control' value="<%out.print(userToChangeProfile.getAddress());%>"/>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <input type='submit' class='btn btn-success btn-block' value='Update Profile' />    <td>
        </tr>



    </table>
</form>


<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>