package com.authority_content.model;

public class AuthorityContentBean {

    private Integer authority_content_id;
    private String authority_content;

    @Override
    public String toString() {
        return "authority_contentBean{" +
                "authority_content_id=" + authority_content_id +
                ", authority_content='" + authority_content + '\'' +
                '}';
    }

    public Integer getAuthority_content_id() {
        return authority_content_id;
    }

    public void setAuthority_content_id(Integer authority_content_id) {
        this.authority_content_id = authority_content_id;
    }

    public String getAuthority_content() {
        return authority_content;
    }

    public void setAuthority_content(String authority_content) {
        this.authority_content = authority_content;
    }
}
