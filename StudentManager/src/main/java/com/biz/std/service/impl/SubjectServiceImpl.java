package com.biz.std.service.impl;

import com.biz.std.model.Score;
import com.biz.std.model.Student;
import com.biz.std.model.Subject;
import com.biz.std.repository.ScoreRepository;
import com.biz.std.repository.StudentRepository;
import com.biz.std.repository.SubjectRepository;
import com.biz.std.repository.specification.SubjectPagingFilterSpecification;
import com.biz.std.service.SubjectService;
import com.biz.std.util.conversion.SubjectListTurnSubjectVoList;
import com.biz.std.util.conversion.SubjectVoturnSubject;
import com.biz.std.vo.PageResult;
import com.biz.std.vo.PageVo;
import com.biz.std.vo.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * by zale on 2017/5/16.
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;// 注入
    @Autowired
    private SubjectVoturnSubject subjectVoturnSubjectService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private Subject subject;
    @Autowired
    private Student student;
    @Autowired
    private Subject subjectTemp; // 接收数据库返回的学科对象
    @Autowired
    private List<Subject> subjectsList; // 接收数据库查询的学科列表
    @Autowired
    private List<Score> scoretsList; // 接收数据库查询的学科列表

    /**
     * 跳转到学科管理页
     */
    @Override
    public PageResult<SubjectVo> goSubjectManager(PageVo pageVo) {

        Pageable pageable = new PageRequest(pageVo.getPageIndex() - 1, pageVo.getPageSize());
        Page<Subject> page = subjectRepository.findAll(new SubjectPagingFilterSpecification(), pageable);
        subjectsList = page.getContent();// 当前页显示的学科
        // 处理学科选修人数 平均分
        subjectsList = this.initSubjectNum(subjectsList);
        // Turn
        List<SubjectVo> subjectVoList = new SubjectListTurnSubjectVoList().apply(subjectsList);
        return new PageResult<SubjectVo>(pageVo.getPageIndex(), subjectVoList.size(), subjectVoList, page.getTotalPages());
    }

    /**
     * 处理学科选修人数 平均分
     */
    private List<Subject> initSubjectNum(List<Subject> subjectsList) {
        for (int i = 0; i < subjectsList.size(); i++) {
            int sid = subjectsList.get(i).getId();
            // 查询选修人数
            scoretsList = scoreRepository.countStudentsbySubjectId(sid);
            subjectsList.get(i).setNumber(scoretsList.size());// 选修人数
            // 平均分
            int sum = 0;
            for (Score score : scoretsList) {
                sum += score.getScore();
            }
            if (scoretsList.size() != 0) {
                subjectsList.get(i).setAverage(sum / scoretsList.size());
            }
        }
        return subjectsList;
    }

    /**
     * 添加学科信息
     */
    @Override
    public void saveSubject(SubjectVo subjectVo) {

        // 学科初始化
        subjectVo.setNumber(0);
        subjectVo.setAverage(0);
        subjectVo.setState(GradeServiceImpl.ACTIVESTATECODE);
        // Vo 转 PO
        subject = subjectVoturnSubjectService.apply(subjectVo);
        // 校验学科名称是否重复
        int counts = subjectRepository.checkSubjectByName(subject.getName());
        if (counts < 1 && subjectVo.getName() != null) {// 学科无重复
            // 保存至数据库
            subjectRepository.save(subject);
        }
    }

    /**
     * 修改学科信息
     */
    @Override
    public void updateSubject(SubjectVo subjectVo) {
        this.updateOrDeleteSubject(subjectVo, "update");
    }

    /**
     * 删除学科
     */
    @Override
    public void deleteSubject(SubjectVo subjectVo) {
        this.updateOrDeleteSubject(subjectVo, "delete");

        // 更新学生选课数
        scoretsList = scoreRepository.findScorebySubjectId(subjectVo.getId());
        for (Score score : scoretsList) {
            student = studentRepository.findOne(score.getStudentId());
            student.setSub_num(student.getSub_num() - 1);
            studentRepository.save(student);
        }
        // 更改分数表记录状态
        scoretsList = scoreRepository.countStudentsbySubjectId(subjectVo.getId());
        for (Score score : scoretsList) {
            score.setState(GradeServiceImpl.DELETESTATECODE);
            scoreRepository.save(score);
        }
    }

    /**
     * 处理修改与删除学科
     */
    private void updateOrDeleteSubject(SubjectVo subjectVo, String flag) {
        // Vo转PO
        subject = subjectVoturnSubjectService.apply(subjectVo);
        // 通过学科ID获取该学科
        subjectTemp = subjectRepository.findSubjectById(subject.getId());
        if ("update".equals(flag)) {// update
            // 修改学科名称信息
            subjectTemp.setName(subject.getName());
        } else if ("delete".equals(flag)) {// delete
            // 修改学科状态信息
            subjectTemp.setState(GradeServiceImpl.DELETESTATECODE);
        }
        // 更新数据库
        subjectRepository.save(subjectTemp);
    }
}
