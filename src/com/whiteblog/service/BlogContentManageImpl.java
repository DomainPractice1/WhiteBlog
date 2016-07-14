package com.whiteblog.service;
import java.util.List;

import com.whiteblog.dao.BlogDAO;
import com.whiteblog.dao.CollectionDAO;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.User;
import com.whiteblog.entity.Collection;
public class BlogContentManageImpl {
	private BlogDAO  blogDAO;
	private CollectionDAO collectionDAO;
	public CollectionDAO getCollectionDAO() {
		return collectionDAO;
	}

	public void setCollectionDAO(CollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}
	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}
	public Blog findById(java.lang.Integer id){
		return blogDAO.findById(id);
	}
	public void setupdateBlog(Blog blog){
		blogDAO.attachDirty(blog);
	}
	public void deleteBlog(Blog blog){
		blogDAO.delete(blog);
	}
	// viewcount
		public void setupdateviewcount(Blog instance) {
			blogDAO.updateView(instance);
			}
		 public  int ifcollect(User user,int blogID){
			 	if(user == null)
			 		return 0;
		    	List<Collection> mycollection=collectionDAO.findByUserId(user.getUserId());
		    	if(mycollection.isEmpty()==true){
		    		return 0;
		    	}
		    	else{
		    		for(int i=0;i<mycollection.size();i++){
		    			if(mycollection.get(i).getBlogId()==blogID){
		    				
		    				return 1;
		    			}
		    				
		    		}
		    	}
		    	return 0;
		    }
}
