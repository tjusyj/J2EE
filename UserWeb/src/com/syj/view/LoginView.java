package com.syj.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginView
 */
public class LoginView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String err = (String)request.getAttribute("err");
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String username = "";
		Cookie cookies[] = request.getCookies();
		if(cookies != null){
			for(Cookie c : cookies){
				if(c.getName().equals("Cookie_username")){
					username = c.getValue();
				}
			}
		}
		
		out.println("<h1>登陆</h1>");
		out.println("<form action='/UserWeb/LoginCtrl' method='post'>");
		out.println("user:<input type='text' name='username' value='"+username+"' autofocus /><br/>");
		out.println("code:<input type='password' name='password'/><br/>");
		out.println("<input type='checkbox' value='keep' name='keepCheck'/>cookie<br/>");
		out.println("<input type='submit' value='login'/><br/>");
		out.println("</form><br/>");
		if(err!=null){
			out.println("<font color='red'>"+err+"</font>");
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
