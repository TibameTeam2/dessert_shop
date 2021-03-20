package com.member.model;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import com.emp.model.EmpVO;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import com.util.JedisUtil;

import java.util.List;

import static cn.hutool.core.util.RandomUtil.randomString;

public class MemberService {

    MemberDao dao = new MemberDaoImpl();

    public boolean register(MemberBean member) {
        MemberBean m = dao.findByPrimaryKey(member.getMember_account());
        if (m != null) {
            System.out.println("m=" + m);
            return false;
        }
        dao.insert(member);


//        String activeCode = RandomUtil.randomString(8);

//        Jedis jedis = JedisUtil.getJedis();
//        jedis.set(member.getMember_account(),activeCode);
//        jedis.close();
//
//        MailUtil.send("jasonwu1994@gmail.com", "嗜甜，信箱驗證", activeCode, false);
        new Thread(() -> {
            String activeCode = randomString(8);
            Jedis jedis = JedisUtil.getJedis();
            jedis.set(member.getMember_account(),activeCode);
            jedis.close();
            MailUtil.send("jasonwu1994@gmail.com", "嗜甜，信箱驗證", activeCode, false);
            System.out.println("thread "+member);
        }).start();
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




    public void deleteMember(String member_account) {
        dao.delete(member_account);
    }

    public MemberBean getOneMember(String member_account) {
        return dao.findByPrimaryKey(member_account);
    }

    public List<MemberBean> getAll() {
        return dao.selectAll();
    }



    public void update(MemberBean member) {
        dao.update(member);
    }

    public void addMember(MemberBean member){
        dao.insert(member);
    }
}
