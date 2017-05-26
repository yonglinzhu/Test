package com.biz.std.service.impl;

import com.biz.std.model.*;
import com.biz.std.repository.*;
import com.biz.std.repository.specification.StudentPagingFilterSpecification;
import com.biz.std.service.StudentService;
import com.biz.std.util.conversion.*;
import com.biz.std.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * by zale on 2017/5/8.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;//注入StudentRepository
    @Autowired
    private GradeRepository gradeRepository;//注入GradeRepository
    @Autowired
    private BaseStudentNumRepository baseStudentNumRepository;//注入BaseStudentNumRepository
    @Autowired
    private SubjectRepository subjectRepository;//注入SubjectRepository
    @Autowired
    private ScoreRepository scoreRepository;//注入ScoreRepository
    @Autowired
    private StudentVoturnStudent studentVoturnStudentService;
    @Autowired
    private ScoreVoTurnScore scoreVoTurnScoreService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    @Autowired
    private Student student;
    @Autowired
    private Student studentTemp;
    @Autowired
    private Score score;
    @Autowired
    private Score scoreTemp;
    @Autowired
    private Grade grade;
    @Autowired
    private BaseStudentNum baseStudentNum;
    @Autowired
    private List<Student> studentList; // 接收数据库查询的学生列表
    @Autowired
    private List<StudentVo> studentVoList; // 传到controller层的学生列表
    @Autowired
    private List<Grade> gradeList;// 接收数据库查询的班级列表
    @Autowired
    private List<Score> scoreList;// 接收数据库查询的学科列表
    @Autowired
    private List<Subject> subjectsList;// 接收数据库查询的班级列表

    /**
     * 跳转至学生信息页 并分页显示学生信息
     */
    @Override
    public PageResult<StudentVo> goStudentManager(PageVo pageVo) {

        Pageable pageable = new PageRequest(pageVo.getPageIndex() - 1, pageVo.getPageSize());
        Page<Student> page = studentRepository.findAll(new StudentPagingFilterSpecification(), pageable);
        studentList = page.getContent();// 当前页显示的学生
        // 学生平均分处理方法
        studentList = studentAverageProcessing(studentList);
        // Turn
        studentVoList = new StudentListTurnStudentVoList().apply(studentList);
        // 前台传值
        return new PageResult<StudentVo>(pageVo.getPageIndex(), studentVoList.size(), studentVoList, page.getTotalPages());
    }

    /**
     * 获取所有有效班级信息
     */
    @Override
    public List<GradeVo> findGradeList() {
        gradeList = gradeRepository.findAll();
        List<GradeVo> gradeVoList = new GradeListTurnGradeVoList().apply(gradeList);
        return gradeVoList;
    }

    /**
     * 保存学生信息
     */
    @Override
    public void saveStudent(StudentVo studentVo) {

        studentVo.setState(GradeServiceImpl.ACTIVESTATECODE);
        // 初始化学号
        studentVo = this.initStudentnumber(studentVo);
        // Vo转PO
        student = studentVoturnStudentService.apply(studentVo);
        studentRepository.save(student);
        // 更新班级人数
        updateGradeNum(student.getGrade_id());

    }

    /**
     * 更新班级人数
     */
    private void updateGradeNum(int gid) {
        // 更新班级人数
        int count = studentRepository.countStudentByGrade_id(gid);
        grade = gradeRepository.findOne(gid);
        grade.setNumber(count);
        gradeRepository.save(grade);
    }

    /**
     * 修改学生信息
     */
    @Override
    public void updateStudent(StudentVo studentVo) {
        this.updateOrDeleteSubject(studentVo, "update");
    }

    /**
     * 删除该学生
     */
    @Override
    public void deleteStudent(StudentVo studentVo) {
        this.updateOrDeleteSubject(studentVo, "delete");
    }

    /**
     * 处理修改与删除学生
     */
    private void updateOrDeleteSubject(StudentVo studentVo, String flag) {
        // Vo转PO
        student = studentVoturnStudentService.apply(studentVo);
        // 通过学生ID获取该学生信息
        studentTemp = studentRepository.findOne(student.getId());
        if ("update".equals(flag)) {// update
            // 修改学生名称信息
            studentTemp.setName(student.getName());
            studentTemp.setSex(student.getSex());
            studentTemp.setBirthday(student.getBirthday());
            studentTemp.setGrade_id(student.getGrade_id());
        } else if ("delete".equals(flag)) {// delete
            // 修改学生状态信息
            studentTemp.setState(GradeServiceImpl.DELETESTATECODE);
        }
        // 更新数据库
        studentRepository.save(studentTemp);
        if ("delete".equals(flag)) {
            // 更新班级人数
            updateGradeNum(studentTemp.getGrade_id());
        }
    }

    /**
     * 处理学生学号
     */
    private StudentVo initStudentnumber(StudentVo studentVo) {
        // 获取班级基础学号
        baseStudentNum = baseStudentNumRepository.findBaseStudentNumByGradeId(studentVo.getGrade_id());
        String baseNum = baseStudentNum.getBaseNum();
        int identification = Integer.parseInt(baseStudentNum.getIdentification()) + 1;
        studentVo.setNumber(baseNum + identification);// 初始化学生学号
        // baseStudentNum 更新
        baseStudentNum.setIdentification(identification + "");
        baseStudentNumRepository.save(baseStudentNum);

        return studentVo;
    }

    /**
     * 跳转至选课页
     */
    @Override
    public List<SubjectVo> goAddSubject(StudentVo studentVo) {
        // 存储学生ID - [增加选修课程时用]
        session.putValue("studentId", studentVo.getId());
        return this.getSubject(studentVo.getId());

    }

    /**
     * 获取学生未选课程
     */
    private List<SubjectVo> getSubject(int id) {

        scoreList = scoreRepository.findScoreByStudentId(id);// 获取已选的学科ID
        subjectsList = subjectRepository.getAllSubjects();// 获取系统所有学科
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
    public void addSubject(ScoreVo scoreVo) {
        scoreVo.setState(GradeServiceImpl.ACTIVESTATECODE);
        int sutdentId = (Integer) session.getAttribute("studentId");
        scoreVo.setStudentId(sutdentId);
        scoreVo.setScore(0);
        // Vo 转 PO
        score = scoreVoTurnScoreService.apply(scoreVo);
        // 数据库更新
        scoreRepository.save(score);
        // 更新学生选课数
        scoreList = scoreRepository.findScoreByStudentId(sutdentId);
        student = studentRepository.findOne(sutdentId);
        student.setSub_num(scoreList.size());
        studentRepository.save(student);
    }

    /**
     * 跳转至分数录入页
     */
    @Override
    public List<ScoreVo> goEntryScore(StudentVo studentVo) {
        return this.entryScoreView(studentVo.getId());
    }

    private List<ScoreVo> entryScoreView(int id) {
        // 获取该学生已选的课程
        scoreList = scoreRepository.findScoreByStudentId(id);
        List<ScoreVo> scoreVoList = new ScoreListTurnScoreVoList().apply(scoreList);
        return scoreVoList;
    }

    /**
     * 分数录入
     */
    @Override
    public void entryScore(ScoreVo scoreVo) {
        // Vo 转 PO
        score = scoreVoTurnScoreService.apply(scoreVo);
        // 根据ID查询其所有分数的信息
        scoreTemp = scoreRepository.findScoreById(score.getId());
        // 更新数据
        scoreTemp.setScore(score.getScore());
        // 数据库更新
        scoreRepository.save(scoreTemp);

    }

    /**
     * 学生平均分处理方法
     */
    private List<Student> studentAverageProcessing(List<Student> studentList) {
        // 学生平均分处理
        // 获取学生选择的所有学科
        if (studentList != null && studentList.size() != 0) {
            for (int i = 0; i < studentList.size(); i++) {
                scoreList = scoreRepository.findScoreByStudentId(studentList.get(i).getId());
                double sumScore = 0;// 总分数
                if (scoreList.size() != 0 && scoreList != null) {
                    for (Score s : scoreList) {
                        sumScore += s.getScore();
                    }
                    double averageScore = sumScore / scoreList.size();// 平均分
                    studentList.get(i).setAverage(averageScore);
                } else {
                    studentList.get(i).setAverage(0);
                }
            }
        } else {// end if

        }
        return studentList;
    }

    /**
     * 图片上传
     */
    @Override
    public void uploadPicture(StudentVo studentVo, HttpServletRequest request) throws IOException {

        // 获取支持文件上传的Request对象 MultipartHttpServletRequest
        MultipartHttpServletRequest mtpreq = (MultipartHttpServletRequest) request;
        // 通过 mtpreq 获取文件域中的文件
        MultipartFile file = mtpreq.getFile("file");
        // 通过MultipartFile 对象获取文件的原文件名
        String fileName = file.getOriginalFilename();
        if (fileName != null && !"".equals(fileName)) {
            // 生成一个uuid 的文件名
            UUID randomUUID = UUID.randomUUID();
            // 获取文件的后缀名
            int i = fileName.lastIndexOf(".");
            String uuidName = randomUUID.toString() + fileName.substring(i);

            //获取服务器的路径地址（被上传文件的保存地址）
            String realPath = request.getSession().getServletContext().getRealPath("/file");
            //将路径转化为文件夹 并 判断文件夹是否存在
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            //获取一个文件的保存路径
            String path = realPath + "/" + uuidName;
            // 数据库更新
            student = studentRepository.findOne(studentVo.getId());
            student.setPicture(path);
            studentRepository.save(student);
            try {
                file.transferTo(new File(path));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 图片显示
     */
    @Override
    public void pictureView(StudentVo studentVo, HttpServletResponse response) throws IOException {
        // 通过学生ID获取头像地址
        student = studentRepository.findOne(studentVo.getId());
        String path = student.getPicture();
        if (!"--".equals(path)) {
            //读取本地图片输入流
            FileInputStream inputStream = new FileInputStream(path);
            int i = inputStream.available();
            //byte数组用于存放图片字节数据
            byte[] buff = new byte[i];
            inputStream.read(buff);
            //记得关闭输入流
            inputStream.close();
            //设置发送到客户端的响应内容类型
            response.setContentType("image/*");
            OutputStream out = response.getOutputStream();
            out.write(buff);
            //关闭响应输出流
            out.close();
        }
    }

}
