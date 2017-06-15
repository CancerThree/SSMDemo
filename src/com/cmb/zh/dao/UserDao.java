package com.cmb.zh.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.cmb.zh.domain.User;

public interface UserDao {
	ArrayList<User> queryByName(String name);
	
	ArrayList<User> queryByEmail(String email);
	
	int deleteByPrimaryKey(BigDecimal userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(BigDecimal userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
