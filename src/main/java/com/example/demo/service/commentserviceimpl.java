package com.example.demo.service;

import com.example.demo.model.board;
import com.example.demo.model.comment;
import com.example.demo.persistence.commentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentserviceimpl implements commentservice{


    @Autowired
    public commentRepository commentRepo;

    @Override
    public List<comment> getcommentlist() {
        return (List<comment>)commentRepo.findAll();
    }
    @Override
    public void insertcomment(comment comment) {
        commentRepo.save(comment);
    }


    @Override
    public void deletecomment(comment comment) {

    }
}
