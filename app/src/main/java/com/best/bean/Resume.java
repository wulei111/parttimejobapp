package com.best.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by PCID on 2015/11/23.
 */
public class Resume extends BmobObject {
    private Integer company_id;
    private Integer user_id;

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Resume(Integer company_id, Integer user_id) {

        this.company_id = company_id;
        this.user_id = user_id;
    }
}
