package com.biz.std.service.impl;

import com.biz.std.model.*;
import com.biz.std.repository.GradeRepository;
import com.biz.std.repository.ScoreRepository;
import com.biz.std.repository.specification.GradePagingFilterSpecification;
import com.biz.std.service.GradeService;
import com.biz.std.util.conversion.GradeListTurnGradeVoList;
import com.biz.std.util.conversion.GradeVoturnGrade;
import com.biz.std.vo.GradeVo;
import com.biz.std.vo.PageResult;
import com.biz.std.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * by zale on 2017/5/10.
 */
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private GradeVoturnGrade gradeVoturnGradeService;

    /**
     * 状态码值
     */
    public static final String DELETESTATECODE = "0";// 删除状态码
    public static final String ACTIVESTATECODE = "1";// 正在使用状态码

    /**
     * 跳转至班级信息也 并分页显示班级信息
     */
    @Override
    public PageResult<GradeVo> goGradeManager(PageVo pageVo) {

        Pageable pageable = new PageRequest(pageVo.getPageIndex() - 1, pageVo.getPageSize());
        Page<Grade> page = gradeRepository.findAll(new GradePagingFilterSpecification(), pageable);
        List<Grade> gradeList = page.getContent();// 当前页显示的班级
        // 班级平均分处理
        gradeList = this.gradeAverageProcessing(gradeList);
        // Turn
        List<GradeVo> gradeVoList = new GradeListTurnGradeVoList().apply(gradeList);

        return new PageResult<GradeVo>(pageVo.getPageIndex(), gradeVoList.size(), gradeVoList, page.getTotalPages());
    }

    /**
     * 获取所有有效班级信息
     */
    @Override
    public List<GradeVo> findGradeList() {
        List<Grade> gradeList = gradeRepository.findAll();
        List<GradeVo> gradeVoList = new GradeListTurnGradeVoList().apply(gradeList);
        return gradeVoList;
    }

    /**
     * 保存班级
     */
    @Override
    @Transactional
    public void saveGrade(GradeVo gradeVo) {
        // 校验班级名称是否重复
        Grade gradeExsit = gradeRepository.findByClassnameAndStateNot(gradeVo.getClassname(), GradeServiceImpl.DELETESTATECODE);
        if (gradeExsit != null) {
            return;
        }
        gradeVo.setState(ACTIVESTATECODE);// 初始化班级状态为：正在使用
        gradeVo.setAverage(new BigDecimal("0.000"));
        // Vo转PO
        Grade grade = gradeVoturnGradeService.apply(gradeVo);
        gradeRepository.save(grade);
    }

    /**
     * 修改班级信息
     */
    @Override
    @Transactional
    public void updateGrade(GradeVo gradeVo) {
        // Vo转PO
        Grade grade = gradeVoturnGradeService.apply(gradeVo);
        // 获取该班级
        Grade gradeTemp = gradeRepository.findOne(grade.getId());
        // 修改班级信息
        gradeTemp.setClassname(grade.getClassname());
        // 更新数据库
        gradeRepository.save(gradeTemp);
    }

    /**
     * 删除班级
     */
    @Override
    @Transactional
    public void deleteGradeById(Integer id) {
        // 获取该班级
        Grade gradeTemp = gradeRepository.findOne(id);
        // 修改班级状态（改为删除状态）
        gradeTemp.setState(DELETESTATECODE);
        // 更新数据库
        gradeRepository.save(gradeTemp);
    }

    /**
     * 班级平均分处理方法
     */
    private List<Grade> gradeAverageProcessing(List<Grade> gradeList) {

        if (gradeList != null && gradeList.size() != 0) {
            for (int i = 0; i < gradeList.size(); i++) {
                // 获取每个班级的平均分数
                BigDecimal studentScore = new BigDecimal("0.000");// 单个班级平局分数
                if (gradeList.get(i).getStudents() != null && gradeList.get(i).getStudents().size() != 0) {
                    for (int j = 0; j < gradeList.get(i).getStudents().size(); j++) {
                        List<Score> scoreList = scoreRepository.findByStudentIdAndStateNot(gradeList.get(i).getStudents().get(j).getId(), GradeServiceImpl.DELETESTATECODE);
                        if (scoreList != null && scoreList.size() != 0) {// 判断该学生是否有选修课程
                            BigDecimal tempScore = scoreRepository.calculateScoreByStudentId(gradeList.get(i).getStudents().get(j).getId(), GradeServiceImpl.ACTIVESTATECODE);
                            studentScore = studentScore.add(tempScore);
                        }
                    }
                    // 计算平局分
                    studentScore = studentScore.divide(new BigDecimal(gradeList.get(i).getStudents().size()), 3, BigDecimal.ROUND_HALF_UP);
                }
                //
                gradeList.get(i).setAverage(studentScore);
            }
        }
        return gradeList;
    }
}
