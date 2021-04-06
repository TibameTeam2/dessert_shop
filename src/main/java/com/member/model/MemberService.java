package com.member.model;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import com.emp.model.EmpVO;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import com.util.JedisUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static cn.hutool.core.util.RandomUtil.randomString;

public class MemberService {

    MemberDao dao = new MemberDaoImpl();

    public boolean register(MemberBean member, String path) {
        MemberBean m = dao.findByPrimaryKey(member.getMember_account());
        if (m != null) {
            System.out.println("m=" + m);
            return false;
        }
        dao.insert(member);

        new Thread(() -> {
            String activeCode = IdUtil.randomUUID();
            Jedis jedis = JedisUtil.getJedis();
            jedis.set(activeCode, member.getMember_account());
            jedis.expire(activeCode, 600);
            jedis.close();
            String context = "請點擊以下連結啟用您的帳號(10分鐘內有效)\n" + path + "/TEA103G2/front-end/login.html?activeCode=" + activeCode;
            MailUtil.send("jasonwu1994@gmail.com", "嗜甜，信箱驗證", context, false);
            System.out.println("thread " + member);
        }).start();
        return true;
    }

    public boolean registerWithGoogle(MemberBean member){
        MemberBean m = dao.findByPrimaryKey(member.getMember_account());
        if (m != null) {
            System.out.println("m=" + m);
            return false;
        }
        member.setRegister_method(2);
        member.setMember_status(1);
        dao.insert(member);
        return true;
    }

    public MemberBean login(MemberBean member) {
        MemberBean m = dao.findByPrimaryKey(member.getMember_account());
        if (m == null) {
            System.out.println("m=" + m);
            return null;
        }
        if (member.getMember_password().equals(m.getMember_password()))
            return m;
        return null;
    }

    public boolean emailActive(String member_account) {
        MemberBean member = dao.findByPrimaryKey(member_account);
        if (member == null) {
            return false;
        }
        member.setMember_status(1);
        dao.update(member);
        return true;
    }

    public boolean forgetPassword(String member_email, String path) {
        MemberBean member = dao.findByEmail(member_email);
        if (member == null) return false;
        new Thread(() -> {
            String resetPasswordCode = IdUtil.randomUUID();
            Jedis jedis = JedisUtil.getJedis();
            jedis.set(resetPasswordCode, member.getMember_account());
            jedis.expire(resetPasswordCode, 1800);
            jedis.close();
            String context = "請點擊以下連結重置您的密碼(30分鐘內有效)\n" + path + "/TEA103G2/front-end/login.html?resetPasswordCode=" + resetPasswordCode;
            MailUtil.send(member.getMember_email(), "嗜甜，重置密碼", context, false);
            System.out.println("thread " + member);
        }).start();
        return true;
    }

    public boolean changePassword(String member_account, String password) {
        MemberBean member = dao.findByPrimaryKey(member_account);
        if (member == null) return false;
        member.setMember_password(password);
        dao.update(member);
        return true;
    }


    public void deleteMember(String member_account) {
        dao.delete(member_account);
    }

    public MemberBean getOneMember(String member_account) {
        return dao.findByPrimaryKey(member_account);
    }

    public List<MemberBean> getAll() {
        return dao.selectAll();
    }


    public boolean update(MemberBean member) {
        dao.update(member);
        return true;
    }

    public void addMember(MemberBean member) {
        dao.insert(member);
    }
}
