<%@ page import="Models.User" %><%--
    Author : MUKTADIR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../Resources/jQuery/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="../Resources/Bootstrap/css/bootstrap.min.css">
    <script src="../Resources/Bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../Resources/jQueryUI/jquery-ui.min.css">
    <script src="../Resources/jQueryUI/jquery-ui.js"></script>

    <style>
        td { 
            padding: 10px;
        }
        body{
            padding: 20px;
        }
    </style>
    <title> ${param.title} - Electronic Shop Management System</title>
</head>
<body>
<%
    if(session.getAttribute("user") != null){
        %>

<center>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">

            <ul class="nav navbar-nav">
                <li class="active"><a href="../index.jsp">Home</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="../#">Customer
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../Customer/SellProduct.jsp">Sell Product</a></li>
                        <li><a href="../Customer/AddCustomer.jsp">Add Customer</a></li>
                        <li><a href="../Customer/SellingLog.jsp">SellingLog</a></li>
                        <li><a href="../Customer/InstallmentSchedules.jsp">Installment Schedule</a></li>
                        <li><a href="../Customer/InstallmentLog.jsp">Installment Log</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="../#">Supplier
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../Supplier/BuyProduct.jsp">Buy Product</a></li>
                        <li><a href="../Supplier/AddSupplier.jsp">Add Supplier</a></li>
                        <li><a href="../Supplier/BuyingLog.jsp">Buying Log</a></li>
                        <li><a href="../Supplier/Pay.jsp">Pay Supplier</a></li>
                        <li><a href="../Supplier/PaymentLog.jsp">Payment Log</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="../#">Administration
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../Administration/AddProduct.jsp">Add Product</a></li>
                        <li><a href="../Administration/AddEmployee.jsp">Add Employee</a></li>
                        <li><a href="../Administration/ChangeProfile.do?mobile=<%out.print(((User)session.getAttribute("user")).getMobile());%>">Edit Profile</a></li>
                        <li><a href="../Administration/ChangeEmployeeProfile.jsp">Edit Employee Profile</a></li>
                        <li><a href="../Administration/Report.jsp">Report</a></li>
                        <li><a href="../Administration/ViewStock.jsp">View Stock</a></li>
                    </ul>
                </li>
                <li><a href="../Administration/Logout.jsp">Log Out</a></li>
            </ul>
        </div>
    </nav>
<%
    }
    %>
    <h1>${param.title}</h1>
