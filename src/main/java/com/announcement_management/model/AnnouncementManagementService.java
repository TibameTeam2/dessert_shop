package com.announcement_management.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class AnnouncementManagementService {
	
private AnnouncementManagementDAO_interface dao;
	
	public AnnouncementManagementService() {
		dao = new AnnouncementManagementDAO();
	}
	
	public AnnouncementManagementBean addAnn(String announcement_name, String announcement_content, byte[] announcement_image,
			Timestamp announcement_time, Integer announcement_type, Integer announcement_status,String employee_account) {

		AnnouncementManagementBean AMB = new AnnouncementManagementBean();
		
		
		AMB.setAnnouncement_name(announcement_name);
		AMB.setAnnouncement_content(announcement_content);

		byte[] pic;
		try {
			pic = getPictureByteArray("C:/project/images/announcement_management/d.png");

			AMB.setAnnouncement_image(announcement_image);

		} catch (IOException e2) {
			e2.printStackTrace();
		}

//		String time = "2021-02-27 15:00:00";
//		Timestamp ts = Timestamp.valueOf(time);
		
		AMB.setAnnouncement_time(announcement_time);
		AMB.setAnnouncement_type(announcement_type);
		AMB.setAnnouncement_status(announcement_status);
		AMB.setEmployee_account(employee_account);
		dao.insert(AMB);

		return AMB;
	}

	public AnnouncementManagementBean updateAnn(String announcement_name, String announcement_content, byte[] announcement_image,
			Timestamp announcement_time, Integer announcement_type, Integer announcement_status,String employee_account,Integer announcement_id) {

		AnnouncementManagementBean AMB = new AnnouncementManagementBean();

		AMB.setAnnouncement_name(announcement_name);
		AMB.setAnnouncement_content(announcement_content);

		byte[] pic;
		try {
			pic = getPictureByteArray("C:/project/images/announcement_management/d.png");

			AMB.setAnnouncement_image(announcement_image);

		} catch (IOException e2) {
			e2.printStackTrace();
		}

//		String time = "2021-02-27 15:00:00";
//		Timestamp ts = Timestamp.valueOf(time);
		
		AMB.setAnnouncement_time(announcement_time);
		AMB.setAnnouncement_type(announcement_type);
		AMB.setAnnouncement_status(announcement_status);
		AMB.setEmployee_account(employee_account);
		AMB.setAnnouncement_id(announcement_id);
		dao.update(AMB);

		return AMB;
	}

	public void deleteAnn(Integer announcement_id) {
		dao.delete(announcement_id);
	}
	
	public AnnouncementManagementBean getOneAnn(Integer announcement_id) {
		return dao.findByPrimaryKey(announcement_id);
	}

	public List<AnnouncementManagementBean> getAll() {
		return dao.getAll();
	}
	
	public List<AnnouncementManagementBean> getAll01() {
		return dao.getAll01();
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	
	
	public boolean addAM2(AnnouncementManagementBean am) {
        try {
            dao.insert(am);
            return true;
        }catch (Exception e){

            return false;
        }
    }
	
	public boolean updateAM2(AnnouncementManagementBean am) {
        try {
        	AnnouncementManagementBean m = dao.findByPrimaryKey(am.getAnnouncement_id());
            m.setAnnouncement_name(am.getAnnouncement_name());
            m.setAnnouncement_content(am.getAnnouncement_content());
            m.setAnnouncement_time(am.getAnnouncement_time());
            m.setAnnouncement_type(am.getAnnouncement_type());
            m.setAnnouncement_status(am.getAnnouncement_status());
            m.setEmployee_account(am.getEmployee_account());
            
            if(am.getAnnouncement_image()!=null){
                m.setAnnouncement_image(am.getAnnouncement_image());
            }
            dao.update(m);
            return true;
        }catch (Exception e){
            e.printStackTrace(System.err);
            return false;
        }
    }
	

}
