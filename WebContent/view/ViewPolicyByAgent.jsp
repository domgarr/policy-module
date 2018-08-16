<!-- CREATED BY JIAN AN CHIANG -->
<!-- August 14 2018 4:00PM -->
<!-- VIEW POLICY (MANAGER'S VIEW) -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Policy By Agent</title>
<script>
	//VALIDATES AGENT TEXTBOX INPUT
	function validate(){ 
		var text = document.forms["ViewCustomerByAgentID"]["agent"].value;
		if (!text.match(/^\d+$/)){
			document.forms["ViewCustomerByAgentID"]["agent"].value = text.substring(0,text.length-1);
		}
	}
	//AUTO ERASE AGENT TEXTBOX WHEN CLICKED
	function erase(){
		document.forms["ViewCustomerByAgentID"]["agent"].value = "";
	} 
</script>
</head>

<body>

<div align="center">
<h2>Search Policy</h2>
<hr>

<!--FORM SUBMITTED TO FIND COLLECTION OF CUSTOMER IDS -->
<form name="ViewCustomerByAgentID" method="get" action="MainServlet"> 

<table>
	<tr>
		<td>Enter Agent ID</td>													  <!-- RETRIEVE AGENT ID LAST INPUTTED FROM SERVLET...UNLESS IT'S NULL -->
		<td><input type="text" name="agent" oninput="validate()" onfocus="erase()" value="<%if (session.getAttribute("agentid")!=null)%><%=session.getAttribute("agentid")%>" required></td>
		<td><input type="submit" name="SearchCust" value="Search"></td>
	</tr>
</table>
</form>
</div>

<!-- DISPLAY CUSTOMER ID DROPDOWN WHEN SEARCH IS CLICKED -->
<div align="center" <%  
if (request.getParameter("SearchCust")!=null || request.getParameter("SearchPolicy")!=null){%>
	style="display: block;"<%
	}else{
	%>style="display: none;"<% 
}%>>

<!--FORM SUBMITTED TO FIND COLLECTION OF POLICY IDS -->
<form name="ViewPolicyByCustomerID" method="get" action="MainServlet">
<table>
	<tr>	
		<td>Select Customer ID</td>
		<td><select name="custid" required>
		<%
		if (request.getParameter("agent")!=null){
			String []custarray = (String[]) session.getAttribute("custid"); //RETRIEVE CUSTID ARRAY FOR AGENT ID
			for (String i:custarray){ %> <!-- LOAD ALL CUSTOMER IDS INTO THE DROP DOWN BOX --> 
				<option <%if (i.equals(session.getAttribute("cust")) && request.getParameter("SearchCust")==null){%>selected<%}%>><%=i%></option> 
			<%}	   //KEEP THE ONE SELECTED AS SELECTED WHEN SEARCH POLICY IS CLICKED. DO NOT DEFAULT BACK TO THE FIRST OPTION 
		}%>		
		</select></td>
		<td><input type="submit" name="SearchPolicy" value="Search"></td>
		
		<td><input type="hidden" name="agent" value="<%=session.getAttribute("agentid")%>"> <!-- RETRIEVE AGENT ID VALUE TO BE SUBMITTED AGAIN -->
	</tr>
</table>
</form>
</div>


<div align="center" <% 
if (request.getParameter("SearchPolicy")!=null && request.getParameter("SearchCust")==null){%>
	style="display: block;"<%
	}else{
	%>style="display: none;"><% 
}%>

<!-- SUBMIT ALL DETAILS TO POLICY INFORMATION PAGE -->
<form name="ViewDetails">
<table>
	<tr>
		<td>Select Policy ID</td>
		<td><select name="policyid" required>
		<%
		if (request.getParameter("custid")!=null){
			String []policyarray = (String[]) session.getAttribute("policyid"); //RETRIEVE POLICYID ARRAY FOR CUSTOMER ID
			for (String i:policyarray){ %>
				<option><%=i%></option>
			<%}	
		}%>	
		</select></td>
	</tr>
</table>
<br><br>
	<input type="submit" class="button" name="view" value="View Details"/>
	
	<input type="hidden" name="agent" value="<%=session.getAttribute("agentid")%>"> <!-- RETRIEVE AGENT ID AND CUSTOMER ID VALUE TO BE SUBMITTED AGAIN -->
	<input type="hidden" name="custid" value="<%=session.getAttribute("cust")%>">
	
</form>
</div>

</body>
</html>