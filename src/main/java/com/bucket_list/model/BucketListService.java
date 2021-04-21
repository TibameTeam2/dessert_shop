package com.bucket_list.model;

import java.util.List;
import java.util.Set;

public class BucketListService {

	private BucketListDao dao;
	
	public BucketListService() {
		dao = new BucketListDaoImpl();
	}
	
	//用在顧客將商品收藏進收藏清單
	public BucketListBean addBucketList(String member_account, Integer product_id, Integer bucket_list_status) {
		BucketListBean bucketListBean = new BucketListBean();
		bucketListBean.setMember_account(member_account);
		bucketListBean.setProduct_id(product_id);
		bucketListBean.setBucket_list_status(bucket_list_status);
		dao.insert(bucketListBean);
		
		return bucketListBean;
	}
	
	
	public BucketListBean updateBucketList(Integer bucket_list_status, String member_account, Integer product_id) {
	
		BucketListBean bucketListBean = new BucketListBean();
		bucketListBean.setBucket_list_status(bucket_list_status);
		bucketListBean.setMember_account(member_account);
		bucketListBean.setProduct_id(product_id);
		dao.update(bucketListBean);
		
		return bucketListBean;
		
	}
	
	
	public void deleteBucketList(String member_account,Integer product_id) {
		dao.delete(member_account, product_id);
	}
	
	
	public BucketListBean getOneBucketList(String member_account, Integer product_id) {
		return dao.findByPrimaryKey(member_account, product_id);
	}
	
	
	public List<BucketListBean> getAll(){
		return dao.getAll();
	}
	
	
	//按下「垃圾桶」,是把bucket_list_status狀態改為0(因怡蓉說有分析價值), 且不顯示該商品。
	public void changeBucketListStatusToZero(String member_account, Integer product_id) {
		dao.changeBucketListStatusToZero(member_account, product_id);
	}
	
	
	
	//傳入member_account要顯示該顧客的收藏清單
	public List<BucketListBean> getMyBucketList(String member_account) {
		return dao.getMyBucketList(member_account);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
