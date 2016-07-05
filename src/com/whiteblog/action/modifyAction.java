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
			Blogtype formerType=blogtypeService.getBlogtype(formerBlog.getTypeId());
			
			//设置blog的type
			List<Blogtype> typesAlready = null;
			if(blogtype.getTypename()==null || blogtype.getTypename().trim()==""){
				currentBlog.setTypeId(Integer.parseInt(category));
			}
			else if(!formerType.getTypename().equals(blogtype.getTypename())){
				typesAlready=blogtypeService.getBlogtype(blogtype.getTypename());
				if(!typesAlready.isEmpty()){
					currentBlog.setTypeId(typesAlready.get(0).getTypeId());
				}
				else{
					blogtype.setUserId(formerBlog.getUserId());
					blogtypeService.addBlogtype(blogtype);
					List<Blogtype> types = blogtypeService.getBlogtype(blogtype.getTypename());			
					if(!types.isEmpty()){
						currentBlog.setTypeId(types.get(0).getTypeId());				
					}
				}
			}else{
				currentBlog.setTypeId(formerBlog.getTypeId());
			}
			
			//检查blog内容里是否有敏感词
			if(isContainFilterWords())
				currentBlog.setFilterwords(0);
			else
				currentBlog.setFilterwords(1);
			blogService.updateBlog(currentBlog);
		}
		return SUCCESS;
	}
	
	public void validate() {
		this.clearErrorsAndMessages();
		if (currentBlog.getTitle() == null || currentBlog.getTitle().trim().equals(""))
		{
			addFieldError("title","请输入文章标题");
		}
		if (currentBlog.getContent() == null || currentBlog.getContent().trim().equals(""))
		{
			addFieldError("content","文章内容为空");
		}
		if ((blogtype.getTypename()== null || blogtype.getTypename().trim().equals(""))&&((category == null || category.trim().equals(""))))
		{
			addFieldError("tags","请选择文章分类或新建个人分类");
		}			
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
			System.out.println("读取过滤词异常");
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
}
