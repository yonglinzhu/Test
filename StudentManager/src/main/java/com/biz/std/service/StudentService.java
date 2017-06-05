package com.biz.std.service;

import com.biz.std.vo.*;

/**
 * 学生service接口
 *
 * by zale on 2017/5/8.
 */
public interface StudentService {
    PageResult<StudentVo> goStudentManager(PageVo pageVo);// 跳转至班级信息管理页

    void saveStudent(StudentVo studentVo);// 保存学生信息

    void updateStudent(StudentVo studentVo);// 修改学生信息

    void deleteStudent(StudentVo studentVo);// 删除学生信息




}
