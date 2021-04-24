package com.cart.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.card_detail.model.CardDetailBean;
import com.cart.model.CartBean;
import com.cart.model.CartProductBean;
import com.cart.model.CartProductService;
import com.coupon.model.CouponBean;
import com.coupon.model.CouponService;
import com.coupon_code.model.CouponCodeBean;
import com.member.model.MemberBean;
import com.notice.model.NoticeBean;
import com.notice.model.NoticeService;
import com.order_detail.model.OrderDetailBean;
import com.order_detail.model.OrderDetailService;
import com.order_master.model.OrderMasterBean;
import com.product.model.ProductService;
import com.util.BaseServlet;
import com.util.JedisUtil;
import com.util.ResultInfo;
import com.util.LineUtil;

import cn.hutool.core.io.IoUtil;
import redis.clients.jedis.Jedis;

public class CartServlet extends BaseServlet {

	CartProductService svc = new CartProductService();
	OrderDetailService OrderDetailSvc = new OrderDetailService();
	ProductService ProductSvc = new ProductService();
	CouponService CouponSvc = new CouponService();
	NoticeService NoticeSvc = new NoticeService();

	// 取得購物車資料
	public void getCartData(HttpServletRequest req, HttpServletResponse res) {

		// 從session取得member
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/cart.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			List<List> list = svc.getCartDataByMemberAccount(member.getMember_account(), req.getContextPath());
			info.setData(list);

			info.setFlag(true);
			info.setMsg("已將購物車+圖片src+現貨數量資料載入!");
		}

		writeValueByWriter(res, info);

	}

	// 取得優惠券資料
	public void getCouponData(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/cart.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			List<CouponBean> list = svc.getCouponDataByMemberAccount(member.getMember_account());
			info.setData(list);

			info.setFlag(true);
			info.setMsg("已將優惠券資料載入!");
		}

		writeValueByWriter(res, info);

	}

	// 印出圖片
	public void getProductImage(HttpServletRequest req, HttpServletResponse res) {

		// 取得product_id
		Integer product_id = new Integer(req.getParameter("product_id"));

		res.setContentType("image/png");

		try {
			IoUtil.write(res.getOutputStream(), true,
					IoUtil.readBytes(svc.getProductImageByProductId(product_id), true));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 更新購物車內商品數量
	public void updateQuantity(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/cart.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			// 取資料
			Integer cart_id = new Integer(req.getParameter("cart_id"));
			Integer product_id = new Integer(req.getParameter("product_id"));
			Integer product_quantity = new Integer(req.getParameter("product_quantity"));
			// 更新數量
			List<Integer> list_product_qty_and_max_qty = svc.updateQuantityAtCart(cart_id, product_id, product_quantity);		
			info.setData(list_product_qty_and_max_qty);
			
			info.setFlag(true);
			info.setMsg("已更新數量!");
		}

		writeValueByWriter(res, info);

	}

	// 刪除購物車內商品
	public void deleteProduct(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/cart.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			// 取資料
			Integer cart_id = new Integer(req.getParameter("cart_id"));
			// 刪除商品
			svc.deleteProductAtCart(cart_id);

			info.setFlag(true);
			info.setMsg("已刪除商品!");
		}

		writeValueByWriter(res, info);

	}

	// 使用優惠碼新增優惠券
	public void insertCoupon(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/cart.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			// 取資料
			String coupon_code = req.getParameter("coupon_code").trim();
			System.out.println(coupon_code);
			// 新增優惠券

			// 檢查優惠碼是否存在
			CouponCodeBean CCB = svc.selectCouponCode(coupon_code);
			if (CCB != null) {

				// 檢查優惠碼是否過期
				java.sql.Timestamp ts_now = new java.sql.Timestamp(System.currentTimeMillis());
				if (ts_now.getTime() < CCB.getCoupon_code_expire_date().getTime()) {

					// 檢查該會員是否有該優惠碼生成的優惠券
					CouponBean CB_exist = svc.selectCoupon(member.getMember_account(), CCB.getCoupon_code_id());
					if (CB_exist == null) {

						CouponBean CB_new = new CouponBean();
						CB_new.setMember_account(member.getMember_account());
						CB_new.setCoupon_sending_time(ts_now);
						CB_new.setCoupon_effective_date(ts_now);
						CB_new.setCoupon_expire_date(CCB.getCoupon_code_expire_date());
						CB_new.setCoupon_text_content(CCB.getCoupon_code_text_content());
						CB_new.setCoupon_content(CCB.getCoupon_code_content());
						CB_new.setDiscount_type(CCB.getDiscount_type());
						CB_new.setCoupon_status(0);
						CB_new.setEmployee_account(CCB.getEmployee_account());
						CB_new.setCoupon_code_id(CCB.getCoupon_code_id());
						svc.insertCoupon(CB_new);

						info.setFlag(true);
						info.setMsg("成功使用優惠碼新增優惠券!");
						CouponBean CB_insert_success = svc.selectCoupon(member.getMember_account(),
								CB_new.getCoupon_code_id());
						info.setData(CB_insert_success);

					} else {
						info.setFlag(false);
						info.setMsg("優惠碼已使用過!");
					}

				} else {
					info.setFlag(false);
					info.setMsg("優惠碼已過期!");
				}

			} else {
				info.setFlag(false);
				info.setMsg("優惠碼無效!");
			}

		}

		writeValueByWriter(res, info);

	}

	// session設定所選優惠券並跳轉結帳
	public void setCouponAndCheckout(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/cart.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			// 檢查購物車是否為空
			if (svc.getCartDataBeforeOrder(member.getMember_account()).isEmpty()) {
				info.setFlag(false);
				info.setMsg("購物車內無商品!");
			} else {
							
				List<CartProductBean> list_cartProductBean = svc.getCartDataBeforeOrder(member.getMember_account());
				//檢查購物車商品數量是否有商品超過現貨
				boolean overlimit = false;
				for (CartProductBean CPBean : list_cartProductBean) {
					Integer max_product_quantity = svc.getProductAvailableQty(CPBean.getProduct_id());
					if (CPBean.getProduct_quantity() > max_product_quantity && max_product_quantity != 0 && max_product_quantity != null) {
						svc.updateQuantityAtCart(CPBean.getCart_id(), CPBean.getProduct_id(), max_product_quantity);
						overlimit = true;
					} else if (max_product_quantity == null || max_product_quantity == 0) {
						svc.deleteProductAtCart(CPBean.getCart_id());
						overlimit = true;
					}
				}
				if(overlimit) {
					info.setFlag(false);
					info.setMsg("購物車內商品現貨不足!");
					info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/cart.html");
					writeValueByWriter(res, info);
					return ;
				}		
						
				
				// 取資料
				Integer coupon_id = new Integer(req.getParameter("coupon_id"));
				Integer coupon_price = new Integer(req.getParameter("coupon_price"));
				
				// 設定coupon_id
				if (coupon_id == 0) {
					req.getSession().removeAttribute("coupon_id");
					req.getSession().removeAttribute("coupon_price");
					info.setFlag(true);
					info.setMsg("不使用優惠券前往結帳!");
					System.out.println(info.getMsg());
					info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/checkout.html");
				} else {
					req.getSession().setAttribute("coupon_id", coupon_id);
					req.getSession().setAttribute("coupon_price", coupon_price);
					info.setFlag(true);
					info.setMsg("已設定優惠券並前往結帳!");
					System.out.println(info.getMsg());
					info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/checkout.html");
				}

			}

		}

		writeValueByWriter(res, info);

	}

	// checkout載入資料
	public void checkoutData(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		Integer coupon_price = (Integer) req.getSession().getAttribute("coupon_price");		
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/checkout.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			List list = svc.getCartDataByMemberAccount(member.getMember_account(), req.getContextPath());
			List<CardDetailBean> list_card = svc.selectAllCard(member.getMember_account());
			list.add(list_card);
			list.add(member);
			list.add(coupon_price);

			info.setData(list);
			info.setFlag(true);
			info.setMsg("已將購物車+圖片src+現貨數量+信用卡+會員+優惠券資料載入!");
		}

		writeValueByWriter(res, info);

	}

	// 新增信用卡資料
	public void insertCreditCard(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/checkout.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			// 取資料並包裝
			String card_number = req.getParameter("card_number").trim();
			String card_expired_day = req.getParameter("card_expired_day").trim();
			String card_cvc = req.getParameter("card_cvc").trim();
			
			//信用卡正規表示法判斷
			String reg1 = "^[0-9]{4}\\s[0-9]{4}\\s[0-9]{4}\\s[0-9]{4}$";
			String reg2 ="^((0[1-9])|(1[0-2]))/[0-9]{2}$";
			String reg3 = "^[0-9]{3}$";			
			if (!card_number.matches(reg1) || !card_expired_day.matches(reg2) || !card_cvc.matches(reg3)) {
				info.setFlag(false);
				info.setMsg("信用卡格式不符!");
				writeValueByWriter(res, info);
				return ;
			}			

			CardDetailBean card_detailBean = new CardDetailBean();
			card_detailBean.setMember_account(member.getMember_account());
			card_detailBean.setCard_number(card_number);
			card_detailBean.setCard_expired_day(card_expired_day);
			card_detailBean.setCard_cvc(card_cvc);

			// insert信用卡取得Id並取得該信用卡資料
			Integer card_id = svc.insertCard(card_detailBean);
			card_detailBean = svc.selectOneCard(card_id);

			info.setData(card_detailBean);
			info.setFlag(true);
			info.setMsg("已新增信用卡!");
		}

		writeValueByWriter(res, info);

	}

	// 刪除信用卡資料
	public void deleteCreditCard(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/checkout.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			// 取資料
			Integer card_id = new Integer(req.getParameter("card_id"));
			// delete信用卡
			svc.deleteCardById(card_id);

			info.setFlag(true);
			info.setMsg("已刪除信用卡!");
		}

		writeValueByWriter(res, info);

	}

	// 送出訂單
	public void setOrder(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		Integer coupon_id = (Integer) req.getSession().getAttribute("coupon_id");
		OrderMasterBean orderMasterBean = new OrderMasterBean();
		// 取付款方式
		Integer payment_method = new Integer(req.getParameter("payment_method"));
		String card_number = req.getParameter("card_number");
		
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/checkout.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else if (payment_method == 0) {
			info.setFlag(false);
			info.setMsg("付款方式未設定完成!");
		} else if (svc.getCartDataBeforeOrder(member.getMember_account()).isEmpty()) {
			info.setFlag(false);
			info.setMsg("購物車內無商品!");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/cart.html");
		} else {
			// 取資料並包裝
			// orderMaster
			java.sql.Timestamp payment_time = null;
			Integer order_status = 0;
			String invoice_number = null;
			
			if (payment_method == 1) {
				// 信用卡付款
				
				//檢查信用卡資料是否存在
				List<CardDetailBean> list_card = svc.selectAllCard(member.getMember_account());
				boolean card_number_exist = false;
				for (CardDetailBean CDBean : list_card) {
					if (CDBean.getCard_number().equals(card_number)) {
						card_number_exist = true;
						break;
					}
				}
				if (!card_number_exist) {
					info.setFlag(false);
					info.setMsg("查無信用卡資料!");
					info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/checkout.html");
					writeValueByWriter(res, info);
					return ;
				}
				
				payment_time = new java.sql.Timestamp(System.currentTimeMillis()); // 當前時間
				order_status = 1; // 已付款
				invoice_number = svc.invoice_random(); // 生成發票
			}
			Integer order_total = new Integer(req.getParameter("order_total"));
			String order_remarks = req.getParameter("order_remarks").trim();
			System.out.println(order_remarks);

			orderMasterBean.setMember_account(member.getMember_account());
			orderMasterBean.setPayment_time(payment_time);
			orderMasterBean.setPayment_method(payment_method);
			orderMasterBean.setCoupon_id(coupon_id);
			orderMasterBean.setOrder_status(order_status);
			orderMasterBean.setInvoice_number(invoice_number);
			orderMasterBean.setOrder_total(order_total);
			orderMasterBean.setOrder_remarks(order_remarks);

			
			List<CartProductBean> list_cartProductBean = svc.getCartDataBeforeOrder(member.getMember_account());
			//檢查購物車商品數量是否有商品超過現貨
			boolean overlimit = false;
			for (CartProductBean CPBean : list_cartProductBean) {
				Integer max_product_quantity = svc.getProductAvailableQty(CPBean.getProduct_id());
				if (CPBean.getProduct_quantity() > max_product_quantity && max_product_quantity != 0 && max_product_quantity != null) {
					svc.updateQuantityAtCart(CPBean.getCart_id(), CPBean.getProduct_id(), max_product_quantity);
					overlimit = true;
				} else if (max_product_quantity == null || max_product_quantity == 0) {
					svc.deleteProductAtCart(CPBean.getCart_id());
					overlimit = true;
				}
			}
			if(overlimit) {
				info.setFlag(false);
				info.setMsg("購物車內商品現貨不足!");
				info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/cart.html");
				writeValueByWriter(res, info);
				return ;
			}
			
			
			// 生成list_orderDetailBean和訂單內容
			List<OrderDetailBean> list_orderDetailBean = new ArrayList<OrderDetailBean>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String current_time = sdf.format(new java.sql.Timestamp(System.currentTimeMillis()));
			String order_message = "訂單日期："+current_time+"\n訂單內容：";
			for (CartProductBean CPBean : list_cartProductBean) {
				OrderDetailBean ODBean = new OrderDetailBean();
				ODBean.setProduct_id(CPBean.getProduct_id());
				ODBean.setProduct_qty(CPBean.getProduct_quantity());
				ODBean.setProduct_price(CPBean.getProduct_price());
				list_orderDetailBean.add(ODBean);			
				//Line訂單內容
				order_message += "\n" + CPBean.getProduct_name() + " x " + CPBean.getProduct_quantity();		
			}
			if (coupon_id != null) {
				order_message += "\n優惠內容：" + CouponSvc.getOneCoupon(coupon_id).getCoupon_text_content();
			}
			order_message += "\n訂單金額：" + order_total + "元";
			

			// insert訂單
			Integer new_order_master_id = svc.insertOrder(orderMasterBean, list_orderDetailBean);
			
			// 清除購物車+減少商品現貨數量
			svc.deleteCartAndReduceProductQty(list_cartProductBean);
		
			// 改優惠券狀態
			if (coupon_id != null) {
				svc.updateCouponStatus(coupon_id, 1);
			}
			req.getSession().removeAttribute("coupon_id");
			req.getSession().removeAttribute("coupon_price");					
			
			//Line通知訊息-到店付款方式
			String message = "您的訂單完成!\n請撥空至本店付款!\n" + order_message;	
			
			info.setMsg("到店付款方式的訂單完成!");
			
			//匯款方式處理
			if (payment_method == 1) {
				info.setMsg("信用卡付款方式的訂單完成!");
				//更新銷售數量
				List<OrderDetailBean> list_OD = OrderDetailSvc.getAllByOrderMasterId(new_order_master_id);
	            for (OrderDetailBean ODBean : list_OD) {
	            	ProductSvc.addProductPurchase(ODBean.getProduct_id(), ODBean.getProduct_qty());
	            }
	            //Line通知訊息-信用卡付款方式
	            message = "您的訂單已使用信用卡付款完成!\n" + order_message;
			} else if (payment_method == 3) {
				info.setMsg("匯款方式的訂單完成!");
				String payCode = svc.payCode_random(); // 生成匯款帳戶
				info.setData(payCode);
				// 存Redis
				Jedis jedis = JedisUtil.getJedis();
				jedis.hset(member.getMember_account(), "payCode_id-" + new_order_master_id, payCode);
				jedis.close();
				//Line通知訊息-匯款方式
				message = "您的訂單完成!\n請匯款至此銀行帳戶:\n" + payCode + "\n" + order_message;
			}
			
			info.setFlag(true);		
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/my-account.html?active=order");
			 			
			//Line發通知
			LineUtil.linePushMessage(member.getMember_account(), message);
			
			//Notice
			NoticeBean noticeBean = new NoticeBean();
			noticeBean.setMember_account(member.getMember_account());
			noticeBean.setNotice_type(1);
			noticeBean.setNotice_dispatcher(req.getContextPath() + "/TEA103G2/front-end/my-account.html?active=order");
			String notice_content =  "通知!訂單日期：" + current_time + "，狀態：";
			if (payment_method == 1) {
				notice_content += "已付款!";
			} else {
				notice_content += "未付款!";
			}
			noticeBean.setNotice_content(notice_content);	
			NoticeSvc.addWSNotice(noticeBean);
			
		}

		writeValueByWriter(res, info);

	}
	
	

	/* ==================== 商品頁面新增商品到購物車 ==================== */
	// 請在登入狀態下傳product_id及product_quantity
	public void insertProductIntoCart(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/product-all.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			Integer product_id = new Integer(req.getParameter("product_id"));
			Integer product_quantity = new Integer(req.getParameter("product_quantity"));
			
			CartBean cartBean = new CartBean();
			cartBean.setMember_account(member.getMember_account());
			cartBean.setProduct_id(product_id);
			cartBean.setProduct_quantity(product_quantity);
			//新增或更新購物車商品數量
			svc.insertOrUpdateCart(cartBean);

			info.setFlag(true);
			info.setData(svc.getProductAvailableQty(product_id));
			info.setMsg("已將商品新增購物車並回傳現貨數量!");
		}

		writeValueByWriter(res, info);

	}

	
	/* ==================== 取得購物車當前商品數量 ==================== */
	
	public void getCartQty(HttpServletRequest req, HttpServletResponse res) {
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			return ;
		} else {
			Integer cart_qty = svc.getCartDataBeforeOrder(member.getMember_account()).size();
			info.setData(cart_qty);		
		}
		
		writeValueByWriter(res, info);
		
	}
	
	
	
}
