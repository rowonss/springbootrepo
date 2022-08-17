package com.example.demo.controller.account_info;

import com.example.demo.model.board;
import com.example.demo.service.account_info.Memberservice;
import com.example.demo.model.account_info.Member;
import com.example.demo.service.boardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class membercontroller {

    //게시판 : 사용자관점, 시스템관리관점 (회원 관리, 게시판 관리, 컨텐츠 관리)
    //getaccountlist : 전체 회원 목록 보기 : 웹솔루션에서 웹시스템을 관리하는 관리자를 기능

    @Autowired
    private Memberservice memberservice;

    @Autowired
    private com.example.demo.service.boardservice boardservice;


    @GetMapping("insertaccount")
    public String insertaccountview(){
        return "insertaccount";
    }

    @PostMapping("insertaccount")
    public String insertaccount(Member member){

        member.setCreateDate(new Date());
        member.setUpdateDate(new Date());
        memberservice.insertMember(member);

        return "index";
    }

    @GetMapping("getaccountlist")
    public String getaccountlist(Model model) {

        model.addAttribute("memberlist", memberservice.getMemberList());

        return "getaccountlist";
    }

    @GetMapping("deleteaccount")
    public String deleteaccount (Member member){

        memberservice.deleteMember(member);

        return "redirect:getaccountlist";
    }


    @RequestMapping("updateaccount")
    public String updateaccount (Member member, Model model){

        Member thismember = memberservice.getMember(member);
        String writer = thismember.getId();

        List<board> boardlist = boardservice.findorderbykey(writer);
        model.addAttribute("boardlist", boardlist);
        model.addAttribute(memberservice.getMember(member));

        return "updateaccount";
    }

    @PostMapping("updateaccount")
    public String update (Member member){

        memberservice.updateMember(member);

        return "redirect:getaccountlist";
    }

    @GetMapping("selectaccount")
    public String selectaccount (){
        return "selectaccount";
    }

    @PostMapping("selectaccount")
    public String result (Member member, Model model){
        model.addAttribute("memberlist",memberservice.getMemberListWhereEmailLike(member.getEmail()));
        return "resultaccount";
    }


}
