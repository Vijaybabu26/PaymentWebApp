<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="payment.web.entity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Wallet To Wallet Transaction</title>
</head>
<body>
	<center>
	<form action="http://localhost:8080/PaymentWebApp/WWTransactionSer" method="post">
		<h1>Send Money Wallet To Wallet</h1><br>
		<h2>Send Money From One Mobile No To Another Mobile No Who Are Registred</h2><br><br><hr><br>
		<h2>Enter The Desitination Details</h2>
		<label>Enter The Reciver Mobile No : </label>
		<input type="text" name= "RMobileNo">
		<br><br>
		<label>Enter The Amount : </label>
		<input type="text" name ="TxnAmount">
		<br><br>
		<% User sessionUser = (User)session.getAttribute("userd");
			String UserPhno = sessionUser.getPhno();
		%>
		
		<h2>Your Mobile No : <%=UserPhno %></h2> 
		<label>Enter Your Account Pin : </label>
		<input type="text" name ="AcctPin">
		<br><br>
		<input type="submit" Value="Send Money">
		<hr>
	</form>
	</center>
</body>
</html>