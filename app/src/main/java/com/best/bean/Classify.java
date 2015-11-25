package com.best.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by PCID on 2015/11/23.
 */
public class Classify extends BmobObject {
    private Integer classify_id;
    private String classify_name;
    private String classify_icon;

    public Classify(Integer classify_id, String classify_name, String classify_icon) {
        this.classify_id = classify_id;
        this.classify_name = classify_name;
        this.classify_icon = classify_icon;
    }

    public Integer getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(Integer classify_id) {
        this.classify_id = classify_id;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public String getClassify_icon() {
        return classify_icon;
    }

    public void setClassify_icon(String classify_icon) {
        this.classify_icon = classify_icon;
    }
}
