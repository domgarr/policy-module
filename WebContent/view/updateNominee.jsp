<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.policy.data.Nominee"%>
<%@ page import="java.util.*"%>
<%@ page import="com.policy.data.Policy"%>
<%
	// Nominee data
	List<Nominee> myNominees = new ArrayList<Nominee>();
	myNominees.add(new Nominee());
	myNominees.add(new Nominee());
	myNominees.add(new Nominee());
	myNominees.get(0).setNomineeName("Nominee A");
	myNominees.get(0).setRelationshipToCustomer("Parent");
	myNominees.get(0).setPurposeOfChanged("Change 1");
	myNominees.get(1).setNomineeName("Nominee B");
	myNominees.get(1).setRelationshipToCustomer("Spouse");
	myNominees.get(1).setPurposeOfChanged("Change 2");
	myNominees.get(2).setNomineeName("Nominee C");
	myNominees.get(2).setRelationshipToCustomer("Friend");
	myNominees.get(2).setPurposeOfChanged("Change 3");

	// Policy data
	Policy policy = new Policy();
	policy.setPolicyId(1);
	policy.setPolicyName("Tom cat");
	
	// Relationships
	String[] relationship = {"Parent", "Spouse", "Friend"};
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	text-align: center;
}
table {
	text-align: left;
	margin: 20px auto 20px auto;
}
button {
	margin: 15px 20px;
	padding: 10px 20px;
}
td {
	padding: 5px;
}
</style>
</head>
<body>
	<h1>Policy Nominees</h1>
	<div id="update-nominee-content">
		<form id="update-nominee-form" action="" method="POST">
			<table>
				<% for(int i=0; i<myNominees.size(); i++) { %>
				<tr>
					<td class="tbl-labels">Nominee name</td>
					<td class="tbl-data"><input type="text" name="nominee-name"
						id="nominee-name" value="<%= (myNominees.get(i).getNomineeName()) %>">
					</td>
				</tr>
				<tr>
					<td class="tbl-labels">Relationship with the nominee</td>
					<td class="tbl-data"><select name="nominee-relationship" id="nominee-relationship">
						<% 
							for(int j=0; j<relationship.length; j++) {
								if(relationship[j].equals(myNominees.get(i).getRelationshipToCustomer())) { 
						%>
						<option selected="selected"><%= myNominees.get(i).getRelationshipToCustomer() %></option>
						<% } else { %>
						<option><%= relationship[j] %></option>
						<% }} %>
					</select></td>
				</tr>
				<tr>
					<td class="tbl-labels">Purpose of changing the nominee</td>
					<td class="tbl-data"><textarea name="nominee-name"
							id="nominee-name" rows="5" cols="30">
							<%= myNominees.get(i).getPurposeOfChanged() %>
						</textarea></td>
				</tr>
				<tr>
					<td class="tbl-labels">Upload new nominee personal id proof</td>
					<td class="tbl-data"><input type="file"></td>
				</tr>
				<% } %>
			</table>
			<button type="submit" name="btn-update-nominee"
				id="btn-update-nominee">Update Nominees</button>
			<button type="button" name="btn-cancel" id="btn-cancel">Cancel</button>
		</form>
	</div>

	<script>
		document.getElementById("btn-cancel").addEventListener("click",
				function() {
					window.location.href = "customerViewPolicy.jsp";
				});
	</script>
</body>
</html>