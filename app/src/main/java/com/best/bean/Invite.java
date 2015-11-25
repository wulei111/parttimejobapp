package com.best.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by PCID on 2015/11/23.
 */
public class Invite extends BmobObject {
    private Integer invite_id;
    private Integer classify_id;
    private Integer user_id;
    private String invite_title;
    private String invite_time;
    private String invite_address;
    private Integer invite_money;
    private Integer invite_personNum;
    private String invite_context;
    private String invite_check;
    private String invite_urgent;
    private String invite_sex;
    private Integer invite_days;
    private Integer invite_addressid;
    private String invite_danwei;//新

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

    public Integer getInvite_money() {
        return invite_money;
    }

    public void setInvite_money(Integer invite_money) {
        this.invite_money = invite_money;
    }

    public Integer getInvite_personNum() {
        return invite_personNum;
    }

    public void setInvite_personNum(Integer invite_personNum) {
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

    public String getInvite_urgent() {
        return invite_urgent;
    }

    public void setInvite_urgent(String invite_urgent) {
        this.invite_urgent = invite_urgent;
    }

    public String getInvite_sex() {
        return invite_sex;
    }

    public void setInvite_sex(String invite_sex) {
        this.invite_sex = invite_sex;
    }

    public Integer getInvite_days() {
        return invite_days;
    }

    public void setInvite_days(Integer invite_days) {
        this.invite_days = invite_days;
    }

    public Integer getInvite_addressid() {
        return invite_addressid;
    }

    public void setInvite_addressid(Integer invite_addressid) {
        this.invite_addressid = invite_addressid;
    }
    public String getInvite_danwei(){
        return  invite_danwei;
    }
    public void setInvite_danwei(String invite_danwei){
        this.invite_danwei = invite_danwei;
    }

    public Invite(Integer invite_id, Integer classify_id, Integer user_id, String invite_title, String invite_time, String invite_address, Integer invite_money, Integer invite_personNum, String invite_context, String invite_check, String invite_urgent, String invite_sex, Integer invite_days, Integer invite_addressid,String invite_danwei) {
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
        this.invite_urgent = invite_urgent;
        this.invite_sex = invite_sex;
        this.invite_days = invite_days;
        this.invite_addressid = invite_addressid;
        this.invite_danwei = invite_danwei;
    }
}
