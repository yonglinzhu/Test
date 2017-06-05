package com.biz.std.util.conversion;

import com.biz.std.model.Score;
import com.biz.std.vo.ScoreVo;
import com.google.common.base.Function;
import org.springframework.stereotype.Service;

/**
 * ScoreVo 转 ScorePO
 *
 * by zale on 2017/5/11.
 */
@Service
public class ScoreVoTurnScore implements Function<ScoreVo, Score> {

    @Override
    public Score apply(ScoreVo scoreVo) {
        Score score = new Score();
        score.setId(scoreVo.getId());
        score.setScore(scoreVo.getScore());
        score.setStudentId(scoreVo.getStudentId());
        score.setSubjectId(scoreVo.getSubjectId());
        score.setState(scoreVo.getState());
        score.setSubjectName(scoreVo.getSubjectName());
        return score;
    }
}
