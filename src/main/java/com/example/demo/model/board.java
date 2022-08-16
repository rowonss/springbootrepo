package com.example.demo.model;

import java.util.Date;

import lombok.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class board {

    @Id @GeneratedValue
    private int KeyValue;

    @GeneratedValue
    private Long Seq;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false, updatable = false)
    private String writer;

    @Column(nullable = false)
    @ColumnDefault("'no content'")
    private String content;

    @Column(length = 10)
    private String category;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @ColumnDefault("0")
    @Column(insertable = false)
    private Long cnt;

    @PrePersist
    protected void prePersist() {
        createDate = new Date();
    }

}
