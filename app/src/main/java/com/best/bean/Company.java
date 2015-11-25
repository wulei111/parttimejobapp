package com.best.bean;

import cn.bmob.v3.BmobObject;

/*
2015-11-24
 */

public class Company extends BmobObject{
    private Integer company_id;
    private Integer user_id;
    private Integer classify_id;
    private Integer invite_id;
    private String company_name;
    private String company_class;

    private Integer company_size;
    private String company_person;
    private String company_tel;
    private String company_Email;
    private String company_address;
    private String company_context;
    private String company_number;
    private String company_rank;

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

    public Integer getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(Integer classify_id) {
        this.classify_id = classify_id;
    }

    public Integer getInvite_id() {
        return invite_id;
    }

    public void setInvite_id(Integer invite_id) {
        this.invite_id = invite_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_class() {
        return company_class;
    }

    public void setCompany_class(String company_class) {
        this.company_class = company_class;
    }

    public Integer getCompany_size() {
        return company_size;
    }

    public void setCompany_size(Integer company_size) {
        this.company_size = company_size;
    }

    public String getCompany_person() {
        return company_person;
    }

    public void setCompany_person(String company_person) {
        this.company_person = company_person;
    }

    public String getCompany_tel() {
        return company_tel;
    }

    public void setCompany_tel(String company_tel) {
        this.company_tel = company_tel;
    }

    public String getCompany_Email() {
        return company_Email;
    }

    public void setCompany_Email(String company_Email) {
        this.company_Email = company_Email;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_context() {
        return company_context;
    }

    public void setCompany_context(String company_context) {
        this.company_context = company_context;
    }

    public String getCompany_number() {
        return company_number;
    }

    public void setCompany_number(String company_number) {
        this.company_number = company_number;
    }

    public String getCompany_rank() {
        return company_rank;
    }

    public void setCompany_rank(String company_rank) {
        this.company_rank = company_rank;
    }

    public String getCompany_score() {
        return company_score;
    }

    public void setCompany_score(String company_score) {
        this.company_score = company_score;
    }

    public String getCompany_blackList() {
        return company_blackList;
    }

    public void setCompany_blackList(String company_blackList) {
        this.company_blackList = company_blackList;
    }

    public Company(Integer company_id, Integer user_id, Integer classify_id, Integer invite_id, String company_name, String company_class, Integer company_size, String company_person, String company_tel, String company_Email, String company_address, String company_context, String company_number, String company_rank, String company_score, String company_blackList) {

        this.company_id = company_id;
        this.user_id = user_id;
        this.classify_id = classify_id;
        this.invite_id = invite_id;
        this.company_name = company_name;
        this.company_class = company_class;
        this.company_size = company_size;
        this.company_person = company_person;
        this.company_tel = company_tel;
        this.company_Email = company_Email;
        this.company_address = company_address;
        this.company_context = company_context;
        this.company_number = company_number;
        this.company_rank = company_rank;
        this.company_score = company_score;
        this.company_blackList = company_blackList;
    }

    private String company_score;
    private String company_blackList;

}
