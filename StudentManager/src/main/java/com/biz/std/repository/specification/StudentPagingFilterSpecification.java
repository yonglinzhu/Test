package com.biz.std.repository.specification;

import com.biz.std.model.Student;
import com.google.common.collect.Lists;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * -
 * Author: Evan  Date: 2017/5/24
 * Email: yonglin.zhu@biz-united.com.cn
 */
public class StudentPagingFilterSpecification implements Specification<Student>{
    @Override
    public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//        List<Predicate> list = Lists.newArrayList();
//        list.add(cb.notEqual(root.get("")));
        Path path = root.get("state");

        return cb.notEqual(path, "0");
    }
}
