package com.whiteblog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; 

import com.mysql.jdbc.Statement;
import com.whiteblog.entity.User;
/**
 * 拦截器不知其SSH依赖注入，只好使用传统的数据库连接
 * @author EdisonPan
 *
 */
public class CookieDAO {
	static final String findAll = "select * from user";
	public static  Connection con = null;
	public static  Statement stat = null;
	public static ResultSet rs = null;
	
	public static void connectDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://10.125.103.139:3306/whiteblog", "qwe", "123");
		}catch(Exception e){ 
			System.out.println("connectDB " + e);
		}
		
	}
	
	public static void disconnectDB(){
		try{
			con.close();
			if(!stat.isClosed())
				stat.close(); 
			if(!rs.isClosed())
				rs.close();
		}catch(Exception e){
			System.out.println(e);
		}finally{
			System.out.println("diconnect DB on");
		}
	}
	
	public User findAll(String username, String password){
		User res = new User();
		try {
			stat = (Statement) con.createStatement();
			rs = stat.executeQuery(findAll);
			
			while(rs.next()){
				if(rs.getString(2).trim().equals(username.trim()) && rs.getString(3).trim().equals(password.trim())){
					res.setUserId(rs.getInt(1));
					res.setUsername(rs.getString(2));
					res.setPassword(rs.getString(3));
					res.setIdentity(rs.getString(4));
					res.setImage(rs.getString(5));
					res.setCountryId(rs.getInt(6));
					res.setCityId(rs.getInt(7));
					res.setProvinceId(rs.getInt(8));
					res.setJobId(rs.getInt(9));
					res.setSex(rs.getInt(10));
					break;
				} 
			}
			stat.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}catch (Exception ex){
			//ex.printStackTrace();
		}
		
		return res; 
	}
	 
	
}
