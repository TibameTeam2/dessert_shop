package com.member.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class MemberBean implements Serializable {
    private String member_account;
    private String member_password;
    private String member_name;
    private String member_phone;
    private String member_email;
    private byte[] member_photo;
    private Integer member_gender;
    private Date member_birthday;
    private Timestamp register_time;
    private Integer register_method;
    private Integer member_status;

    @Override
    public String toString() {
        return "MemberBean{" +
                "member_account='" + member_account + '\'' +
                ", member_password='" + member_password + '\'' +
                ", member_name='" + member_name + '\'' +
                ", member_phone='" + member_phone + '\'' +
                ", member_email='" + member_email + '\'' +
                ", member_photo=" + Arrays.toString(member_photo) +
                ", member_gender=" + member_gender +
                ", member_birthday=" + member_birthday +
                ", register_time=" + register_time +
                ", register_method=" + register_method +
                ", member_status=" + member_status +
                '}';
    }

    public String getMember_account() {
        return member_account;
    }

    public void setMember_account(String member_account) {
        this.member_account = member_account;
    }

    public String getMember_password() {
        return member_password;
    }

    public void setMember_password(String member_password) {
        this.member_password = member_password;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public byte[] getMember_photo() {
        return member_photo;
    }

    public void setMember_photo(byte[] member_photo) {
        this.member_photo = member_photo;
    }

    public Integer getMember_gender() {
        return member_gender;
    }

    public void setMember_gender(Integer member_gender) {
        this.member_gender = member_gender;
    }

    public Date getMember_birthday() {
        return member_birthday;
    }

    public void setMember_birthday(Date member_birthday) {
        this.member_birthday = member_birthday;
    }

    public Timestamp getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        this.register_time = register_time;
    }

    public Integer getRegister_method() {
        return register_method;
    }

    public void setRegister_method(Integer register_method) {
        this.register_method = register_method;
    }

    public Integer getMember_status() {
        return member_status;
    }

    public void setMember_status(Integer member_status) {
        this.member_status = member_status;
    }
}
