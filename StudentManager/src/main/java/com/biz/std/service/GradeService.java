package com.biz.std.service;

import com.biz.std.vo.GradeVo;

/**
 * 班级service接口
 *
 * by zale on 2017/5/10.
 */
public interface GradeService {

    void saveGrade(GradeVo gradeVo);// 保存班级

    void updateGrade(GradeVo gradeVo);// 修改班级信息

    void deleteGradeById(Integer id);// 删除班级

    void goGradeManager(String pageNum);// 跳转至班级信息管理页
}
