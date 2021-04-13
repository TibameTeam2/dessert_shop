package com.product.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.model.ProductBean;
import com.product.model.ProductService;
import com.util.BaseServlet;
import com.util.ResultInfo;


public class ProductServlet_front extends BaseServlet {
	
    public void test(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("test");
    }   
    
// 呈現在http://localhost:8081/dessert_shop/product/getAllProduct
    public void getAllProduct(HttpServletRequest req, HttpServletResponse res) {
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAll();
    	   	
		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("資料取得成功!");
		info.setData(productList);

		writeValueByWriter(res, info);

    }
    
    // http://localhost:8081/dessert_shop/product/getOneProduct?id=?
    public void getOneProduct(HttpServletRequest req, HttpServletResponse res) {
    	Integer product_id = new Integer(req.getParameter("id"));
    	ProductService productSvc = new ProductService();
    	ProductBean productBean = productSvc.getOneProduct(product_id);

		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("資料取得成功!");
		info.setData(productBean);

		writeValueByWriter(res, info);

    }
    
    //  http://localhost:8081/dessert_shop/product/getAllSortByPurchase
    public void getAllSortByPurchase(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("銷售量排序");
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAllSortByPurchase();
    	   	
		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("資料取得成功!");
		info.setData(productList);

		writeValueByWriter(res, info);
    }
    
    // http://localhost:8081/dessert_shop/product/getAllSortByStar
    public void getAllSortByStar(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("累計星等排序");
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAllSortByStar();
    	
    	ResultInfo info = new ResultInfo();
    	
    	info.setFlag(true);
    	info.setMsg("資料取得成功!");
    	info.setData(productList);
    	
    	writeValueByWriter(res, info);
    }
    
    //  http://localhost:8081/dessert_shop/product/getAllSortByPrice
    public void getAllSortByPrice(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("價格排序");
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAllSortByPrice();
    	
    	ResultInfo info = new ResultInfo();
    	
    	info.setFlag(true);
    	info.setMsg("資料取得成功!");
    	info.setData(productList);
    	
    	writeValueByWriter(res, info);
    }
 
    //  http://localhost:8081/dessert_shop/product/getAllSortByCalorie
    public void getAllSortByCalorie(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("熱量排序");
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAllSortByCalorie();
    	
    	ResultInfo info = new ResultInfo();
    	
    	info.setFlag(true);
    	info.setMsg("資料取得成功!");
    	info.setData(productList);
    	
    	writeValueByWriter(res, info);
    }

    //  http://localhost:8081/dessert_shop/product/getAllSortBySweetness
    public void getAllSortBySweetness(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("甜度排序");
    	ProductService productSvc = new ProductService();
    	List<ProductBean> productList = productSvc.getAllSortBySweetness();
    	
    	ResultInfo info = new ResultInfo();
    	
    	info.setFlag(true);
    	info.setMsg("資料取得成功!");
    	info.setData(productList);
    	
    	writeValueByWriter(res, info);
    }

//  下方輪播的熱賣商品 取暢銷前五名
//  http://localhost:8081/dessert_shop/product/getHotSales
    public void getHotSales(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("熱賣商品");
    	ProductService productSvc = new ProductService();
//    	List<ProductBean> productList = productSvc.getHotSales();
//    	
//    	ResultInfo info = new ResultInfo();
//    	
//    	info.setFlag(true);
//    	info.setMsg("資料取得成功!");
//    	info.setData(productList);
//    	
//    	writeValueByWriter(res, info);
    }
    
    
    
    //  http://localhost:8081/dessert_shop/product/backend_addProduct
    public void backend_addProduct(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
    	System.out.println("新增商品");
    	System.out.println(req.getParameter("product_name"));
    	// 所有form表單裡的資訊，已由form表單傳遞，使用name為key去取對應的值
    	
    	// 獲取addProduct_form的數據
    	Map<String, String[]> map = req.getParameterMap();
    	
// 這裡要把type跟subtype擷取出來設定給Map

// 圖片的部分?
    	
    	System.out.println("map="+new ObjectMapper().writeValueAsString(map)); // 需要丟出JsonProcessingException
    	
    	// 封裝物件
    	ProductBean productBean = new ProductBean();
    	try {
    		BeanUtils.populate(productBean, map);
    	}catch(IllegalAccessException | InvocationTargetException e) {
    	    e.printStackTrace();
    	}
    	System.out.println(productBean);
    	
    	
    	
    	// 調用service將商品新增至DB
    	
    	
    	// 將product_id塞給productBean反回前端
    	
    	
//    	ProductService productSvc = new ProductService();
//    	List<ProductBean> productList = productSvc.getAllSortBySweetness();
//    	
//    	ResultInfo info = new ResultInfo();
//    	
//    	info.setFlag(true);
//    	info.setMsg("資料取得成功!");
//    	info.setData(productList);
//    	
//    	writeValueByWriter(res, info);
    }
    
//  http://localhost:8081/dessert_shop/product/backend_checkProductName
    public void backend_checkProductName(HttpServletRequest req, HttpServletResponse res) {
    	//獲取數據
    	String product_name = req.getParameter("product_name");
    	System.out.println("商品名稱:" + product_name);//
    	ProductService producSvc = new ProductService();
 // 新增getOneProductByName的service dao
    	ProductBean productBean = producSvc.getOneProductByName(product_name);
    	ResultInfo info = new ResultInfo();
    	System.out.println("ResultInfo");//
    	if(productBean == null) {
    		System.out.println("ResultInfo的null,flag要設true");//
    		info.setFlag(true);
    		info.setMsg("\""+ product_name +"\"" + "可以使用");
    	}else {
    		System.out.println("ResultInfo的else,flag要設false");//
    		info.setFlag(false);
    		info.setMsg("\"" + product_name + "\"" + "名稱重複，請重新輸入");
    	}
    	writeValueByWriter(res, info);
    }
    
    
    
    
    
    
    
// 給別人使用的API
    
    //id?  star?  review+1
//    在service中新增來自評論的星星與review的方法 給語心使用 
    
    //  http://localhost:8081/dessert_shop/product/addReviewStar
//    public void addReviewStar(HttpServletRequest req, HttpServletResponse res) {
//    	System.out.println("新增星等與評論");
//    	Integer star_rating = new Integer (req.getParameter("rating"));
//    	ProductService productSvc = new ProductService();
//    	ProductBean productBean = new ProductBean(); 
//    	ProductBean updateBean = productSvc.addReviewStar(productBean);
//    	
//    	ResultInfo info = new ResultInfo();
//    	
//    	info.setFlag(true);
//    	info.setMsg("評論與星等更新成功!");
//    	info.setData(updateBean);
//    	
//    	writeValueByWriter(res, info);
//    	
//    }
    
    
    
    
    
    
}
