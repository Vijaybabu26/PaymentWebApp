<%@page import="jakarta.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<div class = "navbar">
<h3>WELCOME TO</h3>
<h1>PAYMENT WEB APP</h1>

<form>
<input type="Submit" value="LOG OUT" align="right"/>
</form>
</div>

<div class="header">
	<% String UserName = (String)request.getAttribute("name"); %>
	<h2>Welcome <%=UserName %> ;</h2>
</div>
</center>
</body>
</html>