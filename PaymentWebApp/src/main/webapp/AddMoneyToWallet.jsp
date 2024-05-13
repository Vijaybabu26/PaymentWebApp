<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Money To Wallet</title>
</head>
<body>
	<center>
	<h1>Add Money To Wallet</h1><hr>
	
	<form>

	
	<h1>Enter The Destination Details</h1><br>
	<form action="http://localhost:8080/PaymentWebApp/AddMoneyToWallet" method="post">
	<label><h3>Enter The Bank Account No : </h3></label><input type="text" name="accountno"><br>
	<h1>Enter The Source Details</h1><br>
	<label><h3>Enter The Amount :  </h3></label><input type="text" name="wamount"><br>
	<input type="submit" value="Add Money">
	</form>
	</center>
	</form>
	
	</center>
</body>
</html>