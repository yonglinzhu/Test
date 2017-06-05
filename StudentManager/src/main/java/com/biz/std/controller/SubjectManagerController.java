package com.biz.std.controller;

import com.biz.std.service.SubjectService;
import com.biz.std.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 学科信息管理controller层
 *
 * by zale on 2017/5/10.
 */
@Controller
@RequestMapping("/")
public class SubjectManagerController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 转到学科信息页
     */
    @RequestMapping("/goSubjectManager")
    public ModelAndView goSubjectManager(PageVo pageVo) {
        PageResult<SubjectVo> pageResult = subjectService.goSubjectManager(pageVo);
        return new ModelAndView("subjectManager").addObject("pageResult", pageResult);
    }

    /**
     * 添加学科信息
     */
    @RequestMapping("/saveSubject")
    public String saveSubject(@ModelAttribute SubjectVo subjectVo) {
        subjectService.saveSubject(subjectVo);
        return "redirect:/goSubjectManager";
    }

    /**
     * 修改学科信息
     */
    @RequestMapping("/updateSubject")
    public String updateSubject(@ModelAttribute SubjectVo subjectVo) {
        subjectService.updateSubject(subjectVo);
        return "redirect:/goSubjectManager";
    }

    /**
     * 删除学科信息
     */
    @RequestMapping("/deleteSubject")
    public String deleteSubject(@ModelAttribute SubjectVo subjectVo) {
        subjectService.deleteSubject(subjectVo);
        return "redirect:/goSubjectManager";
    }

    /**
     * 跳转至选课页
     */
    @RequestMapping("/goAddSubject")
    public ModelAndView goAddSubject(@ModelAttribute StudentVo studentVo) {
        List<SubjectVo> subjectVoList = subjectService.goAddSubject(studentVo);
        return new ModelAndView("addSubject").addObject("subjectsList", subjectVoList);
    }

    /**
     * 选修该课程
     */
    @RequestMapping("/addSubject")
    public String addSubject(@ModelAttribute ScoreVo scoreVo) {
        subjectService.addSubject(scoreVo);
        return "redirect:/goAddSubject";
    }


}
