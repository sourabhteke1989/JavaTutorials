package com.example.struts2.action;

import org.apache.commons.lang3.StringUtils;
import com.example.struts2.datatypes.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	
	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String adminLogin(){
		if(user != null && !StringUtils.isEmpty(user.getUserName()) && !StringUtils.isEmpty(user.getPassword()) &&
				user.getUserName().equals("admin") && user.getPassword().equals("admin")){
			return SUCCESS;
		}else{
			return LOGIN;
		}
	}

	public User getModel() {
		return user;
	}
	
}
