
package com.whiteblog.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.service.BlogServiceImp;
import com.whiteblog.service.BlogTypeServiceImp;
import com.whiteblog.service.UserManagerImpl;
import com.whiteblog.service.fileManagerImpl;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.entity.User;
import com.whiteblog.dao.BlogDAO;
import com.whiteblog.dao.BlogtypeDAO;
import com.whiteblog.dao.UserDAO;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class publishAction extends ActionSupport{
	 	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private BlogServiceImp blogService;    
	 	private BlogTypeServiceImp blogtypeService;
	 	private UserManagerImpl usermanager;
	 	private Blog blog;
	 	private Blogtype blogtype;
	 	
	 	private String title;
	 	private String content;
	 	private String tags;
	 	private String category;
	 	private String userName;
	 	private String hint;
	 	private int id;
		//private 
		public String execute(){		
			UserDAO userDAO=usermanager.getUserdao();
			BlogDAO blogDAO=blogService.getBlogDAO();
			BlogtypeDAO blogtypeDAO=blogtypeService.getBlogtypeDAO();
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			User me=(User)session.get("loginUser");
			userName=me.getUsername();
			List<User> result = userDAO.findByUsername(userName);
			int userID=0;
			if(!result.isEmpty()){
				userID=result.get(0).getUserId();
			}
			else{
				hint="发布失败。请先登录再发布！";
				return ERROR;
			}
						
			int typeID=-1;
			List<Blogtype> typesAlready = null;
			if(!tags.trim().equals("")){//优先个人新建的分类
				typesAlready=blogtypeDAO.findByTypename(tags);
				if(!typesAlready.isEmpty()){
					typeID=typesAlready.get(0).getTypeId();
				}
			}
			else{
				typeID=Integer.parseInt(category);
			}
			
								
			if(typeID==-1){
				blogtype=new Blogtype();
				blogtype.setTypename(tags);
				blogtype.setUserId(userID);
							
				blogtypeDAO.save(blogtype);
				
				List<Blogtype> types = blogtypeDAO.findByTypename(tags);			
				if(!types.isEmpty()){
					typeID=types.get(0).getTypeId();				
				}
				else{
					hint="发布失败。";
					return ERROR;
				}
			}
						
			blog=new Blog();
			blog.setContent(content);
			blog.setTitle(title);
			blog.setTypeId(typeID);
			blog.setUserId(userID);
			blog.setUsername(userName);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			blog.setTime(df.format(new Date()));// new Date()为获取当前系统时间
			
			List<String> filterWords = fileManagerImpl.getWords();
			System.out.println("[filterWords size]"+filterWords.size());
			blog.setFilterwords(1);
			for(int i=0;i<filterWords.size();i++){
				if(content.contains(filterWords.get(i))){
					hint="文章中包含敏感词！";
					blog.setFilterwords(0);
					break;
				}else{
					continue;
				}
			}
			
			
			blogDAO.save(blog);
			hint="成功发布！";
			
			List<Blog> newBlog = blogDAO.findAll();
			int blogID=0;
			if(!newBlog.isEmpty()){
				int size=newBlog.size();
				blogID=newBlog.get(size-1).getBlogId();
			}
			else{
				hint="发布不成功请重试！";
				return ERROR;
			}
			id=blogID;
			return SUCCESS;
		}
		public void validate() {
			this.clearErrorsAndMessages();
			if (title == null || title.trim().equals(""))
			{
				addFieldError("title","请输入文章标题");
			}
			if (content == null || content.trim().equals(""))
			{
				addFieldError("content","文章内容为空");
			}
			if ((tags == null || tags.trim().equals(""))&&((category == null || category.trim().equals(""))))
			{
				addFieldError("tags","请选择文章分类或新建个人分类");
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
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			String blogContentFormer=content;
			while(true){
				int start=blogContentFormer.indexOf("[code=");
				if(start==-1){
					break;
				}
				String pre="<pre class=\"brush:";
				//blogContentFormer.replaceFirst("[code=", "<pre class=\"brush:\"");
				int endStart=blogContentFormer.indexOf("]",start);
				String typeCode=blogContentFormer.substring(start+6, endStart);
				String typeJs="";
				switch(typeCode.charAt(0)){
				case 'c':
					if(typeCode.length()==1){//c
						typeJs="cpp";					
					}else if(typeCode.length()==2){//cs
						typeJs="cs";
					}				
					else//css
						typeJs="css";				
					break;
				case 'h':			
					typeJs="xml";
					break;
				case 'j':
					if(typeCode.length()==2)					
						typeJs="js";
					else
						typeJs="java";
					break;
				case 'p':
					if(typeCode.equals("php"))
						typeJs="php";
					else if(typeCode.equals("py"))
						typeJs="python";
					break;				
				default:
					break;
				}
				blogContentFormer=blogContentFormer.substring(0,start)+pre+typeJs+";\">"+blogContentFormer.substring(endStart+1,blogContentFormer.length());
				int endTag=blogContentFormer.indexOf("[/code]");
				blogContentFormer=blogContentFormer.substring(0,endTag)+"</pre>"+blogContentFormer.substring(endTag+7,blogContentFormer.length());
			}	
			//System.out.println(blogContentFormer.contains("<br />"));
			blogContentFormer=blogContentFormer.replace("<br />", "\n");						
			this.content = blogContentFormer;
		}
		public String getTags() {
			return tags;
		}
		public void setTags(String tags) {
			this.tags = tags;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public Blog getBlog() {
			return blog;
		}
		public void setBlog(Blog blog) {
			this.blog = blog;
		}
		public Blogtype getBlogtype() {
			return blogtype;
		}
		public void setBlogtype(Blogtype blogtype) {
			this.blogtype = blogtype;
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
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
	
		
}
