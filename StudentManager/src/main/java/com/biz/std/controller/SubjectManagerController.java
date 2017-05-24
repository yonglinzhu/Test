package com.biz.std.controller;

import com.biz.std.model.Subject;
import com.biz.std.service.SubjectService;
import com.biz.std.vo.PageResult;
import com.biz.std.vo.PageVo;
import com.biz.std.vo.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        PageResult<Subject> pageResult = subjectService.goSubjectManager(pageVo);
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

}
