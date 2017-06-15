package com.cmb.zh.dao;

import com.cmb.zh.domain.UserInfo;
import java.math.BigDecimal;

public interface UserInfoDao {
    int deleteByPrimaryKey(BigDecimal userid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(BigDecimal userid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}