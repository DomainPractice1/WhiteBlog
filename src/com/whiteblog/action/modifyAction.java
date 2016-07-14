package com.whiteblog.action;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.service.BlogServiceImp;
import com.whiteblog.service.BlogTypeServiceImp;
import com.whiteblog.service.UserManagerImpl;
import com.whiteblog.service.fileManagerImpl;

public class modifyAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlogServiceImp blogService;    
 	private BlogTypeServiceImp blogtypeService;
 	private UserManagerImpl usermanager;
	private int id;
	private Blog formerBlog;
	private Blog currentBlog;
	private Blogtype blogtype;	
	private String category;
	private String hint;
	private List<String> filterWords;

	public String execute() throws IllegalAccessException, InvocationTargetException{
		formerBlog=blogService.getBlog(id);
		if(formerBlog!=null){
			currentBlog.setBlogId(formerBlog.getBlogId());
			currentBlog.setUserId(formerBlog.getUserId());
			currentBlog.setUsername(formerBlog.getUsername());
			currentBlog.setTime(formerBlog.getTime());
			currentBlog.setCommentnumber(formerBlog.getCommentnumber());
			currentBlog.setForwardnumber(formerBlog.getForwardnumber());
			currentBlog.setLikenumber(formerBlog.getLikenumber());
			currentBlog.setViewnumber(formerBlog.getViewnumber());
			currentBlog.setStatus(formerBlog.getStatus());			
			Blogtype formerType=blogtypeService.getBlogtype(formerBlog.getTypeId());
			currentBlog.setTypeId(formerType.getTypeId());
			
			blogtype.setSupertypeId(Integer.parseInt(category));
			blogtype.setTypeId(formerType.getTypeId());
			blogtype.setUserId(formerType.getUserId());
			
			if(blogtype.getSupertypeId().intValue()!=formerType.getSupertypeId().intValue() || !blogtype.getTypename().equals(formerType.getTypename())){//和之前的类型不同
				List<Blogtype> typesAlready = null;
				typesAlready=blogtypeService.getBlogtypeDAO().findBySupertypeId(blogtype.getSupertypeId());
				if(!typesAlready.isEmpty()){
					for(int i=0;i<typesAlready.size();i++){
						if(typesAlready.get(i).getTypename().equals(blogtype.getTypename())){//查找是否有现存的type
							currentBlog.setTypeId(typesAlready.get(i).getTypeId());
							break;
						}
					}									
				}
				if(currentBlog.getTypeId().intValue()==formerType.getTypeId().intValue()){	//没有现存的					
					blogtype.setTypeId(null);
					blogtypeService.getBlogtypeDAO().save(blogtype);
					List<Blogtype> types = blogtypeService.getBlogtypeDAO().findBySupertypeId(blogtype.getSupertypeId());			
					if(!types.isEmpty()){
						for(int i=0;i<types.size();i++){
							if(types.get(i).getTypename().equals(blogtype.getTypename())){
								currentBlog.setTypeId(types.get(i).getTypeId());
								break;
							}
						}		
					}
				}				
			}
			else
				currentBlog.setTypeId(formerType.getTypeId());
			
			
			//检查blog内容里是否有敏感词
			if(isContainFilterWords())
				currentBlog.setFilterwords(0);
			else
				currentBlog.setFilterwords(1);
			blogService.updateBlog(currentBlog);
		}
		return SUCCESS;
	}

	private boolean isContainFilterWords(){
		if(filterWords==null)		
			fetchFilterWords();
		String blogContent=currentBlog.getContent();
		for(int i=0;i<filterWords.size();i++){
			if(blogContent.contains(filterWords.get(i)))
				return true;
		}
		return false;
	}
	
	private void fetchFilterWords(){
		try{
			filterWords=fileManagerImpl.getWords();			
		}
		catch(Exception e){
			//System.out.println("读取过滤词异常");
			e.printStackTrace();
		}
	}
	
	public BlogServiceImp getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogServiceImp blogService) {
		this.blogService = blogService;
	}

	public BlogTypeServiceImp getBlogtypeService() {
		return blogtypeService;
	}

	public void setBlogtypeService(BlogTypeServiceImp blogtypeService) {
		this.blogtypeService = blogtypeService;
	}

	public UserManagerImpl getUsermanager() {
		return usermanager;
	}

	public void setUsermanager(UserManagerImpl usermanager) {
		this.usermanager = usermanager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Blog getFormerBlog() {
		return formerBlog;
	}

	public void setFormerBlog(Blog formerBlog) {
		this.formerBlog = formerBlog;
	}

	public Blogtype getBlogtype() {
		return blogtype;
	}

	public void setBlogtype(Blogtype blogtype) {
		this.blogtype = blogtype;
	}

	public Blog getCurrentBlog() {
		return currentBlog;
	}

	public void setCurrentBlog(Blog currentBlog) {
		this.currentBlog = currentBlog;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
