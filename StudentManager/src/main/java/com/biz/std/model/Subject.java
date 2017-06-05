package com.biz.std.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 学科Model
 *
 * by zale on 2017/5/16.
 */
@Entity
@Table(name = "tb_subject")
public class Subject implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50)
    private String name;// 学科名称

    private Integer number;// 选修人数

    private BigDecimal average;// 平均分

    @Column(length = 10)
    private String state;// 状态

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
