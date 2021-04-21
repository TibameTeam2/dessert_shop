package com.bucket_list.model;

import java.util.List;
import java.util.Set;

public interface BucketListDao {
	
	//回傳count
	public Integer insert(BucketListBean bucketListBean);
	
	public void update(BucketListBean bucketListBean);
	
	public void delete(String member_account, Integer product_id);
	
	//聯合主鍵
	public BucketListBean findByPrimaryKey(String member_account, Integer product_id);
	
	public List<BucketListBean> getAll();

	//按下「垃圾桶」,是把bucket_list_status狀態改為0(因怡蓉說有分析價值), 且不顯示該商品。
	public void changeBucketListStatusToZero(String member_account, Integer product_id);

	//傳入member_account要顯示該顧客的收藏清單
	public List<BucketListBean> getMyBucketList(String member_account);
}
