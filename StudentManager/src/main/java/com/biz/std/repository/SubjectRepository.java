package com.biz.std.repository;

import com.biz.std.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * by zale on 2017/5/10.
 */
public interface SubjectRepository extends JpaRepository<Subject, Integer>,
        JpaSpecificationExecutor<Subject> {
    // 通过学科名字查找是否重复
    @Query(value = "select count(id) from tb_subject WHERE name = ?1 AND state != '0'", nativeQuery = true)
    int checkSubjectByName(String name);

    // 通过学科ID查询学科信息
    @Query(value = "SELECT * FROM tb_subject WHERE id = ?1 AND state != '0'", nativeQuery = true)
    Subject findSubjectById(int id);

    // 获取所有班级信息
    @Query(value = "SELECT * FROM tb_subject WHERE state != '0'", nativeQuery = true)
    List<Subject> getAllSubjects();

}
