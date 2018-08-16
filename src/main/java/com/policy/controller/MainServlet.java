package com.policy.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.policy.data.Policy;
import com.policy.service.PolicyService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   	
    	
	   	
		   	HttpSession ses = request.getSession();
		   	
		   	String polName = request.getParameter("policy_name");
		   	ses.setAttribute("policy_name", polName);
	   		 
			int numNominees = Integer.parseInt(request.getParameter("nominee"));
		   	ses.setAttribute("nominee", numNominees);
		   	
			double numTenure = Double.parseDouble(request.getParameter("year"));
		   	ses.setAttribute("year", numTenure);
		   	
		   	double minSum = Double.parseDouble(request.getParameter("min"));
		   	ses.setAttribute("min", minSum);
		   	
		   	String maxSum = request.getParameter("max");
		   	ses.setAttribute("max", maxSum);
		   	
		   	String preReq = request.getParameter("pre-req");
		   	ses.setAttribute("pre-req", preReq);
		   	
		   	
		   	Policy obj = new Policy();
		   	obj.setPolicyName(polName);
		   	obj.setNumberNominees(numNominees);
		   	obj.setTenure(numTenure);
		   	obj.setSumAssured(minSum);
		   	obj.setPreReqs(preReq);
		   	obj.setPolicyType("ch");
		   	
		   	ses.setAttribute("policy", obj);

		   	
		   	PolicyService obj1 = new PolicyService();
		   	obj1.addPolicy(obj);
		   	
		   	//Add to session to object.
		   	
		   	response.sendRedirect("view/RegisterPolicySuccess.jsp");
	   	
		

	}


}
