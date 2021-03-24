package com.announcement_management.model;



import java.sql.Timestamp;
import java.util.Arrays;

public class AnnouncementManagementBean {

	private Integer announcement_id;
	private String announcement_name;
	private String announcement_content;
	private byte[] announcement_image;
	private Timestamp announcement_time;
	private Integer announcement_type;
	private Integer announcement_status;
	private String employee_account;
	
	
	
	
	@Override
	public String toString() {
		return "announcement_managementBean [announcement_id=" + announcement_id + ", announcement_name="
				+ announcement_name + ", announcement_content=" + announcement_content + ", announcement_image="
				+ Arrays.toString(announcement_image) + ", announcement_time=" + announcement_time
				+ ", announcement_type=" + announcement_type + ", announcement_status=" + announcement_status
				+ ", employee_account=" + employee_account + "]";
	}
	public Integer getAnnouncement_id() {
		return announcement_id;
	}
	public void setAnnouncement_id(Integer announcement_id) {
		this.announcement_id = announcement_id;
	}
	public String getAnnouncement_name() {
		return announcement_name;
	}
	public void setAnnouncement_name(String announcement_name) {
		this.announcement_name = announcement_name;
	}
	public String getAnnouncement_content() {
		return announcement_content;
	}
	public void setAnnouncement_content(String announcement_content) {
		this.announcement_content = announcement_content;
	}
	public byte[] getAnnouncement_image() {
		return announcement_image;
	}
	public void setAnnouncement_image(byte[] announcement_image) {
		this.announcement_image = announcement_image;
	}
	public Timestamp getAnnouncement_time() {
		return announcement_time;
	}
	public void setAnnouncement_time(Timestamp announcement_time) {
		this.announcement_time = announcement_time;
	}
	public Integer getAnnouncement_type() {
		return announcement_type;
	}
	public void setAnnouncement_type(Integer announcement_type) {
		this.announcement_type = announcement_type;
	}
	public Integer getAnnouncement_status() {
		return announcement_status;
	}
	public void setAnnouncement_status(Integer announcement_status) {
		this.announcement_status = announcement_status;
	}
	public String getEmployee_account() {
		return employee_account;
	}
	public void setEmployee_account(String employee_account) {
		this.employee_account = employee_account;
	}
	
	
	
	
}
