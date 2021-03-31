package com.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartProductBean;
import com.cart.model.CartProductService;
import com.member.model.MemberBean;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class CartServlet extends BaseServlet {
	
	CartProductService svc = new CartProductService();
	
	public void getCartData(HttpServletRequest req, HttpServletResponse res) {
		
		//從session取得member
        MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
            info.setFlag(true);
            info.setMsg("已將購物車內商品載入!");
            
            List<CartProductBean> list = svc.getCartDataByMemberAccount(member.getMember_account());   
                      
            info.setData(list);        
        }
        
        writeValueByWriter(res, info);
		
	}
	
	

}
