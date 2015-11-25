package com.best.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by xing on 2015/11/19.
 */
public class User extends BmobObject{
    private String username;
    private String password;
    private String user_phone;
    private String user_icon;
    private Integer user_id;
    private String user_sex;
    private Integer user_age;
    private String user_qq;
    private String user_borthday;
    private String user_jieshao;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public Integer getUser_age() {
        return user_age;
    }

    public void setUser_age(Integer user_age) {
        this.user_age = user_age;
    }

    public String getUser_qq() {
        return user_qq;
    }

    public void setUser_qq(String user_qq) {
        this.user_qq = user_qq;
    }

    public String getUser_borthday() {
        return user_borthday;
    }

    public void setUser_borthday(String user_borthday) {
        this.user_borthday = user_borthday;
    }

    public String getUser_jieshao() {
        return user_jieshao;
    }

    public void setUser_jieshao(String user_jieshao) {
        this.user_jieshao = user_jieshao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
