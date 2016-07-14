package com.whiteblog.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.whiteblog.dao.CityDAO;
import com.whiteblog.dao.CookieDAO;
import com.whiteblog.dao.HobbyDAO;
import com.whiteblog.dao.ProvinceDAO;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.entity.City;
import com.whiteblog.entity.Hobby;
import com.whiteblog.entity.Province;
import com.whiteblog.entity.Supertype;
import com.whiteblog.entity.User;
import com.whiteblog.service.BlogManagerImpl;
import com.whiteblog.service.BlogTypeServiceImp;
import com.whiteblog.service.SuperTypeService;
import com.whiteblog.service.fileManagerImpl;

public class BlogTypeAction {
	private String mesContent2;
	private Integer id;
	private Integer bid;
	private BlogTypeServiceImp blogtypeService;
	private BlogManagerImpl blogManager;
	private SuperTypeService superTypeService;
	private CityDAO cityDAO;
	private ProvinceDAO provinceDAO;
	private HobbyDAO hobbyDAO;
	private int supertypeId;
	static public final  String SUCCESS = "success";
	static public final  String ERROR = "error";

	public HobbyDAO getHobbyDAO() {
		return hobbyDAO;
	}

	public void setHobbyDAO(HobbyDAO hobbyDAO) {
		this.hobbyDAO = hobbyDAO;
	}

	public CityDAO getCityDAO() {
		return cityDAO;
	}

	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	public ProvinceDAO getProvinceDAO() {
		return provinceDAO;
	}

	public void setProvinceDAO(ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}

	public SuperTypeService getSuperTypeService() {
		return superTypeService;
	}
	
	public int getSupertypeId() {
		return supertypeId;
	}

	public void setSupertypeId(int supertypeId) {
		this.supertypeId = supertypeId;
	}

	public void setSuperTypeService(SuperTypeService superTypeService) {
		this.superTypeService = superTypeService;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public BlogManagerImpl getBlogManager() {
		return blogManager;
	}
	public void setBlogManager(BlogManagerImpl blogManager) {
		this.blogManager = blogManager;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMesContent2() {
		return mesContent2;
	}
	public void setMesContent2(String mesContent2) {
		this.mesContent2 = mesContent2;
	}
	public BlogTypeServiceImp getBlogtypeService() {
		return blogtypeService;
	}
	public void setBlogtypeService(BlogTypeServiceImp blogtypeService) {
		this.blogtypeService = blogtypeService;
	}
	public String addTags(){
		String str = mesContent2;  
		System.out.println("addTags SuperTag " + supertypeId);
			List<Blogtype> btl = blogtypeService.getBlogtypeDAO().findByTypename(str);
			if(btl.size() < 1) {
				Blogtype bt = new Blogtype();
				bt.setUserId(id);
				bt.setTypename(str); 
				bt.setSupertypeId(supertypeId);
				Blog b = blogManager.getBlogDao().findById(bid);
				Map<String, Object>session = (Map<String, Object>)ActionContext.getContext().getSession();
				User u = (User)session.get("loginUser");
				if(!u.getUserId().equals(b.getUserId()))
					return ERROR;
				blogtypeService.getBlogtypeDAO().save(bt);  
				List<Blogtype> list = blogtypeService.getBlogtypeDAO().findByTypename(str);
				bt = list.get(list.size() - 1);
				b.setTypeId(bt.getTypeId());
				blogManager.getBlogDao().attachDirty(b);
			}
			else{ 
				for(int i = 0; i < btl.size(); i++){
					if(btl.get(i).getTypename().compareTo(str) == 0){
						Blogtype bt = new Blogtype();
						bt.setUserId(id);
						bt.setTypename(str); 
						bt.setSupertypeId(supertypeId);
						Blog b = blogManager.getBlogDao().findById(bid);
						Map<String, Object>session = (Map<String, Object>)ActionContext.getContext().getSession();
						User u = (User)session.get("loginUser");
						if(!u.getUserId().equals(b.getUserId()))
							return ERROR;
						blogtypeService.getBlogtypeDAO().attachDirty(bt);
						List<Blogtype> list = blogtypeService.getBlogtypeDAO().findByTypename(str);
						bt = list.get(list.size() - 1);
						b.setTypeId(bt.getTypeId());
						blogManager.getBlogDao().attachDirty(b);
						break;
					}
				}
			}
			
			/*增加了一部分工能，显示所有的Tags*/
			@SuppressWarnings("unchecked")
			List<Blogtype> abtl = (List<Blogtype>)blogtypeService.getBlogtypeDAO().findAll();
			
			List<Blogtype> tmpList = new ArrayList<Blogtype>();
			
			for(Blogtype bt: abtl){
				boolean flag = false;
				for(Blogtype tmp: tmpList){
					if(tmp.getTypename().compareTo(bt.getTypename())== 0){
						flag = true;
						break;
					}
				}
				if(!flag)
					tmpList.add(bt);
			}
			
			ActionContext.getContext().put("allTags", tmpList);		
		return SUCCESS;
	}
//	
	
	public String preparationAction(){
		/*增加了一部分工能，显示所有的Tags*/
		@SuppressWarnings("unchecked")
		List<Blogtype> abtl = (List<Blogtype>)blogtypeService.getBlogtypeDAO().findAll();
		
		List<Blogtype> tmpList = new ArrayList<Blogtype>();
		
		for(Blogtype bt: abtl){
			boolean flag = false;
			for(Blogtype tmp: tmpList){
				if(tmp.getTypename().compareTo(bt.getTypename())== 0){
					flag = true;
					break;
				}
			}
			if(!flag)
				tmpList.add(bt);
		}
		@SuppressWarnings("unchecked")
		List<Supertype> sl = superTypeService.getSupertypeDAO().findAll();
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.put("allTags", tmpList);
		session.put("allSuperTags", sl);

		
		String p = org.apache.struts2.ServletActionContext.getServletContext().getRealPath("/");
		
		fileManagerImpl.readTxtFile(p+"/WEB-INF/classes/words.txt");
		

		/*拦截器使用的数据库连接*/
		CookieDAO.connectDB();
		
		return SUCCESS;
	}
	
}
