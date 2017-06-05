package com.biz.std.service;

import com.biz.std.vo.StudentVo;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 * -
 * Author: Evan  Date: 2017/6/4
 * Email: yonglin.zhu@biz-united.com.cn
 */
public interface FileUpLoadService {

    void uploadPicture(StudentVo studentVo, MultipartHttpServletRequest request) throws IOException;// 图片上传

    void pictureView(StudentVo studentVo, HttpServletResponse response) throws IOException;// 图片显示

}
