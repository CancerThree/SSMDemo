package com.cmb.zh.service.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmb.zh.Util.CommonException;
import com.cmb.zh.Util.CommonVal;
import com.cmb.zh.Util.CommonVal.ErrorCode;
import com.cmb.zh.dao.FollowsDao;
import com.cmb.zh.dao.UserDao;
import com.cmb.zh.dao.UserInfoDao;
import com.cmb.zh.domain.Follows;
import com.cmb.zh.domain.User;
import com.cmb.zh.domain.UserInfo;

@Service("UserAdminService")
public class UserAdminService {
	@Resource
	UserDao userDao;
	@Resource
	UserInfoDao userInfoDao;
	@Resource
	FollowsDao followsDao;
	
	public ErrorCode deleteUser(User user){
		if (user == null || user.getUserid() == null){
			return ErrorCode.ERROR_PARAM_INVALID;
		}
		try{
			int deletedRows = userDao.deleteByPrimaryKey(user.getUserid());
			
			if (deletedRows == 0){
				return ErrorCode.ERROR_USER_NOT_EXIST;
			} else if (deletedRows == 1){
				return ErrorCode.OK;
			} else {
				return ErrorCode.ERROR_UNKOWN_ERROR;
			}
		}catch(Exception e){
			System.out.print(e);
			return ErrorCode.ERROR_WRITEDB_FAIL;
		}
	}
	
	public ArrayList<User> SearchUser(String name){
		ArrayList<User> userList = null;
		
		if (name == null){
			return userList;
		}
		
		try {
			userList = userDao.queryByName(name);
			return userList;
		}catch(Exception e){
			System.out.print(e);
			return null;
		}
	}
	
	@Transactional(rollbackFor = CommonException.class)
	public HashMap<User, UserInfo> getUsersAndInfo(String name) throws CommonException {
		HashMap<User, UserInfo> users = new HashMap<User, UserInfo>();
		ArrayList<User> userList = null;
		
		if (name == null){
			throw new CommonException(CommonVal.ErrorCode.ERROR_PARAM_INVALID);
		}
		
		try {
			userList = userDao.queryByName(name);
			
			if (userList.size() == 0) {
				return users;
			}
			
			for(int i=0; i<userList.size(); i++){
				User user = userList.get(i);
				UserInfo userInfo = userInfoDao.selectByPrimaryKey(user.getUserid());
				System.out.println("user info:" + userInfo);
				users.put(user, userInfo);
			}
			return users;
		}catch(Exception e){
			throw new CommonException(CommonVal.ErrorCode.ERROR_WRITEDB_FAIL, e);
		}
	}
	
	@Transactional(rollbackFor = CommonException.class)
	public HashMap<User, UserInfo> getFollowersInfo(BigDecimal userid) throws CommonException {
		ArrayList<User> userList = null;
		HashMap<User, UserInfo> users = new HashMap<User, UserInfo>();
		
		if (userid == null){
			throw new CommonException(ErrorCode.ERROR_PARAM_INVALID);
		}
		
		try {
			userList = followsDao.findFollowers(userid);
			
			if (userList.size() == 0) {
				return users;
			}
			
			for(int i=0; i<userList.size(); i++){
				User user = userList.get(i);
				UserInfo userInfo = userInfoDao.selectByPrimaryKey(user.getUserid());
				System.out.println("user info:" + userInfo);
				users.put(user, userInfo);
			}
			
			return users;
		} catch (Exception e){
			throw new CommonException(ErrorCode.ERROR_WRITEDB_FAIL, e);
		}
	}
	
	@Transactional(rollbackFor = CommonException.class)
	public HashMap<User, UserInfo> getFollowingUserInfo(BigDecimal userid) throws CommonException {
		ArrayList<User> userList = null;
		HashMap<User, UserInfo> users = new HashMap<User, UserInfo>();
		
		if (userid == null){
			throw new CommonException(ErrorCode.ERROR_PARAM_INVALID);
		}
		
		try {
			userList = followsDao.findFollowings(userid);
			
			if (userList.size() == 0) {
				return users;
			}
			
			for(int i=0; i<userList.size(); i++){
				User user = userList.get(i);
				UserInfo userInfo = userInfoDao.selectByPrimaryKey(user.getUserid());
				System.out.println("user info:" + userInfo);
				users.put(user, userInfo);
			}
			
			return users;
		} catch (Exception e) {
			throw new CommonException(ErrorCode.ERROR_WRITEDB_FAIL, e);
		}
	}
	
	@Transactional(rollbackFor = CommonException.class)
	public int getFollowsCount(BigDecimal userid, BigDecimal follow) throws CommonException {
		if (userid == null || follow == null) {
			throw new CommonException(ErrorCode.ERROR_PARAM_INVALID);
		}
		
		try {
			return followsDao.getFollowRelationCount(userid, follow);
		} catch(Exception e) {
			System.out.println(e);
			throw new CommonException(ErrorCode.ERROR_WRITEDB_FAIL, e);
		}
	}
	
	@Transactional(rollbackFor = CommonException.class)
	public int followUser(Follows follow) throws CommonException {
		if (follow == null || follow.getUserid() == null || follow.getFollow() == null) {
			throw new CommonException(ErrorCode.ERROR_PARAM_INVALID);
		}
		
		try {
			return followsDao.insertSelective(follow);
		} catch (Exception e) {
			System.out.println(e);
			throw new CommonException(ErrorCode.ERROR_WRITEDB_FAIL, e);
		}
	}
	
	public int unFollowUser(Follows follow) throws CommonException {
		if (follow == null || follow.getUserid() == null || follow.getFollow() == null) {
			throw new CommonException(ErrorCode.ERROR_PARAM_INVALID);
		}
		
		try {
			return followsDao.deleteFollow(follow);
		} catch (Exception e) {
			System.out.println(e);
			throw new CommonException(ErrorCode.ERROR_WRITEDB_FAIL, e);
		}
	}
}
