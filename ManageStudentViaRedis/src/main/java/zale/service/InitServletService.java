package zale.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.Request;

import redis.clients.jedis.Jedis;
import zale.entity.StudentEntity;
import zale.utils.RedisManagerUtil;
import zale.utils.SerializeUtil;

/**
 * Created with Eclipse.
 * User: 朱永林.
 * Date: 2017年4月18日.
 * Time: 下午5:54:07.
 * Explain:
 */
public class InitServletService {

	public static long PAGETOTAL = 10;
	private Set<String> studentSet = null;
	private List<StudentEntity> sutdentlist = null;
	private Jedis jedis = null;

	/*
	 * 初始学生信息，并显示前端页面
	 */
	public void initDate(HttpServletRequest req, HttpServletResponse resp) {
		// 获取session对象
		HttpSession session = req.getSession();
		System.out.println("OK!");
		jedis = RedisManagerUtil.connectionRedis();

		long pagetotal = PagingDateService.getPageTotal();
		int pageNum = 1;
		boolean pagingFlag = false;// 标记是否为分页操作
		if (req.getParameter("pageNum") != null) {// 分页操作
			System.out.println("分页操作！");
			String pageNumItem = (String) req.getParameter("pageNum");

			// 判断页码是否为整数
			Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(pageNumItem);
			if (mer.find()) {
				pageNum = Integer.parseInt(pageNumItem);
			}
			// 处理页码上限（非法页码）
			if (pageNum > pagetotal) {
				pageNum = (int) pagetotal;// 跳转最大页码为实际最大页码
			} else if (pageNum < 1) {// 处理页码下限（非法页码）
				pageNum = 1;
			}
			pagingFlag = true;
		}

		if (!pagingFlag) {// 分页
			studentSet = jedis.zrevrange("studentAvg", 0, PAGETOTAL - 1);
		} else {// 初始化页面
			studentSet = jedis.zrevrange("studentAvg", (pageNum - 1) * PAGETOTAL,
					((pageNum - 1) * PAGETOTAL) + (PAGETOTAL - 1));
		}

		sutdentlist = new ArrayList<StudentEntity>();
		// 反序列化（获取student对象并存入List）
		for (String key : studentSet) {
			byte[] value = jedis.get(key.getBytes());
			Object object = SerializeUtil.unserialize(value);
			StudentEntity studentTemp = (StudentEntity) object;
			sutdentlist.add(studentTemp);

		}

		// 传值到前端jsp
		req.setAttribute("studentlist", sutdentlist);
		req.setAttribute("pagetotal", pagetotal);
		req.setAttribute("pageNum", pageNum);
		// update studentInfo 停留当前页面
		session.setAttribute("sessionPageNum", pageNum);
		System.out.println(pageNum + ":" + pagetotal);
		try {
			req.getRequestDispatcher("/studentInfoManager.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
