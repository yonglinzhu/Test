package com.biz.std.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 班级Model
 *
 * by zale on 2017/5/10.
 */
@Entity
@Table(name = "tb_grade")
@Component
public class Grade {
    @Id
    @GeneratedValue
    private Integer id;

    // 班级名
    private String classname;
    // 人数
    private int number;
    // 平均分
    private double average;
    // 状态
    private String state;

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", classname='" + classname + '\'' +
                ", number=" + number +
                ", average='" + average + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
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
