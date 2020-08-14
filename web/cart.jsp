<%-- 
    Document   : cart.jsp
    Created on : Jul 14, 2020, 3:33:22 PM
    Author     : nhatp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/shop-homepage.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="searchItem.jsp">Resource Management</a>
                <s:if test="%{#session.user.roleID != 1}">
                    <a href="cart.jsp" class="nav-link">Cart<s:if test="#session.cartSize">(<s:property value="#session.cartSize"/>)</s:if></a>
                </s:if>
                <div  id="navbarResponsive">
                    <a class="nav-link" href="LogoutAction">Logout</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <s:if test="%{#request.error != null}">
                <div class="alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong><s:property value="#request.error"/></strong> 
                </div>
            </s:if>
            <s:if test="%{#request.success != null}">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong><s:property value="#request.success"/></strong> 
                </div>
            </s:if>
            <form action="BookingAction" method="POST">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Item name</th>
                            <th>Booking date</th>
                            <th>Return date</th>
                            <th>Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.cart.cart" var="cart">
                            <tr>
                                <td><s:property value="#cart.value.itemName"/></td>
                                <td><s:property value="#cart.value.bookingDate"/></td>
                                <td><s:property value="#cart.value.returnDate"/></td>
                                <td><s:property value="#cart.value.quantity"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <input type="submit" class="btn btn-outline-primary" value="Send request"/>
            </form>
        </div>
    </body>
    <script src="jquery/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</html>
