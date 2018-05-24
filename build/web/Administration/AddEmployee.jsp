<%@ page import="Models.User" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Add Employee" />
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
<form action="./AddEmployee.do" method="post">
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
            <label for='father'>Father</label>
        </td>
        <td>
            <input type='text' name='father' id='father' class='form-control'/>
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
            <label for='password'>Password</label>
        </td>
        <td>
            <input type='password' name='password' id='password' class='form-control'/>
        </td>
    </tr>
    <tr>
        <td>
            <label for='salary'>Salary</label>
        </td>
        <td>
            <input type='text' name='salary' id='salary' class='form-control'/>
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
        </td>
        <td>
            <input type='submit' class='btn btn-success btn-block' value='Add Employee' />    <td>
    </tr>

    

</table>
</form>


<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>