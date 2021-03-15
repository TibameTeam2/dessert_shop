package com.member.model;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import com.util.JedisUtil;

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


        String activeCode = RandomUtil.randomString(8);

        Jedis jedis = JedisUtil.getJedis();
        jedis.set(member.getMember_account(),activeCode);
        jedis.close();


        MailUtil.send("jasonwu1994@gmail.com", "嗜甜，信箱驗證", activeCode, false);

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

}
