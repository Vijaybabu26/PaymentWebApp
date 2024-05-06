<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
<center>

	<h1>Register</h1>
	<form action="http://localhost:8080/PaymentWebApp/RegisterServlet" method="post"> 
	<label>First Name : </label><input type="text" name="fname"><br>
	<label>Last Name : </label><input type="text" name="lname"><br>
	<label>Phone No : </label><input type="text" name="phno"><br>
	<label>Email Id : </label><input type="text" name="email"><br>
	<label>Date Of Birth : </label><input type="date" name="dob"><br>
	<label>Address : </label><input type="text" name="address"><br>
	<label>Password : </label><input type="text" name="password"><br>
	<label>Confirm Password : </label><input type="text" name="Confirm"><br>
	
	<input type="submit">
	</form>
</center>
</body>
</html>