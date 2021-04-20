package com.order_master.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.card_detail.model.CardDetailBean;
import com.cart.model.CartProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemberBean;
import com.order_detail.model.OrderDetailBean;
import com.order_detail.model.OrderDetailService;
import com.order_master.model.OrderMasterBean;
import com.order_master.model.OrderMasterService;
import com.product.model.ProductService;
import com.util.BaseServlet;
import com.util.ResultInfo;
import com.util.LineUtil;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.digest.DigestUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class OrderMasterServlet extends BaseServlet {
	
	OrderMasterService OrderMasterSvc = new OrderMasterService();
	OrderDetailService OrderDetailSvc = new OrderDetailService();
	CartProductService CartProductSvc = new CartProductService();
	ProductService ProductSvc = new ProductService();
	
	//載入訂單資料
	public void getOrderMaster(HttpServletRequest req, HttpServletResponse res) {
        MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/my-account.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
        	
        	List<OrderMasterBean> list_OM = OrderMasterSvc.getOrderMaster(member.getMember_account());
        	List<List> list_OD_All = new ArrayList<List>();
        	for (OrderMasterBean OMBean : list_OM) {
        		List<OrderDetailBean> list_OD = OrderDetailSvc.getAllByOrderMasterId(OMBean.getOrder_master_id());
        		list_OD_All.add(list_OD);
        	}
        	
        	List<List> list = new ArrayList<List>();
        	list.add(list_OM);
        	list.add(list_OD_All);
        	
        	info.setFlag(true);
        	info.setData(list);
        	info.setMsg(member.getMember_account()+"的訂單資料和訂單明細");
        }
        writeValueByWriter(res, info);
	}

	
	//載入信用卡資料
	public void getCardData(HttpServletRequest req, HttpServletResponse res) {
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/my-account.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
        	List<CardDetailBean> list_card = CartProductSvc.selectAllCard(member.getMember_account());
        	
			info.setData(list_card);
			info.setFlag(true);
			info.setMsg("已將信用卡資料載入!");	
        }
        writeValueByWriter(res, info);
	}
	
		
	//新增信用卡資料
	public void insertCreditCard(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/my-account.html");
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
			Integer card_id = CartProductSvc.insertCard(card_detailBean);
			card_detailBean = CartProductSvc.selectOneCard(card_id);

			info.setData(card_detailBean);
			info.setFlag(true);
			info.setMsg("已新增信用卡!");
		}

		writeValueByWriter(res, info);

	}
	
	
	//刪除信用卡資料
	public void deleteCreditCard(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
			req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/my-account.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
		} else {
			// 取資料
			Integer card_id = new Integer(req.getParameter("card_id"));
			// delete信用卡
			CartProductSvc.deleteCardById(card_id);

			info.setFlag(true);
			info.setMsg("已刪除信用卡!");
		}

		writeValueByWriter(res, info);

	}
	
	
	
	//載入後台訂單資料
	public void backend_getAll(HttpServletRequest req, HttpServletResponse res) {
		
		List<OrderMasterBean> list = OrderMasterSvc.getAll();
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("取得所有訂單資料!");
        info.setData(list);
        writeValueByWriter(res, info);
		
	}
	
	
	//修改訂單狀態
	public void backend_updateOrderMaster(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		//獲取數據
		Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
        //查詢原本orderMaster並修改status
        OrderMasterBean orderMasterBean = OrderMasterSvc.getOneOrderMaster(new Integer(map.get("order_master_id")[0])); 
        orderMasterBean.setOrder_status(new Integer(map.get("order_status")[0]));
        System.out.println(orderMasterBean);
        
        boolean flag = OrderMasterSvc.update_backend(orderMasterBean);
        
        ResultInfo info = new ResultInfo();
        //創建結果 準備返回前端
        if (flag) {
            //修改成功
            info.setFlag(true);
            info.setMsg("修改成功!");
        } else {
            //修改失敗
            info.setFlag(false);
            info.setMsg("修改失敗!");
        }
        
        writeValueByWriter(res, info);
	
	}
	
	
	//顯示訂單明細
	public void backend_showOrderDetail(HttpServletRequest req, HttpServletResponse res) {
		
		//獲取數據
		Integer order_master_id = new Integer(req.getParameter("order_master_id"));
		//查詢資料庫
		List<OrderDetailBean> list_OD = OrderDetailSvc.getAllByOrderMasterId(order_master_id);
		
		ResultInfo info = new ResultInfo();		
		if (list_OD == null) {
			info.setFlag(false);
			info.setMsg("查無訂單明細!");
		} else {
			info.setFlag(true);
			info.setMsg("查詢訂單明細成功!");
			info.setData(list_OD);
		}
	
		writeValueByWriter(res, info);
		
	}
	
	
	//取消訂單
	public void cancelOrderMaster(HttpServletRequest req, HttpServletResponse res) {
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/my-account.html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
			writeValueByWriter(res, info);
			return ;
        }
		
		//獲取數據
		Integer order_master_id = new Integer(req.getParameter("order_master_id"));
		//查詢原本orderMaster並修改status
		OrderMasterBean orderMasterBean = OrderMasterSvc.getOneOrderMaster(order_master_id);
		if (orderMasterBean.getOrder_status() == 0) {
			orderMasterBean.setOrder_status(3);
		} else {
			//取消失敗
            info.setFlag(false);
            info.setMsg("取消失敗!");
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/my-account.html");
            writeValueByWriter(res, info);
            return ;
		}		
		
		boolean flag = OrderMasterSvc.update_backend(orderMasterBean);
		
        //創建結果 準備返回前端
        if (flag) {
            //取消成功      	        	
            info.setFlag(true);
            info.setMsg("取消成功!");
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/my-account.html");
            //優惠券還原狀態
            if (orderMasterBean.getCoupon_id() != null && orderMasterBean.getCoupon_id() != 0) {
            	CartProductSvc.updateCouponStatus(orderMasterBean.getCoupon_id(), 0);
            }
            //Line發通知-取消訂單
            LineUtil.linePushMessage(orderMasterBean.getMember_account(), "您的訂單已取消!"); 
        } else {
            //取消失敗
            info.setFlag(false);
            info.setMsg("取消失敗!");
        }
        
        writeValueByWriter(res, info);
		
	}
	
	
	
	

	
	
	/*====================== 付款成功API ========================*/
	public void paySuccess(HttpServletRequest req, HttpServletResponse res) {
		
		//獲取數據
		Integer order_master_id = new Integer(req.getParameter("order_master_id"));
		
		ResultInfo info = new ResultInfo();
		//查詢原本orderMaster並修改資料
		OrderMasterBean orderMasterBean = OrderMasterSvc.getOneOrderMaster(order_master_id);
		if (orderMasterBean == null) {
			//付款失敗
            info.setFlag(false);
            info.setMsg("付款失敗!查無此訂單!");
            writeValueByWriter(res, info);
            return ;
		}
		if (orderMasterBean.getOrder_status() == 0) {
			orderMasterBean.setOrder_status(1);
		} else {
			//付款失敗
            info.setFlag(false);
            info.setMsg("付款失敗!訂單原狀態不是未付款!");
            writeValueByWriter(res, info);
            return ;
		}
		orderMasterBean.setPayment_time(new java.sql.Timestamp(System.currentTimeMillis()));
		orderMasterBean.setInvoice_number(CartProductSvc.invoice_random());
		
		boolean flag = OrderMasterSvc.update_backend(orderMasterBean);
		
        //創建結果 準備返回前端
        if (flag) {
            //付款成功
            info.setFlag(true);
            info.setData(orderMasterBean.getInvoice_number());
            info.setMsg("付款成功!");
            //更新銷售數量
            List<OrderDetailBean> list_OD = OrderDetailSvc.getAllByOrderMasterId(order_master_id);
            for (OrderDetailBean ODBean : list_OD) {
            	ProductSvc.addProductPurchase(ODBean.getProduct_id(), ODBean.getProduct_qty());
            } 
            //Line發通知-匯款成功
            if (orderMasterBean.getPayment_method() == 3) {           	
            	LineUtil.linePushMessage(orderMasterBean.getMember_account(), "匯款完成!");           	
            }       
            
        } else {
            //付款失敗
            info.setFlag(false);
            info.setMsg("付款失敗!");
        }
		
		writeValueByWriter(res, info);
		
	}
	

	
}
