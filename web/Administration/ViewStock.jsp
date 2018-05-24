<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Product" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="View Stock" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    ArrayList<Product> products = Product.getProducts();
    String curCat = "";
%>

<table class="table table-bordered">

    <%
        for (Product product:products){
            if(! product.getCategory().equals(curCat)){
                curCat = product.getCategory();
                out.print(String.format("<tr  class='alert-success'><td  colspan=5 style='text-align:center;'>%s</td></tr>", curCat));
                %>
    <tr class="active">
        <td>Id</td>
        <td>Name</td>
        <td>Stock QTY</td>
        <td>Cost Price</td>
        <td>Selling Price</td>
    </tr>
    <%
            }
    %>
        <tr>
            <td><% out.print(product.getProductId()); %></td>
            <td><% out.print(product.getCompany() + " " + product.getModel() ); %></td>
            <td><% out.print(product.getStockQty()); %></td>
            <td><% out.print(product.getCostPrice()); %></td>
            <td><% out.print(product.getSellingPrice()); %></td>
        </tr>
    <%
        }
    %>
</table>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>