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
 * User: ������.
 * Date: 2017��4��18��.
 * Time: ����5:54:07.
 * Explain:
 */
public class InitServletService {

	public static long PAGETOTAL = 10;
	private Set<String> studentSet = null;
	private List<StudentEntity> sutdentlist = null;
	private Jedis jedis = null;

	/*
	 * ��ʼѧ����Ϣ������ʾǰ��ҳ��
	 */
	public void initDate(HttpServletRequest req, HttpServletResponse resp) {
		// ��ȡsession����
		HttpSession session = req.getSession();
		System.out.println("OK!");
		jedis = RedisManagerUtil.connectionRedis();

		long pagetotal = PagingDateService.getPageTotal();
		int pageNum = 1;
		boolean pagingFlag = false;// ����Ƿ�Ϊ��ҳ����
		if (req.getParameter("pageNum") != null) {// ��ҳ����
			System.out.println("��ҳ������");
			String pageNumItem = (String) req.getParameter("pageNum");

			// �ж�ҳ���Ƿ�Ϊ����
			Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(pageNumItem);
			if (mer.find()) {
				pageNum = Integer.parseInt(pageNumItem);
			}
			// ����ҳ�����ޣ��Ƿ�ҳ�룩
			if (pageNum > pagetotal) {
				pageNum = (int) pagetotal;// ��ת���ҳ��Ϊʵ�����ҳ��
			} else if (pageNum < 1) {// ����ҳ�����ޣ��Ƿ�ҳ�룩
				pageNum = 1;
			}
			pagingFlag = true;
		}

		if (!pagingFlag) {// ��ҳ
			studentSet = jedis.zrevrange("studentAvg", 0, PAGETOTAL - 1);
		} else {// ��ʼ��ҳ��
			studentSet = jedis.zrevrange("studentAvg", (pageNum - 1) * PAGETOTAL,
					((pageNum - 1) * PAGETOTAL) + (PAGETOTAL - 1));
		}

		sutdentlist = new ArrayList<StudentEntity>();
		// �����л�����ȡstudent���󲢴���List��
		for (String key : studentSet) {
			byte[] value = jedis.get(key.getBytes());
			Object object = SerializeUtil.unserialize(value);
			StudentEntity studentTemp = (StudentEntity) object;
			sutdentlist.add(studentTemp);

		}

		// ��ֵ��ǰ��jsp
		req.setAttribute("studentlist", sutdentlist);
		req.setAttribute("pagetotal", pagetotal);
		req.setAttribute("pageNum", pageNum);
		// update studentInfo ͣ����ǰҳ��
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
