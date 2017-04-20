package zale.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import redis.clients.jedis.Jedis;
import zale.entity.StudentEntity;
import zale.utils.RedisManagerUtil;
import zale.utils.SerializeUtil;

/**
 * Created with Eclipse.
 * User: ������.
 * Date: 2017��4��19��.
 * Time: ����2:15:45.
 * Explain:
 */
public class UpdateStudentServletService {
	private Jedis jedis = null;

	/*
	 * add/update student
	 */
	public void addStudent(HttpServletRequest request, HttpServletResponse resp) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		jedis = RedisManagerUtil.connectionRedis();
		// ����ѧ����Ϣ�ı�ʶ
		String updateFlag = request.getParameter("updateFlag");
		// ��ȡ�������ı�����,���ݱ��е�name��ȡ����д��ֵ
		// ʹ��beanUtil����װstudent
		StudentEntity student = new StudentEntity();

		String birth = request.getParameter("birthdayitem");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// ��װѧ�� ��������
			student.setBirthday(format.parse(birth));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {// ʹ��BeanUtils ��װStudent����
			BeanUtils.populate(student, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// �ж��Ƿ�Ϊ����ѧ����Ϣ
		if (!"YES".equals(updateFlag)) {// ���ѧ��flag
			// ���jedis����

			// IDΨһ��ʶ�����������ѧ������
			String studentNum = "0";
			int num = 0;
			if (jedis.exists("studentNum")) {// ���ݿ���ڴ�Key
				studentNum = jedis.get("studentNum");
				num = Integer.parseInt(studentNum);
			}

			num++;// ѧ��������һ
			studentNum = num + "";
			jedis.set("studentNum", studentNum);
			student.setId("student:" + studentNum);// ����Student���� ID
			System.out.println("OK!");

		}
		// �洢 ID Avg ���������򡢷�ҳ��
		jedis.zadd("studentAvg", (double) student.getAvgscore(), student.getId());
		// ���������л��洢
		jedis.set(student.getId().getBytes(), SerializeUtil.serialize(student));

		// �ر�Redis����
		RedisManagerUtil.closeRedis();
		try {
			request.getRequestDispatcher("/InitStudentInfo").forward(request, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
