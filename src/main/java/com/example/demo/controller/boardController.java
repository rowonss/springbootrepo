package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Enumeration;

import com.example.demo.model.account_info.Member;
import com.example.demo.model.board;
import com.example.demo.model.comment;
import com.example.demo.persistence.account_info.MemberRepository;
import com.example.demo.service.account_info.Memberservice;
import com.example.demo.service.boardservice;
import com.example.demo.service.commentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class boardController {

    @Autowired
    private boardservice boardservice;

    @Autowired
    private Memberservice memberservice;
    @Autowired
    private commentservice commentservice;
    static String[] category_list = {"자유", "게임", "만화", "차량"};
    static ArrayList<comment> comment_array = new ArrayList<comment>();
    static ArrayList<board> board_array = new ArrayList<board>();

    static Member loginuser = null;
    static int commentKey = 0;


    static Queue<board> visitedpage = new LinkedList();
    static Queue<Integer> alreadycheck = new LinkedList<>();

    //어노테이션은 메서드 혹은 클래스에 속성, 정의를 해서 스프링이나 자바에서 찾기 쉽도록 해주는 선언부
    //리퀘스트파람 : 클라이언트에서 스트링 문자열을 서버에 전달하는 매개변수 선언

    @GetMapping("index")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("login")
    public String login (@RequestParam ("id") String id,
                         @RequestParam ("password") String password){
        loginuser = memberservice.findMemberByIdAndPassword(id,password);;
        System.out.println("로그인");
        return "redirect:index";
    }

    @RequestMapping("logout")
    public String logout (){
        loginuser = null;
        return "redirect:index";
    }

    @GetMapping("insertboard")
    public String insertBoard(Model model) {
        model.addAttribute("loginuser",loginuser);
        return "insertboard";
    }

    @PostMapping("insertboard")
    public String insertBoard_conf(board board) {
        boardservice.insertboard(board);
        return "redirect:getBoardList";
        //redirect 페이지 전환
    }

    @PostMapping("boardcomment")
    public String boardcomment(
            HttpServletRequest request,
            comment comment
    ) {
        commentservice.insertcomment(comment);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("getboard")
    public String getboard(@RequestParam boolean linked, board board, Model model) {
        boardservice.boardcounter(board);
        List<comment> comments =  commentservice.getcommentlist();
        List<comment> thiscomments = new ArrayList<>();

        board thisboard = boardservice.getboard(board);
        if (!linked && !alreadycheck.contains(thisboard.getKeyValue())) {
            visitedpage.offer(thisboard);
            alreadycheck.offer(thisboard.getKeyValue());
            if (visitedpage.size() == 6) {
                visitedpage.poll();
                alreadycheck.poll();
            }
        }

        for(int i=0; i<comments.size(); i++){
            if(comments.get(i).getKeyValue() == thisboard.getKeyValue())
                thiscomments.add(comments.get(i));
        }

        model.addAttribute("commentList", thiscomments);
        model.addAttribute("board", boardservice.getboard(board));
        return "getboard";
    }

    @GetMapping("deleteboard")
    public String deleteboard(board board) {
        boardservice.deleteboard(board);
        return "redirect:getBoardList";
    }

    @RequestMapping("/getBoardList")
    public String getbo(Model model, @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC)Pageable pageable){
        if(loginuser == null){
            return "redirect:index";
        }
        model.addAttribute("loginuser",loginuser);
        model.addAttribute("visit", visitedpage);
        model.addAttribute("categorylist", category_list);
        model.addAttribute("boardList", boardservice.list(pageable));

        return "getBoardList";
    }

//    @RequestMapping("/getBoardList")
//    public String getBoardList(Model model) {
//        if(loginuser == null){
//            return "redirect:index";
//        }
//        model.addAttribute("loginuser",loginuser);
//        model.addAttribute("visit", visitedpage);
//        model.addAttribute("categorylist", category_list);
//        model.addAttribute("boardList", boardservice.getboardlist());
//        return "getBoardList";
//    }

    @RequestMapping("/findboardlist")
    public String findboardlist(Model model,
    @RequestParam ("writer") String writer) {

        model.addAttribute("loginuser",loginuser);
        model.addAttribute("visit", visitedpage);
        model.addAttribute("categorylist", category_list);
        model.addAttribute("boardList", boardservice.findboardbywriter(writer));
        return "getBoardList";
    }

    @RequestMapping("searchboard")
    public String categorize(
            @RequestParam("category") String category,
            @RequestParam("titlesearch") String titlesearch,
            board board, Model model) {

        List<board> list = boardservice.getboardlist();

        if(Objects.equals(category, "전체")){
            for(int i=0; i<list.size();i++){
                if(!list.get(i).getTitle().contains(titlesearch)){
                    list.remove(i);
                    i--;
                }
            }
            model.addAttribute("categorylist", category_list);
            model.addAttribute("boardList", list);

            return "getBoardList";
        }

        for (int i = 0; i < list.size(); i++) {
            if (!Objects.equals(list.get(i).getCategory(), category)) {
                list.remove(i);
                i--;
            }
        }
        for(int i=0; i<list.size();i++){
            if(!list.get(i).getTitle().contains(titlesearch)){
                list.remove(i);
                i--;
            }
        }

        model.addAttribute("categorylist", category_list);
        model.addAttribute("boardList", list);

        return "getBoardList";
    }

    @RequestMapping("updateboard")
    public String updateboard(
            @RequestParam("content") String content,
            @RequestParam("KeyValue") int KeyValue,
            Model model
    ) {
        model.addAttribute("content", content);
        model.addAttribute("KeyValue", KeyValue);
        return "updateboard";
    }

    @PostMapping("update")
    public String update(
            @RequestParam("content") String content,
            @RequestParam("KeyValue") int KeyValue) {

        for (int i = 0; i < board_array.size(); i++) {
            if (board_array.get(i).getKeyValue() == KeyValue) {
                board_array.get(i).setContent(content);
            }
        }

        return "redirect:getBoardList";
    }

}
