package com.cmb.zh.service.user;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cmb.zh.Util.CommonVal.ErrorCode;
import com.cmb.zh.dao.UserDao;
import com.cmb.zh.domain.User;

@Service("UserAdminService")
public class UserAdminService {
	@Resource
	UserDao userDao;
	
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
}
