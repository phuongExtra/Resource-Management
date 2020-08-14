<%-- 
    Document   : manageRequest
    Created on : Jul 15, 2020, 1:52:56 PM
    Author     : nhatp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Request</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/shop-homepage.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="searchItem.jsp">Resource Management</a>
                <a href="ManageRequestAction" class="nav-link">Manage Request</a>
                <div  id="navbarResponsive">
                    <a class="nav-link" href="LogoutAction">Logout</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <s:if test="%{#request.error}">
                <div class="alert alert-danger alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong><s:property value="#request.error"/></strong> 
                </div>
            </s:if>
            <s:if test="%{#request.success}">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong><s:property value="#request.success"/></strong> 
                </div>
            </s:if>
            <form action="SearchRequestAction" method="POST">
                <input type="hidden" name="page" value="1"/>
                <div class="form-group row" style="margin-top: 100px">
                    <div class="col-6">
                        <label>Search by item name</label>
                        <input type="text" class="form-control" name="itemNameSearch" value="<s:property value="#request.itemNameSearch"/>"/></br>
                        <label>Search by username</label>
                        <input type="text" class="form-control" name="usernameSearch" value="<s:property value="#request.usernameSearch"/>"/></br>
                        <label>Search by status</label>
                        <select class="form-control" name="statusSearch" required="true">
                            <option value="0" <s:if test="%{#request.statusSearch == 0}">selected="selected"</s:if>>All</option>
                            <option value="1" <s:if test="%{#request.statusSearch == 1}">selected="selected"</s:if>>New</option>
                            <option value="4" <s:if test="%{#request.statusSearch == 4}">selected="selected"</s:if>>Accepted</option>
                            <option value="5" <s:if test="%{#request.statusSearch == 5}">selected="selected"</s:if>>Deleted</option>
                            </select></br>
                            <input type="submit" class="btn btn-primary" value="Search"/>
                        </div>
                        <div class="col-6">
                            <label>From date</label>
                            <input type="date" class="form-control" name="fromDate" value="<s:property value="#request.fromDate"/>" required="true"/></br>
                        <label>To date</label>
                        <input type="date" class="form-control" name="toDate" value="<s:property value="#request.toDate"/>" required="true"/></br>
                    </div>
                </div>
            </form>
            <s:if test="%{#request.requestList}">
                <div class="form-group" style="margin-top: 100px">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>User request</th>
                                <th>Resource request</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="%{#request.requestList}" status="counter">
                                <tr>
                                    <td><s:property value="#counter.count"></s:property></td>
                                    <td><s:property value="username"></s:property></td>
                                        <td>
                                        <s:iterator value="itemList" var="list">
                                            <table class="table">
                                                <tbody>
                                                    <tr>
                                                        <th><s:property value="#list.itemName"/></th>
                                                        <th><s:property value="#list.quantity"/></th>
                                                        <th><s:property value="#list.bookingDate"/></th>
                                                        <th><s:property value="#list.returnDate"/></th>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </s:iterator>
                                    </td>
                                    <td>
                                        <s:if test="%{statusID == 1}">New</s:if>
                                        <s:if test="%{statusID == 4}">Accepted</s:if>
                                        <s:if test="%{statusID == 5}">Declined</s:if>
                                        </td>
                                        <td>
                                            <form action="UpdateRequestAction" method="POST">
                                                <input type="hidden" name="bookingID" value="<s:property value="bookingID"/>"/>
                                            <input type="hidden" name="itemNameSearch" value="<s:property value="#request.itemNameSearch"/>"/>
                                            <input type="hidden" name="usernameSearch" value="<s:property value="#request.usernameSearch"/>"/>
                                            <input type="hidden" name="statusSearch" value="<s:property value="#request.statusSearch"/>"/>
                                            <input type="hidden" name="fromDate" value="<s:property value="#request.fromDate"/>"/>
                                            <input type="hidden" name="toDate" value="<s:property value="#request.toDate"/>"/>
                                            <input type="hidden" name="page" value="<s:property value="#request.page"/>"/>
                                            <s:if test="%{statusID == 1}"> 
                                                <input type="submit" class="btn btn-outline-success" style="margin-right: 10px" name="action" value="Accept"/>
                                                <input type="submit" class="btn btn-outline-danger" name="action" value="Decline"/>
                                            </s:if>
                                        </form>
                                    </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
            </s:if>
            <s:if test="#request.requestList">
                <form  action="SearchRequestAction" method="POST">
                    <input type="hidden" name="roleID" value="<s:property value="%{#session.user.roleID}"/>"/>
                    <s:if test="#request.page != 1">
                        <div class="info-prev" style="float:  left">
                            <input type="hidden" name="page" value="<s:property value="#request.page"/>"/>
                            <input type="hidden" name="itemNameSearch" value="<s:property value="#request.itemNameSearch"/>"/>
                            <input type="hidden" name="usernameSearch" value="<s:property value="#request.usernameSearch"/>"/>
                            <input type="hidden" name="statusSearch" value="<s:property value="#request.statusSearch"/>"/>
                            <input type="hidden" name="fromDate" value="<s:property value="#request.fromDate"/>"/>
                            <input type="hidden" name="toDate" value="<s:property value="#request.toDate"/>"/>
                            <input class="btn btn-outline-primary" type="submit" name="action" value="Previous Page"/>
                        </div>
                    </s:if>

                    <s:if test="#request.page != #request.totalPages ">
                        <div class="info-next" style="float: right">
                            <input type="hidden" name="page" value="<s:property value="#request.page"/>"/>
                            <input type="hidden" name="itemNameSearch" value="<s:property value="#request.itemNameSearch"/>"/>
                            <input type="hidden" name="usernameSearch" value="<s:property value="#request.usernameSearch"/>"/>
                            <input type="hidden" name="statusSearch" value="<s:property value="#request.statusSearch"/>"/>
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
