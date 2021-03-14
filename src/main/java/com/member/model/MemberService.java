package com.member.model;

public class MemberService {

    MemberDao dao = new MemberDaoImpl();

    public boolean register(MemberBean member) {
        MemberBean m = dao.findByPrimaryKey(member.getMember_account());
        if (m != null) {
            System.out.println("m=" + m);
            return false;
        }
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

}
