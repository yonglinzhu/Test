package com.biz.std.util.conversion;

import com.biz.std.model.Subject;
import com.biz.std.vo.SubjectVo;
import com.google.common.base.Function;
import org.springframework.stereotype.Service;

/**
 * SubjectVo 转 SubjectPO
 * <p>
 * by zale on 2017/5/11.
 */
@Service
public class SubjectVoturnSubject implements Function<SubjectVo, Subject> {

    @Override
    public Subject apply(SubjectVo subjectVo) {
        Subject subject = new Subject();
        subject.setId(subjectVo.getId());
        subject.setAverage(subjectVo.getAverage());
        subject.setNumber(subjectVo.getNumber());
        subject.setState(subjectVo.getState());
        subject.setName(subjectVo.getName());
        return subject;
    }
}
