<%@ page import="Models.Product" %>
<%@ page import="java.util.ArrayList" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Add Product" />
</jsp:include>
<%-- ******************************************************** --%>
<script>
    $(document).ready(function() {
        $('#addCategory').click(function () {
            var cat = prompt("Please enter category", "");
            if (cat != null && cat != "") {
                $('#category').append($('<option>', {
                    value: cat,
                    text: cat
                }));
            }
        });

        $('#addCompany').click(function () {
            var com = prompt("Please enter company", "");
            if (com != null && com != "") {
                $('#company').append($('<option>', {
                    value: com,
                    text: com
                }));
            }
        });
    });

</script>
<%
    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    ArrayList<String> categories = Product.getCategories();
    ArrayList<String> companies = Product.getCompanies();
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

<form action="AddProduct.do" method="post">
    <table>
        <tr>
            <td>
                <label for="category">Categories</label>
            </td>
            <td>
                <select name="category" id="category" >
                    <option value="" disabled selected>Select category</option>
                    <%
                        for(String s:categories){
                            out.print(String.format("<option value='%s'>%s</option>", s, s));
                        }
                    %>
                </select>
                <button type="button" id="addCategory" class="btn btn-primary btn-sm">
                    <span class="glyphicon glyphicon-plus"></span>
                </button>
            </td>
        </tr>
        <tr>
            <td>
                <label for='model'>Model</label>
            </td>
            <td>
                <input type='text' name='model' id='model' class='form-control'/>
            </td>
        </tr>
        <tr>
            <td>
                Company
            </td>
            <td>
                <select name="company" id="company">
                    <option value="" disabled selected>Select Company</option>
                    <%
                        for(String s:companies){
                            out.print(String.format("<option value='%s'>%s</option>", s, s));
                        }
                    %>
                </select>
                <button type="button" id="addCompany" class="btn btn-primary btn-sm">
                    <span class="glyphicon glyphicon-plus"></span>
                </button>
            </td>
        </tr>
        <tr>
            <td>
                <label for='extraInfo'>Extra Info</label>
            </td>
            <td>
                <input type='text' name='extraInfo' id='extraInfo' class='form-control'/>
            </td>
        </tr>
        <tr>
            <td>
                <label for='costPrice'>Cost Price</label>
            </td>
            <td>
                <input type='text' name='costPrice' id='costPrice' class='form-control'/>
            </td>
        </tr>
        <tr>
            <td>
                <label for='sellingPrice'>Selling Price</label>
            </td>
            <td>
                <input type='text' name='sellingPrice' id='sellingPrice' class='form-control'/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Add Product" class="btn btn-primary btn-lg"/>
            </td>
        </tr>
    </table>
</form>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>