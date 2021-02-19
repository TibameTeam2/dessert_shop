package com.card_detail.model;

import java.sql.Timestamp;

public class card_detailBean {
    private Integer card_id;
    private String member_account;
    private String card_number;
    private String card_expired_day;
    private String card_cvc;
    private Timestamp card_addedDate;

    @Override
    public String toString() {
        return "card_detailBean{" +
                "card_id=" + card_id +
                ", member_account='" + member_account + '\'' +
                ", card_number='" + card_number + '\'' +
                ", card_expired_day='" + card_expired_day + '\'' +
                ", card_cvc='" + card_cvc + '\'' +
                ", card_addedDate=" + card_addedDate +
                '}';
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public String getMember_account() {
        return member_account;
    }

    public void setMember_account(String member_account) {
        this.member_account = member_account;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_expired_day() {
        return card_expired_day;
    }

    public void setCard_expired_day(String card_expired_day) {
        this.card_expired_day = card_expired_day;
    }

    public String getCard_cvc() {
        return card_cvc;
    }

    public void setCard_cvc(String card_cvc) {
        this.card_cvc = card_cvc;
    }

    public Timestamp getCard_addedDate() {
        return card_addedDate;
    }

    public void setCard_addedDate(Timestamp card_addedDate) {
        this.card_addedDate = card_addedDate;
    }
}
