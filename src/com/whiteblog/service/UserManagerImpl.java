package com.whiteblog.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.whiteblog.dao.UserDAO;
import com.whiteblog.entity.User;
import com.whiteblog.form.UserForm;

public class UserManagerImpl {
	
	private UserDAO userdao;

	public UserDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}
	
	public List<User> getAlluser(){
		return this.userdao.findAll();
	}

	public String checklogin(UserForm userform){
		
		List<User> result = userdao.findByUsername(userform.getUsername());
		if(!result.isEmpty()){
			if(result.get(0).getPassword().equals(userform.getPassword())){
				if(result.get(0).getIdentity().equals("user")){
					return "user";
				}else{
					return "admin";
				}			
			}else{
				return "false";
			}
		}else{
			return "false";
		}
	}

	public String register(UserForm userform) throws IllegalAccessException, InvocationTargetException{
		
		User newuser = new User();
		
		BeanUtils.copyProperties(newuser,userform);
		newuser.setIdentity("user");
		userdao.save(newuser);
		
		return "SUCCESS";
		
	}
	
	public User findUser(String userName){
		
		List<User> result = userdao.findByUsername(userName);
		
		if(!result.isEmpty()){
			
			return result.get(0);
			
		}else{
			
			return null;
			
		}
		
	}
	
	public String findUsernameById(Integer id){
		User u = userdao.findById(id);
		if(u == null) 
			return null;
		return u.getUsername();
	}
	
	public String deleteUserById(Integer id){
		User u = userdao.findById(id);
		if(u != null){
			userdao.delete(u);
			return "success";
		}else{
			return "error";
		}	
	}
	
	public List<User> getAllUser(){
		
		List<User> result = userdao.findAll();
	
		return result;
	}
	

}
