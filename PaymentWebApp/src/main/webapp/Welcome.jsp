<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<center>
		<h1>Login</h1><br><hr><br>

		<form action="http://localhost:8080/PaymentWebApp/LoginServlet" method="post">
		<label>Mobile No : </label><input type="text" name="Phno"><br><br>
		<label>PassWord :</label><input type="text" name="PassWord"><br><br>
		<input type="submit">
		</form><hr>
		<form>
		<a href="Register.jsp"><h2>REGISTER</h2></a>
		</form>

</center>
</body>
</html>