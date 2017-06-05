package com.biz.std.repository;

import com.biz.std.model.Score;
import com.biz.std.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * by zale on 2017/5/10.
 */
@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

    // 根据学生ID获取所选学科
    List<Score> findByStudentIdAndStateNot(Integer studentId, String state);

    @Query("select avg(o.score) FROM Score o WHERE o.studentId = ?1 AND o.state = ?2")
    BigDecimal calculateAverageByStudentId(int id, String state);// 获取平局分根据学生ID

    @Query("select avg(o.score) FROM Score o WHERE o.subjectId = ?1 AND o.state = ?2")
    BigDecimal calculateAverageBySubjectId(int id, String state);// 获取平局分根据学科ID

    @Query("select sum(o.score) FROM Score o WHERE o.studentId = ?1 AND o.state = ?2")
    BigDecimal calculateScoreByStudentId(int id, String state);// 获取单个学生总分数根据学生ID

    // 根据学科ID获取所选学科
    List<Score> findBySubjectIdAndStateNot(Integer subjectId, String state);
}
