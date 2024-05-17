<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Wallet To Bank Transaction</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	
	<center><br>
	<h1>Send Money To Wallet</h1><br><hr>
	<h3>Send money from Bank Account To Wallet</h3><br><hr>
	<h3>Send money using Account No Mobile No</h3><br><hr>
	<h1>Enter The Destination Details</h1><br>
	<form action="http://localhost:8080/PaymentWebApp/WBTransactionSer" method="post">
	<label><h3>Enter The Bank Account No : </h3></label><input type="text" name="accountno"><br><hr>
	<h1>Enter The Source Details</h1><br>
<%--<label><h3>Enter The Mobile No :</h3></label><input type="text" name="phno"><br> --%>	
	<label><h3>Enter The Pin :  </h3></label><input type="text" name="pin"><br>
	<label><h3>Enter The Amount :  </h3></label><input type="text" name="Wamount"><br>
	<input type="Submit" value="SEND MONEY">
	</form>
	</center>
</body>
</html>