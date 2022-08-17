package com.example.demo.model;

import java.util.Date;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Getter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class comment {

    @Id @GeneratedValue
    private int seq;

    @Column(nullable = false)
    private int KeyValue;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false)
    private String comments;

}
