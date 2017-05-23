package com.biz.std.vo;

import org.springframework.stereotype.Component;

/**
 * by zale on 2017/5/10.
 */
@Component
public class GradeVo {
    private int id;
    private String classname;
    private int number;
    private double average;
    // 状态
    private String state;

    @Override
    public String toString() {
        return "GradeVo{" +
                "id=" + id +
                ", classname='" + classname + '\'' +
                ", number=" + number +
                ", average='" + average + '\'' +
                '}';
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
