<%-- 
    Document   : manager.jsp
    Created on : Jul 8, 2020, 1:26:57 PM
    Author     : nhatp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/shop-homepage.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="#">Resource Management</a>
                <s:if test="%{#session.user.roleID == 1}">
                    <a href="manageRequest.jsp" class="nav-link">Manage Request</a>
                </s:if>
                <s:if test="%{#session.user.roleID != 1}">
                    <a href="cart.jsp" class="nav-link">Cart<s:if test="#session.cartSize">(<s:property value="#session.cartSize"/>)</s:if></a>
                    <a href="requestHistory.jsp" class="nav-link">Request history</a>
                </s:if>
                <div  id="navbarResponsive">
                    <a class="nav-link" href="LogoutAction">Logout</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <form action="SearchItemAction" method="POST">
                <div class="form-group row" style="margin-top: 100px">
                    <div class="col-6">
                        <label>Search by category</label>
                        <select name="categorySearch" class="form-control">
                            <s:iterator value="#session.category" var="tag">
                                <option value="<s:property value="#tag"/>" <s:if test="#tag eq #request.categorySearch">selected="selected"</s:if>><s:property value="#tag"/></option>
                            </s:iterator>
                        </select></br>
                        <label>Search by name</label>
                        <input type="text" class="form-control" name="nameSearch" value="<s:property value="#request.nameSearch"/>"/></br>
                        <input type="submit" class="btn btn-primary" value="Search"/>
                    </div>
                    <div class="col-6">
                        <label>From date</label>
                        <input type="date" class="form-control" name="fromDate" value="<s:property value="#request.fromDate"/>" required="true"/></br>
                        <label>To date</label>
                        <input type="date" class="form-control" name="toDate" value="<s:property value="#request.toDate"/>" required="true"/></br>
                        <input type="hidden" name="page" value="1"/>
                        <input type="hidden" name="roleID" value="<s:property value="%{#session.user.roleID}"/>"/>
                    </div>
                </div>
            </form>
            <s:if test="# request.resourceList">
                <div class="form-group" style="margin-top: 100px">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Item name</th>
                                <th>Color</th>
                                <th>Category</th>
                                <th>Quantity</th>
                                <th>Available</th>
                                <th>Request booking</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="%{#request.resourceList}" status="counter">
                                <tr>
                                    <td><s:property value="#counter.count"></s:property></td>
                                    <td><s:property value="itemName"></s:property></td>
                                    <td><s:property value="color"></s:property></td>
                                    <td><s:property value="category"></s:property></td>
                                    <td><s:property value="quantity"></s:property></td>
                                    <td><s:property value="available"></s:property></td>
                                    <td><s:if test="#session.user.roleID == 1">
                                            Not available for this account!
                                        </s:if>
                                        <s:if test="%{#session.user.roleID != 1}">
                                            <s:if test="available != 0">
                                                <form action="AddToCartAction" method="POST">
                                                    <div class="row">
                                                        <div class="col-6">
                                                            <select name="amount" class="form-control">
                                                                <s:iterator begin="1" end="available" var="amo">
                                                                    <option value="<s:property value="#amo"/>"><s:property value="#amo"/></option>
                                                                </s:iterator>
                                                            </select>
                                                        </div>
                                                        <div class="col-6">
                                                            <input type="hidden" name="fromDate" value="<s:property value="#request.fromDate"/>"/>
                                                            <input type="hidden" name="toDate" value="<s:property value="#request.toDate"/>"/>
                                                            <input type="hidden" name="itemID" value="<s:property value="itemID"/>"/>
                                                            <input type="submit" class="btn btn-outline-primary" value="Add to cart">
                                                        </div>
                                                    </div>
                                                </form>
                                            </s:if>
                                            <s:if test="available == 0">
                                                Out of stock!
                                            </s:if>
                                        </s:if></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
            </s:if>
            <s:if test="#request.resourceList">
                <form  action="SearchItemAction" method="POST">
                    <input type="hidden" name="roleID" value="<s:property value="%{#session.user.roleID}"/>"/>
                    <s:if test="#request.page != 1">
                        <div class="info-prev" style="float:  left">
                            <input type="hidden" name="page" value="<s:property value="#request.page"/>"/>
                            <input type="hidden" name="nameSearch" value="<s:property value="#request.nameSearch"/>"/>
                            <input type="hidden" name="categorySearch" value="<s:property value="#request.categorySearch"/>"/>
                            <input type="hidden" name="fromDate" value="<s:property value="#request.fromDate"/>"/>
                            <input type="hidden" name="toDate" value="<s:property value="#request.toDate"/>"/>
                            <input class="btn btn-outline-primary" type="submit" name="action" value="Previous Page"/>
                        </div>
                    </s:if>

                    <s:if test="#request.page != #request.totalPages ">
                        <div class="info-next" style="float: right">
                            <input type="hidden" name="page" value="<s:property value="#request.page"/>"/>
                            <input type="hidden" name="nameSearch" value="<s:property value="#request.nameSearch"/>"/>
                            <input type="hidden" name="categorySearch" value="<s:property value="#request.categorySearch"/>"/>
                            <input type="hidden" name="fromDate" value="<s:property value="#request.fromDate"/>"/>
                            <input type="hidden" name="toDate" value="<s:property value="#request.toDate"/>"/>
                            <input class="btn btn-outline-primary" type="submit" name="action" value="Next Page"/>
                        </div>
                    </s:if>
                </form>
            </s:if>
        </div>
    </body>
    <script src="jquery/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</html>
