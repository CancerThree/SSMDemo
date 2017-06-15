package com.cmb.zh.service.user;

import java.util.ArrayList;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmb.zh.Util.CommonVal;
import com.cmb.zh.Util.CommonVal.ErrorCode;
import com.cmb.zh.dao.UserDao;
import com.cmb.zh.domain.User;

/**
 * @CreateTime: 2017年5月3日15:21:25
 * @author 80230912
 * @Desciption: 用户注册/登录服务 
 **/

@Service("RegisterService")
public class RegisterService {
	@Resource
	UserDao userDao;
	
//	private User onlineUser;
	
	private void setUserOnlineStatus(HttpSession session, boolean online, User user) {
		session.setAttribute(CommonVal.SESSION_KEY_ONLINE, online);
		session.setAttribute(CommonVal.SESSION_KEY_USER, user);
	}
	
	/**
	 * @param User user
	 * @return ErrorCode
	 * @desciption 注册用户
	 * */
	@Transactional
	public ErrorCode register(User user, HttpSession session) {
		if (user == null) {
			return ErrorCode.ERROR_PARAM_INVALID;
		}
		
		String userName = user.getUsername();
		String email = user.getEmail();
		String pwd = user.getPwd();
		
		if (userName == null || email == null || pwd == null) {
			return ErrorCode.ERROR_PARAM_INVALID;
		}
		
		ArrayList<User> userList = userDao.queryByEmail(email);
		if (userList != null && userList.size() != 0) {
			System.out.println(this.getClass() + "\tuserList size:" + userList.size());
			return ErrorCode.ERROR_USER_EXISTS;
		}
		
		int insertRows = userDao.insertSelective(user);
		if (insertRows != 1) {
			System.out.println(this.getClass() + ":\tinsert res:" + insertRows);
			return ErrorCode.ERROR_WRITEDB_FAIL;
		}
		
		userList = userDao.queryByEmail(email);
		user = userList == null ? null : userList.get(0);
		
		setUserOnlineStatus(session, true, user);
		return ErrorCode.OK;
	}
	
	/**
	 * @param User user
	 * @param session
	 * @return errorCode
	 * @description 用户登录
	 * */
	public ErrorCode login(User user, HttpSession session) {
		if (user == null) {
			return ErrorCode.ERROR_PARAM_INVALID;
		}
		
		String email = user.getEmail();
		String pwd = user.getPwd();
		
		if (email == null || pwd == null) {
			return ErrorCode.ERROR_PARAM_INVALID;
		}
		
		ArrayList<User> userList = userDao.queryByEmail(email);
		if (userList == null || userList.size() != 1) {
			System.out.println(this.getClass() + "\tuserList size:" + userList.size());
			return ErrorCode.ERROR_USER_NOT_EXIST;
		}
		
		if (!Objects.equals(userList.get(0).getPwd(), pwd)) {
			return ErrorCode.ERROR_PWD_INCORRECT;
		}
		
		userList = userDao.queryByEmail(email);
		user = userList == null ? null : userList.get(0);
		
		setUserOnlineStatus(session, true, user);
		return ErrorCode.OK;
	}
}
