package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.board;
import com.example.demo.persistence.boardRepository;

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
    public board getboard(board board) {
        return boardRepo.findById(board.getKeyValue()).get();
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