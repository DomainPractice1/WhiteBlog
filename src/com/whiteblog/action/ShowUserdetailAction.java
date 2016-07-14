package com.whiteblog.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.dao.UserDAO;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Supertype;
import com.whiteblog.entity.User;
import com.whiteblog.service.ShowUserdetailService;

public class ShowUserdetailAction extends ActionSupport {
	private ShowUserdetailService showUserdetailService;
	private UserDAO userDAO;
	private Integer attentionUserid;
	

	public Integer getAttentionUserid() {
		return attentionUserid;
	}

	public void setAttentionUserid(Integer attentionUserid) {
		this.attentionUserid = attentionUserid;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ShowUserdetailService getShowUserdetailService() {
		return showUserdetailService;
	}

	public void setShowUserdetailService(
			ShowUserdetailService showUserdetailService) {
		this.showUserdetailService = showUserdetailService;
	}

	// <!-- 显示用户信息 -->
	public String excute() {
		System.out.println("我在查看别人的信息");
		
		System.out.println(attentionUserid);
		int attentionID =attentionUserid;
		Map<String, Object> session = ActionContext.getContext().getSession();
		User me = userDAO.findById(attentionID);
		HttpServletRequest request = ServletActionContext.getRequest();
		String userName = me.getUsername();
		request.setAttribute("userName", userName);
		request.setAttribute("userID", me.getUserId());

		// 得到原创数
		int originalcount = showUserdetailService.findtheCreat(me);
		System.out.println("ACTION:原创" + originalcount);
		request.setAttribute("originalcount", originalcount);
		// 得到转发数
		int forwardcount = showUserdetailService.findtheForward(me);
		System.out.println("ACTION:原创" + forwardcount);
		request.setAttribute("forwardcount", forwardcount);
		// 得到国家
		String country = showUserdetailService.findcountry(me);
		request.setAttribute("country", country);
		// 得到省份
		String province = showUserdetailService.findprovince(me);
		request.setAttribute("province", province);
		// 得到城市
		String city = showUserdetailService.findcity(me);
		request.setAttribute("city", city);
		// 得到工作
		String job = showUserdetailService.findjob(me);
		request.setAttribute("job", job);
		// 得到爱好
		List<Supertype> supertype = showUserdetailService.findhobby(me);
		session.put("supertype", supertype);
		// 得到性别
		String sex;
		if (me.getSex() == 0)
			sex = "男";
		else
			sex = "女";
		request.setAttribute("sex", sex);
		// 我关注的
		List<User> attention = showUserdetailService.findActiveAttention(me);
		session.put("attention", attention);
		int attentionAcount = attention.size();
		request.setAttribute("attentionAcount", attentionAcount);
		// 关注我的
		List<User> fans = showUserdetailService.findPassiveAttention(me);
		session.put("fans", fans);
		int fansAcount = fans.size();
		request.setAttribute("fansAcount", fansAcount);
		// 展示文章列表
		List<Blog> myblog = showUserdetailService.showBlog(me);
		System.out.println("我就想看看我的文章列表"+myblog.get(0).getTitle());
		session.put("myblog", myblog);
		//检验用户是否被关注过
		User logUser=(User) session.get("loginUser");
		int ifAttention=showUserdetailService.ifAttention(logUser, attentionUserid);
		request.setAttribute("ifAttention", ifAttention);
		return SUCCESS;
	}

}
