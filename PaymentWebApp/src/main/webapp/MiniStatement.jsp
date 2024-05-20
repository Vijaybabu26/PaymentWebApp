<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List"%>
    <%@ page import="payment.web.entity.Transaction" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mini Statement</title>
</head>
<body>
	<center>
	<div class="header">
	<h1>Mini Statement</h1>
	</div><hr>
	<div class="Home">
	<a href="http://localhost:8080/PaymentWebApp/BankAcctList"><h2>Back To Home</h2></a>
	</div>
	<div class="TransactionList">
	<h1>MINI STATEMENTS OF YOUR TRANSACTIONS</h1>
	<% List<Transaction> txnlist = (List<Transaction>)session.getAttribute("txnlist");
	if(txnlist != null){
		for(int i=0;i<txnlist.size();i++){
			Transaction txn = txnlist.get(i);%>
		<hr>
		<h3>Transaction Id : <%=txn.getTxnId() %></h3>
		<h3>Transaction Date : <%=txn.getTxnDate() %></h3>
		<h3>Transaction Status : <%=txn.getTxnStatus() %></h3>
		<h3>Transaction Amount : <%=txn.getTxnAmount() %></h3>
		<h3>Transaction Source to Destination : <%=txn.getSourceType() %> To <%=txn.getDestType() %></h3>
		<h3></h3>
		<%}
	}else{
	%>
	<h1> No Transactions Has Been Done </h1>
	<%} %>
	</div>
	</center>
</body>
</html>