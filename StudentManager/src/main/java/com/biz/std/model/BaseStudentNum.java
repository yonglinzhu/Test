package com.biz.std.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生信息管理 - 添加学生 - 学号Model
 *
 * by zale on 2017/5/11.
 */
@Entity
@Table(name = "tb_baseStudentNum")
@Component
public class BaseStudentNum {
    @Id
    @GeneratedValue
    private Integer id;
    // 基础学号
    private String baseNum;
    // 学生标识
    private String Identification;
    // 班级ID
    private int gradeId;

    @Override
    public String toString() {
        return "BaseStudentNum{" +
                "id=" + id +
                ", baseNum='" + baseNum + '\'' +
                ", gradeId=" + gradeId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaseNum() {
        return baseNum;
    }

    public void setBaseNum(String baseNum) {
        this.baseNum = baseNum;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getIdentification() {
        return Identification;
    }

    public void setIdentification(String identification) {
        Identification = identification;
    }
}
