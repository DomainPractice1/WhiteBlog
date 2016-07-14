package com.whiteblog.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Supertype;
import com.whiteblog.entity.User;
import com.whiteblog.service.DeleteCollectService;
import com.whiteblog.service.ShowUserinfo;

public class DeleteCollectAction extends ActionSupport {
	private DeleteCollectService deleteCollectService;
	private Integer blogid;
	//private ShowUserinfoAction showUserinfoAction;
	private ShowUserinfo showUserinfo;

	public ShowUserinfo getShowUserinfo() {
		return showUserinfo;
	}

	public void setShowUserinfo(ShowUserinfo showUserinfo) {
		this.showUserinfo = showUserinfo;
	}
	public Integer getBlogid() {
		return blogid;
	}

	public void setBlogid(Integer blogid) {
		this.blogid = blogid;
	}

	public DeleteCollectService getDeleteCollectService() {
		return deleteCollectService;
	}

	public void setDeleteCollectService(DeleteCollectService deleteCollectService) {
		this.deleteCollectService = deleteCollectService;
	}
	public String excute(){
		//Map<String, Object> session = ActionContext.getContext().getSession();
		//int blogid = (Integer) session.get("blogid");
		System.out.println(blogid);
		deleteCollectService.deleteCollect(blogid);
		//showUserinfoAction.showcreat();
		Map<String, Object> session = ActionContext.getContext().getSession();
		User me = (User) session.get("loginUser");
		String userName = me.getUsername();
		HttpServletRequest request = ServletActionContext.getRequest();
		// 得到原创数
		int originalcount = showUserinfo.findtheCreat(me);
		System.out.println("ACTION:原创"+originalcount);
		request.setAttribute("originalcount", originalcount);
		// 得到转发数
		int forwardcount = showUserinfo.findtheForward(me);
		System.out.println("ACTION:原创"+forwardcount);
		request.setAttribute("forwardcount", forwardcount);
		// 得到国家
		String country = showUserinfo.findcountry(me);
		if (country == null) {
			country = "暂无";
		}
		request.setAttribute("country", country);
		// 得到省份
		String province = showUserinfo.findprovince(me);
		if(province==null)
			province=" ";
		request.setAttribute("province", province);
		// 得到城市
		String city = showUserinfo.findcity(me);
		if(city==null)
			city=" ";
		request.setAttribute("city",city);
		// 得到工作
		String job = showUserinfo.findjob(me);
		if (job == null) {
			job = "暂无";
		}
		request.setAttribute("job", job);
		// 得到爱好
		List<Supertype> supertype=showUserinfo.findhobby(me);
		session.put("supertype", supertype);
		// 得到性别
		String sex;
		if(me.getSex()==0)
			sex="男";
		else
			sex="女";
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
		request.setAttribute("attentionAcount",attentionAcount);
		// 关注我的
		List<User> fans=showUserinfo.findPassiveAttention(me);
		session.put("fans", fans);
		int fansAcount=fans.size();
		request.setAttribute("fansAcount",fansAcount);
		return SUCCESS;
	}

}
