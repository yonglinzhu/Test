package com.biz.std.util.conversion;

import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.vo.StudentVo;
import com.biz.std.vo.SubjectVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SubjectList 转 SubjectVoList
 * -
 * by zale on 2017/5/26.
 */
@Service
public class SubjectListTurnSubjectVoList implements Function<List<Subject>, List<SubjectVo>> {

    private List<SubjectVo> subjectVoList = Lists.newArrayList();

    @Override
    public List<SubjectVo> apply(List<Subject> subjects) {
        SubjectVo subjectVo;
        for (Subject subject : subjects) {
            subjectVo = new SubjectVo();
            subjectVo.setId(subject.getId());
            subjectVo.setAverage(subject.getAverage());
            subjectVo.setNumber(subject.getNumber());
            subjectVo.setState(subject.getState());
            subjectVo.setName(subject.getName());
            // add
            subjectVoList.add(subjectVo);
        }
        return subjectVoList;
    }
}
