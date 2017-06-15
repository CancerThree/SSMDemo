package com.cmb.zh.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cmb.zh.Util.CommonVal;
import com.cmb.zh.Util.CommonVal.ErrorCode;
import com.cmb.zh.domain.User;
import com.cmb.zh.service.user.UserAdminService;

@Controller
public class AdminUserController {
	@Resource
	private UserAdminService userAdminService;
	
	@RequestMapping("/searchUser")
	@ResponseBody
	public ModelAndView adminUser(String name){
//		System.out.println("session is " + session);
//		User thisUser = (User) session.getAttribute(CommonVal.SESSION_KEY_USER);
//		boolean isOnline = (boolean) session.getAttribute(CommonVal.SESSION_KEY_ONLINE);
		ModelAndView mad = new ModelAndView("adminUser");
		System.out.println("name is " + name);
//		if(thisUser != null){
//			session.removeAttribute(CommonVal.SESSION_KEY_USER);
//			session.removeAttribute(CommonVal.SESSION_KEY_ONLINE);
//		}
		ArrayList<User> users = userAdminService.SearchUser(name);
		users = users == null ? new ArrayList<User>() : users;
		
		mad.addObject("users", users);
		
		return mad;
	}
}
