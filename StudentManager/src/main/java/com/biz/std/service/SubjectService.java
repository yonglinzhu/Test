package com.biz.std.service;

import com.biz.std.vo.SubjectVo;

/**
 * 学科service接口
 *
 * by zale on 2017/5/16.
 */
public interface SubjectService {

    void goSubjectManager(String pageNum);// 跳转至学科信息管理页

    void saveSubject(SubjectVo subjectVo);// 添加学科信息

    void updateSubject(SubjectVo subjectVo);// 修改学科信息

    void deleteSubject(SubjectVo subjectVo);// 删除学科
}
