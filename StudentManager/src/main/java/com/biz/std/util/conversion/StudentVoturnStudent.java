package com.biz.std.util.conversion;

import com.biz.std.model.Student;
import com.biz.std.vo.StudentVo;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentVo 转 StudentPO
 *
 * by zale on 2017/5/11.
 */
@Service
public class StudentVoturnStudent implements Function<StudentVo, Student> {
    @Autowired
    private Student student;

    @Override
    public Student apply(StudentVo studentVo) {
        student.setId(studentVo.getId());
        student.setName(studentVo.getName());
        student.setSex(studentVo.getSex());
        student.setBirthday(studentVo.getBirthday());
        student.setAverage(studentVo.getAverage());
        student.setGrade_id(studentVo.getGrade_id());
        student.setPicture(studentVo.getPicture());
        student.setNumber(studentVo.getNumber());
        student.setState(studentVo.getState());
        student.setSub_num(studentVo.getSub_num());
        return student;
    }
}
