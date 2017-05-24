package com.biz.std.repository.specification;

import com.biz.std.model.Grade;
import com.biz.std.model.Subject;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * Description:
 * -
 * Author: Evan  Date: 2017/5/24
 * Email: yonglin.zhu@biz-united.com.cn
 */
public class GradePagingFilterSpecification implements Specification<Grade>{
    @Override
    public Predicate toPredicate(Root<Grade> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        Path path = root.get("state");
        return cb.notEqual(path, "0");
    }
}
