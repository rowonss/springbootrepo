package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.board;
import com.example.demo.persistence.boardRepository;

import javax.transaction.Transactional;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.List;

@Service
public class boardserviceimpl implements boardservice {

    @Autowired
    private boardRepository boardRepo;

    @Override
    public List<board> getboardlist() {
        return (List<board>)boardRepo.findAll();
    }
    @Override
    public void insertboard(board board) {
        boardRepo.save(board);
    }

    @Override
    public List<board> findboardbywriter(String writer) {
        return boardRepo.findAllByWriter(writer);
    }

    @Override
    public List<board> findorderbykey(String writer) {
        return boardRepo.findAllByWriterOrderByCreateDateDesc(writer);
    }

    @Override
    public board getboard(board board) {
        return boardRepo.findById(board.getKeyValue()).get();
    }

    @Transactional
    public Page<board> list(Pageable pageable) {
        return boardRepo.findAll(pageable);
    }


    @Override
    public void updateboard(board board) {
    }
    @Override
    public void deleteboard(board board) {
        boardRepo.deleteById(board.getKeyValue());
    }
    @Override
    public void boardcounter(board board){
        board findBoard = boardRepo.findById(board.getKeyValue()).get();
        findBoard.setCnt(findBoard.getCnt()+1);
        boardRepo.save(findBoard);
    }


}