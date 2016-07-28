package com.syj.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syj.domain.Users;
import com.syj.service.UsersService;

/**
 * Servlet implementation class UserManage
 */
public class UserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = 4;
		int pageNow = 1;
		int pageNum = 0;
		//out
		PrintWriter out = response.getWriter();
		//javascript
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function confirmOper() {return window.confirm('Delete this user?');}");
		out.println("</script>");
		//
		out.println("<h1>User Manage</h1>");
		//set pageNow
		if(request.getParameter("pageNow") != null){
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		//mysql
		UsersService us = new UsersService();
		pageNum = us.getPageNum(pageNow, pageSize);
		ArrayList<Users> users = us.getUsersByPage(pageNow, pageSize);
		//Result
		out.println("<table cellspacing='0' border='1'>");
		out.println("<tr>");
		out.println("<td>Num</td>");
		out.println("<td>User</td>");
		out.println("<td>Email</td>");
		out.println("<td>Delete</td>");
		out.println("<td>Update</td>");
		out.println("</tr>");
		for(Users u : users){
			out.println("<tr>");
			out.println("<td>" + Integer.toString(u.getId()) + "</td>");
			out.println("<td>" + u.getUsername() + "</td>");
			out.println("<td>" + u.getEmail() + "</td>");
			out.println("<td><a onClick='return confirmOper();' href='/UserWeb/UsersCtrl?type=delete&id="+u.getId()+"'>Delete</a></td>");
			out.println("<td><a href='/UserWeb/UsersCtrl?type=gotoUpdateView&id="+u.getId()+"'>Update</a></td>");
			out.println("</tr>");
		}
		out.println("</table><br/>");
		
		//last page
		if(pageNow > 1){
			out.println("<a href='/UserWeb/UserManage?pageNow="+(pageNow-1)+"'>&lt;Last&gt;</a>");
		}
		//all page
		for(int i=1 ; i <= pageNum ; i++){
			out.println("<a href='/UserWeb/UserManage?pageNow="+Integer.toString(i)+"'><"+Integer.toString(i)+"></a>");
		}
		//next page
		if(pageNow < pageNum){
			out.println("<a href='/UserWeb/UserManage?pageNow="+(pageNow+1)+"'>&gt;Next&gt;</a>");
		}
		//page jump
		out.println("<form action='/UserWeb/UserManage' method='post'>");
		out.println("Jump to:<input type='number' name='pageNow' min='1' max='"+pageNum+"'/>");
		out.println("<input type='submit' value='Go'/><br/>");
		out.println("</form><br/>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
