package com.biz.std.service.impl;

import com.biz.std.model.BaseStudentNum;
import com.biz.std.repository.BaseStudentNumRepository;
import com.biz.std.service.BaseStudentNumSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * by zale on 2017/5/11.
 */
@Service
public class BaseStudentNumSerivceImpl implements BaseStudentNumSerivce{
    @Autowired
    private BaseStudentNumRepository baseStudentNumRepository;// 注入BaseStudentNumRepository
    @Override
    public int checkBaseStudentNum() {
        return baseStudentNumRepository.checkBaseStudentNum();
    }

    @Override
    public void saveBasseStudentNum(BaseStudentNum baseStudentNum) {
        baseStudentNumRepository.save(baseStudentNum);
    }

    @Override
    public BaseStudentNum findBaseStudentNumBylastDate() {
        return baseStudentNumRepository.findBaseStudentNumBylastDate();
    }
}
