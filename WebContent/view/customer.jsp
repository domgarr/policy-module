<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.policy.data.Customer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer View</title>
</head>
<body>
<% Customer u = (Customer) session.getAttribute("user"); %>
<h1>User: <%= u.getFirstname() %></h1>
<table>
	<tr>
		<th>Profile management</th>
	</tr>
	
	<tr>
		<td><button type="button" onclick="window.location.href = 'BuyPolicy.jsp';" value="buyPolicy">Buy Policy</button></td>
	</tr>
	<tr>
		<td><button type="button" onclick="window.location.href = 'PolicyDetails.jsp';" value="policyDetails">View Policy Details</button></td>
	</tr>
	</table>
</body>
</html>