package com.biz.std.repository;

import com.biz.std.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * by zale on 2017/5/8.
 */
public interface StudentRepository extends JpaRepository<Student, Integer>,
        JpaSpecificationExecutor<Student> {

    // 通过班级ID查询学生人数
    @Query(value = "select count(id) from tb_student WHERE grade_id = ?1 and state != '0'", nativeQuery = true)
    int countStudentByGrade_id(int id);

    @Query(value = "select * from tb_student WHERE grade_id = ?1", nativeQuery = true)
    List<Student> findStudentByGrade_id(int id);

}
