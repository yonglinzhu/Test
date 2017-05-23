package com.biz.std.vo;


import org.springframework.stereotype.Component;

/**
 * by zale on 2017/5/16.
 */
@Component
public class SubjectVo {
    private Integer id;

    private String name;// 学科名称

    private int number;// 选修人数

    private double average;// 平均分

    private String state;// 状态

    @Override
    public String toString() {
        return "SubjectVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", average=" + average +
                ", state='" + state + '\'' +
                '}';
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
