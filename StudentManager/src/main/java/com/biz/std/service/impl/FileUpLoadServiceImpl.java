package com.biz.std.service.impl;

import com.biz.std.model.Student;
import com.biz.std.repository.StudentRepository;
import com.biz.std.service.FileUpLoadService;
import com.biz.std.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Description:
 * -
 * Author: Evan  Date: 2017/6/4
 * Email: yonglin.zhu@biz-united.com.cn
 */
@Service
public class FileUpLoadServiceImpl implements FileUpLoadService{

    @Autowired
    private StudentRepository studentRepository;
    /**
     * 图片上传
     */
    @Override
    public void uploadPicture(StudentVo studentVo, MultipartHttpServletRequest mtpreq) throws IOException {

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
            String realPath = mtpreq.getSession().getServletContext().getRealPath("/file");
            //将路径转化为文件夹 并 判断文件夹是否存在
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            //获取一个文件的保存路径
            String path = realPath + "/" + uuidName;
            // 数据库更新
            Student student = studentRepository.findOne(studentVo.getId());
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
        Student student = studentRepository.findOne(studentVo.getId());
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
