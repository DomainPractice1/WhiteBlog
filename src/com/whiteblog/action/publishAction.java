
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
	 	private Blog blog;
	 	private Blogtype blogtype;
	 	
	 	private String title;
	 	private String content;
	 	private String tags;
	 	private String category;
	 	private String userName;
	 	private String hint;
	 	private int id;	
		 
		public String execute(){		
			BlogDAO blogDAO=blogService.getBlogDAO();
			BlogtypeDAO blogtypeDAO=blogtypeService.getBlogtypeDAO();
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			User me=(User)session.get("loginUser");
			int userID=me.getUserId();
			userName=me.getUsername();
			
			int typeID=-1;
			Blogtype newType=new Blogtype();
			newType.setTypename(tags);
			newType.setUserId(userID);
			newType.setSupertypeId(Integer.parseInt(category));
			blogtypeDAO.save(newType);
			List<Blogtype> newAddType=blogtypeDAO.findByExample(newType);
			if(newAddType!=null)
				typeID=newAddType.get(0).getTypeId();
			else 
				return ERROR;//该分类未成功添加
						
			blog=new Blog();
			
			blog.setTitle(title);
			blog.setTypeId(typeID);
			blog.setUserId(userID);
			blog.setUsername(userName);
			blog.setStatus(0);//原创
			//初始化
			blog.setCommentnumber(0);
			blog.setForwardnumber(0);
			blog.setLikenumber(0);
			blog.setViewnumber(0);

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			blog.setTime(df.format(new Date()));// new Date()为获取当前系统时间
			
			List<String> filterWords = fileManagerImpl.getWords();
			System.out.println("[filterWords size]"+filterWords.size());
			blog.setFilterwords(1);
			String blogcontent = content;
			
			for(int i=0;i<filterWords.size();i++){
				if(blogcontent.contains(filterWords.get(i))){
					blogcontent = blogcontent.replace(filterWords.get(i),"<markp>"+filterWords.get(i)+"</markp>");	
					System.out.println("["+i+"]"+content);
					hint="文章中包含敏感词！";
					blog.setFilterwords(0);
				}
			}			
			
			blog.setContent(blogcontent);
			
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
			if((category == null || category.trim().equals(""))){
				addFieldError("category","请选择文章分类");
			}			
			if((tags == null || tags.trim().equals(""))){
				addFieldError("tags","请添加个人分类，便于管理");
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
