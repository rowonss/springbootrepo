package com.example.demo;

import com.example.demo.model.account_info.Member;
import com.example.demo.service.account_info.Memberservice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestInsertAccount {

    @Autowired
    private Memberservice memberservice;

    @Test
    public void testInsertAccount() {

        Member member = Member.builder().id("aaa").password("aaa").createDate(new Date()).updateDate(new Date()).build();

        memberservice.insertMember(member);

    }
}
