CREATE DATABASE IF NOT EXISTS sweet;
use sweet;
-- 使用 Ctrl+F 快速定位到自己的資料表 
-- 吳光軒 謝東陞 張浩倫 趙玉婷 梁語心 謝垚筠
-- 會員 會員信用卡 員工 員工權限內容 員工權限 商品資料表 收藏列表 每日優惠商品 商品照片
-- 訂閱者清單 電子報公告 即時客服 通知 公告管理 優惠碼 優惠券
-- 購物車 訂單資料 訂單明細 訂位明細 訂位紀錄 會員評論 評價圖片 業者回覆

DROP TABLE IF EXISTS review_image_upload;
DROP TABLE IF EXISTS dealer_reply;
DROP TABLE IF EXISTS member_comment;

DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS order_detail;
DROP TABLE IF EXISTS order_master;
DROP TABLE IF EXISTS book_detail;
DROP TABLE IF EXISTS book_record;

DROP TABLE IF EXISTS announcement_management;
DROP TABLE IF EXISTS coupon;
DROP TABLE IF EXISTS coupon_code;

DROP TABLE IF EXISTS bucket_list;
DROP TABLE IF EXISTS daily_special;
DROP TABLE IF EXISTS product_image;
DROP TABLE IF EXISTS product;

DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS live_support;
DROP TABLE IF EXISTS newsletter;
DROP TABLE IF EXISTS subscriber_list;

DROP TABLE IF EXISTS employee_authority;
DROP TABLE IF EXISTS authority_content;
DROP TABLE IF EXISTS card_detail;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS employee;

-- 以下設定: 自增主鍵的起點值，也就是初始值，取值範圍是1 .. 655355 --
-- set auto_increment_offset=1;
-- 以下設定: 自增主鍵每次遞增的量，其預設值是1，取值範圍是1 .. 65535 --
-- set auto_increment_increment=1;
-- 會員
CREATE TABLE member (
	member_account    VARCHAR(50) NOT NULL  PRIMARY KEY,
	member_password	  VARCHAR(50),
	member_name		  VARCHAR(50) NOT NULL,
	member_phone	  VARCHAR(50) NOT NULL,
	member_email	  VARCHAR(50) NOT NULL,
	member_photo	  LONGBLOB,
	member_gender	  TINYINT NOT NULL,
	member_birthday	  DATE NOT NULL,
	register_time	  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	register_method	  TINYINT NOT NULL,
	member_status	  TINYINT NOT NULL
);
INSERT INTO member (member_account,member_password,member_name,member_phone,member_email,member_photo,member_gender,member_birthday,register_method,member_status)
VALUES ('jason','202cb962ac59075b964b07152d234b70','傑森','0956732874','jasonwu1994@gmail.com',null,1,'1994-09-24',1,0);
INSERT INTO member (member_account,member_password,member_name,member_phone,member_email,member_photo,member_gender,member_birthday,register_method,member_status)
VALUES ('tom','202cb962ac59075b964b07152d234b70','湯姆貓','0911798243','tom@gmail.com',null,1,'2012-08-04',1,0);
INSERT INTO member (member_account,member_password,member_name,member_phone,member_email,member_photo,member_gender,member_birthday,register_method,member_status)
VALUES ('amy','202cb962ac59075b964b07152d234b70','怡婷','0984471254','amy@gmail.com',null,0,'2000-01-30',1,0);


-- 會員信用卡
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE card_detail (
	card_id     		 INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	member_account       VARCHAR(50) NOT NULL,
	card_number    		 VARCHAR(50) NOT NULL,
	card_expired_day 	 VARCHAR(50) NOT NULL,
	card_cvc      		 VARCHAR(50) NOT NULL,
	card_addedDate       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT cardDetail_member_FK FOREIGN KEY (member_account) REFERENCES member(member_account)
) AUTO_INCREMENT = 1;
INSERT INTO card_detail (member_account,card_number,card_expired_day,card_cvc)
VALUES ('tom','1441 5412 6512 9996','03/12','147');
INSERT INTO card_detail (member_account,card_number,card_expired_day,card_cvc)
VALUES ('tom','4167 1247 6103 5278','04/17','584');
INSERT INTO card_detail (member_account,card_number,card_expired_day,card_cvc)
VALUES ('jason','5873 7552 5827 1487','07/01','362');


-- 員工
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE employee (
	employee_account     VARCHAR(50) NOT NULL  PRIMARY KEY,
	employee_name        VARCHAR(50) NOT NULL,
	employee_password    VARCHAR(50) NOT NULL,
	employee_position 	 VARCHAR(50) NOT NULL,
	employee_photo       LONGBLOB,
	hire_date            DATE NOT NULL,
    employee_status      TINYINT NOT NULL
) AUTO_INCREMENT = 1;
INSERT INTO employee (employee_account,employee_name,employee_password,employee_position,employee_photo,hire_date,employee_status)
VALUES ('jason','傑森','1895','工讀生',null,'2021-02-21',1);
INSERT INTO employee (employee_account,employee_name,employee_password,employee_position,employee_photo,hire_date,employee_status)
VALUES ('peter','小吳','1234','主管',null,'2021-01-02',1);
INSERT INTO employee (employee_account,employee_name,employee_password,employee_position,employee_photo,hire_date,employee_status)
VALUES ('james','君岳','666','老闆',null,'2020-03-21',1);


-- 員工權限內容
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE authority_content (
	authority_content_id   	INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	authority_content     	VARCHAR(2000) NOT NULL
) AUTO_INCREMENT = 1;
INSERT INTO authority_content (authority_content) VALUES ('張貼公告');
INSERT INTO authority_content (authority_content) VALUES ('管理會員');
INSERT INTO authority_content (authority_content) VALUES ('管理商品');


-- 員工權限
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE employee_authority (
	authority_id    	     INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	employee_account   	     VARCHAR(50) NOT NULL,
	authority_content_id     INT NOT NULL,
	CONSTRAINT employeeAuthority_employee_FK FOREIGN KEY (employee_account) REFERENCES employee(employee_account),
	CONSTRAINT employeeAuthority_authorityContent_FK FOREIGN KEY (authority_content_id) REFERENCES authority_content(authority_content_id)
) AUTO_INCREMENT = 1;
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',1);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('peter',1);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('peter',2);


-- 趙玉婷------------------------------------------------------------------------------------------------------------
-- 商品資料表(product)
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE product (
	product_id				INT NOT NULL AUTO_INCREMENT  PRIMARY KEY,		
	product_name	  		VARCHAR(50) NOT NULL,
	product_type      		VARCHAR(50) NOT NULL,
    product_subtype      	VARCHAR(50) NOT NULL,
	product_intro	  		VARCHAR(500) NOT NULL,
    product_ingredient		varchar(2000) NOT NULL,
	product_price	  		SMALLINT NOT NULL,				
	product_available_qty	MEDIUMINT NOT NULL,
	product_status	  		TINYINT NOT NULL,
    expiry_after_buying    	TINYINT NOT NULL,
	product_calorie	  		SMALLINT NOT NULL,
	degree_of_sweetness     TINYINT NOT NULL,
    total_star 				INT NOT NULL,					
    total_review			INT NOT NULL,
    total_purchase			INT NOT NULL    
) AUTO_INCREMENT = 1;
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('Oreo巧克力杯子蛋糕', '蛋糕', '杯子蛋糕','採用義大利進口可可粉...', '可可粉、鮮奶油、巧克力醬', 100, 50, 1, 5, 210, 2, 860, 215, 121);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('草莓千層蛋糕', '蛋糕', '千層蛋糕','採用當日現採新鮮草莓...','鮮奶油、新鮮草莓、', 120, 50, 1, 4, 300, 2, 570, 115, 233);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('藍莓乳酪千層蛋糕', '蛋糕', '千層蛋糕','採用北海道生乳提煉...', '新鮮藍莓、藍莓果醬、creme cheese、牛奶', 120, 50, 1, 7, 310, 1, 115, 25, 156);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('綜合野莓蛋糕捲', '蛋糕', '蛋糕捲','歐洲進口冷凍莓果...', '藍莓、覆盆子、莓果醬、生乳', 120, 50, 1, 7, 310, 1, 115, 25, 156);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('香蕉拿鐵', '飲品', '香濃拿鐵','契作台灣香蕉入料...', '新鮮香蕉、牛奶、奶油、蜂蜜...', 120, 50, 1, 7, 310, 1, 115, 25, 156);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('蔓越莓軟心巧克力', '巧克力', '藝術巧克力','頂級可可粉...', '可可粉、蔓越莓果醬、奶油、明膠...', 65, 100, 1, 10, 211, 3, 135, 30, 216);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('藍莓巧心棒', '巧克力', '藝術巧克力','頂級可可粉...', '可可粉、藍、奶油、椰子粉...', 50, 150, 1, 7, 130, 2, 115, 26, 356);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('特濃苦甜巧克力', '巧克力', '藝術巧克力','頂級可可粉...', '苦甜巧克力、調溫巧克力、奶油...', 70, 150, 1, 10, 230, 1, 118, 25, 546);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('核仁椰棗布朗尼', '巧克力', '生巧克力','中東椰棗...', '核桃、椰棗、奶油、蜂蜜...', 60, 100, 1, 7, 164, 3, 129, 31, 146);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('橙香松子巧克力', '巧克力', '巴克巧克力','松子...', '柳橙乾、檸檬乾、松子...', 120, 50, 1, 10, 133, 2, 130, 35, 133);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('幻彩星空馬卡龍', '精緻小點', '馬卡龍','天然色素...', '蝶豆花、杏仁粉、蛋白、砂糖...', 80, 50, 1, 5, 213, 2, 135, 28, 1346);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('蜜桃甜心馬卡龍', '精緻小點', '馬卡龍','當季水蜜桃...', '水蜜桃、草莓、奶油、杏仁粉、蛋白、砂糖...', 65, 60, 1, 3, 123, 2, 137, 29, 356);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('開心卡士達閃電泡芙', '精緻小點', '泡芙','嚴選開心果...', '開心果、牛奶、奶油、蜂蜜...', 110, 80, 1, 5, 157, 1, 134, 29, 146);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('玫瑰蘋果塔', '精緻小點', '派&塔','日本富士蘋果醃漬...', '新鮮蘋果、糖粉、奶油、肉桂...', 125, 60, 1, 3, 168, 1, 315, 69, 656);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('紅酒櫻桃鮮果酪', '精緻小點', '鮮果酪','紅酒醃漬櫻桃...', '酒漬櫻桃、櫻桃醬、牛奶、雞蛋...', 115, 30, 1, 3, 146, 1, 345, 77, 339);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('大和抹茶', '飲品', '特調茶飲','進口抹茶粉...', '抹茶粉、水...', 165, 100, 1, 1, 10, 1, 349, 79, 349);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('宇治金時拿鐵', '飲品', '香濃拿鐵','進口抹茶粉...', '抹茶粉、牛奶、...', 185, 100, 1, 1, 96, 1, 335, 77, 339);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('蜂蜜菊花茶', '飲品', '特調茶飲','洋甘菊...', '菊花、綠茶、蜂蜜...', 160, 100, 1, 1, 46, 2, 145, 32, 133);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('棉花糖可可牛奶', '飲品', '香濃拿鐵','swiss miss可可粉...', '可可粉、牛奶、棉花糖...', 165, 100, 1, 1, 358, 4, 335, 75, 139);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('香草拿鐵咖啡', '飲品', '經典咖啡','深焙咖啡...', '咖啡、香草、牛奶、蔗糖...', 175, 100, 1, 1, 316, 2, 335, 77, 239);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('莓果香橙果昔', '飲品', '繽紛水果飲','新鮮當季水果...', '柳橙、檸檬、藍莓、桑葚、蜂蜜...', 180, 100, 1, 1, 155, 1, 445, 99, 439);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('草莓鮮奶果昔', '飲品', '繽紛水果飲','新鮮當季水果...', '草莓、蜂蜜、牛奶、蜂蜜...', 185, 100, 1, 1, 165, 1, 435, 105, 449);

-- 收藏列表
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE bucket_list (
	bucket_list_id    	     INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	member_account   	     VARCHAR(50) NOT NULL,
	product_id     		     INT NOT NULL,
    bucket_list_status       TINYINT NOT NULL,
	CONSTRAINT bucketList_member_FK FOREIGN KEY (member_account) REFERENCES member(member_account),
	CONSTRAINT bucketList_product_FK FOREIGN KEY (product_id) REFERENCES product(product_id)
) AUTO_INCREMENT = 1;
INSERT INTO bucket_list (member_account, product_id, bucket_list_status)VALUES ('jason',1,1);
INSERT INTO bucket_list (member_account, product_id, bucket_list_status)VALUES ('jason',2,1);
INSERT INTO bucket_list (member_account, product_id, bucket_list_status)VALUES ('jason',3,1);
INSERT INTO bucket_list (member_account, product_id, bucket_list_status)VALUES ('tom',2,1);
INSERT INTO bucket_list (member_account, product_id, bucket_list_status)VALUES ('amy',3,1);
INSERT INTO bucket_list (member_account, product_id, bucket_list_status)VALUES ('amy',1,1);


-- 每日優惠商品(daily_special)
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE daily_special (
	discount_product_id 	INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	product_id				INT NOT NULL,
    discount_price    		SMALLINT NOT NULL,						
	discount_start_time	  	TIMESTAMP NOT NULL,
    discount_deadline		TIMESTAMP NOT NULL,
	CONSTRAINT daily_special_product_FK FOREIGN KEY (product_id) REFERENCES product(product_id)
) AUTO_INCREMENT = 1;
INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline)
VALUES ('1', 540, '2021-02-22 00:00:00', '2021-02-28 23:59:59');
INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline)
VALUES ('2', 270, '2021-02-22 00:00:00', '2021-02-28 23:59:59');
INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline)
VALUES ('3', 180, '2021-02-22 00:00:00', '2021-02-28 23:59:59');


-- 商品照片(product_image)
set auto_increment_offset = 1;
set auto_increment_increment = 1;
CREATE TABLE product_image (
	image_id			INT  AUTO_INCREMENT  NOT NULL  PRIMARY KEY,
	product_id			INT	 NOT NULL,								
	product_image    	LONGBLOB  NOT NULL,			
	CONSTRAINT product_image_product_FK FOREIGN KEY (product_id) REFERENCES product(product_id)
) AUTO_INCREMENT = 1;
INSERT INTO product_image (product_id, product_image)
VALUES ('001', LOAD_FILE('C:\\project\\images\\product_image\\oreoCup-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('001', LOAD_FILE('C:\\project\\images\\product_image\\oreoCup-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('002', LOAD_FILE('C:\\project\\images\\product_image\\strawCrepe-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('002', LOAD_FILE('C:\\project\\images\\product_image\\strawCrepe-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('001', LOAD_FILE('C:\\project\\images\\product_image\\oreoCup-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('002', LOAD_FILE('C:\\project\\images\\product_image\\strawCrepe-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('002', LOAD_FILE('C:\\project\\images\\product_image\\strawCrepe-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('003', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCrepe-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('003', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCrepe-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('004', LOAD_FILE('C:\\project\\images\\product_image\\3berryRoll-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('003', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCrepe-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('004', LOAD_FILE('C:\\project\\images\\product_image\\3berryRoll-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('004', LOAD_FILE('C:\\project\\images\\product_image\\3berryRoll-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('005', LOAD_FILE('C:\\project\\images\\product_image\\bananaLatte-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('005', LOAD_FILE('C:\\project\\images\\product_image\\bananaLatte-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('005', LOAD_FILE('C:\\project\\images\\product_image\\bananaLatte-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('006', LOAD_FILE('C:\\project\\images\\product_image\\berryBullet-a.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('006', LOAD_FILE('C:\\project\\images\\product_image\\berryBullet-b.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('006', LOAD_FILE('C:\\project\\images\\product_image\\berryBullet-c.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('006', LOAD_FILE('C:\\project\\images\\product_image\\berryBullet-d.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('006', LOAD_FILE('C:\\project\\images\\product_image\\berryBullet-e.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('007', LOAD_FILE('C:\\project\\images\\product_image\\blueBar-a.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('007', LOAD_FILE('C:\\project\\images\\product_image\\blueBar-b.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('007', LOAD_FILE('C:\\project\\images\\product_image\\blueBar-c.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('007', LOAD_FILE('C:\\project\\images\\product_image\\blueBar-d.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('008', LOAD_FILE('C:\\project\\images\\product_image\\darkBar-a.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('008', LOAD_FILE('C:\\project\\images\\product_image\\darkBar-b.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('008', LOAD_FILE('C:\\project\\images\\product_image\\darkBar-c.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('008', LOAD_FILE('C:\\project\\images\\product_image\\darkBar-d.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('008', LOAD_FILE('C:\\project\\images\\product_image\\darkBar-e.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('009', LOAD_FILE('C:\\project\\images\\product_image\\brownieChoco-a.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('009', LOAD_FILE('C:\\project\\images\\product_image\\brownieChoco-b.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('009', LOAD_FILE('C:\\project\\images\\product_image\\brownieChoco-c.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('009', LOAD_FILE('C:\\project\\images\\product_image\\brownieChoco-d.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('009', LOAD_FILE('C:\\project\\images\\product_image\\brownieChoco-e.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('010', LOAD_FILE('C:\\project\\images\\product_image\\apricotBark-a.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('010', LOAD_FILE('C:\\project\\images\\product_image\\apricotBark-b.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('011', LOAD_FILE('C:\\project\\images\\product_image\\starMocaron-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('011', LOAD_FILE('C:\\project\\images\\product_image\\starMocaron-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('011', LOAD_FILE('C:\\project\\images\\product_image\\starMocaron-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('011', LOAD_FILE('C:\\project\\images\\product_image\\starMocaron-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('011', LOAD_FILE('C:\\project\\images\\product_image\\starMocaron-5.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('011', LOAD_FILE('C:\\project\\images\\product_image\\starMocaron-6.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('012', LOAD_FILE('C:\\project\\images\\product_image\\peachMacaron-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('012', LOAD_FILE('C:\\project\\images\\product_image\\peachMacaron-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('012', LOAD_FILE('C:\\project\\images\\product_image\\peachMacaron-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('012', LOAD_FILE('C:\\project\\images\\product_image\\peachMacaron-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('012', LOAD_FILE('C:\\project\\images\\product_image\\peachMacaron-5.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('012', LOAD_FILE('C:\\project\\images\\product_image\\peachMacaron-6.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('013', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('013', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('013', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('013', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('013', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-5.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('013', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-6.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('013', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-7.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('014', LOAD_FILE('C:\\project\\images\\product_image\\appleT-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('014', LOAD_FILE('C:\\project\\images\\product_image\\appleT-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('014', LOAD_FILE('C:\\project\\images\\product_image\\appleT-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('014', LOAD_FILE('C:\\project\\images\\product_image\\appleT-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('015', LOAD_FILE('C:\\project\\images\\product_image\\berryPC-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('015', LOAD_FILE('C:\\project\\images\\product_image\\berryPC-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('015', LOAD_FILE('C:\\project\\images\\product_image\\berryPC-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('016', LOAD_FILE('C:\\project\\images\\product_image\\matcha-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('016', LOAD_FILE('C:\\project\\images\\product_image\\matcha-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('016', LOAD_FILE('C:\\project\\images\\product_image\\matcha-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('016', LOAD_FILE('C:\\project\\images\\product_image\\matcha-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('017', LOAD_FILE('C:\\project\\images\\product_image\\matchaLatte-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('017', LOAD_FILE('C:\\project\\images\\product_image\\matchaLatte-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('017', LOAD_FILE('C:\\project\\images\\product_image\\matchaLatte-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('017', LOAD_FILE('C:\\project\\images\\product_image\\matchaLatte-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('018', LOAD_FILE('C:\\project\\images\\product_image\\chrysanT-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('018', LOAD_FILE('C:\\project\\images\\product_image\\chrysanT-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('018', LOAD_FILE('C:\\project\\images\\product_image\\chrysanT-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('018', LOAD_FILE('C:\\project\\images\\product_image\\chrysanT-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('019', LOAD_FILE('C:\\project\\images\\product_image\\toastedMCoffee-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('019', LOAD_FILE('C:\\project\\images\\product_image\\toastedMCoffee-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('019', LOAD_FILE('C:\\project\\images\\product_image\\toastedMCoffee-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('019', LOAD_FILE('C:\\project\\images\\product_image\\toastedMCoffee-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('019', LOAD_FILE('C:\\project\\images\\product_image\\toastedMCoffee-5.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('019', LOAD_FILE('C:\\project\\images\\product_image\\toastedMCoffee-6.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('020', LOAD_FILE('C:\\project\\images\\product_image\\vanillaCoffee-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('020', LOAD_FILE('C:\\project\\images\\product_image\\vanillaCoffee-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('020', LOAD_FILE('C:\\project\\images\\product_image\\vanillaCoffee-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('020', LOAD_FILE('C:\\project\\images\\product_image\\vanillaCoffee-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('021', LOAD_FILE('C:\\project\\images\\product_image\\bOrangeSm-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('021', LOAD_FILE('C:\\project\\images\\product_image\\bOrangeSm-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('021', LOAD_FILE('C:\\project\\images\\product_image\\bOrangeSm-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('022', LOAD_FILE('C:\\project\\images\\product_image\\strawMilkSm-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('022', LOAD_FILE('C:\\project\\images\\product_image\\strawMilkSm-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('022', LOAD_FILE('C:\\project\\images\\product_image\\strawMilkSm-3.jpg'));



-- 謝垚筠------------------------------------------------------------------------------------------------------------
-- 訂閱者清單
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE subscriber_list (
	subscriber_id			INT  AUTO_INCREMENT NOT NULL PRIMARY KEY,
    subscriber_email 		VARCHAR(50) NOT NULL,
    subscriber_status		TINYINT NOT NULL,
    subscriber_date			TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    member_account   		VARCHAR(50), 	
    CONSTRAINT subscriberList_member_FK FOREIGN KEY(member_account) REFERENCES  member(member_account)
)AUTO_INCREMENT=1;
INSERT INTO subscriber_list(subscriber_email,subscriber_status) VALUES ('David@yahoo.cm.tw','1');
INSERT INTO subscriber_list(subscriber_email,subscriber_status,member_account) VALUES ('jason@gmail.com','1','jason');
INSERT INTO subscriber_list(subscriber_email,subscriber_status,member_account) VALUES ('peter@gmail.com','1','tom');


-- 電子報公告 --
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE newsletter (
	newsletter_id				INT AUTO_INCREMENT NOT NULL PRIMARY KEY,  
    newsletter_content			VARCHAR(1000), 
    newsletter_image 			LONGBLOB,
    newsletter_releasing_time 	TIMESTAMP NOT NULL ,
    newsletter_status			TINYINT NOT NULL,
    employee_account			VARCHAR(50) NOT NULL,
    CONSTRAINT newsletter_employee_FK FOREIGN KEY(employee_account) REFERENCES  employee(employee_account)
)AUTO_INCREMENT=1;
INSERT INTO newsletter(newsletter_content,newsletter_releasing_time,newsletter_status,employee_account) 
VALUES('以色列已經有近半數國民打了輝瑞新冠疫苗第一劑。以色列政府決定，2月21號開始，全國大部份解封，不過進入密閉空間、娛樂場所，仍必須下載以色列衛生部的「綠色通行證」，經認證後才可以進入。以色列直說，這是他們「回歸正常生活的開始」','2021-01-01','1','peter');
INSERT INTO newsletter(newsletter_image,newsletter_releasing_time,newsletter_status,employee_account) VALUES (LOAD_FILE('C:/test_img/tomcat.png'),'2021-02-02','0','james');
INSERT INTO newsletter(newsletter_content,newsletter_image,newsletter_releasing_time,newsletter_status,employee_account) VALUES ('譯自英文-托馬斯·貓（Thomas Cat）是一個虛構的角色，也是米高梅（Metro-Goldwyn-Mayer）的湯姆和傑瑞（Tom and Jerry）戲劇動畫短篇小說系列中兩個名義上的主角之一。',LOAD_FILE('C:/test_img/tomcat2.png'),'2021-02-02','0','james');


-- 即時客服 --
set auto_increment_offset=1; 		
set auto_increment_increment=1; 	
CREATE TABLE live_support (
	customer_service_id		INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	chat_history			VARCHAR(500) NOT NULL,  
    sender					TINYINT NOT NULL,	-- 0員工發的 1會員發的
    chat_time				TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    member_account			VARCHAR(50) NOT NULL,
    employee_account		VARCHAR(50) NOT NULL,
    CONSTRAINT liveSupport_member_FK FOREIGN KEY(member_account) REFERENCES  member(member_account),
    CONSTRAINT liveSupport_employee_FK FOREIGN KEY(employee_account) REFERENCES  employee(employee_account)
)AUTO_INCREMENT=1;
INSERT INTO live_support(chat_history,sender,member_account,employee_account) VALUES ('我們需要神隊友','1','tom','peter');
INSERT INTO live_support(chat_history,sender,member_account,employee_account) VALUES ('無此選項','0','tom','peter');
INSERT INTO live_support(chat_history,sender,member_account,employee_account) VALUES ('那就放棄吧','1','tom','peter');


-- 通知 --
set auto_increment_offset=1; 		
set auto_increment_increment=1; 	
CREATE TABLE notice (
	notice_id			INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    notice_type			TINYINT NOT NULL,
	notice_content		VARCHAR(1000)  NOT NULL,	
    notice_time			TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    read_status			TINYINT NOT NULL,
	member_account		VARCHAR(50) NOT NULL,
    CONSTRAINT notice_member_FK FOREIGN KEY(member_account) REFERENCES  member(member_account)
)AUTO_INCREMENT=1;
INSERT INTO notice(notice_type,notice_content,read_status,member_account) VALUES ('1','提醒您：03/01 14:00 有訂位，位子保留10分鐘，逾期不候，若不克前來，請提前通知，謝謝!','0','amy');
INSERT INTO notice(notice_type,notice_content,read_status,member_account) VALUES ('1','提醒您：04/01 16:00 有訂位，位子保留10分鐘，逾期不候，若不克前來，請提前通知，謝謝!','0','jason');
INSERT INTO notice(notice_type,notice_content,read_status,member_account) VALUES ('0','提醒您：05/01 12:00 有訂位，位子保留10分鐘，逾期不候，若不克前來，請提前通知，謝謝!','0','tom');


-- 張浩倫------------------------------------------------------------------------------------------------------------
-- 公告管理
set auto_increment_offset=1;
set auto_increment_increment=1;
create table announcement_management (
	announcement_id       int AUTO_INCREMENT NOT NULL  PRIMARY KEY,
	announcement_name	  VARCHAR(50) NOT NULL,
	announcement_content  VARCHAR(2000),
	announcement_image	  LONGBLOB,
	announcement_time	  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	announcement_type	  TINYINT NOT NULL,
	announcement_status	  TINYINT NOT NULL,
    employee_account      varchar(50) NOT NULL,        
    foreign key (employee_account) references employee (employee_account)
)AUTO_INCREMENT=1;
insert into announcement_management (announcement_name,announcement_content,
announcement_image,announcement_type,announcement_status,employee_account)
values('提拉米蘇特價','提拉米蘇超好吃der',null,1,1,'jason');
insert into announcement_management (announcement_name,announcement_content,
announcement_image,announcement_type,announcement_status,employee_account)
values('檸檬塔暫停供應','員工擠檸檬噴到眼睛',null,1,1,'peter');


-- 優惠碼
set auto_increment_offset=1;
set auto_increment_increment=1;
create table coupon_code(
	coupon_code_id                  INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    coupon_code                     VARCHAR(50) NOT NULL,
	coupon_code_effective_date      TIMESTAMP not null,
	coupon_code_expire_date         TIMESTAMP NOT NULL,
	coupon_code_text_content        VARCHAR(2000) NOT NULL,
	coupon_code_content             FLOAT NOT NULL,
	discount_type                   TINYINT NOT NULL,
	employee_account                VARCHAR(50) NOT NULL,
	foreign key (employee_account) references employee(employee_account)
)AUTO_INCREMENT=1;
insert into coupon_code(coupon_code,coupon_code_effective_date,coupon_code_expire_date,
coupon_code_text_content,coupon_code_content,discount_type,employee_account)
values('MAH6203',"2021-02-27","2022-03-27",'訂餐享8折',0.8,0,'jason');
insert into coupon_code(coupon_code,coupon_code_effective_date,coupon_code_expire_date,
coupon_code_text_content,coupon_code_content,discount_type,employee_account)
values('9K8928',"2021-03-14","2021-03-15",'單身者在白色情人節折價30元',30,1,'peter');
insert into coupon_code(coupon_code,coupon_code_effective_date,coupon_code_expire_date,
coupon_code_text_content,coupon_code_content,discount_type,employee_account)
values('AGQ2598',"2021-03-17","2021-03-31",'即期蛋糕享6折',0.6,0,'james');
insert into coupon_code(coupon_code,coupon_code_effective_date,coupon_code_expire_date,
coupon_code_text_content,coupon_code_content,discount_type,employee_account)
values('HCJ2473',"2021-04-05","2022-04-06",'黑咖啡折20元',20,1,'james');
insert into coupon_code(coupon_code,coupon_code_effective_date,coupon_code_expire_date,
coupon_code_text_content,coupon_code_content,discount_type,employee_account)
values('FUCK7414',"2021-04-05","2022-02-06",'糖料併折50元',50,1,'james');


-- 優惠券
set auto_increment_offset=1;
set auto_increment_increment=1;
create table coupon(
	coupon_id               INT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
	member_account          VARCHAR(50) NOt NULL,
	coupon_sending_time     TIMESTAMP NOT NULL,
	coupon_effective_date   TIMESTAMP NOT NULL,
	coupon_expire_date      TIMESTAMP NOT NULL,
	coupon_text_content     VARCHAR(2000) NOT NULL,
	coupon_content          FLOAT NOT NULL,
	discount_type           TINYINT NOT NULL,
	coupon_status           TINYINT NOT NULL,
	employee_account        VARCHAR(50) NOT NULL,
	coupon_code_id          INT,
	foreign key (employee_account) references employee(employee_account),
	foreign key (member_account) references member(member_account),
	foreign key (coupon_code_id) references coupon_code(coupon_code_id)
)AUTO_INCREMENT=1;
insert into coupon(member_account,coupon_sending_time,coupon_effective_date,
coupon_expire_date,coupon_text_content,
coupon_content,discount_type,coupon_status,employee_account,coupon_code_id)
values('tom','2021-02-27','2021-02-27','2022-03-27','訂餐享8折',0.8,0,0,'jason',1);
insert into coupon(member_account,coupon_sending_time,coupon_effective_date,
coupon_expire_date,coupon_text_content,
coupon_content,discount_type,coupon_status,employee_account,coupon_code_id)
values('jason','2021-03-17','2021-03-17','2021-03-31','即期蛋糕享6折',0.6,0,0,'peter','3');
insert into coupon(member_account,coupon_sending_time,coupon_effective_date,
coupon_expire_date,coupon_text_content,
coupon_content,discount_type,coupon_status,employee_account,coupon_code_id)
values('jason','2021-04-05','2021-04-06','2022-04-06','黑咖啡折20元',20,1,1,'peter','4');
insert into coupon(member_account,coupon_sending_time,coupon_effective_date,
coupon_expire_date,coupon_text_content,
coupon_content,discount_type,coupon_status,employee_account,coupon_code_id)
values('jason','2021-03-11','2021-03-14','2021-03-15','單身者在白色情人節折價30元',30,1,0,'peter','2');



-- 謝東陞------------------------------------------------------------------------------------------------------------
-- 購物車
set auto_increment_offset=1;
set auto_increment_increment=1; 
CREATE TABLE cart (
	cart_id   		  int not null auto_increment PRIMARY KEY,
	member_account	  varchar(50) not null,
	product_id		  int not null,
	product_quantity  int not null,
	constraint cart_member_fk foreign key (member_account) references member (member_account),
    constraint cart_product_fk foreign key (product_id) references product (product_id)
) AUTO_INCREMENT = 1;
INSERT INTO cart (member_account, product_id, product_quantity)VALUES ('jason', 1, '8');
INSERT INTO cart (member_account, product_id, product_quantity)VALUES ('tom', 2, '7');
INSERT INTO cart (member_account, product_id, product_quantity)VALUES ('amy', 3, '8');
INSERT INTO cart (member_account, product_id, product_quantity)VALUES ('jason', 3, '7');


-- 訂單資料
set auto_increment_offset=1;
set auto_increment_increment=1; 
CREATE TABLE order_master (
	order_master_id      int not null auto_increment PRIMARY KEY,
	member_account	     varchar(50) not null,
	order_time		     timestamp default current_timestamp,
	payment_time         timestamp,
    payment_method       tinyint not null,
    coupon_id			 int,
    order_status		 tinyint not null,
    invoice_number		 varchar(50),
    order_total			 int not null,
    order_remarks	  	 varchar(2000),
    constraint orderMaster_member_FK foreign key (member_account) references member (member_account)
--  constraint orderMaster_coupon_FK foreign key (coupon_id) references coupon(coupon_id)
) AUTO_INCREMENT = 1;
INSERT INTO  order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks)
VALUES ('tom', '2021-01-02 03:04:05', '1', 2, '1', 'AA12345678', '8', '紅色死神說要加糖');
INSERT INTO  order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks)
VALUES ('jason', '2021-01-02 03:04:06', '1', null, '1', 'AA12345672', '7', '紅色死神說要加鹽');
INSERT INTO  order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks)
VALUES ('amy', '2021-01-02 03:04:07', '1', null, '2', 'AA12345673', '6', '紅色死神說要加醬油');


-- 訂單明細
set auto_increment_offset=1;
set auto_increment_increment=1; 
CREATE TABLE order_detail (
	order_detail_id   int not null auto_increment PRIMARY KEY,
	order_master_id	  int not null,
	product_id		  int not null,
	product_qty       int not null,
    product_price     int not null,
	constraint orderDetail_orderMaster_FK foreign key (order_master_id) references order_master (order_master_id),
    constraint orderDetail_product_FK foreign key (product_id) references product (product_id)
) AUTO_INCREMENT = 1;
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('1', 1, '8', '100');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('2', 2, '7', '120');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('3', 3, '8', '120');


-- 訂位明細
set auto_increment_offset=1;
set auto_increment_increment=1; 
CREATE TABLE book_detail (
	booking_detail_id   	 	 int not null auto_increment PRIMARY KEY,
	member_account	   		 	 varchar(50),
	booking_establish_time	     timestamp default current_timestamp not null,
	booking_time                 timestamp not null,
    people_num					 int not null,
    booking_status				 int not null,
    book_postscript				 varchar(2000),
    contact_num					 varchar(50) not null,
    booking_name				 varchar(50) not null,
    constraint bookDetail_member_FK foreign key (member_account) references member (member_account)
) AUTO_INCREMENT = 1;
INSERT INTO book_detail (member_account, booking_time, people_num, booking_status, book_postscript, contact_num, booking_name)
VALUES ('jason', '2021-01-02 03:04:05', '7', '1', '紅色死神加兒童椅', '09-12345678', '紅色死神');
INSERT INTO book_detail (member_account, booking_time, people_num, booking_status, book_postscript, contact_num, booking_name)
VALUES ('tom', '2021-01-02 03:04:06', '8', '4', '藍色死神加個兒童椅', '09-12345672', '藍色死神');
INSERT INTO book_detail (member_account, booking_time, people_num, booking_status, book_postscript, contact_num, booking_name)
VALUES ('amy', '2021-01-02 03:04:07', '9', '1', '綠色死神加個兒童椅', '09-12345673', '綠色死神');


-- 訂位紀錄
set auto_increment_offset=1;
set auto_increment_increment=1; 
CREATE TABLE book_record (
	book_record_id  		  int not null auto_increment PRIMARY KEY,
	booking_date	  		  date not null,
	ten_total_count		      tinyint not null,
	twelve_total_count        tinyint not null,
    fourteen_total_count      tinyint not null,
    sixteen_total_count 	  tinyint not null,
    eighteen_total_count 	  tinyint not null,
    twenty_total_count 	      tinyint not null
) AUTO_INCREMENT = 1;
INSERT INTO book_record (booking_date, ten_total_count, twelve_total_count, fourteen_total_count, sixteen_total_count, eighteen_total_count, twenty_total_count)
VALUES ('2021-01-02', '0', '1', '2', '3', '4', '5');
INSERT INTO book_record (booking_date, ten_total_count, twelve_total_count, fourteen_total_count, sixteen_total_count, eighteen_total_count, twenty_total_count)
VALUES ('2021-01-03', '1', '2', '3', '4', '5', '6');
INSERT INTO book_record (booking_date, ten_total_count, twelve_total_count, fourteen_total_count, sixteen_total_count, eighteen_total_count, twenty_total_count)
VALUES ('2021-01-04', '2', '3', '4', '5', '6', '7');


-- 梁語心------------------------------------------------------------------------------------------------------------
-- 會員評論
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE member_comment(
	review_id			INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    order_detail_id		INT NOT NULL,
    comment_content 	VARCHAR(2000),
    rating 				TINYINT NOT NULL,
    comment_time 		TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    product_id			INT NOT NULL,
    comment_status 		TINYINT NOT NULL,
    constraint fk_member_comment_order_detail foreign key (order_detail_id) references order_detail (order_detail_id),
    constraint fk_member_comment_product foreign key (product_id) references product (product_id)
) AUTO_INCREMENT = 1;
INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES(1, '好', 5, 1, 1);
INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES(1, '好吃', 4, 2, 1);
INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES(1, '好吃好', 1, 3, 1);


-- 評價圖片
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE review_image_upload(
	review_image_id		INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	review_image		LONGBLOB NOT NULL,
	review_id 			INT NOT NULL,
	constraint fk_review_image_upload_member_comment foreign key (review_id) references member_comment(review_id)
) AUTO_INCREMENT = 1;
INSERT INTO review_image_upload(review_image, review_id) VALUES (LOAD_FILE('C:\\project\\images\\review_image_upload\\pie1.jpg'), 1);
INSERT INTO review_image_upload(review_image, review_id) VALUES (LOAD_FILE('C:\\project\\images\\review_image_upload\\pie2.jpg'), 2);
INSERT INTO review_image_upload(review_image, review_id) VALUES (LOAD_FILE('C:\\project\\images\\review_image_upload\\pie3.jpg'), 3);


-- 業者回覆
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE dealer_reply(
	reply_id			INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	review_id			INT NOT NULL,
	reply_content		VARCHAR(2000) NOT NULL,
	reply_time 			TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	employee_account	VARCHAR(50) NOT NULL,
 constraint FK_dealerReply_memberComment foreign key (review_id) references member_comment(review_id),
 constraint FK_dealerReply_employee foreign key (employee_account) references employee(employee_account)
) AUTO_INCREMENT = 1;
INSERT INTO dealer_reply(review_id, reply_content, employee_account) VALUES(1, '謝謝您的惠顧1', 'jason');
INSERT INTO dealer_reply(review_id, reply_content, employee_account) VALUES(2, '謝謝您的惠顧2', 'peter');
INSERT INTO dealer_reply(review_id, reply_content, employee_account) VALUES(3, '謝謝您的惠顧3', 'james');


show VARIABLES like '%max_allowed_packet%';
set global max_allowed_packet = 400*1024*1024;