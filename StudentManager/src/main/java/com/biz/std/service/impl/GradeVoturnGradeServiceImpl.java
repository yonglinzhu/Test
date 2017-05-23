package com.biz.std.service.impl;

import com.biz.std.model.Grade;
import com.biz.std.vo.GradeVo;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GradeVo 转 GradePO
 *
 * by zale on 2017/5/11.
 */
@Service
public class GradeVoturnGradeServiceImpl implements Function<GradeVo,Grade>{
    @Autowired
    private Grade grade;

    @Override
    public Grade apply(GradeVo gradeVo) {
        grade.setId(gradeVo.getId());
        grade.setClassname(gradeVo.getClassname());
        grade.setNumber(gradeVo.getNumber());
        grade.setAverage(gradeVo.getAverage());
        grade.setState(gradeVo.getState());
        return grade;
    }
}
