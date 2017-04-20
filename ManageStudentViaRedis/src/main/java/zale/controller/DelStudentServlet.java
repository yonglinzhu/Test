package zale.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zale.service.DelStudentService;

/**
 * Created with Eclipse.
 * User: ������.
 * Date: 2017��4��19��.
 * Time: ����11:23:14.
 * Explain:ɾ��ѧ�� Servlet
 */
@WebServlet(name = "DelStudentServlet", urlPatterns = "/delStudent")
public class DelStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);// get������doPpost��������
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		DelStudentService delStudentService = new DelStudentService();
		delStudentService.delStudent(request, resp);
	}
}
