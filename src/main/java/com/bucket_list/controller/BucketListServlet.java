package com.bucket_list.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bucket_list.model.BucketListBean;
import com.bucket_list.model.BucketListService;
import com.bucket_list.model.WishlistBigBean;
import com.bucket_list.model.WishlistBigService;
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
//	ProductDAO productDao = new ProductDAO();
	DailySpecialService dailySpecialSvc = new DailySpecialService();
	WishlistBigService WishlistBigSvc = new WishlistBigService();

	// 傳入member_account要顯示該顧客的收藏清單
	public void getMyBucketList(HttpServletRequest req, HttpServletResponse res) {
		List<WishlistBigBean> bigList = new ArrayList<WishlistBigBean>();

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();

		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
		} else {
			String member_account = member.getMember_account();
			List<BucketListBean> list = bucketListSvc.getMyBucketList(member_account);

			for (BucketListBean blBean : list) {

				// 巨大Bean用來裝取到的值寫給前台
				WishlistBigBean wBean = new WishlistBigBean();

				// 從list中取bucket_list中的四個欄位, 放進wBean
				Integer bucket_list_id = blBean.getBucket_list_id();
				System.out.println("bucket_list_id = " + bucket_list_id);
				wBean.setBucket_list_id(bucket_list_id);

				wBean.setMember_account(member_account);

				Integer product_id = blBean.getProduct_id();
				wBean.setProduct_id(product_id);

				Integer bucket_list_status = blBean.getBucket_list_status();
				wBean.setBucket_list_status(bucket_list_status);

				// 憑product_id取得product_name, product_type, product_subtype, product_price,
				// product_available_qty
//					Integer product_id = wBean.getProduct_id();
				System.out.println("product_id = " + product_id);

				ProductBean pBean = productSvc.getOneProduct(wBean.getProduct_id());

				String product_name = pBean.getProduct_name();
				wBean.setProduct_name(product_name);

				String product_type = pBean.getProduct_type();
				wBean.setProduct_type(product_type);

				String product_subtype = pBean.getProduct_subtype();
				wBean.setProduct_subtype(product_subtype);

				Integer product_price = pBean.getProduct_price();
				wBean.setProduct_price(product_price);

				Integer product_available_qty = pBean.getProduct_available_qty();
				wBean.setProduct_available_qty(product_available_qty);

				// 取得一張商品照片
				WishlistBigBean pBean1 = WishlistBigSvc.getImage(product_id);
				Integer image_id = pBean1.getImage_id();
				wBean.setImage_id(image_id);
				wBean.setProduct_image("/product_jsp/product.do?action=getProductImage&id=" + image_id);

				// 取得該商品的優惠價格, 需在前台判斷 dsPrice 是否為 null (註: product_id為30幾的才有discount_price)
				DailySpecialBean dsBean = dailySpecialSvc.findDiscountPriceByProductId(product_id);
				Integer discount_price = dsBean.getDiscount_price();
				wBean.setDiscount_price(discount_price);
				System.out.println("wBean = " + wBean);

				bigList.add(wBean);
			}

			System.out.println("bigList = " + bigList);

			info.setFlag(true);
			info.setData(bigList);
			info.setMsg(member.getMember_account() + " 的收藏清單");

			writeValueByWriter(res, info);

		} // else的尾巴

	}

	// 加入收藏
	// 判斷是否重複product_id
	public void addBucketList(HttpServletRequest req, HttpServletResponse res) {

		ResultInfo info = new ResultInfo();
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		String member_account = member.getMember_account();
		Integer product_id = Convert.toInt(req.getParameter("ProductId"));
		Integer bucket_list_status = 1;

		// 印印看, 確認是否有從前台傳過來
		System.out.println("product_id = " + product_id);

//		要寫跟資料庫該會員現有的收藏是否重複
		BucketListBean bucketListBean = bucketListSvc.getOneBucketList(member_account, product_id);

		if (bucketListBean == null) {
			BucketListBean bucketListBean1 = bucketListSvc.addBucketList(member_account, product_id,
					bucket_list_status);

			info.setFlag(true);
			info.setMsg("成功加入收藏");
			info.setRedirect(req.getContextPath() + req.getServletPath() + "/whishlist_k.html");
			writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端

		} else {
			info.setFlag(false);
			info.setMsg("已收藏過相同商品");
			info.setRedirect(req.getContextPath() + req.getServletPath() + "/whishlist_k.html");
			writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端
		}

	}
	

	// 按下「垃圾桶」,是把bucket_list_status狀態改為0(因怡蓉說有分析價值), 且不顯示該商品。
	public void changeBucketListStatusToZero(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		String member_account = member.getMember_account();
		Integer product_id = Convert.toInt(req.getParameter("ProductId"));

		// 印印看, 確認是否有從前台傳過來
		System.out.println("member_account = " + member_account);
		System.out.println("product_id = " + product_id);

		if (product_id != null) {

			bucketListSvc.changeBucketListStatusToZero(member_account, product_id);

			ResultInfo info = new ResultInfo();
			info.setFlag(true);
			info.setMsg("成功將bucket_list_status狀態改為0");
			info.setRedirect(req.getContextPath() + req.getServletPath() + "/whishlist_k.html");
			writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端

		} else {

			ResultInfo info = new ResultInfo();
			info.setFlag(false);
			info.setMsg("因product_id為null, 所以將bucket_list_status狀態改為0 失敗");
			info.setRedirect(req.getContextPath() + req.getServletPath() + "/whishlist_k.html");
		}

	}

	// 真正的刪除(用不到)
	public void deleteBucketList(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		String member_account = member.getMember_account();
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
