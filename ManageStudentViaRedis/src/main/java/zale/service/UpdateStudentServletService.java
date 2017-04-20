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
 * User: 朱永林.
 * Date: 2017年4月19日.
 * Time: 下午2:15:45.
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
		// 更新学生信息的标识
		String updateFlag = request.getParameter("updateFlag");
		// 获取传过来的表单数据,根据表单中的name获取所填写的值
		// 使用beanUtil来封装student
		StudentEntity student = new StudentEntity();

		String birth = request.getParameter("birthdayitem");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 封装学生 生日属性
			student.setBirthday(format.parse(birth));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {// 使用BeanUtils 封装Student对象
			BeanUtils.populate(student, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// 判断是否为更新学生信息
		if (!"YES".equals(updateFlag)) {// 添加学生flag
			// 获得jedis对象

			// ID唯一标识（根据总添加学生数）
			String studentNum = "0";
			int num = 0;
			if (jedis.exists("studentNum")) {// 数据库存在此Key
				studentNum = jedis.get("studentNum");
				num = Integer.parseInt(studentNum);
			}

			num++;// 学生人数加一
			studentNum = num + "";
			jedis.set("studentNum", studentNum);
			student.setId("student:" + studentNum);// 设置Student对象 ID
			System.out.println("OK!");

		}
		// 存储 ID Avg （便于排序、分页）
		jedis.zadd("studentAvg", (double) student.getAvgscore(), student.getId());
		// 将对象序列化存储
		jedis.set(student.getId().getBytes(), SerializeUtil.serialize(student));

		// 关闭Redis连接
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
