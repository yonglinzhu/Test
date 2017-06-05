package com.biz.std.service.impl;

import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.repository.ScoreRepository;
import com.biz.std.repository.StudentRepository;
import com.biz.std.repository.SubjectRepository;
import com.biz.std.repository.specification.SubjectPagingFilterSpecification;
import com.biz.std.service.ScoreSerivce;
import com.biz.std.service.SubjectService;
import com.biz.std.util.conversion.ScoreVoTurnScore;
import com.biz.std.util.conversion.SubjectListTurnSubjectVoList;
import com.biz.std.util.conversion.SubjectVoturnSubject;
import com.biz.std.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * by zale on 2017/5/16.
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectVoturnSubject subjectVoturnSubjectService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private ScoreVoTurnScore scoreVoTurnScoreService;
    @Autowired
    private ScoreSerivce scoreSerivce;

    /**
     * 跳转到学科管理页
     */
    @Override
    public PageResult<SubjectVo> goSubjectManager(PageVo pageVo) {

        Pageable pageable = new PageRequest(pageVo.getPageIndex() - 1, pageVo.getPageSize());
        Page<Subject> page = subjectRepository.findAll(new SubjectPagingFilterSpecification(), pageable);
        List<Subject> subjectsList = page.getContent();// 当前页显示的学科
        // 处理学科选修人数 平均分
        subjectsList = this.initSubjectNum(subjectsList);
        // Turn
        List<SubjectVo> subjectVoList = new SubjectListTurnSubjectVoList().apply(subjectsList);
        return new PageResult<SubjectVo>(pageVo.getPageIndex(), subjectVoList.size(), subjectVoList, page.getTotalPages());
    }

    /**
     * 跳转至选课页
     */
    @Override
    public List<SubjectVo> goAddSubject(StudentVo studentVo) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();

        if (session.getAttribute("studentId_goAddSubject") != null && studentVo.getId() == null) {
            return this.getSubject((Integer) session.getAttribute("studentId_goAddSubject"));
        } else {
            /**
             *  存储学生ID
             *
             *  1、选修课程操作-用
             *  2、重定向-用（处理NullPointerException）
             */
            session.putValue("studentId_goAddSubject", studentVo.getId());
        }
        return this.getSubject(studentVo.getId());

    }

    /**
     * 处理学科选修人数 平均分
     */
    private List<Subject> initSubjectNum(List<Subject> subjectsList) {
        if (subjectsList != null && subjectsList.size() != 0) {
            for (int i = 0; i < subjectsList.size(); i++) {
                // 获取平局分
                BigDecimal average = scoreRepository.calculateAverageBySubjectId(subjectsList.get(i).getId(), GradeServiceImpl.ACTIVESTATECODE);
                if (average == null) {
                    subjectsList.get(i).setAverage(new BigDecimal("0.000"));
                } else {
                    subjectsList.get(i).setAverage(average);
                }
                // 获取选修人数
                List<Score> scoreList = scoreRepository.findBySubjectIdAndStateNot(subjectsList.get(i).getId(), GradeServiceImpl.DELETESTATECODE);
                subjectsList.get(i).setNumber(scoreList.size());
            }// end for
        }
        return subjectsList;
    }

    /**
     * 添加学科信息
     */
    @Override
    @Transactional
    public void saveSubject(SubjectVo subjectVo) {

        // 学科初始化
        subjectVo.setState(GradeServiceImpl.ACTIVESTATECODE);
        // Vo 转 PO
        Subject subject = subjectVoturnSubjectService.apply(subjectVo);
        System.out.println("进入添加学科method！");
        // 校验学科名称是否重复
        if (subjectRepository.findByNameAndStateNot(subject.getName(), GradeServiceImpl.DELETESTATECODE) == null) {// 学科无重复
            // 保存至数据库
            subject.setAverage(new BigDecimal("0.000"));
            subjectRepository.save(subject);
        }
    }

    /**
     * 修改学科信息
     */
    @Override
    @Transactional
    public void updateSubject(SubjectVo subjectVo) {
        // Vo转PO
        Subject subject = subjectVoturnSubjectService.apply(subjectVo);
        // 通过学科ID获取该学科
        Subject subjectTemp = subjectRepository.findByIdAndStateNot(subject.getId(), GradeServiceImpl.DELETESTATECODE);
        // 修改学科名称信息
        subjectTemp.setName(subject.getName());
        // 更新数据库
        subjectRepository.save(subjectTemp);
    }

    /**
     * 删除学科
     */
    @Override
    @Transactional
    public void deleteSubject(SubjectVo subjectVo) {
        // Vo转PO
        Subject subject = subjectVoturnSubjectService.apply(subjectVo);
        // 通过学科ID获取该学科
        Subject subjectTemp = subjectRepository.findByIdAndStateNot(subject.getId(), GradeServiceImpl.DELETESTATECODE);
        // 修改学科状态信息
        subjectTemp.setState(GradeServiceImpl.DELETESTATECODE);
        // 删除中间表Score相应记录
        UpdateScoreVo updateScoreVo = new UpdateScoreVo();
        updateScoreVo.setId(subjectTemp.getId());
        updateScoreVo.setType("deletSubject");
        scoreSerivce.deleteScore(updateScoreVo);
        // 更新数据库
        subjectRepository.save(subjectTemp);
    }

    /**
     * 获取学生未选课程
     */
    private List<SubjectVo> getSubject(Integer id) {
        List<Score> scoreList = scoreRepository.findByStudentIdAndStateNot(id, GradeServiceImpl.DELETESTATECODE);// 获取学生已选学科
        List<Subject> subjectsList = subjectRepository.findByStateNot(GradeServiceImpl.DELETESTATECODE);// 获取所有未删除学科
        // 去掉已选择的课程
        for (Score score : scoreList) {
            for (int i = 0; i < subjectsList.size(); i++) {
                if (score.getSubjectId() == subjectsList.get(i).getId()) {
                    subjectsList.remove(i);
                    break;
                }
            }
        }
        List<SubjectVo> subjectVoList = new SubjectListTurnSubjectVoList().apply(subjectsList);
        return subjectVoList;
    }

    /**
     * 增加选修课程
     */
    @Override
    @Transactional
    public void addSubject(ScoreVo scoreVo) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        scoreVo.setState(GradeServiceImpl.ACTIVESTATECODE);
        int studentId = (Integer) session.getAttribute("studentId_goAddSubject");
        scoreVo.setStudentId(studentId);
        scoreVo.setScore(new BigDecimal("0.000"));
        // Vo 转 PO
        Score score = scoreVoTurnScoreService.apply(scoreVo);
        // 数据库更新
        scoreRepository.save(score);
    }
}
