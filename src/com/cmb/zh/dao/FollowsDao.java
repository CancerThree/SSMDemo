package com.cmb.zh.dao;

import com.cmb.zh.domain.Follows;
import com.cmb.zh.domain.User;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface FollowsDao {
	int deleteFollow(Follows record);
	
	ArrayList<User> findNotFollowings(BigDecimal id);
	
	ArrayList<User> findFollowings(BigDecimal id);
	
	ArrayList<User> findFollowers(BigDecimal id);
	
    int deleteByPrimaryKey(BigDecimal id);

    int insert(Follows record);

    int insertSelective(Follows record);

    Follows selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(Follows record);

    int updateByPrimaryKey(Follows record);
}