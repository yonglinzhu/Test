package com.biz.std.vo;

import com.biz.std.model.Student;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * by zale on 2017/5/10.
 */
public class GradeVo {
    private int id;
    private String classname;
    private List<Student> students;
    private BigDecimal average;
    // 状态
    private String state;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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
