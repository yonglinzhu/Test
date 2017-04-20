package zale.service;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import zale.utils.RedisManagerUtil;

/**
 * Created with Eclipse.
 * User: ������.
 * Date: 2017��4��19��.
 * Time: ����3:44:25.
 * Explain:
 */

public class PagingDateService {

	private static Jedis jedis = null;
	private static long pageTotal;
	
	/*
	 * ������ҳ��
	 */
	@Test
	public static long getPageTotal() {
		jedis = RedisManagerUtil.connectionRedis();

		Long studentNum = jedis.zcard("studentAvg");
		pageTotal = studentNum / InitServletService.PAGETOTAL;
		if (studentNum % InitServletService.PAGETOTAL != 0) {
			pageTotal++;
		}
		RedisManagerUtil.closeRedis();
		return pageTotal;
	}
}
