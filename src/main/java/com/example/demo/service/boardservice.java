package com.example.demo.service;

import com.example.demo.model.board;

import java.util.List;

public interface boardservice {
    List<board> getboardlist();

    void insertboard(board board);

    board getboard(board board);

    void updateboard(board board);

    void deleteboard(board board);
    void boardcounter(board board);
}
