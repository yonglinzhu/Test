package com.biz.std.util.conversion;

import com.biz.std.model.Student;
import com.biz.std.vo.StudentVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * StudentList 转 StudentVoList
 * -
 * by zale on 2017/5/26.
 */
@Service
public class StudentListTurnStudentVoList implements Function<List<Student>, List<StudentVo>> {

    private List<StudentVo> studentVoList = Lists.newArrayList();

    @Override
    public List<StudentVo> apply(List<Student> students) {
        StudentVo studentVo;
        for (Student student : students) {
            studentVo = new StudentVo();
            studentVo.setState(student.getState());
            studentVo.setNumber(student.getNumber());
            studentVo.setSub_num(student.getSub_num());
            studentVo.setPicture(student.getPicture());
            studentVo.setAverage(student.getAverage());
            studentVo.setBirthday(student.getBirthday());
            studentVo.setGrade_id(student.getGrade_id());
            studentVo.setName(student.getName());
            studentVo.setSex(student.getSex());
            studentVo.setId(student.getId());
            // add
            studentVoList.add(studentVo);
        }
        return studentVoList;
    }
}
