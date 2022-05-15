<%@page import="com.PowerConsumption"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Consumption</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/PowerCon.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-8">
			<h1 class="m-3">Power Consumption Details</h1>
			<form id="formPowerConsumption" name="formPowerConsumption" method="post" action="PowerCon.jsp">
			<!-- Id -->
				<div class="input-group input-group-sm mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="lblId">ID: </span>
					</div>
					<input type="text" id="Id" name="Id">
				</div>
			<!-- Account No-->
				<div class="input-group input-group-sm mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="lblAccNo">Account No: </span>
					</div>
					<input type="number" id="accountNo" name="accountNo" >
				</div>
			<!-- Invoice No -->
				<div class="input-group input-group-sm mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="lblInvNo">Invoice No: </span>
					</div>
					<input type="number" id="invoiceNo" name="invoiceNo" >
				</div>
			<!-- User Name -->
				<div class="input-group input-group-sm mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="lbluserName">User Name: </span>
					</div>
					<input type="text" id="userName" name="userName" >
				</div>
			<!-- Used Unit -->
				<div class="input-group input-group-sm mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="lblUnits">Used Unit: </span>
					</div>
					<input type="number" id="usedUnit" name="usedUnit" >
				</div>
				
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<input type="button" id="btnSave" value="Save" class="btn btn-primary">
				
				<br>  
				<div id="divItemsGrid">   
					<%
					PowerConsumption pwCn = new PowerConsumption();
						out.print(pwCn.getPowerConsumptions());
					%>  
					
				</div>
			</form>
	</div>
</div>

</body>
</html>