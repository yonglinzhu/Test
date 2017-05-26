package com.biz.std.util.conversion;

import com.biz.std.model.Grade;
import com.biz.std.model.Student;
import com.biz.std.vo.GradeVo;
import com.biz.std.vo.StudentVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * GradeList 转 GradeVoList
 * -
 * by zale on 2017/5/26.
 */
@Service
public class GradeListTurnGradeVoList implements Function<List<Grade>, List<GradeVo>> {

    private List<GradeVo> gradeVoList = Lists.newArrayList();

    @Override
    public List<GradeVo> apply(List<Grade> grades) {
        GradeVo gradeVo;
        for (Grade grade : grades) {
            gradeVo = new GradeVo();
            gradeVo.setId(grade.getId());
            gradeVo.setClassname(grade.getClassname());
            gradeVo.setNumber(grade.getNumber());
            gradeVo.setAverage(grade.getAverage());
            gradeVo.setState(grade.getState());
            // add
            gradeVoList.add(gradeVo);
        }
        return gradeVoList;
    }
}
