package com.policy.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hses = request.getSession(true);
		
		//SERVLET WILL SEND AGENT ID CALLING A FUNCTION OF A CLASS AND RETURN A STRING OF CUSTIDS.
		response.setContentType("text/html");
		if (request.getParameter("SearchCust")!=null) {
			String[] array = {"1","2","3","4","5"}; //RETRIEVE CUSTOMER ARRAY
			
			String agentid = request.getParameter("agent");
			if (agentid!=null) {
				hses.setAttribute("agentid", agentid);
			}else {
				hses.setAttribute("agentid", " ");
			}
			hses.setAttribute("custid", array);
			getServletContext().getRequestDispatcher("/ViewPolicyByAgent.jsp").forward(request, response);
		}else if (request.getParameter("SearchPolicy")!=null) {
			
			String[] array = {}; //RETRIEVE POLICY ARRAY
			
			String custid = request.getParameter("custid");
			hses.setAttribute("cust", custid);
			hses.setAttribute("policyid", array);
			getServletContext().getRequestDispatcher("/ViewPolicyByAgent.jsp").forward(request, response);
		}else if (request.getParameter("view")!=null) { //AFTER CLICKING VIEW POLICIES.
			String policyid = request.getParameter("policyid");
			hses.setAttribute("policyid", policyid);
			String agentid = (String) hses.getAttribute("agentid");
			String custid = (String) hses.getAttribute("cust");
			System.out.println(agentid);
			System.out.println(custid);
			System.out.println(policyid);
		}	
	}
}
