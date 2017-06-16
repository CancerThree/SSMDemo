package com.cmb.zh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cmb.zh.Util.CommonVal;
import com.cmb.zh.Util.CommonVal.ErrorCode;
import com.cmb.zh.domain.User;
import com.cmb.zh.domain.UserInfo;
import com.cmb.zh.service.user.UserInfoService;

@Controller()
@RequestMapping("/explore")
public class selfInfoController {
	
	@Resource
	private UserInfoService usrInfoService;
	
	@RequestMapping("/selfIndex")
	public ModelAndView getSelfIndex(HttpSession session) {
		User thisUser = (User) session.getAttribute(CommonVal.SESSION_KEY_USER);
		ModelAndView mad = null;
		
		if (thisUser != null) {
			mad = new ModelAndView("myProfile");
			UserInfo userInfo = usrInfoService.getUserInfoByUserId(thisUser.getUserid());
			mad.addObject("userInfo", userInfo);
		}

		return mad;
	}
	
	@RequestMapping("/selfIndex/saveSelfInfo")
	@ResponseBody
	public ErrorCode saveSelfInfo(UserInfo userInfo, HttpSession session) {
		ErrorCode error = null;
		
		if (userInfo == null && session == null) {
			error = ErrorCode.ERROR_UNKOWN_ERROR;
		} else {
			userInfo.setUserid(((User)session.getAttribute(CommonVal.SESSION_KEY_USER)).getUserid());
			error = usrInfoService.updateUserInfo(userInfo);
		}
		
		return error;
	}
}
