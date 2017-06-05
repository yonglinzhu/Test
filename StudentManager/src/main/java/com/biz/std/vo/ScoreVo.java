package com.biz.std.vo;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * by zale on 2017/5/16.
 */
public class ScoreVo {

    private Integer id;

    private Integer studentId;// 学生ID
    private Integer subjectId;// 学科ID
    private BigDecimal score;// 分数
    private String state;// 状态
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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
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
