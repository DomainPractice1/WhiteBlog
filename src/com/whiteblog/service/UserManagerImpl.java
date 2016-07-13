package com.whiteblog.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.whiteblog.dao.UserDAO;
import com.whiteblog.entity.User;
import com.whiteblog.form.UserForm;

public class UserManagerImpl{
	
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
			
			String password = EncryptServiceImpl.convert(result.get(0).getPassword());

			if(password.equals(userform.getPassword())){
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
		newuser.setPassword(EncryptServiceImpl.convert(newuser.getPassword()));//加密存储
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
	
	public User matchUserByUsername(String name){
		User res = null;
		List<User> ul = this.userdao.findAll();
		for(User u: ul){
			System.out.println("matchUserByUsername " + u.getUsername());
			if(u.getUsername().equals(name))
				res = u;
		}
		return res;
	}
	
}
