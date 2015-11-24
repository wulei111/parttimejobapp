package com.best.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Vonte on 2015/11/23.
 */
public class Invite extends BmobObject{
    private Integer invite_id;
    private Integer classify_id;
    private Integer user_id;
    private String invite_title;
    private String invite_time;
    private String invite_address;
    private String invite_money;
    private String invite_personNum;
    private String invite_context;
    private String invite_check;
    private String incite_urgent;
    private String incite_sex;
    private Integer incite_days;
    private Integer incite_addressid;

    public Integer getInvite_id() {
        return invite_id;
    }

    public void setInvite_id(Integer invite_id) {
        this.invite_id = invite_id;
    }

    public Integer getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(Integer classify_id) {
        this.classify_id = classify_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getInvite_title() {
        return invite_title;
    }

    public void setInvite_title(String invite_title) {
        this.invite_title = invite_title;
    }

    public String getInvite_time() {
        return invite_time;
    }

    public void setInvite_time(String invite_time) {
        this.invite_time = invite_time;
    }

    public String getInvite_address() {
        return invite_address;
    }

    public void setInvite_address(String invite_address) {
        this.invite_address = invite_address;
    }

    public String getInvite_money() {
        return invite_money;
    }

    public void setInvite_money(String invite_money) {
        this.invite_money = invite_money;
    }

    public String getInvite_personNum() {
        return invite_personNum;
    }

    public void setInvite_personNum(String invite_personNum) {
        this.invite_personNum = invite_personNum;
    }

    public String getInvite_context() {
        return invite_context;
    }

    public void setInvite_context(String invite_context) {
        this.invite_context = invite_context;
    }

    public String getInvite_check() {
        return invite_check;
    }

    public void setInvite_check(String invite_check) {
        this.invite_check = invite_check;
    }

    public String getIncite_urgent() {
        return incite_urgent;
    }

    public void setIncite_urgent(String incite_urgent) {
        this.incite_urgent = incite_urgent;
    }

    public String getIncite_sex() {
        return incite_sex;
    }

    public void setIncite_sex(String incite_sex) {
        this.incite_sex = incite_sex;
    }

    public Integer getIncite_days() {
        return incite_days;
    }

    public void setIncite_days(Integer incite_days) {
        this.incite_days = incite_days;
    }

    public Integer getIncite_addressid() {
        return incite_addressid;
    }

    public void setIncite_addressid(Integer incite_addressid) {
        this.incite_addressid = incite_addressid;
    }

    public Invite(Integer invite_id, Integer classify_id, Integer user_id, String invite_title, String invite_time, String invite_address, String invite_money, String invite_personNum, String invite_context, String invite_check, String incite_urgent, String incite_sex, Integer incite_days, Integer incite_addressid) {

        this.invite_id = invite_id;
        this.classify_id = classify_id;
        this.user_id = user_id;
        this.invite_title = invite_title;
        this.invite_time = invite_time;
        this.invite_address = invite_address;
        this.invite_money = invite_money;
        this.invite_personNum = invite_personNum;
        this.invite_context = invite_context;
        this.invite_check = invite_check;
        this.incite_urgent = incite_urgent;
        this.incite_sex = incite_sex;
        this.incite_days = incite_days;
        this.incite_addressid = incite_addressid;
    }
}
