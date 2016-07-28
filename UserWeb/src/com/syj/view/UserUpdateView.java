package com.syj.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syj.domain.Users;

/**
 * Servlet implementation class UserUpdate
 */
public class UserUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Users u = (Users)request.getAttribute("userInfo");
		
		out.println("<h1>Update User</h1>");
		out.println("<form action='/UserWeb/UsersCtrl?type=update' method='post'>");
		out.println("<table cellspacing='0' border='1'>");
		out.println("<tr><td>ID</td><td><input type='text' name='id' readonly value='" + Integer.toString(u.getId()) + "'/></td></tr>");
		out.println("<tr><td>User</td><td><input type='text' name='name' value='" + u.getUsername() + "'/></td></tr>");
		out.println("<tr><td>Email</td><td><input type='text' name='email' value='" + u.getEmail() + "'/></td></tr>");
		out.println("<tr><td>Grade</td><td><input type='number' name='grade' value='" + u.getGrade() + "'/></td></tr>");
		out.println("<tr><td>Password</td><td><input type='password' name='password' value='" + u.getPassword() + "'/></td></tr>");
		out.println("<tr><td>Reset</td><td><input type='reset' value='clear'/></td></tr>");
		out.println("<tr><td>Update</td><td><input type='submit' value='update'/></td></tr>");
		out.println("</table><br/>");
		out.println("</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
