package com.example.demo.persistence;

import com.example.demo.model.comment;
import org.springframework.data.repository.CrudRepository;

public interface commentRepository extends CrudRepository<comment, Integer> {

}
