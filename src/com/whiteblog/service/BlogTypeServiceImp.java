package com.whiteblog.service;

import com.whiteblog.dao.BlogtypeDAO;
import com.whiteblog.entity.Blogtype;



import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class BlogTypeServiceImp {
	private BlogtypeDAO blogtypeDAO;

	public String addBlogtype(Blogtype blogtype)throws IllegalAccessException, InvocationTargetException{
		Blogtype newBlogtype = new Blogtype();		
		BeanUtils.copyProperties(blogtype,newBlogtype);		
		blogtypeDAO.save(newBlogtype);		
		return "SUCCESS";
	}
	public Blogtype getBlogtype(int typeID){
		return blogtypeDAO.findById(typeID);
	}
	public List<Blogtype> getBlogtype(String typeName){
		return blogtypeDAO.findByTypename(typeName);
	}
	public BlogtypeDAO getBlogtypeDAO() {
		return blogtypeDAO;
	}

	public void setBlogtypeDAO(BlogtypeDAO blogtypeDAO) {
		this.blogtypeDAO = blogtypeDAO;
	}		
}
