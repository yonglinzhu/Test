package zale.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import zale.utils.RedisManagerUtil;

/**
 * Created with Eclipse.
 * User: ������.
 * Date: 2017��4��19��.
 * Time: ����11:24:35.
 * Explain:
 */
public class DelStudentService {
	private Jedis jedis = null;

	/*
	 * delete student
	 */
	public void delStudent(HttpServletRequest req, HttpServletResponse resp) {
		jedis = RedisManagerUtil.connectionRedis();
		// ��ȡҪɾ����ѧ��ID
		String id = req.getParameter("id");
		System.out.println("id:" + id);

		// ɾ��redjs �� sortset���� Key=studentAvg ��member��id��
		jedis.zrem("studentAvg", id);

		// ɾ��redjs �� String���� Key=id
		jedis.del(id);// ɾ��ѧ������

		// �ر�Redis����
		RedisManagerUtil.closeRedis();
		try {
			req.getRequestDispatcher("/InitStudentInfo").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
