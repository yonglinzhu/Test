package com.biz.std.vo;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * by zale on 2017/5/16.
 */
public class SubjectVo {
    private Integer id;

    private String name;// 学科名称

    private Integer number;// 选修人数

    private BigDecimal average;// 平均分

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
