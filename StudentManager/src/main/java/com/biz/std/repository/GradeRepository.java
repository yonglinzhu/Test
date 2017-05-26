package com.biz.std.repository;

import com.biz.std.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * by zale on 2017/5/10.
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer>,
        JpaSpecificationExecutor<Grade> {
    // 通过班级名称查询班级ID
    @Query(value = "SELECT id FROM tb_grade WHERE classname = ?1 AND state != '0'", nativeQuery = true)
    int findGradeIDByGradeName(String name);
}
