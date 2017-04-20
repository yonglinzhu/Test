package zale.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zale.service.InitServletService;

/**
 * Created with Eclipse.
 * User: 朱永林.
 * Date: 2017年4月18日.
 * Time: 下午5:47:50.
 * Explain:处理页面显示的内容
 */
@WebServlet(name = "InitServlet", urlPatterns = "/InitStudentInfo")
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);// get请求交由doPpost方法处理
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InitServletService initServletService = new InitServletService();
		initServletService.initDate(req, resp);
	}
}
