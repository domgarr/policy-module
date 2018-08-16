<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.policy.data.Manager" %>
<%@ page import="com.policy.controller.Dummy" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="../javascript/utilities.js"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin page</title>
</head>
<% Manager m = (Manager) session.getAttribute("user"); %>
<h1>Admin <%= m.getFullname() %> ID: <%= m.getManagerId() %></h1>
<body>
	<table>
	<tr>
		<th>Policy Management
	</tr>
	
	<tr>
		<td><button type="button" onclick="window.location.href = 'CreateForm.jsp';" value="newPolicy">Create Policy</button></td>
	</tr>
	<tr>
		<td><button type="button" onclick="window.location.href = 'UpdatePolicy.jsp';" value="updatePolicy">Update Policy</button></td>
	</tr>
	<tr>
		<td><button type="button" onclick="window.location.href = 'DeletePolicy.jsp';" value="DeletePolicy">Delete Policy</button></td>
	</tr>
	<tr>
		<td><form action="/PolicyModule/logout" mehtod="post"><input type="submit" name="logout" value="logout"></form></td>
	</tr>
	</table>
</body>
</html>