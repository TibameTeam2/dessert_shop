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
	

	// http://localhost:8081/dessert_shop/product/backend_getOneProductByName
	public void backend_getOneProductByName(HttpServletRequest req, HttpServletResponse res) {
		String product_name = req.getParameter("product_name");
		
		System.out.println("Servlet product_name:"+product_name);
		
		ProductService productSvc = new ProductService();
		ProductBean productBean = productSvc.getOneProductByName(product_name);
		System.out.println("servlet backend_getOneProductByName:"+productBean);
		
		
		ResultInfo info = new ResultInfo();
		
		info.setFlag(true);
		info.setMsg("資料取得成功!");
		info.setData(productBean);
		
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
	
	
// 後端的getAllDailySpecial，不論效期所有優惠商品皆出現
// 呈現在http://localhost:8081/dessert_shop/product/backend_getAllDailySpecial
	public void backend_getAllDailySpecial(HttpServletRequest req, HttpServletResponse res) {
		ProductService productSvc = new ProductService();
		List<ProductBean> productList = productSvc.getAll();
		
		ResultInfo info = new ResultInfo();
		
		info.setFlag(true);
		info.setMsg("資料取得成功!");
		info.setData(productList);
		
		writeValueByWriter(res, info);
		
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


	// 後端_修改商品資訊
	// http://localhost:8081/dessert_shop/product/backend_updateProuct
	public void backend_updateProuct(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("更新商品資訊");
		// 所有form表單裡的資訊，已由form表單傳遞，使用name為key去取對應的值
				String product_category = req.getParameter("product_category");
				System.out.println("更新的產品分類:" + product_category);//

				String[] product_type = { product_category.split(":")[0] };
				System.out.println("product_type:" + product_type[0]);//

				String[] product_subtype = { product_category.split(":")[1] };
				System.out.println("product_subtype:" + product_subtype[0]);//

				// 獲取updateProduct_form的數據
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
		    		
		    		// 調用service將商品"更新"至DB
		    		ProductService productSvc = new ProductService();
		    		productBean = productSvc.updateProduct(productBean);
		    		System.out.println("更新後的productBean:" + productBean);
		    		
		    	}catch(IllegalAccessException | InvocationTargetException e) {
		    	    e.printStackTrace();
		    	}
// 照片的部分 修改???		    	
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
						System.out.println("附加一張照片");//
					}  
				}
				System.out.println("for 完的piBeanList:"+ piBeanList);
				ProductImageService piSvc = new ProductImageService();
				piSvc.addMultiProductImages(piBeanList);
				
				System.out.println("backend_addProduct跑完!");//
				ResultInfo info = new ResultInfo();
				info.setFlag(true);
				info.setMsg("修改商品資訊成功!");

				writeValueByWriter(res, info);

				System.out.println("修改商品資訊成功!");//
		
	}
	
// 後端_修改商品狀態
	// http://localhost:8081/dessert_shop/product/backend_updateProuctStatus
	public void backend_updateProuctStatus(HttpServletRequest req, HttpServletResponse res){
		System.out.println("Servlet更新商品狀態");
		Integer product_id = new Integer(req.getParameter("product_id"));
		Integer product_status = new Integer(req.getParameter("product_status"));
		System.out.println("後端更新取得product_status:"+product_status);
		ProductService productSvc = new ProductService();
		productSvc.updateProductStatus(product_id, product_status);

		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("商品狀態更新成功!");

		writeValueByWriter(res, info);
		
	}


}
