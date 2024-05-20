""<%@page import="jakarta.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List"%>
    <%@ page import="payment.web.entity.BankAccount" %>
    <%@ page import="payment.web.entity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DashBoard - PaymentWebApp</title>
</head>
<body>
<center>
<div class = "navbar">
<h2>WELCOME TO</h2>
<h1>PAYMENT WEB APP</h1>

<form action="http://localhost:8080/PaymentWebApp/LogoutServlet">
<input type="Submit" value="LOG OUT" align="right"/>
</form><hr>
</div>

<div class="header">
	<% String UserName = (String)session.getAttribute("name"); %>
	<h1>Welcome <%= UserName %>;</h1>
	
	<h2>Your Current Wallet Balance : <%= (double)session.getAttribute("Wamount")%></h2> 
	<form action="AddMoneyToWallet.jsp">
		<input type="submit" value="Add Money To Wallet" >
	</form>
</div><hr>
<div class="menu">

<a href="Transaction.jsp"><h2>Transactions</h2></a>
<a href="http://localhost:8080/PaymentWebApp/MiniStatementListSer"><h2>Mini Statements</h2></a>
</div><hr>
</center>    
<div class="BankAcctList"> 
	<h1>BANK ACCOUNT LIST</h1><br>
	<% List<BankAccount> balist = (List<BankAccount>)session.getAttribute("Balist"); 
		if(balist != null){
		for(int i= 0;i<balist.size();i++){ 
			BankAccount ba = balist.get(i);%>
		<hr>
		<h2>BankAcount No : <%=ba.getBankAcctNo()%></h2>
		<h2>BankAcount Name : <%=ba.getBankAcctName()%></h2>
		<h2>BankAcount IfscCode : <%=ba.getBankIFSCCode()%></h2>
		<h2>Current Bank Balance : <%=ba.getCurrBankBal()%></h2>
		
		<%} %>
		<%} %>
		<form action="AddBankAcct.jsp">
		<input type="submit" value="Add Bank Account" >
		</form>
</div>

</body>
</html>