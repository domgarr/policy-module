<%@ page language="java" contentType="text/html; charset=ISO-8859-1,Unicode #8896 / U+22C1 " pageEncoding="ISO-8859-1"%>
<!DOCTYPE html> <!-- created by Hamza at 3pm   -->
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
  
  	<script type="text/javascript">   //function to check number in number of nominee's module 1.51
	
	    var i = 0;
	    function updateNumber(){
			var i=document.forms["CreateFormUI"]["display"].value;
			if(!i.match(/^\d+$/)){
				   document.getElementById("display").value = i.substring(0,i.length-1);
			}
	    	if(i>10){
	    		document.getElementById("display").value=10;
	    	}
	    	else if(i<0){
	    		document.getElementById("display").value=0;
	    	}
	    }
	    </script>
	    
	    <script type="text/javascript"> //function to increment number for nominee 1.51
	    
	    function incNumber() {
	        if (i < 10) {
	            i++;
	        } else if (i = 10) {
	            i = 10;
	        }
	        document.getElementById("display").value = i;
	    }
	
	    </script>
	    
	    <script type="text/javascript"> //function to decrement number for nominee 1.51
	    function decNumber() {
	        if (i > 0) {
	            --i;
	        } else if (i = 0) {
	            i = 10;
	        }
	        document.getElementById("display").value = i;
	    }
	    </script>
	    
	    
      <script type="text/javascript"> //function to show custom tenure text box module 1.51
        function show() { document.getElementById('area').style.display = 'block'; }
        function hide() { document.getElementById('area').style.display = 'none'; }
      </script>  
      
	<script type="text/javascript">  //function to dynamically add text boxes used in module 1.51 (register policy)
	   function add() {
	     var element = document.createElement("input");
	     element.setAttribute("type", "text");
	     element.setAttribute("name", "mytext");
	     element.setAttribute("placeholder", "Please enter pre-requisite")
	     element.setAttribute("style", "width:300px")
	     element.required=true;
	     var br = document.createElement("br");
	     var spanvar = document.getElementById("myspan");
	     spanvar.appendChild(element);
	     spanvar.appendChild(br);
	    
	   }
	   function remove() {
		     var element = document.createElement("input");
		     element.setAttribute("type", "text");
		     element.setAttribute("name", "mytext");
		     var spanvar = document.getElementById("myspan");
		     spanvar.removeChild(myspan.childNodes[0]);
		     spanvar.removeChild(myspan.childNodes[0]);
		   }
	   function validate(){ //function to validate numeric values
		   var checkMin=document.forms["CreateFormUI"]["min"].value;
		   var checkMax=document.forms["CreateFormUI"]["max"].value;
		   var checkTenure=document.forms["CreateFormUI"]["area"].value;
		   if(!checkMin.match(/^\d+$/)){
			   document.forms["CreateFormUI"]["min"].value= checkMin.substring(0,checkMin.length-1);
		   }
		   else if(!checkMax.match(/^\d+$/)){
			   document.forms["CreateFormUI"]["max"].value= checkMax.substring(0,checkMax.length-1);
		   }
		   else if(!checkTenure.match(/^\d+$/)){
			   document.getElementById("area").value = checkTenure.substring(0,checkTenure.length-1);
		   }
	   }
	</SCRIPT>
</head>
<body>
<div class="container">
  <div  id="form_style" >
  <form id="CreateFormUI" method= "post" action ="MainServlet" ">
  	<div class="form-group">
		<table align="center" >
			<h1 align="center"> Register Policy</h1>
			<tr> 
			
				<td> Policy Name </td>
				<td> <input type="text" name="policy_name" required = "required" pattern="([^\s][A-z0-9\s]+){2,100}"/> </td>
				<td> </td>
				<td> </td>
				<td> </td>
				<td> </td>
				<td>   <button type="reset" value="Reset">Reset form</button> </td>
				
			</tr>
				<div>
				
			<tr>
					
				<td> Number of Nominee's </td>
				<td>
						
					<input type="text" name="nominee" id="display" value="0" oninput="updateNumber()" required="required" pattern="^[0-9]*$" >
				    <input type="button" value="+" id="inc" onclick="incNumber()"/>
				    <input type="button" value="-" id="dec" onclick="decNumber()"/>
				</td> 
			</tr>

    			</div>
    			<tr> 
	    			<td> Tenure </td>
	    			<td> <br>
	    				<input type="radio" name="year" value="1" checked onclick="hide();"/> 1 Year<br>
	  					<input type="radio" name="year" value="2" onclick="hide();"/> 2 Year<br>
	  					<input type="radio" name="year" value="3" onclick="hide();" /> 3 Year <br>
	    				<input type="radio" name="year" value="custom" onclick="show();" /> Custom	 
	    				<input type="text" 	name="year" value="4" id="area" style= "display: none;" oninput="validate()">
 				
	    			</td>
				</tr>
			
				<tr> 
					<td> Sum Assured </td>
					<td> <br>
						 Min: <input type="text" id="min" name="min" value="0" oninput="validate()"/> <br>
					     Max:<input type="text" id="max" name="max" value="1" oninput="validate()" />
					</td>
				</tr>
				<tr> 
					<td> List Pre-Requisites</td>
					<td>
				   		
				   		<div id="certificationtog">
						   <p class="setting">
						   	<br>
						   	  <input type="button" id="addrows" name="add rows" class="addperson" value="Add Rows" onclick="add();" />
						      <input type="button" id="removerows" class="removerows" value="Delete Rows" onclick="remove();" />  
						      <br>
						    <span id="myspan" ></span>
						   	<span style="width: 0px; margin-left: 20px; font-weight: bold; float: none; ">  <br>
						        
						      </span>

						     
						      <br><br>

						   </p>
						</div>
					</td>
					

				
				</tr>
			
			
		</table>
		
		<div class="clearfix" align="center">
			
			<input type="submit" value="Register" id="check"/>
			<button type="cancel" class="cancelbtn">Cancel</button>
			
    	</div >
		<div align="center">
			<a href="url"></a>
			<p><a href="#" style="color:dodgerblue">Click here</a> to login.</p>
		</div>
		</div>
	</form>
	</div>
	</div>
</body>
</html>