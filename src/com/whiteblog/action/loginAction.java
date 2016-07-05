package com.whiteblog.action;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.entity.User;
import com.whiteblog.form.UserForm;
import com.whiteblog.service.BlogTypeServiceImp;
import com.whiteblog.service.UserManagerImpl;
import com.whiteblog.service.fileManagerImpl;

public class loginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8628585232251444052L;
	
	private UserManagerImpl usermanager;
	
	private UserForm userform;
	
	private BlogTypeServiceImp blogtypeService;
		
	public BlogTypeServiceImp getBlogtypeService() {
		return blogtypeService;
	}

	public void setBlogtypeService(BlogTypeServiceImp blogtypeService) {
		this.blogtypeService = blogtypeService;
	}

	public UserForm getUserform() {
		return userform;
	}

	public void setUserform(UserForm userform) {
		this.userform = userform;
	}

	public UserManagerImpl getUsermanager() {
		return usermanager;
	}

	public void setUsermanager(UserManagerImpl usermanager) {
		this.usermanager = usermanager;
	}
	
	public String execute(){		
		if(usermanager.checklogin(userform).equals("user")){
			
			Map<String,Object> session = ActionContext.getContext().getSession();
			
			User loginUser = usermanager.findUser(userform.getUsername());
			
			session.put("loginUser",loginUser);
			
			/*增加了一部分工能，显示所有的Tags*/
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
			
			session.put("allTags", tmpList);

			String p = org.apache.struts2.ServletActionContext.getServletContext().getRealPath("/");
			
			fileManagerImpl.readTxtFile(p+"/WEB-INF/classes/words.txt");
			
			return "user";
			
		}else if(usermanager.checklogin(userform).equals("admin")){
			
			Map<String,Object> session = ActionContext.getContext().getSession();
			
			User loginUser = usermanager.findUser(userform.getUsername());
			
			session.put("loginUser",loginUser);
			
			return "admin";
			
		}else{
			return ERROR;
		}
	
	}
	
}
