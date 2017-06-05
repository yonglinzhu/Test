package com.biz.std.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 班级Model
 *
 * by zale on 2017/5/10.
 */
@Entity
@Table(name = "tb_grade")
public class Grade implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    // 班级名
    @Column(length = 20)
    private String classname;

    // 学生
    @OneToMany(mappedBy = "grade",cascade = CascadeType.ALL)
    @Where(clause = "state=1")
    private List<Student> students;

    // 平均分
    private BigDecimal average;

    // 状态
    @Column(length = 10)
    private String state;

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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
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
