package com.example.demo.service.account_info;


import com.example.demo.persistence.account_info.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.account_info.Member;

import java.util.List;

@Service
public class Memberserviceimpl implements Memberservice{

    @Autowired
    private MemberRepository memberRepo;

    @Override
    public List<Member> getMemeberWhereIdOrEmail(String Email, String Id) {
        return memberRepo.findMemberByEmailOrId(Email,Id);
    }

    @Override
    public List<Member> getMemberList() {
        return (List<Member>) memberRepo.findAll();
    }
    @Override
    public void insertMember(Member member) {
        memberRepo.save(member);
    }

    @Override
    public Member getMember(Member member) {
        return memberRepo.findById(member.getSeq()).get();
    }

    @Override
    public void updateMember(Member member) {
        Member mem = memberRepo.findById(member.getSeq()).get();
        mem.setId(member.getId());
        mem.setPassword(member.getPassword());
        mem.setEmail(member.getEmail());
        memberRepo.save(mem);
    }
    @Override
    public void deleteMember(Member member) {
        memberRepo.deleteById(member.getSeq());
    }
}
