package com.example.demo.persistence;

/**
 * @package : com.example.demo.persistence
 * @name : BoardRepository.java
 * @date : 2022-08-08 오후 6:20
 * @author : Rubisco
 * @version : 1.0.0
 * @modifyed :
 * @description : 게시판 레포지토리
 **/

import com.example.demo.model.board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//CrudRepository를 상속받음
//CrudRepository : Entity 를 매개체로 create, read, update, delete 기능을 하는 인터페이스
//CrudRepository<Board, Long>의 매개변수 Board(Entity)와 Long(PK기본키의 타입)을 선언
//JPA가 어떤 객체로 어떤 타입으로 찾아야하는지 알아 들음
public interface boardRepository extends JpaRepository<board, Integer> {

    List<board> findAllByWriter (String writer);

    List<board> findAllByWriterOrderByCreateDateDesc (String writer);

}
