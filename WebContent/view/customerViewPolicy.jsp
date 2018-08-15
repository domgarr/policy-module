<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.policy.data.Nominee" %>
<%@ page import="java.util.*" %>
<% List<Nominee> myNominees = new ArrayList<Nominee>();
   myNominees.add(new Nominee());
   myNominees.add(new Nominee());
   myNominees.add(new Nominee());
   myNominees.get(0).setNomineeName("Nominee A");
   myNominees.get(0).setRelationshipToCustomer("Parent");
   myNominees.get(1).setNomineeName("Nominee B");
   myNominees.get(1).setRelationshipToCustomer("Spouse");
   myNominees.get(2).setNomineeName("Nominee C");
   myNominees.get(2).setRelationshipToCustomer("Friend");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Policy</title>
<style>
body {
	margin: 10px;
}
#policy-details {
	text-align: center;
}
table {
	margin: 25px auto 25px auto;
    text-align: left;
}
td {
    padding: 5px 15px;
}
.tbl-labels {
	font-weight: bold;
}
button {
	text-align: center;
	margin: 10px 20px 10px 20px;
	padding: 10px 20px;
}
</style>
</head>
<body>
  <div id="policy-details">
    <h1>Policy Details</h1>
    <table>
      <tr>
      	<td class="tbl-labels">Policy ID</td>
      	<td class="tbl-data">123</td>
      </tr>
      <tr>
      	<td class="tbl-labels">Policy Name</td>
      	<td class="tbl-data">Tom Durr</td>
      </tr>
      <tr>
      	<td class="tbl-labels">Type of Insurance</td>
      	<td class="tbl-data">Accidental</td>
      </tr>
      <% for(int i=0; i<myNominees.size(); i++) { %>
      <tr>
        <td class="tbl-labels">Nominee</td>
     	<td class="tbl-data"><%= myNominees.get(i).getNomineeName() %></td>
     	<td class="tbl-labels">Relationship</td>
     	<td class="tbl-data"><%= myNominees.get(i).getRelationshipToCustomer() %></td>
      </tr>
      <% } %>
      <tr>
      	<td class="tbl-labels">Tenure</td>
      	<td class="tbl-data">1 year</td>
      </tr>
      <tr>
      	<td class="tbl-labels">Sum Assured</td>
      	<td class="tbl-data">$1,000,000</td>
      </tr>
      <tr>
      	<td class="tbl-labels">Premium Type</td>
      	<td class="tbl-data">Annually</td>
      </tr>
      <tr>
      	<td class="tbl-labels">Premium Amount</td>
      	<td class="tbl-data">$1,000</td>
      </tr>
      <tr>
      	<td class="tbl-labels">Agent</td>
      	<td class="tbl-data">Linda Kelly</td>
      </tr>
      <tr>
      	<td class="tbl-labels">Policy Initiation Date</td>
      	<td class="tbl-data">August 10, 2018</td>
      </tr>
    </table>
    <button id="update-nominees">Update Nominees</button>
    <button id="go-back-customer-home">Go Back</button>
  </div>
  <script>
  	// Button click event listener for update nominees;
  	// When clicked, redirect page to "updateNominees.jsp"
  	document.getElementById("update-nominees").addEventListener("click", function() {
  		window.location.href = "updateNominee.jsp";
  	});
  	
 	// Button click event listener for go back button;
  	// When clicked, redirect page to customer home page
  	document.getElementById("go-back-customer-home").addEventListener("click", function() {
  		// window.location.href = "updateNominee.jsp";
  		alert("Go back to customer homepage");
  	});
  </script>
</body>
</html>