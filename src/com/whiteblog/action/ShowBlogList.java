package com.whiteblog.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Application;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Likeit;
import com.whiteblog.entity.User;
import com.whiteblog.form.checkLikeForm;
import com.whiteblog.service.LikeService;
import com.whiteblog.service.ShowBlogListService;
import com.whiteblog.service.UserManagerImpl;

public class ShowBlogList extends ActionSupport{
	private List<Blog> blogList;
	private List<Blog> unCheckBlog;
	private ShowBlogListService showBlogListService;
	private UserManagerImpl userManager;
	private LikeService likeService;

	public String execute(){
		System.out.println("[At ShowBlogList] + [Load likelist]"); 	
		Map<String,Object> session = ActionContext.getContext().getSession();	
		blogList=showBlogListService.getAllBlog();
		System.out.println("blogList size:"+blogList.size());				
		for(int i=0;i<blogList.size();i++){
			if(blogList.get(i).getFilterwords()==0){
				blogList.remove(i);
				i--;
			}
		}
		System.out.println("blogList size:"+blogList.size());		
		ActionContext.getContext().getSession().put("blogList", blogList);		
		
		Map<Object,Double> blogrank = new HashMap<Object,Double>();
		Map<String,Double> userrank = new HashMap<String,Double>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		long nowDate = calendar.getTime().getTime();
		
		for(int i=0;i<blogList.size();i++){			
			int likenumber = blogList.get(i).getLikenumber(); 
			int viewnumber = blogList.get(i).getViewnumber();			
			long publishDate = 0L;
			try {
				publishDate = sdf.parse(blogList.get(i).getTime()).getTime();
			} catch (ParseException e) {
				System.out.println("publishDate Error");
				e.printStackTrace();
			}
			long betweenHour = (nowDate - publishDate)/(1000 * 60 * 60);
			double rankvalue = (Math.log10(viewnumber+1)*4 + likenumber)/Math.pow((betweenHour + 2), 1.8);
			blogrank.put(blogList.get(i), rankvalue);
			
			String userName = blogList.get(i).getUsername();
			
			if(userrank.containsKey(userName)){
				double uservalue = userrank.get(userName);
				uservalue += rankvalue;
				System.out.println("["+userName+"]"+uservalue);
			}else{
				userrank.put(userName, rankvalue);
				System.out.println("["+userName+"]"+rankvalue);
			}
			
		}
		
		List<Map.Entry<Object,Double>> mappingList = new ArrayList<Map.Entry<Object,Double>>(blogrank.entrySet());
		
		Collections.sort(mappingList, new Comparator<Map.Entry<Object,Double>>(){ 
		public int compare(Map.Entry<Object,Double> mapping1,Map.Entry<Object,Double> mapping2){ 
			
			if((mapping2.getValue() - mapping1.getValue())>0){
				return 1;
			}else if((mapping2.getValue() - mapping1.getValue())==0){
				return 0;
			}else{
				return -1;
			}
		} 
		}); 
		
		for(Map.Entry<Object,Double> mapping:mappingList){ 
			   System.out.println(mapping.getKey()+":"+mapping.getValue()); 
		} 
		blogrank.clear();
		
		List<Blog> topblog = new ArrayList<Blog>();	
		for(int i=0;i<6;i++){
			Blog blog = (Blog) mappingList.get(i).getKey();
			topblog.add(blog);
		}	
		session.put("topblog", topblog);
		
		List<Map.Entry<String,Double>> urList = new ArrayList<Map.Entry<String,Double>>(userrank.entrySet());
		
		Collections.sort(urList, new Comparator<Map.Entry<String,Double>>(){ 
		public int compare(Map.Entry<String,Double> mapping1,Map.Entry<String,Double> mapping2){ 
			
			if((mapping2.getValue() - mapping1.getValue())>0){
				return 1;
			}else if((mapping2.getValue() - mapping1.getValue())==0){
				return 0;
			}else{
				return -1;
			}
		} 
		}); 
		
		for(Map.Entry<String,Double> mapping:urList){ 
			   System.out.println(mapping.getKey()+":"+mapping.getValue()); 
		} 
		
		List<User> topuser = new ArrayList<User>();	
		if(urList.size()<6){
			for(int i=0;i<urList.size();i++){
				User entry = userManager.findUser(urList.get(i).getKey());
				topuser.add(entry);
			}
		}else{
			for(int i=0;i<6;i++){
				User entry = userManager.findUser(urList.get(i).getKey());
				topuser.add(entry);
			}
		}
		session.put("topuser", topuser);
		
		
		/*载入点赞列表进行检查*/
		if(!session.containsKey("loginUser")){
			return SUCCESS;
		}else{
			User u = (User)session.get("loginUser");
			int userId = u.getUserId();
			List<checkLikeForm> list = new ArrayList<checkLikeForm>();
			List<Likeit> ll = likeService.getLikeitDAO().findAll();
			for(Blog b : blogList){
				String res = "0";
				for(Likeit i : ll){
					if(b.getBlogId().equals(i.getBlogId() )&& b.getUserId().equals(u.getUserId()))
						res = new String("1");
				} 
				checkLikeForm f = new checkLikeForm(b.getBlogId(), res);
				System.out.println("res: " + res);
				list.add(f);
			} 
			ActionContext.getContext().put("one", "1");
			ActionContext.getContext().put("likeitList", list);
		}
				
		return SUCCESS;
	}
	
	public String changeBlogList(){
//		Map<String,Object> session = ActionContext.getContext().getSession();
//		if(!session.containsKey("loginUser")){
//			blogList=showBlogListService.getAllBlog();
//		}else{
//			int userID = (Integer) session.get("loginUser");	
//			blogList=showBlogListService.findByUserId(userID);
//		}
		return SUCCESS;
	}

	public List<Blog> getBlogList() {
		return blogList;
	}

	public LikeService getLikeService() {
		return likeService;
	}

	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}

	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}

	public ShowBlogListService getShowBlogListService() {
		return showBlogListService;
	}

	public void setShowBlogListService(ShowBlogListService showBlogListService) {
		this.showBlogListService = showBlogListService;
	}

	public UserManagerImpl getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManagerImpl userManager) {
		this.userManager = userManager;
	}

	public String showUNCheckBlog(){
		
		Map<String,Object> session = ActionContext.getContext().getSession();	
		unCheckBlog = showBlogListService.getuncheckBlog();
		System.out.println("[uncheckblog size]:"+unCheckBlog.size());
		session.put("uncheckblog",unCheckBlog);
		
		return SUCCESS;
	}
}
