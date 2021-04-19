package com.announcement_management.model;

import java.util.List;

public interface AnnouncementManagementDAO_interface {
	
	public void insert(AnnouncementManagementBean AMB);
	public void update(AnnouncementManagementBean AMB);
	public void delete(Integer announcement_id);
	public AnnouncementManagementBean findByPrimaryKey(Integer announcement_id);
	public List <AnnouncementManagementBean> getAll();
	public List <AnnouncementManagementBean> getAll01();
}
