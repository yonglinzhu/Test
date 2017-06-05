package com.biz.std.repository;

import com.biz.std.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * by zale on 2017/5/10.
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>,
        JpaSpecificationExecutor<Subject> {

    // 通过学科名字查找学科
    Subject findByNameAndStateNot(String name,String state);

    // 通过学科ID查询学科信息
    Subject findByIdAndStateNot(Integer id,String state);

    // 获取所有未删除学科
    List<Subject> findByStateNot(String state);

}
