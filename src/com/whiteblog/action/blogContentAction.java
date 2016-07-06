package com.whiteblog.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.form.BlogContentForm;
import com.whiteblog.service.BlogContentManageImpl;
import com.whiteblog.service.BlogTypeServiceImp;
import com.whiteblog.service.SuperTypeService;
import com.whiteblog.service.UserManagerImpl;
import com.whiteblog.entity.*;
public class blogContentAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BlogContentManageImpl blogContentManage;
	public final String SUCCESS = "success";
	public final String FAIL = "fail";
	private UserManagerImpl userManager;
	private BlogTypeServiceImp blogtypeService;
	private SuperTypeService superTypeService;
	private List<Blogtype> btl ;
	
	public SuperTypeService getSuperTypeService() {
		return superTypeService;
	}
	public void setSuperTypeService(SuperTypeService superTypeService) {
		this.superTypeService = superTypeService;
	}
	public List<Blogtype> getBtl() {
		return btl;
	}
	public void setBtl(List<Blogtype> btl) {
		this.btl = btl;
	}
	public BlogTypeServiceImp getBlogtypeService() {
		return blogtypeService;
	}
	public void setBlogtypeService(BlogTypeServiceImp blogtypeService) {
		this.blogtypeService = blogtypeService;
	}
	public UserManagerImpl getUserManager() {
		return userManager;
	}
	public void setUserManager(UserManagerImpl userManager) {
		this.userManager = userManager;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BlogContentManageImpl getBlogContentManage() {
		return blogContentManage;
	}
	public void setBlogContentManage(BlogContentManageImpl blogContentManage) {
		this.blogContentManage = blogContentManage;
	}
	/**
	 * @return 
	 */
	public String execute(){
		Blog ins = blogContentManage.findById(id);
		if(ins == null)
			return FAIL;
		String username = ins.getUsername();
		System.out.println(username + " and " + ins.getContent() + " BlogContent");		
		
		BlogContentForm blogContentForm = new BlogContentForm(ins, username); 		
		System.out.println(blogContentForm.getUsername() + "blogcontentform");
		Map<String, Object> map = ActionContext.getContext().getSession();
		map.put("req", blogContentForm);
		BlogContentForm b = (BlogContentForm)map.get("req");
		System.out.println(b.getUsername() + " is from map of blogcontentAction");
		ActionContext.getContext().put("req", b);
		btl = blogtypeService.getBlogtypeDAO().findByUserId(ins.getUserId());
		ActionContext.getContext().put("re", btl);
		//ActionContext.getContext().getSession().put("req", blogContentForm);
		ActionContext.getContext().getSession().put("blogId",id);
		
		/*标签的部分*/ 
		int bti = b.getBlog().getTypeId();
		Blogtype bt = blogtypeService.getBlogtype(bti);
		ActionContext.getContext().put("bt", bt);
		
		/*SuperType类的标签哎*/
		Supertype st = superTypeService.getSupertypeDAO().findById(bt.getSupertypeId());
		ActionContext.getContext().put("sbt", st);
		
		return SUCCESS;
	}
	
	public String uncheck(){
		Blog ins = blogContentManage.findById(id);
		if(ins == null)
			return FAIL;
		String username = ins.getUsername();
		System.out.println(username + " and " + ins.getContent() + " BlogContent");		
		
		BlogContentForm blogContentForm = new BlogContentForm(ins, username); 		
		System.out.println(blogContentForm.getUsername() + "blogcontentform");
		Map<String, Object> map = ActionContext.getContext().getSession();
		map.put("req", blogContentForm);
		BlogContentForm b = (BlogContentForm)map.get("req");
		System.out.println(b.getUsername() + " is from map of blogcontentAction");
		ActionContext.getContext().put("req", b);
		btl = blogtypeService.getBlogtypeDAO().findByUserId(ins.getUserId());
		ActionContext.getContext().put("re", btl);
		//ActionContext.getContext().getSession().put("req", blogContentForm);
		ActionContext.getContext().getSession().put("blogId",id);
		
		
		return SUCCESS;		
	}
	
	public String review(){		
		Map<String,Object> session = ActionContext.getContext().getSession();
		Integer blogID = (Integer)session.get("blogId");
		Blog blog = blogContentManage.findById(blogID);
		blog.setFilterwords(1);
		blogContentManage.setupdateBlog(blog);
		return SUCCESS;
	}
}
