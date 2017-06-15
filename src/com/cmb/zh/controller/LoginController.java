package com.cmb.zh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmb.zh.Util.CommonUtil;
import com.cmb.zh.Util.CommonVal.ErrorCode;
import com.cmb.zh.domain.User;
import com.cmb.zh.service.user.RegisterService;

@Controller
public class LoginController {

	@Resource
	private RegisterService regService;
	
	private ErrorCode checkEmail(String email) {
		String pattern = "^\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
		
		if (CommonUtil.isFullyMatchRegex(email, pattern) &&
				email.length() < 50) {
			return ErrorCode.OK;
		}
		
		return ErrorCode.ERROR_EMAIL_INVALID;
	}
	
	private ErrorCode checkPwd(String pwd) {
		String pattern = "[\\(~\\!@#\\$%\\^&\\*\\(\\)\\+\\[\\]\\{\\}\\|;:\'\",\\./<\\>\\?\\)\\-\\w]{6,16}";

		if (CommonUtil.isFullyMatchRegex(pwd, pattern))
		{
			return ErrorCode.OK;
		}
			
		return ErrorCode.ERROR_PWD_INVALID;
	}
	
	private ErrorCode checkUserName(String name) {
		if (name != null && name.length() > 0 && name.length() < 20) {
			return ErrorCode.OK;
		}
		
		return ErrorCode.ERROR_USER_NAME_INVALID;
	}
	
	@RequestMapping(value="/Login", method=RequestMethod.POST)
	@ResponseBody
	public ErrorCode login(User user, HttpSession session) {
		if (user == null) {
			return ErrorCode.ERROR_UNKOWN_ERROR;
		}
		
		ErrorCode error = checkEmail(user.getEmail());
		if (error == ErrorCode.OK) {
			error = checkPwd(user.getPwd());
		}
		
		if (error == ErrorCode.OK) {
			error = regService.login(user, session);
		}
//		session.setAttribute("user", user);
		return error;
	}
	
	@RequestMapping(value="/Regist", method=RequestMethod.POST)
	@ResponseBody
	public ErrorCode regist(User user, HttpSession session) {
		if (user == null) {
			return ErrorCode.ERROR_UNKOWN_ERROR;
		}
		
		ErrorCode error = checkUserName(user.getUsername());
		if (error == ErrorCode.OK) {
			error = checkEmail(user.getEmail());
		}
		if (error == ErrorCode.OK) {
			error = checkPwd(user.getPwd());
		}
		if (error == ErrorCode.OK) {
			error = regService.register(user, session);
		}
		
		return error;
	}
	
	public static void main(String[] args) {
//		System.out.println(checkPwd(""));
//		System.out.println(checkPwd("12345"));
//		System.out.println(checkPwd("123456789456123456789123456"));
//		System.out.println(checkPwd("wenjie@123_~"));
	}
}
