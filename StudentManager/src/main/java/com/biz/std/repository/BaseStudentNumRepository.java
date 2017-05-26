package com.biz.std.repository;

import com.biz.std.model.BaseStudentNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * by zale on 2017/5/11.
 */
@Repository
public interface BaseStudentNumRepository extends JpaRepository<BaseStudentNum, Integer> {
    @Query(value = "select count(id) from tb_baseStudentNum ", nativeQuery = true)
    int checkBaseStudentNum();

    @Query(value = "SELECT * \n" +
            "FROM tb_basestudentnum\n" +
            "WHERE id = (SELECT max(id) FROM tb_basestudentnum) ", nativeQuery = true)
    BaseStudentNum findBaseStudentNumBylastDate();

    @Query(value = "SELECT * FROM tb_basestudentnum WHERE gradeId = ?1 ", nativeQuery = true)
    BaseStudentNum findBaseStudentNumByGradeId(int id);

}
