package com.biz.std.repository;

import com.biz.std.model.Score;
import com.biz.std.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * by zale on 2017/5/10.
 */
@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

    // 根据学生ID获取所选学科
    @Query(value = "SELECT * FROM tb_score WHERE studentId = ?1 AND state != '0'", nativeQuery = true)
    List<Score> findScoreByStudentId(int id);

    // 根据学生ID获取所选学科
    @Query(value = "SELECT * FROM tb_score WHERE id = ?1 AND state != '0'", nativeQuery = true)
    Score findScoreById(int id);

    @Query(value = "select * from tb_score WHERE subjectId = ?1 AND state != '0'", nativeQuery = true)
    List<Score> countStudentsbySubjectId(int subjectId);

    @Query(value = "select * from tb_score WHERE subjectId = ?1 AND state != '0'", nativeQuery = true)
    List<Score> findScorebySubjectId(int subjectId);
}
