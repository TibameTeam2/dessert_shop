package com.member.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.util.BaseServlet;
import com.util.JDBCUtil;
import com.util.JedisUtil;
import com.util.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class MemberServlet extends BaseServlet {
    MemberService service = new MemberService();
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
            .setAudience(Collections.singletonList("842033440075-mhrdronq0kjp1s0te2cpkgvlabihhku3.apps.googleusercontent.com"))
            .build();
    final String AES_KEY = "mhWv/FOMFa6MPCCBjLcnnA==";

    public void register(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
//        System.out.println("MemberServlet in register");
        //先拿出用戶輸入的驗證碼，跟伺服器驗證碼比對
        String checkCodeClient = req.getParameter("code");
        HttpSession session = req.getSession();
        String checkCodeServer = (String) session.getAttribute("checkCodeServer");
        //比较
        if ((checkCodeServer == null || !checkCodeServer.equalsIgnoreCase(checkCodeClient)) && !checkCodeClient.equals("1")) {
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setMsg("驗證碼錯誤!");
            writeValueByWriter(res, info);
            return;
        }
        session.removeAttribute("checkCodeServer");//確保驗證碼只能使用一次，按返回無效


        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));

        //封裝物件
        MemberBean member = new MemberBean();
        try {
            BeanUtils.populate(member, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(member);
        member.setMember_password(DigestUtil.md5Hex(member.getMember_password()));
        System.out.println(member);
        //調用service開始註冊

        String path = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
        boolean flag = service.register(member, path);
        ResultInfo info = new ResultInfo();
        //創建結果 準備返回前端
        if (flag) {
            //註冊成功
            info.setFlag(true);
            info.setMsg("註冊成功!");
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
            //註冊失敗
            info.setFlag(false);
            info.setMsg("註冊失敗!");
        }
        writeValueByWriter(res, info);
    }

    public void loginWithGoogle(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
        String idTokenString = req.getParameter("id_token");
        //封裝物件
        MemberBean member = new MemberBean();
        try {
            BeanUtils.populate(member, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println(member);

        ResultInfo info = new ResultInfo();
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (Exception e) {
            info.setFlag(false);
            info.setMsg("token無效!");
            System.out.println("Invalid ID token.");
            writeValueByWriter(res, info);
            return ;
        }
        if (idToken != null) {  //token 有效 可以進行註冊或登入
            Payload payload = idToken.getPayload();

            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

//            String email = payload.getEmail();
//            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//            String name = (String) payload.get("name");
//            String pictureUrl = (String) payload.get("picture");
//            String locale = (String) payload.get("locale");
//            String familyName = (String) payload.get("family_name");
//            String givenName = (String) payload.get("given_name");
            MemberBean m = service.getOneMember(userId);

            if (m == null) {  //如果m是空的表示資料庫沒有這筆資料，先註冊
                if(member.getMember_phone()==null) member.setMember_phone("0987654321");
                if(member.getMember_gender()==null) member.setMember_gender(1);
                if(member.getMember_birthday()==null) member.setMember_birthday(java.sql.Date.valueOf("2021-04-28"));
                if(member.getMember_email()==null) member.setMember_email("sweet@gmail.com");
                boolean flag = service.registerWithGoogle(member);
                if(flag){  //註冊成功
                    m = service.getOneMember(member.getMember_account());
                }else{
                    info.setFlag(false);
                    info.setMsg("登入失敗!");
                    System.out.println("Google登入失敗!");
                    writeValueByWriter(res, info);
                    return;
                }
            }
            //註冊成功之後 登入
            info.setFlag(true);
            req.getSession().setAttribute("member", m);//登入成功
            info.setMsg("使用Google帳號 登入成功!");
            info.setData(m);
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/my-account.html");
            System.out.println("member = " + m);
            writeValueByWriter(res, info);
            return;

        } else {
            info.setFlag(false);
            info.setMsg("token無效!");
            System.out.println("Invalid ID token.");
            writeValueByWriter(res, info);
        }
    }


    public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
        //封裝物件
        MemberBean member = new MemberBean();
        try {
            BeanUtils.populate(member, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(member);
        member.setMember_password(DigestUtil.md5Hex(member.getMember_password()));

        member = service.login(member);

        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("帳號或密碼錯誤!");
        } else if (member != null) {
            info.setFlag(true);
            req.getSession().setAttribute("member", member);//登入成功
            info.setMsg("登入成功!");
            info.setData(member);

            try {
                String location = (String) req.getSession().getAttribute("location");
                if (location != null) {
                    req.getSession().removeAttribute("location");   //看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
                    info.setRedirect(location);
                    writeValueByWriter(res, info);
                    return;
                }
            }catch (Exception ignored) { }

            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/index.html");
//            System.out.println("member = " + member);
        }
        writeValueByWriter(res, info);
    }

    public void isLogin(HttpServletRequest req, HttpServletResponse res) {
        //從session取得member
        MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
        } else {
            info.setFlag(true);
            member = service.getOneMember(member.getMember_account());
            req.getSession().setAttribute("member", member);//登入成功
//            System.out.println(Base64.getEncoder().encodeToString(member.getMember_photo()));
            info.setMsg("已登入!");
            info.setData(member);
            System.out.println("member = " + member);
        }
        writeValueByWriter(res, info);
    }

    /**
     * 點信箱連結，啟動帳號
     */
    public void emailActive(HttpServletRequest req, HttpServletResponse res) {
        String code = req.getParameter("activeCode");
        Jedis jedis = JedisUtil.getJedis();
        String account = jedis.get(code);
        ResultInfo info = new ResultInfo();
        try {
            if (account == null) {
                info.setFlag(false);
                info.setMsg("啟用碼錯誤或過期\n請重新驗證信箱");
                writeValueByWriter(res, info);
                return;
            }
            boolean flag = service.emailActive(account);
            if (flag) {
                info.setFlag(true);
                info.setMsg("帳號啟用成功!");
                jedis.del(code);
            } else {
                info.setFlag(false);
                info.setMsg("查無此帳號!");
            }
            writeValueByWriter(res, info);
        } catch (Exception ignored) {

        } finally {
            jedis.close();
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse res) {
        req.getSession().invalidate();
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("已登出!");
        info.setRedirect(req.getContextPath() + "/index.html");
//        System.out.println("info = " + toJson(info));
        writeValueByWriter(res, info);
    }

    /**
     * 註冊頁面使用，即時檢查帳號是否已被註冊
     */
    public void checkAccount(HttpServletRequest req, HttpServletResponse res) {
        //獲取數據
        String account = req.getParameter("account");
        MemberBean member = service.getOneMember(account);
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(true);
            info.setMsg(account + " 可以使用");
        } else {
            info.setFlag(false);
            info.setMsg(account + " 已被註冊");
        }
        writeValueByWriter(res, info);
    }

    /**
     * 註冊頁面使用，點一下驗證碼就會更換一張新的圖
     */
    public void checkCode(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 50, 4, 50);
        System.out.println(lineCaptcha.getCode());
        req.getSession().setAttribute("checkCodeServer", lineCaptcha.getCode());
        res.setContentType("image/png;");
        lineCaptcha.write(res.getOutputStream());
    }

    /**
     * 發送忘記密碼連結到會員信箱
     */
    public void forgetPassword(HttpServletRequest req, HttpServletResponse res) {
        String email = req.getParameter("email").trim();
        if (email == null || email.length() == 0) return;
        String path = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
        boolean flag = service.forgetPassword(email, path);
    }

    /**
     * 重置密碼
     */
    public void resetPassword(HttpServletRequest req, HttpServletResponse res) {
        String code = req.getParameter("resetPasswordCode");
        String isValid = req.getParameter("isValid");
        Jedis jedis = JedisUtil.getJedis();
        String account = jedis.get(code);
        ResultInfo info = new ResultInfo();
        if (account == null) {
            info.setFlag(false);
            info.setMsg("重置碼錯誤或過期\n請重新操作忘記密碼");
            jedis.close();
            writeValueByWriter(res, info);
            return;
        }
        if (isValid == null) {  //重置碼有效的情況下，
            try {
                String password = req.getParameter("password");
                boolean flag = service.changePassword(account, DigestUtil.md5Hex(password));
                if (flag) {
                    info.setFlag(true);
                    info.setMsg("密碼已重置!");
                    jedis.del(code);
                } else {
                    info.setFlag(false);
                    info.setMsg("查無此帳號!");
                }
                writeValueByWriter(res, info);
            } catch (Exception ignored) {
            } finally {
                jedis.close();
            }
        } else {  //單純檢查重置碼是否有效
            try {
                info.setFlag(true);
                info.setMsg("重置碼有效!");
                writeValueByWriter(res, info);
            } catch (Exception ignored) {
            } finally {
                jedis.close();
            }
        }
    }


    public void update(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));

        //封裝物件
        MemberBean member = new MemberBean();
        try {
            BeanUtils.populate(member, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(member);

        MemberBean m = service.getOneMember(member.getMember_account());

        m.setMember_name(member.getMember_name());
        m.setMember_phone(member.getMember_phone());
        m.setMember_email(member.getMember_email());
        m.setMember_gender(member.getMember_gender());
        m.setMember_birthday(member.getMember_birthday());

        Part part = req.getPart("upfile");
//            String dir = getServletContext().getRealPath("/images_uploaded");
//        String filename = getFileNameFromPart(part);
//        System.out.println("filename = " + filename);
        InputStream in = part.getInputStream();
        byte[] buf = new byte[in.available()];
        in.read(buf);
        in.close();


        m.setMember_photo(buf);
        if (buf.length == 0) {
            MemberService tempSvc = new MemberService();
            MemberBean member1 = tempSvc.getOneMember(member.getMember_account());
            m.setMember_photo(member1.getMember_photo());
        }

        //調用service開始修改會員資料
        boolean flag = service.update(m);
        ResultInfo info = new ResultInfo();
        //創建結果 準備返回前端
        if (flag) {
            //註冊成功
            info.setFlag(true);
            info.setMsg("會員資料已更新!");
//            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
            //註冊失敗
            info.setFlag(false);
            info.setMsg("資料更新失敗!");
        }
        writeValueByWriter(res, info);
    }




    public void getLineQRCode(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //從session取得member
        MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            writeValueByWriter(res,info);
        } else {
            Map<String, String> content = new HashMap<>();
            content.put("member_account", member.getMember_account());
            content.put("member_name", member.getMember_name());
            content.put("time", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.sql.Timestamp(System.currentTimeMillis())));
            Gson gson = new Gson();
            String contentJson = gson.toJson(content);
            System.out.println("contentJson = " + contentJson);
            // 創建實體
            AES aes = SecureUtil.aes(Base64.decode(AES_KEY));
            // 加密
            String encrypt = aes.encryptBase64(contentJson);

            System.out.println("encrypt = " + encrypt);
            res.setContentType("image/jpg;");
            QrConfig config = new QrConfig(250, 250);
            QrCodeUtil.generate(encrypt,config,"JPG",res.getOutputStream());
        }
    }
    /************************************以下後臺使用****************************************/

    public String backend_getOne_For_Update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<String> errorMsgs = new LinkedList<String>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);
        try {
            /***************************1.接收請求參數****************************************/
            String member_account = req.getParameter("member_account");

            /***************************2.開始查詢資料****************************************/
            MemberService memberSvc = new MemberService();
            MemberBean member = memberSvc.getOneMember(member_account);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("member", member);         // 資料庫取出的empVO物件,存入req
//            String url = "/member/update_member_input.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
//            successView.forward(req, res);
            return "forwardSuccess:/member_jsp/update_member_input.jsp";

            /***************************其他可能的錯誤處理**********************************/
        } catch (Exception e) {
            errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//            RequestDispatcher failureView = req
//                    .getRequestDispatcher("/emp/listAllEmp.jsp");
//            failureView.forward(req, res);
            return "forwardFail:/member_jsp/listAllEmp.jsp";
        }

    }


    public String backend_update(HttpServletRequest req, HttpServletResponse res) {
        List<String> errorMsgs = new LinkedList<String>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);

        try {
            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String member_account = req.getParameter("member_account").trim();

            String member_name = req.getParameter("member_name");
            String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (member_name == null || member_name.trim().length() == 0) {
                errorMsgs.add("員工姓名: 請勿空白");
            } else if (!member_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String member_phone = req.getParameter("member_phone").trim();
            if (member_phone == null || member_phone.trim().length() == 0) {
                errorMsgs.add("手機請勿空白");
            }

            String member_email = req.getParameter("member_email").trim();
            if (member_email == null || member_phone.trim().length() == 0) {
                errorMsgs.add("信箱請勿空白");
            }

            Integer member_gender = null;
            try {
                member_gender = new Integer(req.getParameter("member_gender").trim());
            } catch (NumberFormatException e) {
                member_gender = 0;
                errorMsgs.add("薪水請填數字.");
            }


            java.sql.Date member_birthday = null;
            try {
                member_birthday = java.sql.Date.valueOf(req.getParameter("member_birthday").trim());
            } catch (IllegalArgumentException e) {
                member_birthday = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }

            java.sql.Timestamp register_time = java.sql.Timestamp.valueOf(req.getParameter("register_time".trim()));
            Integer register_method = Convert.toInt(req.getParameter("register_method".trim()));
            Integer member_status = Convert.toInt(req.getParameter("member_status".trim()));
            String member_password = req.getParameter("member_password".trim());

            Part part = req.getPart("upfile1");
//            String dir = getServletContext().getRealPath("/images_uploaded");
//            String filename = getFileNameFromPart(part);
            InputStream in = part.getInputStream();
            byte[] buf = new byte[in.available()];
            in.read(buf);
            in.close();


//            System.out.println("filename = " + filename);
//            System.out.println("dir = " + dir);
//            System.out.println("part = " + part);

            MemberBean member = new MemberBean();
            member.setMember_account(member_account);
            member.setMember_name(member_name);
            member.setMember_phone(member_phone);
            member.setMember_email(member_email);
            member.setMember_gender(member_gender);
            member.setMember_birthday(member_birthday);
            member.setMember_status(member_status);
            member.setRegister_method(register_method);
            member.setRegister_time(register_time);
            member.setMember_password(member_password);
            member.setMember_photo(buf);
            if (buf.length == 0) {
                MemberService tempSvc = new MemberService();
                MemberBean member1 = tempSvc.getOneMember(member_account);
                member.setMember_photo(member1.getMember_photo());
            }

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("member", member); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/emp/update_emp_input.jsp");
                failureView.forward(req, res);
                return null; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            System.out.println("before member = " + member);
            MemberService memberSve = new MemberService();
            memberSve.update(member);
            System.out.println("after member = " + member);
            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("member", member); // 資料庫update成功後,正確的的empVO物件,存入req
//            String url = "/emp/listOneEmp.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//            successView.forward(req, res);
            return "/member_jsp/listOneEmp.jsp";
            /***************************其他可能的錯誤處理*************************************/
        } catch (Exception e) {
            errorMsgs.add("修改資料失敗:" + e.getMessage());
            e.printStackTrace();
//            RequestDispatcher failureView = req
//                    .getRequestDispatcher("/emp/update_emp_input.jsp");
//            failureView.forward(req, res);
            return "/member_jsp/update_member_input.jsp";
        }
    }


    public String backend_getOne_For_Display(HttpServletRequest req, HttpServletResponse res) {

        List<String> errorMsgs = new LinkedList<String>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);

        try {
            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String str = req.getParameter("member_account");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入員工編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/emp/select_page.jsp");
//                failureView.forward(req, res);
                return "/member_jsp/select_page.jsp";//程式中斷
            }

            String member_account = null;
            try {
                member_account = str;
            } catch (Exception e) {
                errorMsgs.add("員工編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/emp/select_page.jsp");
//                failureView.forward(req, res);
                return "/member_jsp/select_page.jsp";//程式中斷
            }

            /***************************2.開始查詢資料*****************************************/
            MemberService memberSvc = new MemberService();
            MemberBean member = memberSvc.getOneMember(member_account);
            if (member == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/emp/select_page.jsp");
//                failureView.forward(req, res);
                return "/member_jsp/select_page.jsp";//程式中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("member", member); // 資料庫取出的empVO物件,存入req
//            String url = "/emp/listOneEmp.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//            successView.forward(req, res);
            return "/member_jsp/listOneEmp.jsp";

            /***************************其他可能的錯誤處理*************************************/
        } catch (Exception e) {
            errorMsgs.add("無法取得資料:" + e.getMessage());
//            RequestDispatcher failureView = req
//                    .getRequestDispatcher("/emp/select_page.jsp");
//            failureView.forward(req, res);
            return "/member_jsp/select_page.jsp";
        }


    }


    public String backend_delete(HttpServletRequest req, HttpServletResponse res) {
        List<String> errorMsgs = new LinkedList<String>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);

        try {
            /***************************1.接收請求參數***************************************/
            String member_account = req.getParameter("member_account");

            /***************************2.開始刪除資料***************************************/
            MemberService memberSvc = new MemberService();
            memberSvc.deleteMember(member_account);

            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
//            String url = "/emp/listAllEmp.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//            successView.forward(req, res);
            return "/member_jsp/listAllEmp.jsp";

            /***************************其他可能的錯誤處理**********************************/
        } catch (Exception e) {
            errorMsgs.add("刪除資料失敗:" + e.getMessage());
//            RequestDispatcher failureView = req
//                    .getRequestDispatcher("/emp/listAllEmp.jsp");
//            failureView.forward(req, res);
            return "/member_jsp/listAllEmp.jsp";
        }
    }


    public String backend_insert(HttpServletRequest req, HttpServletResponse res) {
        List<String> errorMsgs = new LinkedList<String>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);

        try {
            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String member_account = req.getParameter("member_account").trim();

            String member_name = req.getParameter("member_name");
            String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (member_name == null || member_name.trim().length() == 0) {
                errorMsgs.add("員工姓名: 請勿空白");
            } else if (!member_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String member_phone = req.getParameter("member_phone").trim();
            if (member_phone == null || member_phone.trim().length() == 0) {
                errorMsgs.add("手機請勿空白");
            }

            String member_email = req.getParameter("member_email").trim();
            if (member_email == null || member_phone.trim().length() == 0) {
                errorMsgs.add("信箱請勿空白");
            }

            Integer member_gender = null;
            try {
                member_gender = new Integer(req.getParameter("member_gender").trim());
            } catch (NumberFormatException e) {
                member_gender = 0;
                errorMsgs.add("薪水請填數字.");
            }


            java.sql.Date member_birthday = null;
            try {
                member_birthday = java.sql.Date.valueOf(req.getParameter("member_birthday").trim());
            } catch (IllegalArgumentException e) {
                member_birthday = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }

            java.sql.Timestamp register_time = java.sql.Timestamp.valueOf(req.getParameter("register_time".trim()));
            Integer register_method = Convert.toInt(req.getParameter("register_method".trim()));
            Integer member_status = Convert.toInt(req.getParameter("member_status".trim()));
            String member_password = req.getParameter("member_password".trim());

            MemberBean member = new MemberBean();
            member.setMember_account(member_account);
            member.setMember_name(member_name);
            member.setMember_phone(member_phone);
            member.setMember_email(member_email);
            member.setMember_gender(member_gender);
            member.setMember_birthday(member_birthday);
            member.setMember_status(member_status);
            member.setRegister_method(register_method);
            member.setRegister_time(register_time);
            member.setMember_password(member_password);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("member", member); // 含有輸入格式錯誤的empVO物件,也存入req
//                RequestDispatcher failureView = req
//                        .getRequestDispatcher("/emp/addEmp.jsp");
//                failureView.forward(req, res);
                return "/member_jsp/addEmp.jsp";
            }

            /***************************2.開始新增資料***************************************/
            MemberService memberSvc = new MemberService();
            memberSvc.addMember(member);

            /***************************3.新增完成,準備轉交(Send the Success view)***********/
//            String url = "/emp/listAllEmp.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//            successView.forward(req, res);
            return "/member_jsp/listAllEmp.jsp";
            /***************************其他可能的錯誤處理**********************************/
        } catch (Exception e) {
            errorMsgs.add(e.getMessage());
//            RequestDispatcher failureView = req
//                    .getRequestDispatcher("/emp/addEmp.jsp");
//            failureView.forward(req, res);
            return "/member_jsp/addEmp.jsp";
        }
    }

    public void backend_getPhoto(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, SQLException {
        res.setContentType("image/png");
        Connection con = null;
        String driver = JDBCUtil.driver;
        String url = JDBCUtil.url;
        String userid = JDBCUtil.user;
        String passwd = JDBCUtil.password;
        ResultSet rs;
        con = DriverManager.getConnection(url, userid, passwd);
        ServletOutputStream out = res.getOutputStream();
        Statement stmt = con.createStatement();
        String id = req.getParameter("id").trim();
//        ResultSet rs = stmt.executeQuery(
//                "SELECT member_photo FROM sweet.member WHERE member_account =" + id);

        PreparedStatement pstmt = null;
        pstmt = con.prepareStatement("SELECT member_photo FROM sweet.member WHERE member_account =?");

        pstmt.setString(1, id);

        rs = pstmt.executeQuery();

        try {
            if (rs.next()) {
//            BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("member_photo"));
                IoUtil.write(res.getOutputStream(), true, IoUtil.readBytes(rs.getBinaryStream("member_photo"), true));
            }
        } catch (Exception e) {

        } finally {
            rs.close();
            pstmt.close();
            con.close();
        }


//        try {
//            if (rs.next()) {
//                BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("member_photo"));
//                byte[] buf = new byte[4 * 1024]; // 4K buffer
//                int len;
//                while ((len = in.read(buf)) != -1) {
//                    out.write(buf, 0, len);
//                }
//                in.close();
//            } else {
////				res.sendError(HttpServletResponse.SC_NOT_FOUND);
//                InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");
//                byte[] b = new byte[in.available()];
//                in.read(b);
//                out.write(b);
//                in.close();
//            }
//            rs.close();
//            stmt.close();
//        } catch (Exception e) {
////			System.out.println(e);
////            InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
////            byte[] b = new byte[in.available()];
////            in.read(b);
////            out.write(b);
////            in.close();
//        }

    }

//    public void testphoto(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        String imgStr = "";
//        byte[] buf = GenerateImage(imgStr);
//        res.setContentType("image/png;");
//        IoUtil.write(res.getOutputStream(), true, buf);
//    }
//
//    public byte[] GenerateImage(String imgStr) {   //對位元組陣列字串進行Base64解碼並生成圖片
//        byte[] decodedBytes = Base64.getDecoder().decode(imgStr);
//        return decodedBytes;
//    }

    public void backend_getAll(HttpServletRequest req, HttpServletResponse res) {
        List<MemberBean> list = service.getAll();
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("取得所有會員資料!");
        info.setData(list);
        writeValueByWriter(res, info);
    }
}
