package com.biz.std.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 学生Model
 *
 * by zale on 2017/5/8.
 */
@Entity
@Table(name = "tb_student")
@Component
public class Student {
    @Id
    @GeneratedValue
    private Integer id;

    // 姓名
    private String name;

    // 性别
    private String sex;

    // 出生日期
    private String birthday;

    // 学生头像保存路径
    private String picture;

    // 学号
    private String number;

    // 所在班级ID
    private Integer grade_id;

    // 所选学科总数
    @Column(nullable = false, columnDefinition = "int default 0")
    private int sub_num;

    // 状态
    private String state;

    // 平均分
    @Column(nullable = false, columnDefinition = "double default 0")
    private double average;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", picture='" + picture + '\'' +
                ", number='" + number + '\'' +
                ", grade_id=" + grade_id +
                ", sub_num=" + sub_num +
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(Integer grade_id) {
        this.grade_id = grade_id;
    }

    public int getSub_num() {
        return sub_num;
    }

    public void setSub_num(int sub_num) {
        this.sub_num = sub_num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
