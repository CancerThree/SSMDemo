package com.cmb.zh.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cmb.zh.Util.CommonException;
import com.cmb.zh.Util.CommonVal;
import com.cmb.zh.Util.CommonVal.ErrorCode;
import com.cmb.zh.domain.Follows;
import com.cmb.zh.domain.User;
import com.cmb.zh.domain.UserInfo;
import com.cmb.zh.service.user.UserAdminService;
import com.cmb.zh.service.user.UserInfoService;

@Controller()
@RequestMapping("/myInfo")
public class selfInfoController {
	
	@Resource
	private UserInfoService usrInfoService;
	@Resource
	private UserAdminService userAdminService;
	
	@RequestMapping("/")
	public ModelAndView getSelfIndex(HttpSession session) {
		User thisUser = (User) session.getAttribute(CommonVal.SESSION_KEY_USER);
		ModelAndView mad = null;
		
		if (thisUser != null) {
			mad = new ModelAndView("myProfile");
//			UserInfo userInfo = usrInfoService.getUserInfoByUserId(thisUser.getUserid());
//			mad.addObject("userInfo", userInfo);
		}

		return mad;
	}
	
	@RequestMapping("/mySettings")
	public ModelAndView getMySettings(HttpSession session) {
		User thisUser = (User) session.getAttribute(CommonVal.SESSION_KEY_USER);
		ModelAndView mad = null;
		
		if (thisUser != null) {
			mad = new ModelAndView("indexpage/mySettings");
			UserInfo userInfo = usrInfoService.getUserInfoByUserId(thisUser.getUserid());
			mad.addObject("userInfo", userInfo);
		}

		return mad;
	}
	
	@RequestMapping("/mySettings/saveSelfInfo")
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
	
	@RequestMapping("/myFollowers")
	public ModelAndView getFollowers(HttpSession session) {
		User thisUser = (User) session.getAttribute(CommonVal.SESSION_KEY_USER);
		ModelAndView mad = new ModelAndView("indexpage/userList");
		HashMap<User, UserInfo> usersInfo = new HashMap<User, UserInfo>();
		if (thisUser != null) {
			try {
				usersInfo = userAdminService.getFollowersInfo(thisUser.getUserid());
			} catch (CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			UserInfo userInfo = usrInfoService.getUserInfoByUserId(thisUser.getUserid());
			mad.addObject("userInfo", usersInfo);
		}
		
		return mad;
	}
	
	@RequestMapping("/myFollowings")
	public ModelAndView getFollowings(HttpSession session) {
		User thisUser = (User) session.getAttribute(CommonVal.SESSION_KEY_USER);
		ModelAndView mad = new ModelAndView("indexpage/userList");
		HashMap<User, UserInfo> users = new HashMap<User, UserInfo>();
		HashMap<BigDecimal, Integer> isFollow = new HashMap<BigDecimal, Integer>();
		
		if (thisUser != null) {
			try {
				users = userAdminService.getFollowingUserInfo(thisUser.getUserid());
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
				e.printStackTrace();
			}
//			UserInfo userInfo = usrInfoService.getUserInfoByUserId(thisUser.getUserid());
			mad.addObject("users", users);
			mad.addObject("isFollow", isFollow);
		}
		
		return mad;
	}
	
	@RequestMapping("/followUser")
	@ResponseBody
	public ErrorCode followUser(BigDecimal userid, HttpSession session) {
		User thisUser = (User) session.getAttribute(CommonVal.SESSION_KEY_USER);
		
		if (thisUser != null) {
			Follows follow = new Follows();
			follow.setFollow(userid);
			follow.setUserid(thisUser.getUserid());
			
			try {
				if (userAdminService.followUser(follow) > 0) {
					return ErrorCode.OK;
				} else {
					return ErrorCode.ERROR_UNKOWN_ERROR;
				}
			} catch (CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.getErrorCode();
			}
		} else {
			return ErrorCode.ERROR_PARAM_INVALID;
		}
	}
	
	@RequestMapping("/unfollowUser")
	@ResponseBody
	public ErrorCode unfollowUser(BigDecimal userid, HttpSession session){
		User thisUser = (User) session.getAttribute(CommonVal.SESSION_KEY_USER);
		
		if (thisUser != null) {
			Follows follow = new Follows();
			follow.setFollow(userid);
			follow.setUserid(thisUser.getUserid());
			
			try {
				if (userAdminService.unFollowUser(follow) > 0) {
					return ErrorCode.OK;
				} else {
					return ErrorCode.ERROR_UNKOWN_ERROR;
				}
			} catch (CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.getErrorCode();
			}
		} else {
			return ErrorCode.ERROR_PARAM_INVALID;
		}
	}
}
