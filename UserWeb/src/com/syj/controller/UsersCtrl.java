package com.syj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syj.domain.Users;
import com.syj.service.UsersService;

/**
 * Servlet implementation class UsersDeleteCtrl
 */
public class UsersCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get id
		String type = request.getParameter("type");
		
		if(type.equals("delete")){
			String id = request.getParameter("id");
			UsersService us = new UsersService();
			if(us.UsersDelete(id)){
				request.getRequestDispatcher("/OK").forward(request, response);
			}else{
				request.getRequestDispatcher("/ERR").forward(request, response);
			}
		}else if(type.equals("gotoUpdateView")){
			String id = request.getParameter("id");
			UsersService us = new UsersService();
			Users u = us.getUserById(id);
			//save to request
			request.setAttribute("userInfo", u);
			request.getRequestDispatcher("/UserUpdateView").forward(request, response);
		}else if(type.equals("update")){
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String grade = request.getParameter("grade");
			Users u = new Users();
			u.setId(Integer.parseInt(id));
			u.setUsername(name);
			u.setEmail(email);
			u.setPassword(password);
			u.setGrade(Integer.parseInt(grade));
			UsersService us = new UsersService();
			if(us.UsersUpdate(u)){
				request.getRequestDispatcher("/OK").forward(request, response);
			}else{
				request.getRequestDispatcher("/ERR").forward(request, response);
			}
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
