package com.example.demo.model.base;

import com.example.demo.model.base.baseTimeEntity;
import lombok.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class baseTimeEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date creatDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
}
