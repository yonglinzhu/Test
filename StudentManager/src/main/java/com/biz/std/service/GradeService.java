package com.biz.std.service;

import com.biz.std.model.Grade;
import com.biz.std.vo.GradeVo;
import com.biz.std.vo.PageResult;
import com.biz.std.vo.PageVo;
import org.springframework.data.domain.Page;

/**
 * 班级service接口
 * <p>
 * by zale on 2017/5/10.
 */
public interface GradeService {

    PageResult<GradeVo> goGradeManager(PageVo pageVo);// 跳转至班级信息管理页

    void saveGrade(GradeVo gradeVo);// 保存班级

    void updateGrade(GradeVo gradeVo);// 修改班级信息

    void deleteGradeById(Integer id);// 删除班级

}
