<jsp:include page="Resources/header.jsp">
  <jsp:param name="title" value="Home" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    String msg = (String) session.getAttribute("msg");
    if(msg != null){
        out.print(
                "<div class='alert-success'>" + msg +"</div>"
        );
        session.setAttribute("msg", null);
    }
%>

<%-- ******************************************************** --%>
<jsp:include page="Resources/footer.jsp"/>