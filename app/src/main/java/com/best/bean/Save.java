package com.best.bean;

import cn.bmob.v3.BmobObject;


public class Save extends BmobObject{
    private Integer save_id;
    private Integer user_id;
    private String type;
    private Integer invite_id;

    public Integer getSave_id() {
        return save_id;
    }

    public void setSave_id(Integer save_id) {
        this.save_id = save_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getInvite_id() {
        return invite_id;
    }

    public void setInvite_id(Integer invite_id) {
        this.invite_id = invite_id;
    }

    public Save(Integer save_id, Integer user_id, String type, Integer invite_id) {

        this.save_id = save_id;
        this.user_id = user_id;
        this.type = type;
        this.invite_id = invite_id;
    }
}
