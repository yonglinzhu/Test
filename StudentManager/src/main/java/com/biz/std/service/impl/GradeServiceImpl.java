package com.biz.std.service.impl;

import com.biz.std.model.*;
import com.biz.std.repository.GradeRepository;
import com.biz.std.repository.ScoreRepository;
import com.biz.std.repository.StudentRepository;
import com.biz.std.repository.specification.GradePagingFilterSpecification;
import com.biz.std.repository.specification.StudentPagingFilterSpecification;
import com.biz.std.service.GradeService;
import com.biz.std.vo.GradeVo;
import com.biz.std.vo.PageResult;
import com.biz.std.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * by zale on 2017/5/10.
 */
@Service("classService")
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;//注入StudentRepository
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private GradeVoturnGradeServiceImpl gradeVoturnGradeService;
    @Autowired
    private BaseStudentNumSerivceImpl baseStudentNumSerivce;
    @Autowired
    private Grade grade;
    @Autowired
    private Grade gradeTemp; // 接收数据库返回的班级对象
    @Autowired
    private BaseStudentNum baseStudentNumPO;
    @Autowired
    private List<Grade> gradeList;
    @Autowired
    private List<Score> scoreList;
    @Autowired
    private List<Student> studentList; // 接收数据库查询的学生列表
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    /**
     * 状态码值
     */
    public static final String DELETESTATECODE = "0";// 删除状态码
    public static final String ACTIVESTATECODE = "1";// 正在使用状态码

    /**
     * 分页值
     */
    public static final int ENDPADING = 9;// 分页 END

    /**
     * 跳转至班级信息也 并分页显示班级信息
     */
    @Override
    public PageResult<Grade> goGradeManager(PageVo pageVo) {

        Pageable pageable = new PageRequest(pageVo.getPageIndex() - 1, pageVo.getPageSize());
        Page<Grade> page = gradeRepository.findAll(new GradePagingFilterSpecification(), pageable);
        gradeList = page.getContent();// 当前页显示的班级
        // 班级平均分处理
        this.gradeAverageProcessing(gradeList);
        return new PageResult<Grade>(pageVo.getPageIndex(), gradeList.size(), gradeList, page.getTotalPages());
    }

    /**
     * 班级平均分处理方法
     */
    private List<Grade> gradeAverageProcessing(List<Grade> gradeList) {
        // 班级平均分处理
        // 获取班级的所有学生
        if (gradeList != null && gradeList.size() != 0) {
            double sumScoreForGrade = 0;// 该班级总分数
            double sumScoreForStudent = 0;// 学生总分数
            for (int i = 0; i < gradeList.size(); i++) {
                studentList = studentRepository.findStudentByGrade_id(gradeList.get(i).getId());
                if (studentList != null && studentList.size() != 0) {
                    for (Student s : studentList) { // 一个班级
                        scoreList = scoreRepository.findScoreByStudentId(s.getId());
                        if (scoreList != null && scoreList.size() != 0) {
                            for (Score score : scoreList) {// 一个学生的总分数
                                sumScoreForStudent += score.getScore();
                            }
                            sumScoreForGrade += sumScoreForStudent;
                            sumScoreForStudent = 0;//初始化
                        }
                    }
                    sumScoreForGrade = sumScoreForGrade / studentList.size();// 平均分

                    gradeList.get(i).setAverage(sumScoreForGrade);
                    sumScoreForGrade = 0;// 初始化
                } else { // 班级没有学生
                    gradeList.get(i).setAverage(0);
                }

            }
        }// end if
        return gradeList;
    }

    /**
     * 保存班级
     */
    @Override
    public void saveGrade(GradeVo gradeVo) {

        gradeVo.setState(ACTIVESTATECODE);// 初始化班级状态为：正在使用
        gradeVo.setAverage(0);// 初始化班级平均分为：0
        // Vo转PO
        grade = gradeVoturnGradeService.apply(gradeVo);
        gradeRepository.save(grade);
        this.initBaseStudentNum(grade.getClassname());
    }

    /**
     * 修改班级信息
     */
    @Override
    public void updateGrade(GradeVo gradeVo) {
        // Vo转PO
        grade = gradeVoturnGradeService.apply(gradeVo);
        // 获取该班级
        gradeTemp = gradeRepository.findOne(grade.getId());
        // 修改班级信息
        gradeTemp.setClassname(grade.getClassname());
        // 更新数据库
        gradeRepository.save(gradeTemp);
    }

    /**
     * 删除班级
     */
    @Override
    public void deleteGradeById(Integer id) {
        // 获取该班级
        gradeTemp = gradeRepository.findOne(id);
        // 修改班级状态（改为删除状态）
        gradeTemp.setState(DELETESTATECODE);
        // 更新数据库
        gradeRepository.save(gradeTemp);
    }

    /**
     * 初始化班级基础学号
     */
    private void initBaseStudentNum(String gradeName) {
        // 初始化班级基础学号
        int baseStudentNum = baseStudentNumSerivce.checkBaseStudentNum();
        if (baseStudentNum == 0) {// 初始化班级基础学号
            baseStudentNum = 2017001;// 初始化基础学号赋值
            baseStudentNumPO.setBaseNum(baseStudentNum + "");
            baseStudentNumSerivce.saveBasseStudentNum(baseStudentNumPO);
        }
        // 获取上一个班级的基础学号
        baseStudentNumPO = baseStudentNumSerivce.findBaseStudentNumBylastDate();
        // 基础学号增一
        baseStudentNum = Integer.parseInt(baseStudentNumPO.getBaseNum()) + 1;
        // 获取班级ID
        int gradeID = gradeRepository.findGradeIDByGradeName(gradeName);
        // 赋值
        baseStudentNumPO.setGradeId(gradeID);
        baseStudentNumPO.setBaseNum(baseStudentNum + "");
        baseStudentNumPO.setId(0);
        baseStudentNumPO.setIdentification("1001");
        // 更新数据库
        baseStudentNumSerivce.saveBasseStudentNum(baseStudentNumPO);
    }

}
