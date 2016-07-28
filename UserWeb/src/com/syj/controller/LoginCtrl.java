package com.syj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.syj.domain.Users;
import com.syj.service.UsersService;

/**
 * Servlet implementation class LoginCtrl
 */
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String keepCheck = request.getParameter("keepCheck");
		
		UsersService us = new UsersService();
		Users u = new Users();
		u.setUsername(username);
		u.setPassword(password);
		if(us.checkUser(u)){
			//cookie
			if(keepCheck!=null && keepCheck.equals("keep")){
				Cookie c = new Cookie("Cookie_username",username);
				c.setMaxAge(7*24*3600);
				response.addCookie(c);
			}
			//------------------------------------------------------------------------------
			//session
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", u);
			Cookie cookie = new Cookie("JSESSIONID",session.getId());
			//continue session after restart broswer
			//session  cookie
			cookie.setMaxAge(24*3600);
			response.addCookie(cookie);
			//-------------------------------------------------------------------------------
			
			//User Verified
			request.getRequestDispatcher("/MainFrame").forward(request, response);
		}else{
			//User Not Verified
			request.setAttribute("err", "username/password incorrect!");
			request.getRequestDispatcher("/LoginView").forward(request, response);
		}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
