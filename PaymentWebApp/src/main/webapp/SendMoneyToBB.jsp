<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank To Bank Transaction</title>
</head>
<body>
	<center>
	<form action="http://localhost:8080/PaymentWebApp/BBTransactionSer">
		<h1>Send Money Bank To Bank</h1><br>
		<h4>Send Money From One Account No To Another Account No Who Are Having Registred Accounts</h4><br><hr><br>
		<h2>Enter The Desitination Details</h2>
		<label>Enter The Reciver Account No : </label>
		<input type="text" name= "RAcctNo">
		<br><hr>
		<h2>Enter The Source Details</h2>
		<label>Enter The Account Number : </label>
		<input type="text" name ="SAcctNo"><br><br>
		<label>Enter The Account Pin : </label>
		<input type="text" name ="Pin"><br><br>
		<label>Enter The Amount : </label>
		<input type="text" name ="TxnAmount"><br><br>
		<input type="Submit" value="SEND MONEY">
		<hr>
	</form>
	</center>
</body>
</html>