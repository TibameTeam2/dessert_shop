package com.bucket_list.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bucket_list.model.BucketListBean;
import com.bucket_list.model.BucketListService;
import com.daily_special.model.DailySpecialBean;
import com.daily_special.model.DailySpecialService;
import com.member.model.MemberBean;
import com.product.model.ProductBean;
import com.product.model.ProductDAO;
import com.product.model.ProductService;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

public class BucketListServlet extends BaseServlet {

	BucketListService bucketListSvc = new BucketListService();
	ProductService productSvc = new ProductService();
	ProductDAO productDao = new ProductDAO();
	DailySpecialService dailySpecialSvc = new DailySpecialService();

	// 傳入member_account要顯示該顧客的收藏清單
	public void getMyBucketList(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();

		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
		} else {
			String member_account = member.getMember_account();

			List<BucketListBean> list = bucketListSvc.getMyBucketList(member_account);
			System.out.println("list = " + list);
			
			//取得list中的product_id
			for (BucketListBean blBean : list) {
				Integer product_id = blBean.getProduct_id();
				System.out.println("product_id = " + product_id);

				
				//憑product_id取得product_name, product_type, product_subtype, product_price, product_available_qty 和其他欄位 
				ProductBean pBean = productSvc.getOneProduct(product_id);
				System.out.println("pBean = " + pBean);
				
				//取得一張商品照片
				ProductBean pBean1 = productDao.getOneImage(product_id);
				
				//取得該商品的優惠價格
				DailySpecialBean dsBean = dailySpecialSvc.findDiscountPriceByProductId(product_id);
				Integer dsPrice = dsBean.getDiscount_price();
			}
			
			info.setFlag(true);
			info.setMsg(member.getMember_account() + " 的收藏清單");
			info.setData(list);
		}
		writeValueByWriter(res, info);

	}

	// 加入收藏
	public void addBucketList(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();

		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			info.setRedirect(req.getContextPath() + req.getServletPath() + "/login.html");
		} else {
			String member_account = req.getParameter("MemberAccount");
			Integer product_id = Convert.toInt(req.getParameter("ProductId"));
			Integer bucket_list_status = 1;

			// 印印看, 確認是否有從前台傳過來
			System.out.println("member_account = " + member_account);
			System.out.println("product_id = " + product_id);

			BucketListBean bucketListBean = bucketListSvc.addBucketList(member_account, product_id, bucket_list_status);

			info.setFlag(true);
			info.setData(bucketListBean);
			info.setMsg("成功加入收藏");
			info.setRedirect(req.getContextPath() + req.getServletPath() + "/whishlist_k.html");
			writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端
		}

	}

	// 按下「垃圾桶」,是把bucket_list_status狀態改為0(因怡蓉說有分析價值), 且不顯示該商品。
	public void changeBucketListStatusToZero(HttpServletRequest req, HttpServletResponse res) {

		String member_account = req.getParameter("MemberAccount");
		Integer product_id = Convert.toInt(req.getParameter("ProductId"));

		// 印印看, 確認是否有從前台傳過來
		System.out.println("member_account = " + member_account);
		System.out.println("product_id = " + product_id);

		bucketListSvc.changeBucketListStatusToZero(member_account, product_id);

		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setMsg("成功將bucket_list_status狀態改為0");
		info.setRedirect(req.getContextPath() + req.getServletPath() + "/whishlist_k.html");
		writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端

	}

	// 真正的刪除(用不到)
	public void deleteBucketList(HttpServletRequest req, HttpServletResponse res) {

		String member_account = req.getParameter("MemberAccount");
		Integer product_id = Convert.toInt(req.getParameter("ProductId"));

		// 印印看, 確認是否有從前台傳過來
		System.out.println("member_account = " + member_account);
		System.out.println("product_id = " + product_id);

		bucketListSvc.deleteBucketList(member_account, product_id);

		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setMsg("成功從收藏中刪除");
		info.setRedirect(req.getContextPath() + req.getServletPath() + "/whishlist_k.html");
//		writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端
	}
}
