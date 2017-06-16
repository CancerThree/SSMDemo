package com.cmb.zh.domain;

import java.math.BigDecimal;

public class Follows {
    private BigDecimal id;

    private BigDecimal userid;

    private BigDecimal follow;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getUserid() {
        return userid;
    }

    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    public BigDecimal getFollow() {
        return follow;
    }

    public void setFollow(BigDecimal follow) {
        this.follow = follow;
    }
}