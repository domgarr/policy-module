/*
 * Dummy was added because empty folders can't be pushed. 
 * Please delete once another class is created in this package.
 */


package com.policy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Dummy extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String uname = req.getParameter("username");
		String password = req.getParameter("password");
		
		if(uname.equals(password) && uname.equals("admin"))
			res.sendRedirect("view/admin.jsp");
		else if(uname.equals(password) && uname.equals("customer"))
			res.sendRedirect("customer.jsp");
		else {
			PrintWriter out = res.getWriter();
			out.println("Login error!!");
		}
	}
}
