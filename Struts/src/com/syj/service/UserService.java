package com.syj.service;

import java.sql.ResultSet;

import com.syj.domain.User;
import com.syj.util.SqlHelper;

public class UserService {

	public boolean checkUser(User u){
		boolean b = false;
		
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
}
