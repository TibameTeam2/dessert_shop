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
VALUES ('jason','202cb962ac59075b964b07152d234b70','傑森','0956732874','jasonwu1994@gmail.com',LOAD_FILE('C:\\project\\images\\member\\jason_statham.png'),1,'1994-09-24',1,0);
INSERT INTO member (member_account,member_password,member_name,member_phone,member_email,member_photo,member_gender,member_birthday,register_method,member_status)
VALUES ('tom','202cb962ac59075b964b07152d234b70','湯姆貓','0911798243','tom@gmail.com',LOAD_FILE('C:\\project\\images\\member\\tom_cruise.jpg'),1,'2012-08-04',1,0);
INSERT INTO member (member_account,member_password,member_name,member_phone,member_email,member_photo,member_gender,member_birthday,register_method,member_status)
VALUES ('amy','202cb962ac59075b964b07152d234b70','怡婷','0984471254','amy@gmail.com',LOAD_FILE('C:\\project\\images\\member\\amy_adams.jpg'),0,'2000-01-30',1,0);


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
VALUES ('jason','傑森','202cb962ac59075b964b07152d234b70','老闆',LOAD_FILE('C:\\project\\images\\employee\\jason.jpg'),'2021-02-21',1);
INSERT INTO employee (employee_account,employee_name,employee_password,employee_position,employee_photo,hire_date,employee_status)
VALUES ('peter','東昇','202cb962ac59075b964b07152d234b70','主管',LOAD_FILE('C:\\project\\images\\employee\\peter.jpg'),'2021-01-02',1);
INSERT INTO employee (employee_account,employee_name,employee_password,employee_position,employee_photo,hire_date,employee_status)
VALUES ('james','小明','202cb962ac59075b964b07152d234b70','工讀生',LOAD_FILE('C:\\project\\images\\employee\\james.jpg'),'2020-03-21',1);


-- 員工權限內容
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE authority_content (
	authority_content_id   	INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	authority_content     	VARCHAR(2000) NOT NULL
) AUTO_INCREMENT = 1;
INSERT INTO authority_content (authority_content) VALUES ('員工管理');
INSERT INTO authority_content (authority_content) VALUES ('會員管理');
INSERT INTO authority_content (authority_content) VALUES ('公告管理');
INSERT INTO authority_content (authority_content) VALUES ('訂單管理');
INSERT INTO authority_content (authority_content) VALUES ('評價管理');    # 5
INSERT INTO authority_content (authority_content) VALUES ('優惠碼管理');
INSERT INTO authority_content (authority_content) VALUES ('優惠券管理');
INSERT INTO authority_content (authority_content) VALUES ('商品管理');
INSERT INTO authority_content (authority_content) VALUES ('通知管理');
INSERT INTO authority_content (authority_content) VALUES ('訂閱管理');     #10
INSERT INTO authority_content (authority_content) VALUES ('電子報管理');
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
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',2);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',3);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',4);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',5);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',6);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',7);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',8);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',9);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',10);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('jason',11);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('peter',2);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('peter',3);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('peter',5);
INSERT INTO employee_authority (employee_account,authority_content_id) VALUES ('james',3);

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
	product_available_qty	MEDIUMINT UNSIGNED NOT NULL,
	product_status	  		TINYINT NOT NULL,
    expiry_after_buying    	TINYINT NOT NULL,
	product_calorie	  		SMALLINT NOT NULL,
	degree_of_sweetness     TINYINT NOT NULL,
    total_star 				INT NOT NULL,
    total_review			INT NOT NULL,
    total_purchase			INT NOT NULL
) AUTO_INCREMENT = 1;
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('Oreo杯子蛋糕', '蛋糕', '杯子蛋糕','杯子蛋糕的經典重現，巧克力與杯子蛋糕的完美演繹，黑巧克力蛋糕體與鮮奶油、搭配人人都愛的orea點綴口感與視覺，大人小孩都大力推薦商品。', 'Oreo餅乾、可可粉、可可醬', 80, 50, 1, 7, 165, 4, 435, 105, 449);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('草莓千層蛋糕', '蛋糕', '千層蛋糕','季節限定商品! 製作費時的千層餅皮造就了完美的口感，夾層為北海道生乳，搭配當季現採的新鮮草莓，口感豐富，草莓味濃郁卻而不膩。', '草莓、生乳', 160, 50, 1, 3, 165, 2, 455, 110, 501);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('藍莓乳酪千層派', '蛋糕', '千層蛋糕','使用歐洲進口冷凍藍莓，夾層為creame cheese，千層蛋糕餅皮夾藏濃郁的cream cheese，結合千層蛋糕與起士蛋糕的雙重享受，為選擇障礙者的最佳品。', '藍莓、cream cheese、奶油', 150, 50, 1, 5, 211, 2, 115, 25, 156);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('綜合野莓蛋糕捲', '蛋糕', '蛋糕捲','歐洲進口冷凍莓果製作，夾層使用北海道生乳，蛋糕體為柔軟的戚風蛋糕，酸甜滋味伴隨濃郁奶香，無論內外都有莓果的點綴，視覺與味蕾的衝擊，適合喜愛低甜度的饕客。', '藍莓、覆盆子、莓果醬、生乳', 150, 50, 1, 5, 211, 1, 115, 25, 156);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('香蕉拿鐵', '飲品', '香濃拿鐵','嗜甜與台灣蕉農合作，使用契作優質香蕉，融合鮮奶與少許蜂蜜，襯托香蕉濃郁口感與香氣，口感偏甜卻而不膩，適合搭配甜度較低的甜品。', '香蕉、蜂蜜、鮮奶', 140, 50, 1, 5, 211, 3, 115, 25, 156);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('綜合野莓果昔', '飲品', '繽紛水果飲','加拿大進口莓果，搭配自家生產新鮮優格，莓果味完美融合了新鮮優格，清爽順口，每日一杯，身體無負擔！', '藍莓、桑葚、草莓、槲寄生、優格', 180, 50, 1, 1, 210, 2, 885, 215, 121);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('咖啡千層派', '蛋糕', '千層蛋糕','製作費時費工的千層餅皮，搭配深焙咖啡溶入的鮮奶油，口感多層次，甜中帶有點咖啡的苦卻有咖啡的香，值得你細細品嘗！', '咖啡、牛奶、奶油、雞蛋', 140, 50, 1, 7, 224, 1, 115, 25, 156);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('桑葚巧克力杯子蛋糕', '蛋糕', '杯子蛋糕','頂級可可粉製作蛋糕體，巧克力香搭配桑葚風味鮮奶油與果醬，桑葚的完美點綴造就了視覺與味覺上多層享受！', '可可粉、新鮮桑葚、桑葚果醬、奶油', 100, 60, 1, 3, 251, 3, 135, 30, 216);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('槲寄生杯子蛋糕', '蛋糕', '杯子蛋糕','聖誕節的限定商品，猶如雪地中的薑餅屋，蛋糕加入少許薑汁，搭配沖繩黑糖，無論大人小孩都愛，給您香甜的溫暖感受與滿滿的聖誕回憶。', '薑粉、黑糖、槲寄生、砂糖', 95, 50, 1, 5, 253, 3, 115, 26, 356);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('藍莓優格磅蛋糕', '蛋糕', '磅蛋糕','厚實又綿密的蛋糕體夾著藍莓果乾，表面裹上優格口味的白巧克力搭配新鮮藍莓，兩種味到完美的融合，造就了酸酸甜甜的滋味！', '白巧克力、奶油、優格、藍莓、藍莓果乾', 90, 100, 1, 5, 230, 2, 118, 25, 546);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('覆盆子檸檬磅蛋糕', '蛋糕', '磅蛋糕','加拿大空運進口的覆盆子，基底是檸檬口味的磅蛋糕，搭配上綿密奶霜，是磅蛋糕裡面簡單又耐人尋味的基本款卻又是人人都推薦的磅蛋糕!', '蜂蜜，檸檬、覆盆子、鮮奶', 90, 80, 1, 5, 164, 2, 129, 31, 146);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('橙香松子巧克力', '巧克力', '巴克巧克力','純手工的自製巧克力，加入柑橘果乾與口感清脆的松子仁，薄脆的口感及清爽的滋味，值得您嘗試！', '柳橙乾、檸檬乾、松子、可可粉、黑巧克力', 65, 10, 1, 10, 133, 2, 130, 35, 133);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('奶油夾心巧克力', '巧克力', '藝術巧克力','使用苦甜巧克力的微苦中和夾心奶油的甜，經過多道工法製作，精緻的外表讓人不忍心入口卻又好吃到一口接一口！', '苦甜巧克力、調溫巧克力、白巧克力、奶油、明膠、蝶豆花萃取色素', 70, 10, 1, 10, 113, 2, 135, 28, 1346);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('蔓越莓軟心巧克力', '巧克力', '藝術巧克力','卡士達夾心搭配酸甜蔓越莓果醬，經由多道手續與完美得配方，造就了甜而不膩的滋味，在搭配奢華的外表，是送禮的不二選擇。', '黑巧克力、蔓越莓果醬、奶油、砂糖', 65, 10, 1, 7, 123, 3, 137, 29, 356);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('藍莓巧心棒', '巧克力', '藝術巧克力','喜愛甜食的可人大力推薦！口味偏甜的巧克力，內層包裹藍莓風味的椰子粉，獨特的口感搭配清甜果香，獨特組合值得您一試！', '藍莓、椰子粉、黑巧克力、奶油', 60, 10, 1, 5, 157, 1, 134, 29, 146);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('莓果奶霜拿破崙', '精緻小點', '派&塔','酥脆派皮層層交疊，夾著生乳與新鮮的莓果，各個交錯疊放使味道與口感完美分配，果香清甜，爽脆口感讓人愛不釋口。', '覆盆子、黑莓、生乳', 130, 30, 1, 3, 196, 2, 335, 77, 339);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('椰棗布朗尼', '巧克力', '生巧克力','來自中東的紅寶石-椰棗，打成泥後混合焦糖，搭配奶油與可可，費工與費時的多道手續下造就了柔軟黏糯口感，甜度偏甜，是生巧克力的全新搭配。', '椰棗、可可粉、鮮奶油、砂糖', 65, 10, 1, 5, 246, 5, 345, 77, 339);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('甜心覆盆子馬卡龍', '精緻小點', '馬卡龍','來自法國的奢華甜點，所有甜點饕客們必敗商品，覆盆子的酸中和了馬卡龍本身的甜，口感酥脆綿密，甜而不膩！', '杏仁粉、砂糖、覆盆子、奶油', 70, 100, 1, 7, 165, 3, 349, 79, 349);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('草莓蜜桃馬卡龍', '精緻小點', '馬卡龍','來自法國的奢華甜點，覆盆子的酸中和了馬卡龍本身的甜，口感酥脆綿密，甜而不膩！', '杏仁粉、砂糖、覆盆子、奶油、草莓、水蜜桃', 80, 100, 1, 1, 165, 3, 435, 105, 449);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('開心果香藍莓塔', '精緻小點', '派&塔','開心果打碎入料，味覺獨特香氣四溢，上層搭配滿滿的藍莓鮮果與綠葉的點綴，絕妙組合讓人讚不絕口！', '開心果、藍莓、奶油', 160, 30, 1, 3, 146, 1, 145, 32, 133);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('櫻桃螺旋塔', '精緻小點', '派&塔','採用新鮮的櫻桃搭配自製櫻桃果醬，滿滿的鮮豔櫻桃給您口感上與視覺上的雙重饗宴。', '櫻桃、鮮奶油、明膠', 150, 30, 1, 3, 358, 2, 335, 75, 139);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('濃情巧克力塔', '精緻小點', '派&塔','味道偏苦的生巧克力，撒上些許肉桂粉，口感扎實的塔皮完美，每一口都是濃情密意！', '生巧克力、鮮奶油、可可醬', 120, 50, 1, 7, 316, 2, 335, 77, 239);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('伯爵茶香泡芙', '精緻小點', '泡芙','伯爵茶拌入卡士達內餡，外層的酥脆餅皮配上巧克力波羅層，是清爽可愛的熱門甜點！', '伯爵茶葉、卡士達、奶油', 120, 50, 1, 5, 155, 1, 445, 99, 439);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('藍莓鮮果茶', '飲品', '特調茶飲','新鮮藍莓搭配嗜甜嚴選綠茶，淡淡茶香伴隨清甜果香，絕妙的組合！', '藍莓、綠茶、蜂蜜', 190, 100, 1, 1, 150, 2, 435, 105, 449);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('焦糖瑪奇朵', '飲品', '經典咖啡','來自南非的咖啡豆，深焙研磨後萃取汁液，搭配鮮奶，淋上滿滿焦糖，甜而不膩的一款飲品。', '咖啡、焦糖、鮮奶', 180, 100, 1, 1, 165, 3, 445, 105, 491);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('蝶豆花綠茶', '飲品', '特調茶飲','色彩如幻似夢的蝶豆花，渲染嗜甜嚴選的國產綠茶，是簡單又夢幻的飲品，適合搭配甜度較高的甜點。', '蝶豆花、綠茶、蔗糖', 140, 100, 1, 1, 115, 1, 435, 105, 449);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('烤棉花糖摩卡', '飲品','經典咖啡', '嗜甜的招牌商品！滿滿的棉花糖點綴，底層的咖啡加上濃郁巧克力醬，多重的味覺與口感享受，光顧嗜甜的必點商品！', '棉花糖、巧克力醬、咖啡、牛奶、奶霜', 200, 100, 1, 1, 165, 4, 415, 85, 549);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('宇治抹茶拿鐵', '飲品', '香濃拿鐵','日本進口的宇治抹茶粉搭配濃郁的高大鮮乳，喜歡抹茶的人不容錯過的商品。', '抹茶粉、牛奶、蔗糖', 180, 100, 1, 1, 165, 1, 435, 105, 449);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('晴光花草茶', '飲品', '特調茶飲','新鮮當季水果入茶，果香伴隨伯爵茶香，越泡越香甜，甜品的最佳搭配！', '伯爵茶、水蜜桃、決明子、蜂蜜', 160, 100, 1, 1, 165, 1, 433, 107, 459);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('法芙娜巧克力慕斯', '精緻小點', '派&塔','使用法國頂級法芙娜（Valrhona）巧克力原料，夾層含巧克力珍珠米，外表淋上絲滑順口的巧克力醬，鏡面光澤，風味獨特。', '可可粉、可可米、鮮奶、明膠、吉利丁粉', 120, 50, 1, 5, 310, 3, 115, 25, 156);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('香芒華爾滋', '蛋糕', '起士蛋糕','新鮮的芒果切片，搭配濃郁的cream cheese，絕妙組合猶如在舌尖跳舞！', '芒果、明膠、cream cheese', 160, 60, 1, 3, 265, 2, 435, 105, 449);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('莓果薰衣草', '精緻小點', '馬卡龍','季節限定的薰衣草榨取入料，搭配藍莓果醬，淡淡花草清香伴隨酸甜滋味，是馬卡龍中低熱量首選！','薰衣草、藍莓、杏仁粉、蛋白、砂糖', 75, 40, 1, 5, 104, 2, 570, 115, 233);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('洋甘菊綠茶拿鐵', '飲品', '香濃拿鐵','香濃的牛奶混合清淡的綠茶，加上微微香氣的乾燥洋甘菊，由菊花引出綠茶的香，適合早晨來一杯開啟美好的一天！', '乾燥洋甘菊、綠茶、牛奶、砂糖', 170, 100, 1, 1, 165, 1, 435, 105, 449);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('伯爵玫瑰杯子蛋糕', '蛋糕', '杯子蛋糕','使用每日新鮮摘採的玫瑰花瓣，榨取後融入蛋糕與奶油當中，每一口都帶著自然花香，搭配肉桂與黑糖，清爽不甜膩。', '玫瑰、伯爵茶、肉桂、黑糖', 85, 60, 1, 5, 165, 2, 435, 111, 439);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('巧克力馬卡龍', '精緻小點', '馬卡龍','來自法國的奢華甜點，覆盆子的酸中和了馬卡龍本身的甜，口感酥脆綿密，甜而不膩！', '杏仁粉、砂糖、可可粉', 80, 50, 1, 1, 165, 3, 445, 105, 491);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('萊姆閃電泡芙', '精緻小點', '泡芙','清淡的萊姆口味卡士達內餡，搭配酥脆外皮，簡單有品味的一道甜點。', '萊姆、卡士達', 110, 50, 1, 5, 165, 2, 445, 105, 491);
INSERT INTO product (product_name, product_type, product_subtype , product_intro, product_ingredient, product_price, product_available_qty, product_status, expiry_after_buying, product_calorie, degree_of_sweetness, total_star, total_review, total_purchase)
VALUES ('血橙優格磅蛋糕', '蛋糕', '磅蛋糕','口感扎實的蛋糕體，基底溶入阿薩姆紅茶，搭配血橙風味的優格醬，嗜甜最新力作的商品！', '血橙、優格、阿薩姆紅茶', 100, 50, 1, 1, 165, 3, 445, 105, 491);


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
VALUES ('1', 70, '2021-04-01 00:00:00', '2021-04-15 23:59:59');
INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline)
VALUES ('7', 120, '2021-04-01 00:00:00', '2021-04-30 23:59:59');
INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline)
VALUES ('16', 100, '2021-04-01 00:00:00', '2021-04-30 23:59:59');
INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline)
VALUES ('19', 60, '2021-04-20 00:00:00', '2021-05-19 23:59:59');
INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline)
VALUES ('20', 140, '2021-04-14 00:00:00', '2021-04-30 23:59:59');
INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline)
VALUES ('30', 60, '2021-4-01 00:00:00', '2021-05-01 23:59:59');
INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline)
VALUES ('31', 120, '2021-05-01 00:00:00', '2021-05-01 23:59:59');
INSERT INTO daily_special (product_id, discount_price, discount_start_time, discount_deadline)
VALUES ('36', 80, '2021-05-01 00:00:00', '2021-05-14 23:59:59');

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
VALUES ('1', LOAD_FILE('C:\\project\\images\\product_image\\oreoCup-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('1', LOAD_FILE('C:\\project\\images\\product_image\\oreoCup-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('1', LOAD_FILE('C:\\project\\images\\product_image\\oreoCup-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('2', LOAD_FILE('C:\\project\\images\\product_image\\strawCrepe-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('2', LOAD_FILE('C:\\project\\images\\product_image\\strawCrepe-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('2', LOAD_FILE('C:\\project\\images\\product_image\\strawCrepe-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('3', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCrepe-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('3', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCrepe-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('3', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCrepe-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('4', LOAD_FILE('C:\\project\\images\\product_image\\3berryRoll-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('4', LOAD_FILE('C:\\project\\images\\product_image\\3berryRoll-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('4', LOAD_FILE('C:\\project\\images\\product_image\\3berryRoll-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('5', LOAD_FILE('C:\\project\\images\\product_image\\bananaLatte-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('5', LOAD_FILE('C:\\project\\images\\product_image\\bananaLatte-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('5', LOAD_FILE('C:\\project\\images\\product_image\\bananaLatte-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('6', LOAD_FILE('C:\\project\\images\\product_image\\berriesSmoothie-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('6', LOAD_FILE('C:\\project\\images\\product_image\\berriesSmoothie-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('6', LOAD_FILE('C:\\project\\images\\product_image\\berriesSmoothie-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('6', LOAD_FILE('C:\\project\\images\\product_image\\berriesSmoothie-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('7', LOAD_FILE('C:\\project\\images\\product_image\\bavarianCrepe-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('7', LOAD_FILE('C:\\project\\images\\product_image\\bavarianCrepe-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('7', LOAD_FILE('C:\\project\\images\\product_image\\bavarianCrepe-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('8', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCup-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('8', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCup-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('8', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCup-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('8', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCup-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('8', LOAD_FILE('C:\\project\\images\\product_image\\bBerryCup-5.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('9', LOAD_FILE('C:\\project\\images\\product_image\\gingerCup-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('9', LOAD_FILE('C:\\project\\images\\product_image\\gingerCup-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('9', LOAD_FILE('C:\\project\\images\\product_image\\gingerCup-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('10', LOAD_FILE('C:\\project\\images\\product_image\\bBerryPound-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('10', LOAD_FILE('C:\\project\\images\\product_image\\bBerryPound-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('10', LOAD_FILE('C:\\project\\images\\product_image\\bBerryPound-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('11', LOAD_FILE('C:\\project\\images\\product_image\\berryPound-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('11', LOAD_FILE('C:\\project\\images\\product_image\\berryPound-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('11', LOAD_FILE('C:\\project\\images\\product_image\\berryPound-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('12', LOAD_FILE('C:\\project\\images\\product_image\\apricotBark-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('12', LOAD_FILE('C:\\project\\images\\product_image\\apricotBark-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('13', LOAD_FILE('C:\\project\\images\\product_image\\artChoco-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('13', LOAD_FILE('C:\\project\\images\\product_image\\artChoco-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('14', LOAD_FILE('C:\\project\\images\\product_image\\berryBullet-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('14', LOAD_FILE('C:\\project\\images\\product_image\\berryBullet-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('14', LOAD_FILE('C:\\project\\images\\product_image\\berryBullet-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('14', LOAD_FILE('C:\\project\\images\\product_image\\berryBullet-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('15', LOAD_FILE('C:\\project\\images\\product_image\\blueBar-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('15', LOAD_FILE('C:\\project\\images\\product_image\\blueBar-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('15', LOAD_FILE('C:\\project\\images\\product_image\\blueBar-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('15', LOAD_FILE('C:\\project\\images\\product_image\\blueBar-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('16', LOAD_FILE('C:\\project\\images\\product_image\\berryPastry-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('16', LOAD_FILE('C:\\project\\images\\product_image\\berryPastry-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('16', LOAD_FILE('C:\\project\\images\\product_image\\berryPastry-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('16', LOAD_FILE('C:\\project\\images\\product_image\\berryPastry-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('17', LOAD_FILE('C:\\project\\images\\product_image\\brownieChoco-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('17', LOAD_FILE('C:\\project\\images\\product_image\\brownieChoco-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('17', LOAD_FILE('C:\\project\\images\\product_image\\brownieChoco-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('17', LOAD_FILE('C:\\project\\images\\product_image\\brownieChoco-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('17', LOAD_FILE('C:\\project\\images\\product_image\\brownieChoco-5.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('18', LOAD_FILE('C:\\project\\images\\product_image\\berryMacaron-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('18', LOAD_FILE('C:\\project\\images\\product_image\\berryMacaron-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('18', LOAD_FILE('C:\\project\\images\\product_image\\berryMacaron-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('19', LOAD_FILE('C:\\project\\images\\product_image\\peachMacaron-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('19', LOAD_FILE('C:\\project\\images\\product_image\\peachMacaron-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('19', LOAD_FILE('C:\\project\\images\\product_image\\peachMacaron-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('19', LOAD_FILE('C:\\project\\images\\product_image\\peachMacaron-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('20', LOAD_FILE('C:\\project\\images\\product_image\\blueBerryT-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('20', LOAD_FILE('C:\\project\\images\\product_image\\blueBerryT-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('21', LOAD_FILE('C:\\project\\images\\product_image\\cherryT-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('21', LOAD_FILE('C:\\project\\images\\product_image\\cherryT-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('21', LOAD_FILE('C:\\project\\images\\product_image\\cherryT-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('21', LOAD_FILE('C:\\project\\images\\product_image\\cherryT-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('22', LOAD_FILE('C:\\project\\images\\product_image\\chocoT-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('22', LOAD_FILE('C:\\project\\images\\product_image\\chocoT-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('22', LOAD_FILE('C:\\project\\images\\product_image\\chocoT-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('23', LOAD_FILE('C:\\project\\images\\product_image\\earlGrayP-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('23', LOAD_FILE('C:\\project\\images\\product_image\\earlGrayP-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('23', LOAD_FILE('C:\\project\\images\\product_image\\earlGrayP-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('24', LOAD_FILE('C:\\project\\images\\product_image\\bberryT-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('24', LOAD_FILE('C:\\project\\images\\product_image\\bberryT-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('25', LOAD_FILE('C:\\project\\images\\product_image\\macchiato-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('25', LOAD_FILE('C:\\project\\images\\product_image\\macchiato-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('26', LOAD_FILE('C:\\project\\images\\product_image\\butterflyT-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('26', LOAD_FILE('C:\\project\\images\\product_image\\butterflyT-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('27', LOAD_FILE('C:\\project\\images\\product_image\\toastedMCoffee-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('27', LOAD_FILE('C:\\project\\images\\product_image\\toastedMCoffee-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('27', LOAD_FILE('C:\\project\\images\\product_image\\toastedMCoffee-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('27', LOAD_FILE('C:\\project\\images\\product_image\\toastedMCoffee-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('28', LOAD_FILE('C:\\project\\images\\product_image\\matchaLatte-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('28', LOAD_FILE('C:\\project\\images\\product_image\\matchaLatte-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('29', LOAD_FILE('C:\\project\\images\\product_image\\herbalSunT-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('29', LOAD_FILE('C:\\project\\images\\product_image\\herbalSunT-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('30', LOAD_FILE('C:\\project\\images\\product_image\\chocoMousse-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('30', LOAD_FILE('C:\\project\\images\\product_image\\chocoMousse-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('30', LOAD_FILE('C:\\project\\images\\product_image\\chocoMousse-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('31', LOAD_FILE('C:\\project\\images\\product_image\\mangoCheese-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('31', LOAD_FILE('C:\\project\\images\\product_image\\mangoCheese-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('31', LOAD_FILE('C:\\project\\images\\product_image\\mangoCheese-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('32', LOAD_FILE('C:\\project\\images\\product_image\\bBerryMacaron-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('32', LOAD_FILE('C:\\project\\images\\product_image\\bBerryMacaron-5.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('32', LOAD_FILE('C:\\project\\images\\product_image\\bBerryMacaron-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('32', LOAD_FILE('C:\\project\\images\\product_image\\bBerryMacaron-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('32', LOAD_FILE('C:\\project\\images\\product_image\\bBerryMacaron-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('33', LOAD_FILE('C:\\project\\images\\product_image\\chamomileLatte-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('33', LOAD_FILE('C:\\project\\images\\product_image\\chamomileLatte-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('33', LOAD_FILE('C:\\project\\images\\product_image\\chamomileLatte-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('34', LOAD_FILE('C:\\project\\images\\product_image\\roseCup-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('34', LOAD_FILE('C:\\project\\images\\product_image\\roseCup-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('34', LOAD_FILE('C:\\project\\images\\product_image\\roseCup-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('34', LOAD_FILE('C:\\project\\images\\product_image\\roseCup-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('34', LOAD_FILE('C:\\project\\images\\product_image\\roseCup-5.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('35', LOAD_FILE('C:\\project\\images\\product_image\\chocoMacaron-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('35', LOAD_FILE('C:\\project\\images\\product_image\\chocoMacaron-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('35', LOAD_FILE('C:\\project\\images\\product_image\\chocoMacaron-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('36', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('36', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-2.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('36', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-3.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('36', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-4.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('36', LOAD_FILE('C:\\project\\images\\product_image\\limePuff-5.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('37', LOAD_FILE('C:\\project\\images\\product_image\\bOrangePound-1.jpg'));
INSERT INTO product_image (product_id, product_image)
VALUES ('37', LOAD_FILE('C:\\project\\images\\product_image\\bOrangePound-2.jpg'));



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
INSERT INTO newsletter(newsletter_content,newsletter_image,newsletter_releasing_time,newsletter_status,employee_account) VALUES ('感恩母親節，4/20至4/30預購特惠中',LOAD_FILE('C:\\project\\images\\newsletter\\MotherDay.jpg'),'2021-04-15','0','james');
INSERT INTO newsletter(newsletter_content,newsletter_image,newsletter_releasing_time,newsletter_status,employee_account) VALUES ('感恩父親節，7/20至7/31預購特惠中',LOAD_FILE('C:\\project\\images\\newsletter\\FatherDay2.jpg'),'2021-7-15','0','peter');
INSERT INTO newsletter(newsletter_content,newsletter_image,newsletter_releasing_time,newsletter_status,employee_account) VALUES ('歡樂聖誕節，活動專屬商品特惠中',LOAD_FILE('C:\\project\\images\\newsletter\\Christmas.jpg'),'2021-12-01','0','james');
INSERT INTO newsletter(newsletter_content,newsletter_image,newsletter_releasing_time,newsletter_status,employee_account) VALUES ('甜蜜情人節，活動專屬商品特惠中',LOAD_FILE('C:\\project\\images\\newsletter\\Valentines2.png'),'2021-02-01','0','jason');


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
INSERT INTO live_support(chat_history,sender,member_account,employee_account) VALUES ('請問連假營業時間','1','tom','peter');
INSERT INTO live_support(chat_history,sender,member_account,employee_account) VALUES ('連假正常營業','0','tom','peter');
INSERT INTO live_support(chat_history,sender,member_account,employee_account) VALUES ('謝謝!!','1','tom','peter');


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
    notice_dispatcher   VARCHAR(1000),
    CONSTRAINT notice_member_FK FOREIGN KEY(member_account) REFERENCES  member(member_account)
)AUTO_INCREMENT=1;
INSERT INTO notice(notice_type,notice_content,read_status,member_account,notice_dispatcher) VALUES ('1','購買成功，請準時前來提取，謝謝!','0','amy','my-account.html');
INSERT INTO notice(notice_type,notice_content,read_status,member_account,notice_dispatcher) VALUES ('1','購買成功，請準時前來提取，謝謝!','0','jason','my-account.html');
INSERT INTO notice(notice_type,notice_content,read_status,member_account,notice_dispatcher) VALUES ('1','購買成功，請準時前來提取，謝謝!','0','tom','my-account.html');
INSERT INTO notice(notice_type,notice_content,read_status,member_account,notice_dispatcher) VALUES ('2','提醒您：您有訂購商品，於 05/01 提取商品，謝謝!','0','amy','my-account.html');
INSERT INTO notice(notice_type,notice_content,read_status,member_account,notice_dispatcher) VALUES ('2','提醒您：您有訂購商品，於 05/05 提取商品，謝謝!','0','amy','my-account.html');
INSERT INTO notice(notice_type,notice_content,read_status,member_account,notice_dispatcher) VALUES ('3','訂位成功，05/05，12:00，3人，請準時前往，謝謝!','0','amy','my-account.html');
INSERT INTO notice(notice_type,notice_content,read_status,member_account,notice_dispatcher) VALUES ('3','訂位成功，05/10，16:00，4人，請準時前往，謝謝!','0','amy','my-account.html');
INSERT INTO notice(notice_type,notice_content,read_status,member_account,notice_dispatcher) VALUES ('4','提醒您：05/05 12:00 有訂位，位子保留10分鐘，逾期不候，若不克前來，請提前通知，謝謝!','0','amy','my-account.html');
INSERT INTO notice(notice_type,notice_content,read_status,member_account,notice_dispatcher) VALUES ('4','提醒您：05/10 16:00 有訂位，位子保留10分鐘，逾期不候，若不克前來，請提前通知，謝謝!','0','tom','my-account.html');



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
announcement_image,announcement_time,announcement_type,announcement_status,employee_account)
values('提拉米蘇特價','提拉米蘇超好吃derrrrrr',LOAD_FILE('C:/project/images/announcement_management/a.jpg'),'2021-02-26',1,1,'jason');
insert into announcement_management (announcement_name,announcement_content,
announcement_image,announcement_time,announcement_type,announcement_status,employee_account)
values('檸檬塔暫停供應','員工擠檸檬噴到眼睛',LOAD_FILE('C:/project/images/announcement_management/b.PNG'),'2021-02-27',1,1,'peter');
insert into announcement_management (announcement_name,announcement_content,
announcement_image,announcement_time,announcement_type,announcement_status,employee_account)
values('草莓蛋糕下架','草莓農藥太多',LOAD_FILE('C:/project/images/announcement_management/c.PNG'),'2021-02-28',1,1,'james');
insert into announcement_management (announcement_name,announcement_content,
announcement_image,announcement_time,announcement_type,announcement_status,employee_account)
values('嗜甜營業時間','歡迎光臨嗜甜,本店營業時間 :星期一至六 早上 10:30 ~ 晚上 9:30,星期日/例假日 休息',LOAD_FILE('C:/project/images/announcement_management/open.jpg'),'2021-02-28',0,1,'james');
insert into announcement_management (announcement_name,announcement_content,
announcement_image,announcement_time,announcement_type,announcement_status,employee_account)
values('優惠券/優惠碼','嗜甜會不定期提供優惠券/優惠碼給消費者使用,皆可用於網路訂餐及實體消費做優惠的服務,謝謝每一位消費者對嗜甜的愛戴及支持',LOAD_FILE('C:/project/images/announcement_management/coupon.jpg'),'2021-03-01',3,1,'peter');
insert into announcement_management (announcement_name,announcement_content,
announcement_image,announcement_time,announcement_type,announcement_status,employee_account)
values('線上客服','嗜甜在此提供新的服務 "即時線上客服",消費者對嗜甜的回饋和評語我們將會一一改進,如有任何疑問及意見,歡迎使用 "即時線上客服" 服務',LOAD_FILE('C:/project/images/announcement_management/customerService.jpg'),'2021-03-03',2,1,'peter');
insert into announcement_management (announcement_name,announcement_content,
announcement_image,announcement_time,announcement_type,announcement_status,employee_account)
values('草莓蛋糕熱銷中','季節限定的大湖草莓,結合了慕斯,口感既清淡又輕盈,入口即溶,微妙絕配,賞心悅目又美味,',LOAD_FILE('C:/project/images/announcement_management/strawberryCake.jpg'),'2021-03-20',1,1,'jason');
insert into announcement_management (announcement_name,announcement_content,
announcement_image,announcement_time,announcement_type,announcement_status,employee_account)
values('全新消息敬請期待','新功能/新產品準備中,',LOAD_FILE('C:/project/images/announcement_management/comingSoon.jpg'),'2021-03-27',0,1,'jason');


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
values('MAH6203','2021-02-27','2022-03-27','訂餐享8折',0.8,0,'jason');
insert into coupon_code(coupon_code,coupon_code_effective_date,coupon_code_expire_date,
coupon_code_text_content,coupon_code_content,discount_type,employee_account)
values('9K8928','2021-03-14','2021-03-15','單身者在白色情人節折價30元',30,1,'peter');
insert into coupon_code(coupon_code,coupon_code_effective_date,coupon_code_expire_date,
coupon_code_text_content,coupon_code_content,discount_type,employee_account)
values('AGQ2598','2021-03-17','2021-03-31','即期蛋糕享6折',0.6,0,'james');
insert into coupon_code(coupon_code,coupon_code_effective_date,coupon_code_expire_date,
coupon_code_text_content,coupon_code_content,discount_type,employee_account)
values('HCJ2473','2021-04-05','2022-04-06','黑咖啡折20元',20,1,'james');
insert into coupon_code(coupon_code,coupon_code_effective_date,coupon_code_expire_date,
coupon_code_text_content,coupon_code_content,discount_type,employee_account)
values('4147KCUF','2021-04-05','2022-02-06','糖料併折50元',50,1,'james');


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
insert into coupon(member_account,coupon_sending_time,coupon_effective_date,
coupon_expire_date,coupon_text_content,
coupon_content,discount_type,coupon_status,employee_account,coupon_code_id)
values('jason','2021-03-11','2021-03-14','2021-03-15','中獎折價20元',20,1,1,'peter',null);


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
    constraint orderMaster_member_FK foreign key (member_account) references member (member_account),
    constraint orderMaster_coupon_FK foreign key (coupon_id) references coupon(coupon_id)
) AUTO_INCREMENT = 1;
INSERT INTO  order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks)
VALUES ('tom', '2021-01-02 03:04:05', '1', 2, '1', 'AA12345678', '200', '希望能吃到好吃的巧克力杯子');
INSERT INTO  order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks)
VALUES ('jason', '2021-01-02 03:04:06', '1', 1, '1', 'AA12345672', '120', '希望能吃到好吃的草莓千層');
INSERT INTO  order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks)
VALUES ('amy', '2021-01-02 03:04:07', '1', 2, '2', 'AA12345673', '720', '最期待香蕉拿鐵!');
INSERT INTO  order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks)
VALUES ('amy', '2021-01-03 03:04:07', '1', 2, '2', 'AA12345674', '340', '最期待巧克力杯子! 想念草莓千層!');
INSERT INTO  order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks)
VALUES ('amy', '2021-01-04 03:04:07', '1', 1, '2', 'AA12345675', '360', '藍莓乳酪要濃郁才好吃');
INSERT INTO  order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks)
VALUES ('amy', '2021-01-06 03:04:07', '1', 1, '2', 'AA12345676', '460', '無');
INSERT INTO  order_master (member_account, payment_time, payment_method, coupon_id, order_status, invoice_number, order_total, order_remarks)
VALUES ('jason', null, '2', '5', '0', null, '220', '肚子好餓');


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
VALUES ('1', 1, '2', '100');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('2', 2, '1', '120');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('3', 3, '3', '120');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('3', 4, '2', '120');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('3', 5, '1', '120');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('4', 1, '1', '100');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('4', 2, '2', '120');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('5', 3, '3', '120');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('6', 1, '1', '100');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('6', 2, '1', '120');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('6', 3, '1', '120');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('6', 4, '1', '120');
INSERT INTO order_detail (order_master_id, product_id, product_qty, product_price)
VALUES ('7', 5, '2', '120');


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
VALUES ('jason', '2021-04-20 10:00:00', '7', '0', '兒童一位', '09-12345678', '紅傑森');
INSERT INTO book_detail (member_account, booking_time, people_num, booking_status, book_postscript, contact_num, booking_name)
VALUES ('tom', '2021-04-21 12:00:00', '5', '1', '', '09-87654321', '藍湯姆');
INSERT INTO book_detail (member_account, booking_time, people_num, booking_status, book_postscript, contact_num, booking_name)
VALUES ('amy', '2021-04-27 14:00:00', '6', '2', '兒童三位', '09-11222333', '綠艾咪');
INSERT INTO book_detail (member_account, booking_time, people_num, booking_status, book_postscript, contact_num, booking_name)
VALUES ('tom', '2021-04-28 16:00:00', '3', '0', '', '09-11111222', '黃湯姆');


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
VALUES ('2021-04-20', '7', '1', '12', '0', '4', '5');
INSERT INTO book_record (booking_date, ten_total_count, twelve_total_count, fourteen_total_count, sixteen_total_count, eighteen_total_count, twenty_total_count)
VALUES ('2021-04-21', '0', '5', '3', '4', '15', '0');
INSERT INTO book_record (booking_date, ten_total_count, twelve_total_count, fourteen_total_count, sixteen_total_count, eighteen_total_count, twenty_total_count)
VALUES ('2021-04-27', '5', '3', '6', '5', '0', '7');
INSERT INTO book_record (booking_date, ten_total_count, twelve_total_count, fourteen_total_count, sixteen_total_count, eighteen_total_count, twenty_total_count)
VALUES ('2021-04-28', '0', '20', '20', '5', '0', '10');
INSERT INTO book_record (booking_date, ten_total_count, twelve_total_count, fourteen_total_count, sixteen_total_count, eighteen_total_count, twenty_total_count)
VALUES ('2021-04-29', '0', '3', '0', '5', '14', '0');


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
INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES(1, '口感綿密，濕潤濃郁，好吃!', 1, 1, 1);
INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES(2, '新鮮草莓，在一層層細緻的餅皮中，與香滑柔順的奶醬交織，草莓香氣濃郁，酸甜不膩口。', 5, 2, 1);
INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES(3, '完全沒有地雷，至今吃過的三款蛋糕都很好吃! 最推薦的是藍莓乳酪，值得回購!', 5, 3, 1);
INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES(5, '完全被香蕉拿鐵征服味蕾', 4, 5, 0);
INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES(6, '高級巧克力的香氣!', 5, 1, 1);
INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES(7, '清爽的鮮奶油搭配酸酸甜甜的草莓，已融化!', 1, 2, 1);
INSERT INTO member_comment(order_detail_id, comment_content, rating, product_id, comment_status) VALUES(8, '全台灣最好吃的藍莓乳酪!', 5, 3, 0);


-- 評價圖片
set auto_increment_offset=1;
set auto_increment_increment=1;
CREATE TABLE review_image_upload(
	review_image_id		INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	review_image		LONGBLOB NOT NULL,
	review_id 			INT NOT NULL,
	constraint fk_review_image_upload_member_comment foreign key (review_id) references member_comment(review_id)
) AUTO_INCREMENT = 1;
INSERT INTO review_image_upload(review_image, review_id) VALUES (LOAD_FILE('C:\\project\\images\\review_image_upload\\oreoCup-1.jpg'), 1);
INSERT INTO review_image_upload(review_image, review_id) VALUES (LOAD_FILE('C:\\project\\images\\review_image_upload\\strawCrepe-1.jpg'), 2);
INSERT INTO review_image_upload(review_image, review_id) VALUES (LOAD_FILE('C:\\project\\images\\review_image_upload\\strawCrepe-2.jpg'), 2);
INSERT INTO review_image_upload(review_image, review_id) VALUES (LOAD_FILE('C:\\project\\images\\review_image_upload\\bananaLatte-2.jpg'), 4);
INSERT INTO review_image_upload(review_image, review_id) VALUES (LOAD_FILE('C:\\project\\images\\review_image_upload\\strawCrepe-1.jpg'), 6);
INSERT INTO review_image_upload(review_image, review_id) VALUES (LOAD_FILE('C:\\project\\images\\review_image_upload\\strawCrepe-2.jpg'), 6);
INSERT INTO review_image_upload(review_image, review_id) VALUES (LOAD_FILE('C:\\project\\images\\review_image_upload\\strawCrepe-3.jpg'), 6);


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
INSERT INTO dealer_reply(review_id, reply_content, employee_account) VALUES(1, '謝謝您購買巧克力杯子', 'peter');
INSERT INTO dealer_reply(review_id, reply_content, employee_account) VALUES(2, '謝謝您購買草莓千層蛋糕', 'peter');
INSERT INTO dealer_reply(review_id, reply_content, employee_account) VALUES(3, '謝謝您購買藍莓乳酪', 'peter');
INSERT INTO dealer_reply(review_id, reply_content, employee_account) VALUES(5, '謝謝您購買巧克力杯子', 'peter');
INSERT INTO dealer_reply(review_id, reply_content, employee_account) VALUES(6, '謝謝您購買草莓千層蛋糕', 'peter');


show VARIABLES like '%max_allowed_packet%';
set global max_allowed_packet = 400*1024*1024;
