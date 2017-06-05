package com.biz.std.util.conversion;

import com.biz.std.model.Grade;
import com.biz.std.vo.GradeVo;
import com.google.common.base.Function;
import org.springframework.stereotype.Service;

/**
 * GradeVo 转 GradePO
 *
 * by zale on 2017/5/11.
 */
@Service
public class GradeVoturnGrade implements Function<GradeVo,Grade>{

    @Override
    public Grade apply(GradeVo gradeVo) {
        Grade grade = new Grade();
        grade.setId(gradeVo.getId());
        grade.setClassname(gradeVo.getClassname());
        grade.setStudents(gradeVo.getStudents());
        grade.setAverage(gradeVo.getAverage());
        grade.setState(gradeVo.getState());
        return grade;
    }
}
