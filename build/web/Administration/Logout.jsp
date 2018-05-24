<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Log Out" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    session.removeAttribute("user");
%>
Successfully logged out...
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>