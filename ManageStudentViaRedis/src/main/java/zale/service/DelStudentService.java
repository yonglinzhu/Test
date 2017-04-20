package zale.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;
import zale.utils.RedisManagerUtil;

/**
 * Created with Eclipse.
 * User: 朱永林.
 * Date: 2017年4月19日.
 * Time: 下午11:24:35.
 * Explain:
 */
public class DelStudentService {
	private Jedis jedis = null;

	/*
	 * delete student
	 */
	public void delStudent(HttpServletRequest req, HttpServletResponse resp) {
		jedis = RedisManagerUtil.connectionRedis();
		// 获取要删除的学生ID
		String id = req.getParameter("id");
		System.out.println("id:" + id);

		// 删除redjs 中 sortset类型 Key=studentAvg 的member（id）
		jedis.zrem("studentAvg", id);

		// 删除redjs 中 String类型 Key=id
		jedis.del(id);// 删除学生对象

		// 关闭Redis连接
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
