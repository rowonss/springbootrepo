package com.example.demo.model.account_info;

import java.util.Date;

import lombok.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 40, nullable = false)
    private String id;

    @Column(length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Setter
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Setter
    @Temporal(TemporalType.DATE)
    private Date updateDate;
}
