package com.biz.std.service;

import com.biz.std.model.Grade;
import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.vo.PageResult;
import com.biz.std.vo.PageVo;
import com.biz.std.vo.ScoreVo;
import com.biz.std.vo.StudentVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 学生service接口
 *
 * by zale on 2017/5/8.
 */
public interface StudentService {
    PageResult<Student> goStudentManager(PageVo pageVo);// 跳转至班级信息管理页

    List<Grade> findGradeList();

    void saveStudent(StudentVo studentVo);// 保存学生信息

    void updateStudent(StudentVo studentVo);// 修改学生信息

    void deleteStudent(StudentVo studentVo);// 删除学生信息

    List<Subject> goAddSubject(StudentVo studentVo);// 跳转至选课页面

    void addSubject(ScoreVo scoreVo);// 选修该课程

    List<Score> goEntryScore(StudentVo studentVo);// 跳转至分数录入页

    void entryScore(ScoreVo scoreVo);// 分数录入

    void uploadPicture(StudentVo studentVo, HttpServletRequest request) throws IOException;// 图片上传

    void pictureView(StudentVo studentVo, HttpServletResponse response) throws IOException;// 图片显示


}
