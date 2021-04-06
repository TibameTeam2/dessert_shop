package com.notice.model;

import java.util.List;

public class NoticeService {

	private NoticeDaoImpl dao;

	public NoticeService() {

		dao = new NoticeDaoImpl();

	}

	/*********************** 新增 ***********************/
	public Boolean addNotice(NoticeBean noticeBean) {

		try {

			dao.insert(noticeBean);
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	/*********************** 修改 ***********************/
	public NoticeBean updateNotice(Integer notice_id, Integer notice_type, String notice_content,
			java.sql.Timestamp notice_time, Integer read_status, String member_account) {

		NoticeBean noticeBean = new NoticeBean();

		noticeBean.setNotice_id(notice_id);
		noticeBean.setNotice_type(notice_type);
		noticeBean.setNotice_content(notice_content);
		noticeBean.setNotice_time(notice_time);
		noticeBean.setRead_status(read_status);
		noticeBean.setMember_account(member_account);
		dao.update(noticeBean);

		return noticeBean;
	}

	/*********************** 刪除 ***********************/
	public void deleteNotice(Integer notice_id) {

		dao.delete(notice_id);
	}

	/*********************** 查詢(PK) ***********************/
	public NoticeBean getOneNotice(Integer notice_id) {
		return dao.findByPrimaryKey(notice_id);
	}

	/*********************** 查詢(全部) ***********************/
	public List<NoticeBean> getAll() {
		return dao.getAll();
	}

	/*********************** 查詢(類型) ***********************/
	public List<NoticeBean> getType(Integer notice_type) {
		return dao.getType(notice_type);
	}

	/*********************** 查詢(會員) ***********************/
	public List<NoticeBean> getMember(String member_account) {
		return dao.getMember(member_account);
	}

}
