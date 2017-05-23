package com.biz.std.service;

import com.biz.std.model.BaseStudentNum;

/**
 * by zale on 2017/5/11.
 */
public interface BaseStudentNumSerivce {
    int checkBaseStudentNum();// 查询BaseStudentNum是否存在

    void saveBasseStudentNum(BaseStudentNum baseStudentNum);// 保存基础学号

    BaseStudentNum findBaseStudentNumBylastDate();// 查找最新的BaseStudentNum

}
