package com.biz.std.vo;


import org.springframework.stereotype.Component;

/**
 * by zale on 2017/5/16.
 */

@Component
public class ScoreVo {

    private Integer id;

    private int studentId;// 学生ID
    private int subjectId;// 学科ID
    private double score;// 分数
    private String state;// 状态
    private String subjectName;// 学科名称

    @Override
    public String toString() {
        return "ScoreVo{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", score=" + score +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
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
