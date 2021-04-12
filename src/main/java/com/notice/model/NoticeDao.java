package com.notice.model;

import java.util.List;

public interface NoticeDao {
	
	/*************************** 新增  **************************/
    public void insert(NoticeBean noticeBean); 
   
    /*************************** 更新  **************************/
    public void update(NoticeBean noticeBean); 
    
    /*************************** 刪除  **************************/
    public void delete(Integer notice_id);       
    
    /*************************** 查PK **************************/
    public NoticeBean findByPrimaryKey(Integer notice_id);
    
    /*************************** 查全部  **************************/
    public List<NoticeBean> getAll();
    
    /*************************** 查類型  **************************/
    public List<NoticeBean> getType(Integer notice_type);
    
    /*************************** 查會員 **************************/
    public List<NoticeBean> getMember(String member_account);
     

}
