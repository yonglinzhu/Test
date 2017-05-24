package com.biz.std.controller;

import com.biz.std.model.Grade;
import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.service.StudentService;
import com.biz.std.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 学生信息管理controller层
 * <p>
 * by zale on 2017/5/10.
 */
@Controller
@RequestMapping("/")
public class StudentManagerController {

    @Autowired
    private StudentService studentService;// 注入StudentService

    /**
     * 转到学生信息管理页
     */
    @RequestMapping("/goStudentManager")
    public ModelAndView goGradeManager(PageVo pageVo) {
        // 获取学生信息
        PageResult<Student> pageResult = studentService.goStudentManager(pageVo);
        // 获取班级信息
        List<Grade> gradeList = studentService.findGradeList();
        return new ModelAndView("studentManager").addObject("pageResult", pageResult)
                .addObject("student_grade", gradeList);
    }

    /**
     * 添加学生信息
     */
    @RequestMapping("/saveStudent")
    public String saveStudent(@ModelAttribute StudentVo studentVo) {
        studentService.saveStudent(studentVo);
        return "redirect:/goStudentManager";
    }

    /**
     * 修改学生信息
     */
    @RequestMapping("/updateStudent")
    public String updateStudent(@ModelAttribute StudentVo studentVo) {
        studentService.updateStudent(studentVo);
        return "redirect:/goStudentManager";
    }

    /**
     * 删除该学生
     */
    @RequestMapping("/deleteStudent")
    public String deleteStudent(@ModelAttribute StudentVo studentVo) {
        studentService.deleteStudent(studentVo);
        return "redirect:/goStudentManager";
    }

    /**
     * 跳转至选课页
     */
    @RequestMapping("/goAddSubject")
    public ModelAndView goAddSubject(@ModelAttribute StudentVo studentVo) {
        List<Subject> subjectList = studentService.goAddSubject(studentVo);
        return new ModelAndView("addSubject").addObject("subjectsList", subjectList);
    }

    /**
     * 选修该课程
     */
    @RequestMapping("/addSubject")
    public String addSubject(@ModelAttribute ScoreVo scoreVo) {
        studentService.addSubject(scoreVo);
        return "redirect:/goAddSubject";
    }

    /**
     * 跳转至分数录入页
     */
    @RequestMapping("/goEntryScore")
    public ModelAndView goEntryScore(@ModelAttribute StudentVo studentVo) {
        List<Score> scoreList = studentService.goEntryScore(studentVo);
        return new ModelAndView("entryScore").addObject("scoreList", scoreList);
    }

    /**
     * 分数录入
     */
    @RequestMapping("/entryScore")
    public String entryScore(@ModelAttribute ScoreVo scoreVo) {
        studentService.entryScore(scoreVo);
        return "redirect:/goEntryScore";
    }

    /**
     * 图片上传
     */
    @RequestMapping("/uploadPicture")
    public String uploadPicture(StudentVo studentVo, HttpServletRequest request) throws IOException {
        System.out.println(studentVo);
        studentService.uploadPicture(studentVo, request);
        return "redirect:/goStudentManager";
    }

    /**
     * 图片显示
     */
    @RequestMapping("/pictureView")
    public void pictureView(StudentVo studentVo, HttpServletResponse response) throws IOException {
        studentService.pictureView(studentVo, response);
    }

}
