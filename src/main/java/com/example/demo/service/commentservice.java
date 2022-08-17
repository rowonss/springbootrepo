package com.example.demo.service;

import com.example.demo.model.board;
import com.example.demo.model.comment;

import java.util.List;

public interface commentservice {

    List<comment> getcommentlist();

    void insertcomment(comment comment);


    void deletecomment(comment comment);

}
