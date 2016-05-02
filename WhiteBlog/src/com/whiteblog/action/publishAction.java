package com.whiteblog.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.service.BlogServiceImp;
import com.whiteblog.service.BlogTypeServiceImp;
import com.whiteblog.service.UserManagerImpl;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.entity.User;
import com.whiteblog.dao.BlogDAO;
import com.whiteblog.dao.BlogtypeDAO;
import com.whiteblog.dao.UserDAO;
import java.util.Date;
import java.text.SimpleDateFormat;

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
	 	private String userName;
	 	private String hint;
		//private 
		public String execute(){		
			UserDAO userDAO=usermanager.getUserdao();
			BlogDAO blogDAO=blogService.getBlogDAO();
			BlogtypeDAO blogtypeDAO=blogtypeService.getBlogtypeDAO();
			
			List<User> result = userDAO.findByUsername(userName);
			int userID=0;
			if(!result.isEmpty()){
				userID=result.get(0).getUserId();
			}
			else{
				hint="发布失败。请先登录再发布！";
				return ERROR;
			}
						
			int typeID=0;
			List<Blogtype> typesAlready=blogtypeDAO.findByTypename(tags);
			if(!typesAlready.isEmpty()){
				typeID=typesAlready.get(0).getTypeId();
			}
			else{
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
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			blog.setTime(df.format(new Date()));// new Date()为获取当前系统时间
			blogDAO.save(blog);
			hint="成功发布！";
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
			if (tags == null || tags.trim().equals(""))
			{
				addFieldError("tags","请定义文章分类");
			}
			if (userName == null || userName.trim().equals(""))
			{
				addFieldError("userName","用户未登录");
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
			this.content = content;
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
	
		
}
