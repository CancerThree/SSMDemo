package com.cmb.zh.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cmb.zh.Util.CommonException;
import com.cmb.zh.Util.CommonVal;
import com.cmb.zh.Util.CommonVal.ErrorCode;
import com.cmb.zh.domain.User;
import com.cmb.zh.domain.UserInfo;
import com.cmb.zh.service.user.UserAdminService;

@Controller
public class AdminUserController {
	@Resource
	private UserAdminService userAdminService;
	
	@RequestMapping("/searchUser")
//	@ResponseBody
	public ModelAndView adminUser(String name, HttpSession session){
//		System.out.println("session is " + session);
//		User thisUser = (User) session.getAttribute(CommonVal.SESSION_KEY_USER);
//		boolean isOnline = (boolean) session.getAttribute(CommonVal.SESSION_KEY_ONLINE);
		ModelAndView mad = new ModelAndView("searchUser");
		User thisUser = (User) session.getAttribute(CommonVal.SESSION_KEY_USER);
		System.out.println("name is " + name);
//		if(thisUser != null){
//			session.removeAttribute(CommonVal.SESSION_KEY_USER);
//			session.removeAttribute(CommonVal.SESSION_KEY_ONLINE);
//		}
		HashMap<User, UserInfo> users = new HashMap<User, UserInfo>();
		HashMap<BigDecimal, Integer> isFollow = new HashMap<BigDecimal, Integer>();
		
		if (thisUser != null) {
			try {
				users = userAdminService.getUsersAndInfo(name);
				for (Entry<User, UserInfo> entry : users.entrySet()) {
					User key = entry.getKey();
					Integer hasFollow = 0;
					hasFollow = userAdminService.getFollowsCount(thisUser.getUserid(), key.getUserid());
					if (thisUser.getUserid() == key.getUserid()) {
						hasFollow = 0;
					}
					isFollow.put(key.getUserid(), hasFollow);
				}
			} catch (CommonException e) {
				System.out.print(e.getErrorCode());
				e.printStackTrace();
			}
		}
//		ArrayList<User> users = userAdminService.SearchUser(name);
//		users = users == null ? new ArrayList<User>() : users;
		
		mad.addObject("users", users);
		mad.addObject("isFollow", isFollow);
		
		return mad;
	}
}
