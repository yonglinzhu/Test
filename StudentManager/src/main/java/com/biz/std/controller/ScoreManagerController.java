package com.biz.std.controller;

import com.biz.std.service.ScoreSerivce;
import com.biz.std.vo.ScoreVo;
import com.biz.std.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Description:分数录入controller
 * -
 * Author: Evan  Date: 2017/6/4
 * Email: yonglin.zhu@biz-united.com.cn
 */
@Controller
@RequestMapping("/")
public class ScoreManagerController {
    @Autowired
    private ScoreSerivce scoreSerivce;
    /**
     * 跳转至分数录入页
     */
    @RequestMapping("/goEntryScore")
    public ModelAndView goEntryScore(@ModelAttribute StudentVo studentVo) {
        List<ScoreVo> scoreVoList = scoreSerivce.goEntryScore(studentVo);
        return new ModelAndView("entryScore").addObject("scoreList", scoreVoList);
    }

    /**
     * 分数录入
     */
    @RequestMapping("/entryScore")
    public String entryScore(@ModelAttribute ScoreVo scoreVo) {
        scoreSerivce.entryScore(scoreVo);
        return "redirect:/goEntryScore";
    }
}
