package com.vilderlee.clickhousedemo;

import java.sql.Time;
import java.util.Date;

/**
 * User
 *
 * @ClassName User
 * @Description
 * @Author VilderLee
 * @Date 2021/3/29 8:02 下午
 */

public class Users {

    private long id;

    private String username;

    private Time createTime;

    private Date createDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Time getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Time createTime) {
        this.createTime = createTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                ", createDate=" + createDate +
                '}';
    }
}
