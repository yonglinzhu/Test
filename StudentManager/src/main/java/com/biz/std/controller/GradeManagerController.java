package com.biz.std.controller;

import com.biz.std.service.GradeService;
import com.biz.std.vo.GradeVo;
import com.biz.std.vo.PageResult;
import com.biz.std.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 班级信息管理controller
 * -
 * by zale on 2017/5/10.
 */
@Controller
@RequestMapping("/")
public class GradeManagerController {

    @Autowired
    private GradeService gradeService;//注入 GradeService

    /**
     * 转到班级信息页
     */
    @RequestMapping("/goGradeManager")
    public ModelAndView goGradeManager(PageVo pageVo) {
        PageResult<GradeVo> pageResult = gradeService.goGradeManager(pageVo);
        return new ModelAndView("gradeManager").addObject("pageResult", pageResult);
    }

    /**
     * 添加班级信息
     */
    @RequestMapping("/saveGrade")
    public String saveGrade(@ModelAttribute GradeVo gradeVo) {
        gradeService.saveGrade(gradeVo);
        return "redirect:/goGradeManager";
    }

    /**
     * 修改班级信息
     */
    @RequestMapping("/updateGrade")
    public String updateGrade(@ModelAttribute GradeVo gradeVo) {
        gradeService.updateGrade(gradeVo);
        return "redirect:/goGradeManager";
    }

    /**
     * 删除班级
     */
    @RequestMapping("/deleteGrade")
    public String deleteGrade(@ModelAttribute GradeVo gradeVo) {
        gradeService.deleteGradeById(gradeVo.getId());
        return "redirect:/goGradeManager";
    }

}
