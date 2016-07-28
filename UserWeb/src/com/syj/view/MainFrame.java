package com.syj.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syj.domain.Users;

/**
 * Servlet implementation class MainFrame
 */
public class MainFrame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		//verify user
		Users u = (Users)request.getSession().getAttribute("loginUser");
		if(u==null){
			request.setAttribute("err", "Please Login !");
			request.getRequestDispatcher("/LoginView").forward(request, response);
			//important!!!
			return;
		}
		
		String username = u.getUsername();
		out.println("<h1>MainFrame</h1>");
		out.println("welcome xxx  <a href='/UserWeb/MainFrame'>Main</a> <a href='/UserWeb/View'>Log out</a>");
		out.println("<hr/>");
		out.println("welcome "+username+"<br/>");
		out.println("<a href='/UserWeb/LoginView'>Return to Login</a><br/><br/>");
		
		out.println("<a href='/UserWeb/UserManage'>Manage User</a><br/>");
		out.println("<a href=''>New User</a><br/>");
		out.println("<a href=''>Search User</a><br/>");
		out.println("<a href=''>Log out</a><br/>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
