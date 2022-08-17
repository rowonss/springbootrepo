package com.example.demo.service;

import com.example.demo.model.board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface boardservice {
    List<board> getboardlist();

    void insertboard(board board);

    List<board> findboardbywriter(String writer);
    List<board> findorderbykey (String writer);

    board getboard(board board);

    Page<board> list (Pageable pageable);

    void updateboard(board board);

    void deleteboard(board board);
    void boardcounter(board board);
}
