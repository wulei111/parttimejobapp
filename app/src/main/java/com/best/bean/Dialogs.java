package com.best.bean;


public class Dialogs {
    private String title;
    private Boolean bool;

    public Dialogs(String title, Boolean bool) {
        this.title = title;
        this.bool = bool;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }
}
