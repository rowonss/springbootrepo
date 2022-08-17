package com.example.demo.service.account_info;

import com.example.demo.model.account_info.Member;

import java.util.List;

public interface Memberservice {

    List<Member> getMemeberWhereIdOrEmail(String Email, String Id);

    List<Member> getMemberListWhereEmailLike(String Email);

    List<Member> getMemberList();

    void insertMember(Member member);

    Member getMember(Member member);

    void updateMember(Member member);

    void deleteMember(Member member);
}
