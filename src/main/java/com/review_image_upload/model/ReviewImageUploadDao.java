package com.review_image_upload.model;

import java.util.List;

public interface ReviewImageUploadDao {
	public void insert(ReviewImageUploadBean reviewImageUploadBean);
	public void update(ReviewImageUploadBean reviewImageUploadBean);
	public void delete(Integer review_image_id);
	public ReviewImageUploadBean findByPrimaryKey(Integer review_image_id);
	public List<ReviewImageUploadBean> getAll();
//	萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ReviewImageUploadBean> getAll(Map<String, String[]> map);
}
	