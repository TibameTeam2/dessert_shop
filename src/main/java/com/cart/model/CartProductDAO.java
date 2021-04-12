package com.cart.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.card_detail.model.CardDetailBean;
import com.coupon.model.CouponBean;
import com.coupon_code.model.CouponCodeBean;
import com.emp.model.EmpJDBCDAO;
import com.emp.model.EmpVO;
import com.order_detail.model.OrderDetailBean;
import com.order_master.model.OrderMasterBean;
import com.util.JDBCUtil;

public class CartProductDAO {
	private static JdbcTemplate jdbcTemplate;
	private String driver = JDBCUtil.driver;
	private String url = JDBCUtil.url;
	private String userid = JDBCUtil.user;
	private String passwd = JDBCUtil.password;
	
	//拿cart_id, cart.product_id, product_quantity, product_name, product_price
	public List<CartProductBean> selectByMemberAccount(String member_account) {
		String SELECT_ALL =
			"SELECT cart_id, cart.product_id, product_quantity, product_name, product_price FROM sweet.cart left join product on product.product_id = cart.product_id where member_account = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CartProductBean> list = new ArrayList<CartProductBean>();
		CartProductBean cpBean = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, member_account);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				cpBean = new CartProductBean();
				cpBean.setCart_id(rs.getInt("cart_id"));
				cpBean.setProduct_id(rs.getInt("product_id"));
				cpBean.setProduct_quantity(rs.getInt("product_quantity"));
				cpBean.setProduct_name(rs.getString("product_name"));
				cpBean.setProduct_price(rs.getInt("product_price"));
				list.add(cpBean);
			}
			
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
		
	}
	
	
	//拿所有coupon
	public List<CouponBean> selectCouponByMemberAccount(String member_account) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String SELECT_ALL = "SELECT * FROM sweet.coupon where member_account = ? order by coupon_status desc, coupon_expire_date asc";

		CouponBean CB;
		ResultSet rs = null;

		List<CouponBean> list_couponBean = new ArrayList<CouponBean>();

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, member_account);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				CB = new CouponBean();
				CB.setCoupon_id(rs.getInt("coupon_id"));
				CB.setMember_account(rs.getString("member_account"));
				CB.setCoupon_sending_time(rs.getTimestamp("coupon_sending_time"));
				CB.setCoupon_effective_date(rs.getTimestamp("coupon_effective_date"));
				CB.setCoupon_expire_date(rs.getTimestamp("coupon_expire_date"));
				CB.setCoupon_text_content(rs.getString("coupon_text_content"));
				CB.setCoupon_content(rs.getFloat("coupon_content"));
				CB.setDiscount_type(rs.getInt("discount_type"));
				CB.setCoupon_status(rs.getInt("coupon_status"));
				CB.setEmployee_account(rs.getString("employee_account"));
				CB.setCoupon_code_id(rs.getInt("coupon_code_id"));

				list_couponBean.add(CB);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list_couponBean;
	}
	
	
	//拿第一張圖片資料流
	public InputStream getFirstImageByProductId(Integer product_id) {
		String SELECT_FIRST =
				"SELECT product_image FROM sweet.product_image where product_id = ? LIMIT 1";
			Connection con = null;
			PreparedStatement pstmt = null;
			
			ResultSet rs = null;
			InputStream product_image = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(SELECT_FIRST);
				
				pstmt.setInt(1, product_id);
				
				rs = pstmt.executeQuery();
				

				if (rs.next()) {
					product_image = rs.getBinaryStream("product_image");
				}
				
				
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			
			
		return product_image;
		
	}
	
	
	//update購物車商品數量
	public void updateProductQuantity(Integer cart_id, Integer product_quantity) {
		String UPDATE =
				"UPDATE sweet.cart set product_quantity = ? where cart_id = ?";
			Connection con = null;
			PreparedStatement pstmt = null;
			

			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setInt(1, product_quantity);
				pstmt.setInt(2, cart_id);
				
				pstmt.executeUpdate();
				
				System.out.println(1);
				
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
				
	}
	
	
	//delete商品
	public void deleteCart(Integer cart_id) {
		String DELETE =
			"DELETE FROM sweet.cart where cart_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, cart_id);
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	
	//select優惠碼
	public CouponCodeBean selectCouponCodeData(String coupon_code) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String SELECT_ID = "SELECT * FROM sweet.coupon_code WHERE coupon_code = ?";
		ResultSet rs = null;
		CouponCodeBean CCB = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_ID);

			pstmt.setString(1, coupon_code);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CCB = new CouponCodeBean();
				CCB.setCoupon_code_id(rs.getInt("coupon_code_id"));
				CCB.setCoupon_code(rs.getString("coupon_code"));
				CCB.setCoupon_code_effective_date(rs.getTimestamp("coupon_code_effective_date"));
				CCB.setCoupon_code_expire_date(rs.getTimestamp("coupon_code_expire_date"));
				CCB.setCoupon_code_text_content(rs.getString("coupon_code_text_content"));
				CCB.setCoupon_code_content(rs.getFloat("coupon_code_content"));
				CCB.setDiscount_type(rs.getInt("discount_type"));
				CCB.setEmployee_account(rs.getString("employee_account"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return CCB;
		
	}
	
	
	//insert優惠券
	public void insertCouponData(CouponBean CB) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		String INSERT = "INSERT INTO sweet.coupon(member_account,coupon_sending_time,"
				+ "coupon_effective_date,coupon_expire_date,coupon_text_content,coupon_content,"
				+ "discount_type,coupon_status,employee_account,coupon_code_id) VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, CB.getMember_account());
			pstmt.setTimestamp(2, CB.getCoupon_sending_time());
			pstmt.setTimestamp(3, CB.getCoupon_effective_date());
			pstmt.setTimestamp(4, CB.getCoupon_expire_date());
			pstmt.setString(5, CB.getCoupon_text_content());
			pstmt.setFloat(6, CB.getCoupon_content());
			pstmt.setInt(7, CB.getDiscount_type());
			pstmt.setInt(8, CB.getCoupon_status());
			pstmt.setString(9, CB.getEmployee_account());
			pstmt.setInt(10, CB.getCoupon_code_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	
	//select優惠券(對照用/回傳用)
	public CouponBean selectCouponData(String member_account, Integer coupon_code_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String SELECT = "SELECT * FROM sweet.coupon WHERE member_account = ? and coupon_code_id = ?";
		ResultSet rs = null;
		CouponBean CB = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT);
			
			pstmt.setString(1, member_account);
			pstmt.setInt(2, coupon_code_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CB = new CouponBean();
				CB.setCoupon_id(rs.getInt("coupon_id"));
				CB.setMember_account(rs.getString("member_account"));
				CB.setCoupon_sending_time(rs.getTimestamp("coupon_sending_time"));
				CB.setCoupon_effective_date(rs.getTimestamp("coupon_effective_date"));
				CB.setCoupon_expire_date(rs.getTimestamp("coupon_expire_date"));
				CB.setCoupon_text_content(rs.getString("coupon_text_content"));
				CB.setCoupon_content(rs.getFloat("coupon_content"));
				CB.setDiscount_type(rs.getInt("discount_type"));
				CB.setCoupon_status(rs.getInt("coupon_status"));
				CB.setEmployee_account(rs.getString("employee_account"));
				CB.setCoupon_code_id(rs.getInt("coupon_code_id"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return CB;
		
		
	}
	
	
	//select信用卡By會員
	public List<CardDetailBean> selectCardByMember(String member_account) {
		Connection con = null;
	    PreparedStatement pstmt = null;
		String SELECT = "SELECT * FROM sweet.card_detail where member_account = ?";
		
        List<CardDetailBean> list = new ArrayList<CardDetailBean>();
        CardDetailBean card_detailBean = null;    
        ResultSet rs = null;
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(SELECT);
            
            pstmt.setString(1, member_account);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                card_detailBean = new CardDetailBean();
                card_detailBean.setCard_id(rs.getInt("card_id"));
                card_detailBean.setMember_account(rs.getString("member_account"));
                card_detailBean.setCard_number(rs.getString("card_number"));
                card_detailBean.setCard_expired_day(rs.getString("card_expired_day"));
                card_detailBean.setCard_cvc(rs.getString("card_cvc"));
                card_detailBean.setCard_addedDate(rs.getTimestamp("card_addedDate"));
                list.add(card_detailBean);
            }
            
            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        
        return list;
        
    }
	
	
	//insert信用卡並回傳card_id
	public Integer insertCard(CardDetailBean card_detailBean) {

        Connection con = null;
        PreparedStatement pstmt = null;
        String INSERT = "INSERT INTO sweet.card_detail (member_account, card_number, card_expired_day, card_cvc) VALUES (?, ?, ?, ?)";

        ResultSet rs = null;
        Integer card_id = null;
        
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            String cols[] = {"card_id"};
            pstmt = con.prepareStatement(INSERT, cols);

            pstmt.setString(1, card_detailBean.getMember_account());
            pstmt.setString(2, card_detailBean.getCard_number());
            pstmt.setString(3, card_detailBean.getCard_expired_day());
            pstmt.setString(4, card_detailBean.getCard_cvc());

            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            card_id = rs.getInt(1);

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        
        return card_id;

    }
	
	
	//select單筆信用卡byID
	public CardDetailBean selectCardById(Integer card_id) {

      Connection con = null;
      PreparedStatement pstmt = null;
      String SELECT = "SELECT * FROM sweet.card_detail where card_id = ?";
      
      CardDetailBean card_detailBean = null;
      ResultSet rs = null;

      try {

          Class.forName(driver);
          con = DriverManager.getConnection(url, userid, passwd);
          pstmt = con.prepareStatement(SELECT);

          pstmt.setInt(1, card_id);

          rs = pstmt.executeQuery();

          while (rs.next()) {
              card_detailBean = new CardDetailBean();
              card_detailBean.setCard_id(rs.getInt("card_id"));
              card_detailBean.setMember_account(rs.getString("member_account"));
              card_detailBean.setCard_number(rs.getString("card_number"));
              card_detailBean.setCard_expired_day(rs.getString("card_expired_day"));
              card_detailBean.setCard_cvc(rs.getString("card_cvc"));
              card_detailBean.setCard_addedDate(rs.getTimestamp("card_addedDate"));
          }

          // Handle any driver errors
      } catch (ClassNotFoundException e) {
          throw new RuntimeException("Couldn't load database driver. "
                  + e.getMessage());
          // Handle any SQL errors
      } catch (SQLException se) {
          throw new RuntimeException("A database error occured. "
                  + se.getMessage());
          // Clean up JDBC resources
      } finally {
          if (rs != null) {
              try {
                  rs.close();
              } catch (SQLException se) {
                  se.printStackTrace(System.err);
              }
          }
          if (pstmt != null) {
              try {
                  pstmt.close();
              } catch (SQLException se) {
                  se.printStackTrace(System.err);
              }
          }
          if (con != null) {
              try {
                  con.close();
              } catch (Exception e) {
                  e.printStackTrace(System.err);
              }
          }
      }
      
      return card_detailBean;
      
	}
	
	
	//delete信用卡
	public void deleteCard(Integer card_id) {

      Connection con = null;
      PreparedStatement pstmt = null;
      String DELETE = "DELETE FROM sweet.card_detail where card_id = ?";

      try {

          Class.forName(driver);
          con = DriverManager.getConnection(url, userid, passwd);
          pstmt = con.prepareStatement(DELETE);

          pstmt.setInt(1, card_id);

          pstmt.executeUpdate();

          // Handle any driver errors
      } catch (ClassNotFoundException e) {
          throw new RuntimeException("Couldn't load database driver. "
                  + e.getMessage());
          // Handle any SQL errors
      } catch (SQLException se) {
          throw new RuntimeException("A database error occured. "
                  + se.getMessage());
          // Clean up JDBC resources
      } finally {
          if (pstmt != null) {
              try {
                  pstmt.close();
              } catch (SQLException se) {
                  se.printStackTrace(System.err);
              }
          }
          if (con != null) {
              try {
                  con.close();
              } catch (Exception e) {
                  e.printStackTrace(System.err);
              }
          }
      }
  
	}
	
	
	//OrderMaster和Detail一起insert
	public void insertOrderMaster(OrderMasterBean orderMasterBean, List<OrderDetailBean> list_orderDetailBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String INSERT = "INSERT INTO sweet.order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
    		// 先新增訂單資料
			String cols[] = {"order_master_id"};
			pstmt = con.prepareStatement(INSERT, cols);
			
			pstmt.setString(1, orderMasterBean.getMember_account());
			pstmt.setTimestamp(2, orderMasterBean.getPayment_time());
			pstmt.setInt(3, orderMasterBean.getPayment_method());
			pstmt.setInt(4, orderMasterBean.getCoupon_id());
			pstmt.setInt(5, orderMasterBean.getOrder_status());
			pstmt.setString(6, orderMasterBean.getInvoice_number());
			pstmt.setInt(7, orderMasterBean.getOrder_total());
			pstmt.setString(8, orderMasterBean.getOrder_remarks());	
			
//			Statement stmt=	con.createStatement();
//			stmt.executeUpdate("set auto_increment_offset=10;");    //自增主鍵-初始值
//			stmt.executeUpdate("set auto_increment_increment=10;"); //自增主鍵-遞增
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_order_master_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_order_master_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_order_master_id +"(剛新增成功的訂單資料Id)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			System.out.println("list_orderDetailBean.size()-A="+list_orderDetailBean.size());
			for (OrderDetailBean orderDetailBean : list_orderDetailBean) {
				orderDetailBean.setOrder_master_id(new Integer(next_order_master_id)) ;
				insertOrderDetail(orderDetailBean, con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list_orderDetailBean.size()-B="+list_orderDetailBean.size());
			System.out.println("新增訂單資料" + next_order_master_id + "時,共有明細" + list_orderDetailBean.size()
					+ "筆同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-orderMaster");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
			
		
	}
	//insert OrderDetail
	public void insertOrderDetail(OrderDetailBean orderDetailBean, Connection con) {
		
		PreparedStatement pstmt = null;
		String INSERT = "INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price) VALUES (?, ?, ?, ?)";

		try {

     		pstmt = con.prepareStatement(INSERT);

     		pstmt.setInt(1, orderDetailBean.getOrder_master_id());
			pstmt.setInt(2, orderDetailBean.getProduct_id());
			pstmt.setInt(3, orderDetailBean.getProduct_qty());
			pstmt.setInt(4, orderDetailBean.getProduct_price());

//			Statement stmt=	con.createStatement();
//			stmt.executeUpdate("set auto_increment_offset=7001;"); //自增主鍵-初始值
//			stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-orderDetail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		
		
	}
	
	
	

}
