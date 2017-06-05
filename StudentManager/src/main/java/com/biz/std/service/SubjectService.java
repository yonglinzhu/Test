package com.biz.std.service;

import com.biz.std.vo.*;

import java.util.List;

/**
 * 学科service接口
 *
 * by zale on 2017/5/16.
 */
public interface SubjectService {

    PageResult<SubjectVo> goSubjectManager(PageVo pageVo);// 跳转至学科信息管理页

    void saveSubject(SubjectVo subjectVo);// 添加学科信息

    void updateSubject(SubjectVo subjectVo);// 修改学科信息

    void deleteSubject(SubjectVo subjectVo);// 删除学科

    List<SubjectVo> goAddSubject(StudentVo studentVo);// 跳转至选课页面

    void addSubject(ScoreVo scoreVo);// 选修该课程

}
