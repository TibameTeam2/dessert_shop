package com.employee.model;

import java.sql.Date;
import java.util.Arrays;

public class EmployeeBean {

    private String employee_account;
    private String employee_name;
    private String employee_password;
    private String employee_position;
    private byte[] employee_photo;
    private Date hire_date;
    private Integer employee_status;



    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employee_account='" + employee_account + '\'' +
                ", employee_name='" + employee_name + '\'' +
                ", employee_password='" + employee_password + '\'' +
                ", employee_position='" + employee_position + '\'' +
                ", employee_photo=" + Arrays.toString(employee_photo) +
                ", hire_date=" + hire_date +
                ", employee_status=" + employee_status +
                '}';
    }

    public String getEmployee_account() {
        return employee_account;
    }

    public void setEmployee_account(String employee_account) {
        this.employee_account = employee_account;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_password() {
        return employee_password;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    public String getEmployee_position() {
        return employee_position;
    }

    public void setEmployee_position(String employee_position) {
        this.employee_position = employee_position;
    }

    public byte[] getEmployee_photo() {
        return employee_photo;
    }

    public void setEmployee_photo(byte[] employee_photo) {
        this.employee_photo = employee_photo;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Integer getEmployee_status() {
        return employee_status;
    }

    public void setEmployee_status(Integer employee_status) {
        this.employee_status = employee_status;
    }

}
