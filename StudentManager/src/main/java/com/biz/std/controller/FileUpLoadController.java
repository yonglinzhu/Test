package com.biz.std.controller;

import com.biz.std.service.FileUpLoadService;
import com.biz.std.service.StudentService;
import com.biz.std.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:文件上传下载controller
 * -
 * Author: Evan  Date: 2017/6/4
 * Email: yonglin.zhu@biz-united.com.cn
 */
@Controller
@RequestMapping("/")
public class FileUpLoadController {
    @Autowired
    private FileUpLoadService fileUpLoadService;

    /**
     * 图片上传
     */
    @RequestMapping("/uploadPicture")
    public String uploadPicture(StudentVo studentVo, MultipartHttpServletRequest request) throws IOException {
        fileUpLoadService.uploadPicture(studentVo, request);
        return "redirect:/goStudentManager";
    }

    /**
     * 图片显示
     */
    @RequestMapping("/pictureView")
    public void pictureView(StudentVo studentVo, HttpServletResponse response) throws IOException {
        fileUpLoadService.pictureView(studentVo, response);
    }
}
