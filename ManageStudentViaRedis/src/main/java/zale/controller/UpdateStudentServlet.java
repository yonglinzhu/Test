package zale.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import zale.entity.StudentEntity;
import zale.service.UpdateStudentServletService;
import zale.service.InitServletService;

/**
 * Created with Eclipse.
 * User: 朱永林.
 * Date: 2017年4月19日.
 * Time: 下午1:30:12.
 * Explain:
 */
@WebServlet(name = "UpdateStudentServlet", urlPatterns = "/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);// get请求交由doPpost方法处理
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// 调用service层的addStudent方法
		UpdateStudentServletService updateStudentServletService = new UpdateStudentServletService();
		updateStudentServletService.addStudent(request, resp);
	}
}
