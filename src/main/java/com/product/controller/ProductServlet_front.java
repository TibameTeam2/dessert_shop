package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.model.ProductBean;
import com.product.model.ProductService;
import com.product_image.model.ProductImageBean;
import com.product_image.model.ProductImageService;
import com.util.BaseServlet;
import com.util.ResultInfo;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class ProductServlet_front extends BaseServlet {

	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("test");
	}
	
// 後端的getAll，所有商品皆出現
// 呈現在http://localhost:8081/dessert_shop/product/backend_getAllProduct
	public void backend_getAllProduct(HttpServletRequest req, HttpServletResponse res) {
		ProductService productSvc = new ProductService();
		List<ProductBean> productList = productSvc.getAll();
		
		ResultInfo info = new ResultInfo();
		
		info.setFlag(true);
		info.setMsg("資料取得成功!");
		info.setData(productList);
		
		writeValueByWriter(res, info);
		
	}
	
	
// 前端的getAll，只有上架的可以出現
// 呈現在http://localhost:8081/dessert_shop/product/getAllProduct
	public void getAllProduct(HttpServletRequest req, HttpServletResponse res) {
		ProductService productSvc = new ProductService();
		List<ProductBean> productList = productSvc.getAllAvailable();

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

	// http://localhost:8081/dessert_shop/product/getAllSortByPurchase
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

	// http://localhost:8081/dessert_shop/product/getAllSortByPrice
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

	// http://localhost:8081/dessert_shop/product/getAllSortByCalorie
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

	// http://localhost:8081/dessert_shop/product/getAllSortBySweetness
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

	// http://localhost:8081/dessert_shop/product/backend_addProduct
	public void backend_addProduct(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		System.out.println("backend_addProduct 新增商品");//
		
		// 所有form表單裡的資訊，已由form表單傳遞，使用name為key去取對應的值
		String product_category = req.getParameter("product_category");
		System.out.println("收到的產品分類:" + product_category);//

		String[] product_type = { product_category.split(":")[0] };
		System.out.println("product_type:" + product_type[0]);//

		String[] product_subtype = { product_category.split(":")[1] };
		System.out.println("product_subtype:" + product_subtype[0]);//

		// 獲取addProduct_form的數據
    	Map<String, String[]> map = req.getParameterMap();
    	Map parameterMap = new HashMap(map);
    	parameterMap.put("product_type", product_type);
    	parameterMap.put("product_subtype", product_subtype);

    	System.out.println("parameterMap="+new ObjectMapper().writeValueAsString(parameterMap)); // 需要丟出JsonProcessingException

		// 封裝物件
    	ProductBean productBean = new ProductBean();
    	try {
    		BeanUtils.populate(productBean, parameterMap);
    		System.out.println("封裝好的productBean:" + productBean);
    		
    		// 調用service將商品新增至DB
    		ProductService productSvc = new ProductService();
    		productBean = productSvc.addProduct(productBean);
    		System.out.println("插入後的productBean(with Id):" + productBean);
    		
    	}catch(IllegalAccessException | InvocationTargetException e) {
    	    e.printStackTrace();
    	}
    	
		// 將product_id塞給productBean反回前端 → 跳轉到listAllProduct 直接從DB撈

    	List<ProductImageBean> piBeanList =  new ArrayList<ProductImageBean>();

		Collection<Part> parts = req.getParts();

		for (Part part : parts) {
			String header = part.getHeader("Content-Disposition");
			System.out.println("Part Header:"+header);//
			
			if (header.contains("pic")) { //找到每張照片
				System.out.println("OKOK");//
				ProductImageBean piBean = new ProductImageBean();
				piBean.setProduct_id(productBean.getProduct_id());
				
				System.out.println("backend_addProduct回傳的product_id:"+productBean.getProduct_id());//
				
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);

				piBean.setProduct_image(buf);
				piBeanList.add(piBean);
				in.close();
				System.out.println("附加一張照片");
			}  
		}
		System.out.println("for 完的piBeanList:"+ piBeanList);
		ProductImageService piSvc = new ProductImageService();
		piSvc.addMultiProductImages(piBeanList);
		
// 為什麼這裡沒跑?
		System.out.println("backend_addProduct跑完!");
		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setMsg("新增商品&照片成功!");//這行???

		writeValueByWriter(res, info);

	}

//  http://localhost:8081/dessert_shop/product/backend_checkProductName
	public void backend_checkProductName(HttpServletRequest req, HttpServletResponse res) {
		// 獲取數據
		String product_name = req.getParameter("product_name");
		System.out.println("checkProductName_商品名稱:" + product_name);//
		ProductService producSvc = new ProductService();
		// 新增getOneProductByName的service dao
		ProductBean productBean = producSvc.getOneProductByName(product_name);
		ResultInfo info = new ResultInfo();
		System.out.println("checkProductName_ResultInfo");//
		if (productBean == null) {
			info.setFlag(true);
			info.setMsg("\"" + product_name + "\"" + "可以使用");
		} else {
			info.setFlag(false);
			info.setMsg("\"" + product_name + "\"" + "名稱重複，請重新輸入");
		}
		writeValueByWriter(res, info);
	}

// 給別人使用的API >>>>> 改成用service的方法了

////  http://localhost:8081/dessert_shop/product/addProductPruchase
//    public Boolean addProductPruchase(HttpServletRequest req, HttpServletResponse res) {
//    	System.out.println("Servlet的addProductPruchase");
//    	
//    	Integer product_id = new Integer(req.getParameter("product_id"));
//    	Integer single_purchase = new Integer(req.getParameter("product_purchase"));
//    	ProductService productSvc = new ProductService();
//    	ProductBean productBean = productSvc.getOneProduct(product_id);
//    	
//    	try {
//    	Integer databaseTotal_purchase = productBean.getTotal_purchase();
//    	System.out.println("databaseTotal_purchase:"+databaseTotal_purchase);
//    	Integer newTotal_purchase = databaseTotal_purchase + single_purchase;
//    	System.out.println("newTotal_purchase:"+newTotal_purchase);
//    	
//    	productBean.setTotal_purchase(newTotal_purchase);
//    	System.out.println("更新銷售後的productBean:"+productBean);
// 	
//    	return true;
//    	
//    	} catch(Exception e) {
//    	
//    		ResultInfo info = new ResultInfo();
////        	
//        	info.setFlag(false);
//        	info.setMsg("商品銷售量更新失敗!");
//        	info.setData(productBean);
//        	
//        	writeValueByWriter(res, info);	
//    	return false;
//    	}
//    }
//    

//    在service中新增來自評論的星星與review的方法 給語心使用 

	// http://localhost:8081/dessert_shop/product/addReviewStar
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
