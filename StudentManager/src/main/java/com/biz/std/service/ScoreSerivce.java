package com.biz.std.service;

import com.biz.std.vo.ScoreVo;
import com.biz.std.vo.StudentVo;
import com.biz.std.vo.UpdateScoreVo;

import java.util.List;

/**
 * Description:
 * -
 * Author: Evan  Date: 2017/6/4
 * Email: yonglin.zhu@biz-united.com.cn
 */
public interface ScoreSerivce {
    List<ScoreVo> goEntryScore(StudentVo studentVo);// 跳转至分数录入页

    void entryScore(ScoreVo scoreVo);// 分数录入

    void deleteScore(UpdateScoreVo updateScoreVo);// 删除分数记录
}
