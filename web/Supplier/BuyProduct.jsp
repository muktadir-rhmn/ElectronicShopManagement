<%@ page import="Models.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Customer" %>
<%@ page import="Models.Supplier" %>
<jsp:include page="../Resources/header.jsp">
    <jsp:param name="title" value="Buy Product" />
</jsp:include>
<%-- ******************************************************** --%>
<%
    if(session.getAttribute("user") == null){
        out.print("You must login to see this page...");
        return;
    }
    ArrayList<String> categories = Product.getCategories();
    ArrayList<Product> products = Product.getProducts();
    ArrayList<Supplier> suppliers = Supplier.getSuppliers();
%>
<script>
    var products = []; //to store the stockQty, company, costPrice, sellingPrice of a product.
    var models = []; //an array that stores the models of a category

    var orderlines = []; //the orderlines currently added to the table.
    var orderlinePrice = [];
    var totalPrice = 0; //total price of the orderline elements.

    var suppliers = [

        <%
            for ( Supplier s: suppliers) {
                out.println(String.format("{ label: '%s', value: '%s' },", s.getSupplierId() + " " + s.getName() + " "+s.getMobile() + " " +s.getAddress(), s.getSupplierId()));
            }
        %>
    ];
    <%
        for (String cat : categories) {
            out.println(String.format("models[\"%s\"] = [];", cat));
            out.println(String.format("products[\"%s\"] = [];", cat));
        }

       for (Product p : products) {
           out.println(String.format("models[\"%s\"].push(\"%s\");", p.getCategory(), p.getModel()));
           out.print(String.format(
                   "var t = {productId: %s, company: \"%s\", extraInfo: \"%s\", costPrice: %s, sellingPrice: %s, stockQty:%s};",
                             p.getProductId(), p.getCompany(), p.getExtraInfo(), p.getCostPrice(), p.getSellingPrice(), p.getStockQty()
                   ));
           out.println(String.format("products[\"%s\"][\"%s\"] = t;", p.getCategory(), p.getModel()));
       }
    %>


    $("document").ready(function () {
        //models changing whenever category changing
        $("#category").change(function () {
            var cat = $("#category").val();

            $("#nAvailable").text("");
            $("#costPrice").text("");
            $("#sellingPrice").text("");
            $("#model").html("<option value='' disabled selected>Select category</option>");

            for(var i =0; i < models[cat].length; i++){
                var t = models[cat][i];
                $("#model").append($("<option value="+ t +"> "+ t +"</option>"))
            }

        });

        $("#model").change(function () {
            var cat = $("#category").val();
            var mod = $("#model").val();


            $("#nAvailable").text(products[cat][mod].stockQty);
            $("#costPrice").text(products[cat][mod].costPrice);
            $("#sellingPrice").text(products[cat][mod].sellingPrice);
        });

        $("#addOrderline").click(function () {
            var category = $("#category").val();
            var model = $("#model").val();
            var qty = parseInt($("#quantity").val());


            /*validity of qty & duplicate category-model checking code here*/
            if(isNaN(qty)){
                alert("Please select valid quantity");
                return;
            }
            if(category == null){
                alert("Please select valid category");
                return;
            }
            if(model == null){
                alert("Please select valid model");
                return;
            }
            var p = products[category][model];
            

            //add to "orderlines" that will be sent to the server

            var t = {"productId": p.productId, "quantity": qty};
            orderlines.push(t);

            //add into the table
            /*<th>Product</th>
             <th>Rate</th>
             <th>Qty</th>
             <th>Price</th>
             <th>Action</th>*/
            var tr = $("<tr>");
            tr.attr("id", p.productId + "");
            tr.append( //product
                $("<td>").text(p.company + " " + category + " " + model)
            ); //eg. Walton TV WI-121
            tr.append(//rate
                $("<td>").text(p.sellingPrice)
            );
            tr.append(//Qty
                $("<td>").text(qty)
            );
            tr.append(//price
                $("<td>").text(qty * p.sellingPrice)
            );
            tr.append( //Action
                $("<td>").html(
                    "<a href='#' onclick='removeOrderline(" + p.productId + ")'>" +
                    "<span class='glyphicon glyphicon-trash'></span>" +
                    "</a>"
                )
            );

            $("#trTotal").before(tr);


            //total text box update
            orderlinePrice[p.productId] = qty * p.sellingPrice;
            totalPrice = totalPrice + orderlinePrice[p.productId];
            $("#total").val(totalPrice);
            var discount = parseInt($("#discount").val());
            $("#netTotal").val(totalPrice - (isNaN(discount)  ? 0:discount));
        });

        $("#discount").change(function () {
            var discount = parseInt($("#discount").val());
            $("#netTotal").val(totalPrice - (isNaN(discount)  ? 0:discount));
        })


        $("#supplierId").autocomplete({
            source : suppliers
        });

        $("#invoiceForm").submit(function () {
            var jsonCode = JSON.stringify(orderlines);

            var v = $("<input>").attr({
                "type": "hidden",
                "name": "orderlines",
                "value": jsonCode
            });
            $("#invoiceForm").append(v);

            v = $("<input>").attr({
                "type": "hidden",
                "name": "total",
                "value": totalPrice
            });
            $("#invoiceForm").append(v);

        })
    });

    function removeOrderline(productId) {
        totalPrice = totalPrice - orderlinePrice[productId];
        $("#total").val(totalPrice);
        var discount = parseInt($("#discount").val());
        $("#netTotal").val(totalPrice - (isNaN(discount)  ? 0:discount));
        $("#" + productId).remove();

        for(var i=0; i < orderlines.length ; i++){
            if(orderlines[i].productId == productId){
                orderlines = orderlines.splice(i, 1);
                return;
            }
        }
    }
</script>

<%
    String msg = (String) session.getAttribute("msg");
    if(msg != null){
        out.print(
                "<div class='alert alert-success'>" + msg +"</div>"
        );
        session.removeAttribute("msg");
    }
%>

<form id="invoiceForm" action="BuyProduct.do" method="post">
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
            </td>
            <td style="padding-left:60px">
                # Available
            </td>
            <td>
                <span id="nAvailable"></span>
            </td>
        </tr>
        <tr>
            <td >
                <label for='model'>Model</label>
            </td>
            <td>
                <select name="model" id="model" >
                    <option value="" disabled selected>Select Model</option>

                </select>
            </td>
            <td style="padding-left:60px">
                Cost Price
            </td>
            <td>
                <span id="costPrice"></span>
            </td>
        </tr>
        <tr>
            <td>
                <label for='quantity'>Quantity</label>
            </td>
            <td>
                <input type='text' name='quantity' id='quantity' class='form-control'/>
            </td>
            <td style="padding-left:60px">
                Selling Price
            </td>
            <td>
                <span id="sellingPrice"></span>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="button" id="addOrderline" class="btn btn-success btn-block">
                    <span class="glyphicon glyphicon-plus">Add</span>
                </button>
            </td>
        </tr>
    </table>
    <br/>
    <br/>
    <h2>Products</h2>
    <table id="orderlines" class="table table-hover">
        <thead>
        <th>Product</th>
        <th>Rate</th>
        <th>Qty</th>
        <th>Price</th>
        <th>Action</th>
        </thead>

        <tr id="trTotal">
            <td></td>
            <td></td>
            <td style="background: greenyellow">Total</td>
            <td style="background: greenyellow">
                <input type="text" name="total" id="total" disabled="true"/>
            </td>

        </tr>
    </table>
    <br/>
    <br/>
    <h2>Payment</h2>
    <table>
        <tr>
            <td>
                <label for='supplierId'>Supplier</label>
            </td>
            <td>
                <input type='text' name='supplierId' id='supplierId' class='form-control'/>
            </td>
        </tr>
        <tr>
            <td>
                <label for='discount'>Discount</label>
            </td>
            <td>
                <input type='text' name='discount' id='discount' class='form-control'/>
            </td>
        </tr>
        <tr>
            <td>
                <label for='netTotal'>Net Total</label>
            </td>
            <td>
                <input type='text' name='netTotal' id='netTotal' class='form-control' disabled="true"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for='transportationCost'>Xportation cost</label>
            </td>
            <td>
                <input type='text' name='transportationCost' id='transportationCost' class='form-control' />
            </td>
        </tr>


        <tr>
            <td>
            </td>
            <td>
                <input type='submit' class='btn btn-success btn-block' value='Sell Products' />    <td>
        </tr>

    </table>
</form>
<%-- ******************************************************** --%>
<jsp:include page="../Resources/footer.jsp"/>