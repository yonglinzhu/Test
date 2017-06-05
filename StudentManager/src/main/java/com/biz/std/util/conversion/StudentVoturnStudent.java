package com.biz.std.util.conversion;

import com.biz.std.model.Student;
import com.biz.std.vo.StudentVo;
import com.google.common.base.Function;
import org.springframework.stereotype.Service;

/**
 * StudentVo 转 StudentPO
 *
 * by zale on 2017/5/11.
 */
@Service
public class StudentVoturnStudent implements Function<StudentVo, Student> {

    @Override
    public Student apply(StudentVo studentVo) {
        Student student = new Student();
        student.setId(studentVo.getId());
        student.setName(studentVo.getName());
        student.setSex(studentVo.getSex());
        student.setBirthday(studentVo.getBirthday());
        student.setAverage(studentVo.getAverage());
        student.setGrade(studentVo.getGrade());
        student.setPicture(studentVo.getPicture());
        student.setNumber(studentVo.getNumber());
        student.setState(studentVo.getState());
        student.setSubjects(studentVo.getSubjects());
        return student;
    }
}
