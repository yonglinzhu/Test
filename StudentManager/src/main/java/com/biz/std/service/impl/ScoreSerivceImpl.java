package com.biz.std.service.impl;

import com.biz.std.model.Score;
import com.biz.std.repository.ScoreRepository;
import com.biz.std.service.ScoreSerivce;
import com.biz.std.util.conversion.ScoreListTurnScoreVoList;
import com.biz.std.util.conversion.ScoreVoTurnScore;
import com.biz.std.vo.ScoreVo;
import com.biz.std.vo.StudentVo;
import com.biz.std.vo.UpdateScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 * -
 * Author: Evan  Date: 2017/6/4
 * Email: yonglin.zhu@biz-united.com.cn
 */
@Service
public class ScoreSerivceImpl implements ScoreSerivce {
    @Autowired
    private ScoreVoTurnScore scoreVoTurnScoreService;
    @Autowired
    private ScoreRepository scoreRepository;

    /**
     * 跳转至分数录入页
     */
    @Override
    public List<ScoreVo> goEntryScore(StudentVo studentVo) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        if (session.getAttribute("studentId_EntryScore") != null && studentVo.getId() == null) {
            return this.entryScoreView((Integer) session.getAttribute("studentId_EntryScore"));
        } else {
            /**
             *  存储学生ID
             *
             *  1、重定向-用（处理NullPointerException）
             */
            session.putValue("studentId_EntryScore", studentVo.getId());
        }
        return this.entryScoreView(studentVo.getId());
    }

    private List<ScoreVo> entryScoreView(Integer id) {
        // 获取该学生已选的课程
        List<Score> scoreList = scoreRepository.findByStudentIdAndStateNot(id, GradeServiceImpl.DELETESTATECODE);
        List<ScoreVo> scoreVoList = new ScoreListTurnScoreVoList().apply(scoreList);
        return scoreVoList;
    }

    /**
     * 分数录入
     */
    @Override
    @Transactional
    public void entryScore(ScoreVo scoreVo) {

        // Vo 转 PO
        Score score = scoreVoTurnScoreService.apply(scoreVo);
        // 根据ID查询其所有分数的信息
        Score scoreTemp = scoreRepository.findOne(score.getId());
        // 处理并更新数据
        scoreTemp.setScore(handleScore(scoreVo.getScore()));
        // 数据库更新
        scoreRepository.save(scoreTemp);

    }

    /**
     * 处理录入的分数
     * -
     * 简单处理学科分数范围
     */
    private BigDecimal handleScore(BigDecimal score) {
        int compare = score.compareTo(new BigDecimal("150.000"));
        int compare2 = score.compareTo(new BigDecimal("0.000"));
        if (compare == 1) {
            score = new BigDecimal("150.000");
        } else if (compare == -1) {
            if (compare2 == -1) {
                score = new BigDecimal("0.000");
            }
        }
        return score;
    }

    @Override
    @Transactional
    public void deleteScore(UpdateScoreVo updateScoreVo) {
        List<Score> scoreList;

        if ("deletSubject".equals(updateScoreVo.getType())) {// 删除学科-删除相应分数表数据
            scoreList = scoreRepository.findBySubjectIdAndStateNot(updateScoreVo.getId(), GradeServiceImpl.DELETESTATECODE);
        } else if ("deletStudent".equals(updateScoreVo.getType())) {// 删除学生-删除相应分数表数据
            scoreList = scoreRepository.findByStudentIdAndStateNot(updateScoreVo.getId(), GradeServiceImpl.DELETESTATECODE);
        } else {
            return;
        }

        if (scoreList != null && scoreList.size() != 0) {
            for (Score score : scoreList) {
                score.setState(GradeServiceImpl.DELETESTATECODE);
                scoreRepository.save(score);
            }
        }

    }
}
