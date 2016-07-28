package com.syj.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.syj.domain.Users;
import com.syj.util.SqlHelper;

public class UsersService {
	
	public boolean checkUser(Users u){
		boolean b = false;
		
		//注意数据库配置文件小心空格
		String sql = "select * from users where name=? and password=?";
		String parameters[] = {u.getUsername(),u.getPassword()};
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try{
			if(rs.next()){
				b = true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return b;
	}
	
	public Users getUserById(String id){
		Users u = new Users();
		String sql = "select * from users where id=?";
		String parameters[] = {id};
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try{
			if(rs.next()){
				u.setId(Integer.parseInt(id));
				u.setUsername(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setGrade(rs.getInt("grade"));
				u.setPassword(rs.getString("password"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return u;
	}
	
	public ArrayList<Users> getUsersByPage(int pageNow,int pageSize){
		ArrayList<Users> users = new ArrayList<Users>();
		
		String sql = "select * from users limit "+(pageNow-1)*pageSize+","+pageSize;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		try {
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("name");
				String email = rs.getString("email");
				Users u = new Users();
				u.setId(id);
				u.setUsername(username);
				u.setEmail(email);
				users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		
		return users;
	}
	
	public int getPageNum(int pageNow,int pageSize){
		int rowNum=0;
		ResultSet rs = SqlHelper.executeQuery("select count(*) from users", null);
		try {
			if(rs.next()){
				rowNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return (rowNum / pageSize)+1;
	}
	
	public boolean UsersDelete(String id){
		boolean b = true;
		String sql = "DELETE FROM users WHERE id=?";
		String parameters[] = {id};
		try{
			SqlHelper.executeUpdate(sql, parameters);
		}catch (Exception e){
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean UsersUpdate(Users u){
		boolean b = true;
		String sql = "UPDATE users SET name=?,email=?,password=?,grade=? WHERE id=?";
		String parameters[] = {u.getUsername(),u.getEmail(),u.getPassword(),
				Integer.toString(u.getGrade()),Integer.toString(u.getId())};
		try{
			SqlHelper.executeUpdate(sql, parameters);
		}catch (Exception e){
			b = false;
			e.printStackTrace();
		}
		return b;
	}
}
