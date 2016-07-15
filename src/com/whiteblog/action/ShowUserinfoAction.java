	package com.whiteblog.action;

	import java.util.ArrayList;
import java.util.List;
import java.util.Map;

	import javax.servlet.http.HttpServletRequest;

	import org.apache.struts2.ServletActionContext;

	import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.dao.CityDAO;
import com.whiteblog.dao.HobbyDAO;
import com.whiteblog.dao.ProvinceDAO;
import com.whiteblog.dao.SupertypeDAO;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.City;
import com.whiteblog.entity.Collection;
import com.whiteblog.entity.Hobby;
import com.whiteblog.entity.Province;
import com.whiteblog.entity.Supertype;
import com.whiteblog.entity.User;
import com.whiteblog.service.ShowUserinfo;

public class ShowUserinfoAction extends ActionSupport {
	
		private ShowUserinfo showUserinfo;
		private CityDAO cityDAO;
		private ProvinceDAO provinceDAO;
		private HobbyDAO hobbyDAO;
		private SupertypeDAO supertypeDAO;
		private List<User> attentionlist;
		private List<User> fanslist;	
		public List<User> getAttentionlist() {
			return attentionlist;
		}

		public void setAttentionlist(List<User> attentionlist) {
			this.attentionlist = attentionlist;
		}

		public List<User> getFanslist() {
			return fanslist;
		}

		public void setFanslist(List<User> fanslist) {
			this.fanslist = fanslist;
		}
		
		public SupertypeDAO getSupertypeDAO() {
			return supertypeDAO;
		}

		public void setSupertypeDAO(SupertypeDAO supertypeDAO) {
			this.supertypeDAO = supertypeDAO;
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

		public HobbyDAO getHobbyDAO() {
			return hobbyDAO;
		}

		public void setHobbyDAO(HobbyDAO hobbyDAO) {
			this.hobbyDAO = hobbyDAO;
		}

		public ShowUserinfo getShowUserinfo() {
			return showUserinfo;
		}

		public void setShowUserinfo(ShowUserinfo showUserinfo) {
			this.showUserinfo = showUserinfo;
		}

		// <!-- 显示用户信息 -->
		public String showcreat() {
			Map<String, Object> session = ActionContext.getContext().getSession();
			User me = (User) session.get("loginUser");
			String userName = me.getUsername();
			HttpServletRequest request = ServletActionContext.getRequest();
			// 得到原创数
			int originalcount = showUserinfo.findtheCreat(me);
			System.out.println("ACTION:原创"+originalcount);
			ActionContext.getContext().put("originalcount", originalcount);
			//request.setAttribute("originalcount", originalcount);
			// 得到转发数
			int forwardcount = showUserinfo.findtheForward(me);
			System.out.println("ACTION:原创"+forwardcount);
			ActionContext.getContext().put("forwardcount", forwardcount);
			//request.setAttribute("forwardcount", forwardcount);
			// 得到国家
			String country = showUserinfo.findcountry(me);
			if (country == null) {
				country = "暂无";
			}
			ActionContext.getContext().put("country", country);
			//request.setAttribute("country", country);
			// 得到省份
			String province = showUserinfo.findprovince(me);
			if(province==null)
				province=" ";
			ActionContext.getContext().put("province", province);
			//request.setAttribute("province", province);
			//ActionContext.getContext().put("province", province);
			// 得到城市
			String city = showUserinfo.findcity(me);
			if(city==null)
				city=" ";
			ActionContext.getContext().put("city", city);
			//request.setAttribute("city",city);
			// 得到工作
			String job = showUserinfo.findjob(me);
			if (job == null) {
				job = "暂无";
			}
			ActionContext.getContext().put("job", job);
			//request.setAttribute("job", job);
			// 得到爱好
			List<Supertype> supertype=showUserinfo.findhobby(me);
			session.put("supertype", supertype);
			// 得到性别
			String sex;
			if(me.getSex()==0)
				sex="男";
			else
				sex="女";
			ActionContext.getContext().put("sex", sex);
			request.setAttribute("sex",sex);
			// 展示相关推荐
		    List<Blog> related=showUserinfo.findRelated(me);
			session.put("related",related);
			// 展示我的收藏
			List<Blog> collection=showUserinfo.findcollection(me);
			session.put("collection", collection);
			// 我关注的
			List<User> attention=showUserinfo.findActiveAttention(me);
			session.put("attention", attention);
			int attentionAcount=attention.size();
			ActionContext.getContext().put("attentionAcount", attentionAcount);
			//request.setAttribute("attentionAcount",attentionAcount);
			// 关注我的
			List<User> fans=showUserinfo.findPassiveAttention(me);
			session.put("fans", fans);
			int fansAcount=fans.size();
			ActionContext.getContext().put("fansAcount", fansAcount);
			//request.setAttribute("fansAcount",fansAcount);
			
			/*加载所有的城市信息*/
			@SuppressWarnings("unchecked")
			List<City> l = cityDAO.findAll();
			@SuppressWarnings("unchecked")
			List<Province> pl = provinceDAO.findAll();
			@SuppressWarnings("unchecked")
			List<Hobby> hl = hobbyDAO.findAll();
			List<City> cl = new ArrayList<City>();
			List<Supertype> superl = new ArrayList<Supertype>();
			for(int i = 0; i < hl.size(); i ++){
				int t = Integer.parseInt(hl.get(i).getSupertypeId());
				Supertype u = supertypeDAO.findById(t) ;
				u.setSupertypeId(hl.get(i).getHobbyId());
				superl.add(u);
			}
			
			for(int i = 0; i < 18; i ++)
				cl.add(l.get(i));/*
			ActionContext.getContext().getSession().put("cl", cl);
			ActionContext.getContext().getSession().put("pl", pl);
			ActionContext.getContext().getSession().put("hl", hl);*/ 
			ActionContext.getContext().put("cl", cl);
			ActionContext.getContext().put("pl", pl);
			ActionContext.getContext().put("hl", superl);
			return SUCCESS;
		}
		public String execute(){
			Map<String, Object> session = ActionContext.getContext().getSession();
			User me = (User) session.get("loginUser");
			attentionlist = new ArrayList<User>();
			fanslist = new ArrayList<User>();
			attentionlist = showUserinfo.findActiveAttention(me);
			// 关注我的
			fanslist = showUserinfo.findPassiveAttention(me);
			
			return SUCCESS;
		}
}

