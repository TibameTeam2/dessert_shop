package com.notice.model;

import java.util.List;

import com.mysql.cj.protocol.x.Notice;

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
	public NoticeBean updateNotice(NoticeBean noticeBean) {

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

	/*********************** 更新讀取狀態 ***********************/
	public Boolean updateRead(NoticeBean notice) {

//	1.透過dao.findByPrimaryKey取得完整的noticeBean
//	2.比對getMember_account是否一樣(equals)
//	3.Read_status:set(1)
//	4.再呼叫updateNotice(noticeBean)

		NoticeBean noticeId = dao.findByPrimaryKey(notice.getNotice_id());
		
		if (notice.getMember_account().equals(noticeId.getMember_account())) {

			noticeId.setRead_status(1);
			dao.update(noticeId);

		}
		
		System.out.println("已更新"+noticeId+"狀態");

		return true;
	}

}
