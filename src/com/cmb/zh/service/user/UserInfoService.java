package com.cmb.zh.service.user;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cmb.zh.Util.CommonVal.ErrorCode;
import com.cmb.zh.dao.UserDao;
import com.cmb.zh.dao.UserInfoDao;
import com.cmb.zh.domain.User;
import com.cmb.zh.domain.UserInfo;

@Service("UserInfoService")
public class UserInfoService {
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private UserDao userDao;
	
	public UserInfo getUserInfoByUserId(BigDecimal userId) {
		UserInfo userInfo = null;
		
		if (userId != null) {
			userInfo = userInfoDao.selectByPrimaryKey(userId);
		}
		
		return userInfo;
	}
	
	public ErrorCode insertUserInfo(UserInfo userInfo) {
		ErrorCode error = ErrorCode.OK;
		
		if (userInfo != null && userInfo.getUserid() != null) {
			if (userInfoDao.insertSelective(userInfo) != 1) {
				error = ErrorCode.ERROR_WRITEDB_FAIL;
			}
		} else {
			error = ErrorCode.ERROR_PARAM_INVALID;
		}
		
		return error;
	}
	
	public ErrorCode updateUserInfo(UserInfo userInfo) {
		ErrorCode error = ErrorCode.OK;
		
		if (userInfo != null && userInfo.getUserid() != null) {	
			if (userDao.selectByPrimaryKey(userInfo.getUserid()) == null) {
				return ErrorCode.ERROR_USER_NOT_EXIST;
			}
			
			if (getUserInfoByUserId(userInfo.getUserid()) != null) {
				if (userInfoDao.updateByPrimaryKeySelective(userInfo) != 1) {
					error = ErrorCode.ERROR_WRITEDB_FAIL;
				}
			} else {
				error = insertUserInfo(userInfo);
			}
		}
		
		return error;
	}
}
