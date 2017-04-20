package zale.service;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import zale.utils.RedisManagerUtil;

/**
 * Created with Eclipse.
 * User: 朱永林.
 * Date: 2017年4月19日.
 * Time: 下午3:44:25.
 * Explain:
 */

public class PagingDateService {

	private static Jedis jedis = null;
	private static long pageTotal;
	
	/*
	 * 计算总页数
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
