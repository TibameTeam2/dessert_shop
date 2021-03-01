package com.employee_authority.model;

import java.util.Arrays;

public class EmployeeAuthorityBean {
    private Integer authority_id;
    private String employee_account;
    private Integer authority_Content_id;

    @Override
    public String toString() {
        return "Employee_AuthorityBean{" +
                "authority_id='" + authority_id + '\'' +
                ", employee_account='" + employee_account + '\'' +
                ", authority_Content_id='" + authority_Content_id + '\'' +
                '}';
    }

    public Integer getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(Integer authority_id) {
        this.authority_id = authority_id;
    }

    public String getEmployee_account() {
        return employee_account;
    }

    public void setEmployee_account(String employee_account) {
        this.employee_account = employee_account;
    }

    public Integer getAuthority_Content_id() {
        return authority_Content_id;
    }

    public void setAuthority_Content_id(Integer authority_Content_id) {
        this.authority_Content_id = authority_Content_id;
    }

}
