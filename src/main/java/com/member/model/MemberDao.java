package com.member.model;

import java.util.List;

public interface MemberDao {
    void insert(MemberBean memberBean);

    void update(MemberBean memberBean);

    void delete(String member_account);

    MemberBean findByPrimaryKey(String member_account);

    MemberBean findByEmail(String member_email);

    List<MemberBean> selectAll();


}
