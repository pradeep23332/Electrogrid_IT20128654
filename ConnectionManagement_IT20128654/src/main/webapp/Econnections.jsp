<%@page import="com.Econnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connection Management</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Econnections.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Connection Management </h1>
<form id="formEconnection" name="formEconnection" >
 
 <br> Name: 
 <input id="userName" name="userName" type="text" 
 class="form-control form-control-sm">
 <br> Address: 
 <input id="address" name="address" type="text" 
 class="form-control form-control-sm">
 <br> Main Town: 
 <input id="mainTown" name="mainTown" type="text" 
 class="form-control form-control-sm">
 <br> Postal Code: 
 <input id="postalCode" name="postalCode" type="text" 
 class="form-control form-control-sm">
 <br> Post Number: 
 <input id="postNumber" name="postNumber" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidEconnectionIDSave" 
 name="hidEconnectionIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divEconnectionsGrid">
 <%
 Econnection econnectionObj = new Econnection(); 
 out.print(econnectionObj.readEconnections()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>