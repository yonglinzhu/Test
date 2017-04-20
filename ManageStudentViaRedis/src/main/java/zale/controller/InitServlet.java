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
 * User: ������.
 * Date: 2017��4��18��.
 * Time: ����5:47:50.
 * Explain:����ҳ����ʾ������
 */
@WebServlet(name = "InitServlet", urlPatterns = "/InitStudentInfo")
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);// get������doPpost��������
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InitServletService initServletService = new InitServletService();
		initServletService.initDate(req, resp);
	}
}
