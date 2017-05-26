package com.biz.std.util.conversion;

import com.biz.std.model.Score;
import com.biz.std.model.Subject;
import com.biz.std.vo.ScoreVo;
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
public class ScoreListTurnScoreVoList implements Function<List<Score>, List<ScoreVo>> {

    private List<ScoreVo> scoreVoList = Lists.newArrayList();

    @Override
    public List<ScoreVo> apply(List<Score> scores) {
        ScoreVo scoreVo;
        for (Score score : scores) {
            scoreVo = new ScoreVo();
            scoreVo.setId(score.getId());
            scoreVo.setScore(score.getScore());
            scoreVo.setStudentId(score.getStudentId());
            scoreVo.setSubjectId(score.getSubjectId());
            scoreVo.setState(score.getState());
            scoreVo.setSubjectName(score.getSubjectName());
            // add
            scoreVoList.add(scoreVo);
        }
        return scoreVoList;
    }
}
