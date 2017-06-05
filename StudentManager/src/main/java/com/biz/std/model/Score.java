package com.biz.std.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 学生信息管理 - 分数录入 - 分数Model
 * <p>
 * by zale on 2017/5/16.
 */
@Entity
@Table(name = "tb_score")
public class Score implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer studentId;// 学生ID

    private Integer subjectId;// 学科ID

    private BigDecimal score;// 分数

    @Column(length = 10)
    private String state;// 状态

    @Column(length = 50)
    private String subjectName;// 学科名称

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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
