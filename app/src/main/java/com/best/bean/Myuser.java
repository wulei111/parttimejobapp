package com.best.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by xing on 2015/11/20.
 */
public class Myuser extends BmobUser{
    private String user_qq;
    private String user_image;

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_qq() {
        return user_qq;
    }

    public void setUser_qq(String user_qq) {
        this.user_qq = user_qq;
    }

}
