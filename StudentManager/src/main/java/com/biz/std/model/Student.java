package com.biz.std.model;

import com.biz.std.model.common.Sex;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 学生Model
 * <p>
 * by zale on 2017/5/8.
 */
@Entity
@Table(name = "tb_student")
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    // 姓名
    @Column(length = 20)
    private String name;

    // 性别
    private Sex sex;

    // 出生日期
    @Column(length = 20)
    private String birthday;

    // 学生头像保存路径
    @Column(length = 150)
    private String picture;

    // 学号
    @Column(length = 20)
    private String number;

    // 所在班级
    @ManyToOne(optional = false)
    @JoinColumn(name = "grade_id")
    private Grade grade;

    // 所选学科
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_score",
            joinColumns = {@JoinColumn(name = "studentId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "subjectId", referencedColumnName = "id")}
    )
    @Where(clause = "state=1")
    private List<Subject> subjects;

    // 状态
    @Column(length = 10)
    private String state;

    // 平均分
    private BigDecimal average;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
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

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }
}