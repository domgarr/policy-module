<%@ page import="com.policy.data.Policy" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>	 
	<% if(session.getAttribute("policy") == null){ %>
	<h2> Policy was not created </h2> <%}else{ %>
	<h2> <% out.println(((Policy)session.getAttribute("policy")).getPolicyName()); 
		out.println(((Policy)session.getAttribute("policy")).getPolicyName()); 
		
	%></h2>
	<%}%>
</body>
</html>