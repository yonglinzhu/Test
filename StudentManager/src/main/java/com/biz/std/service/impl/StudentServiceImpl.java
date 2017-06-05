package com.biz.std.service.impl;

import com.biz.std.model.*;
import com.biz.std.repository.*;
import com.biz.std.repository.specification.StudentPagingFilterSpecification;
import com.biz.std.service.ScoreSerivce;
import com.biz.std.service.StudentService;
import com.biz.std.service.impl.GradeServiceImpl;
import com.biz.std.util.conversion.*;
import com.biz.std.vo.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * by zale on 2017/5/8.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private StudentVoturnStudent studentVoturnStudentService;
    @Autowired
    private ScoreSerivce scoreSerivce;

    /**
     * 跳转至学生信息页 并分页显示学生信息
     */
    @Override
    public PageResult<StudentVo> goStudentManager(PageVo pageVo) {

        Pageable pageable = new PageRequest(pageVo.getPageIndex() - 1, pageVo.getPageSize());
        Page<Student> page = studentRepository.findAll(new StudentPagingFilterSpecification(), pageable);

        List<Student> studentList = page.getContent();// 当前页显示的学生
        // 学生平均分处理方法
        studentList = studentAverageProcessing(studentList);
        // Turn
        List<StudentVo> studentVoList = new StudentListTurnStudentVoList().apply(studentList);

        // 前台传值
        return new PageResult<StudentVo>(pageVo.getPageIndex(), studentVoList.size(), studentVoList, page.getTotalPages());
    }

    /**
     * 保存学生信息
     */
    @Override
    @Transactional
    public void saveStudent(StudentVo studentVo) {
        // 判断是否已选择班级
        if (studentVo.getGrade().getId() ==0){
            return;
        }
        studentVo.setState(GradeServiceImpl.ACTIVESTATECODE);
        // 初始化学号
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String number = sdf.format(date);
        studentVo.setNumber(number);
        // 初始化平均分
        studentVo.setAverage(new BigDecimal("0.000"));
        // Vo转PO
        Student student = studentVoturnStudentService.apply(studentVo);
        studentRepository.save(student);

    }

    /**
     * 修改学生信息
     */
    @Override
    @Transactional
    public void updateStudent(StudentVo studentVo) {
        // 判断是否已选择班级
        if (studentVo.getGrade().getId() ==0){
            return;
        }
        // Vo转PO
        Student student = studentVoturnStudentService.apply(studentVo);
        // 通过学生ID获取该学生信息
        Student studentTemp = studentRepository.findOne(student.getId());
        // 修改学生信息
        studentTemp.setName(student.getName());
        studentTemp.setSex(student.getSex());
        studentTemp.setBirthday(student.getBirthday());
        studentTemp.setGrade(student.getGrade());
        // 更新数据库
        studentRepository.save(studentTemp);
    }

    /**
     * 删除该学生
     */
    @Override
    @Transactional
    public void deleteStudent(StudentVo studentVo) {
        // Vo转PO
        Student student = studentVoturnStudentService.apply(studentVo);
        // 通过学生ID获取该学生信息
        Student studentTemp = studentRepository.findOne(student.getId());
        // 修改学生状态信息
        studentTemp.setState(GradeServiceImpl.DELETESTATECODE);
        // 删除中间表Score相应记录
        UpdateScoreVo updateScoreVo = new UpdateScoreVo();
        updateScoreVo.setId(studentTemp.getId());
        updateScoreVo.setType("deletStudent");
        scoreSerivce.deleteScore(updateScoreVo);
        // 更新数据库
        studentRepository.save(studentTemp);
    }

    /**
     * 学生平均分处理方法
     */
    private List<Student> studentAverageProcessing(List<Student> studentList) {
        // 学生平均分处理
        if (studentList != null && studentList.size() != 0) {
            for (int i = 0; i < studentList.size(); i++) {
                BigDecimal average = scoreRepository.calculateAverageByStudentId(studentList.get(i).getId(), GradeServiceImpl.ACTIVESTATECODE);
                if (average == null) {
                    studentList.get(i).setAverage(new BigDecimal("0.000"));
                } else {
                    studentList.get(i).setAverage(average);
                }
            }
        }
        return studentList;
    }

}
