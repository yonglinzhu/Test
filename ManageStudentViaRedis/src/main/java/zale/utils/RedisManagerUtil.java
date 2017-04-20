package zale.utils;

import redis.clients.jedis.Jedis;

/**
 * Created with Eclipse.
 * User: 朱永林.
 * Date: 2017年4月19日.
 * Time: 下午2:20:40.
 * Explain:
 */
public class RedisManagerUtil {
	private static Jedis jedis = null;

	/*
	 * 获取连接 Redis
	 */
	public static Jedis connectionRedis() {
		// 连接本地的Redis服务
		jedis = new Jedis("localhost");
		System.out.println("SUCESSFULLY!");
		// 查看服务是否运行
		System.out.println("Server is running!" + jedis.ping() + "\n");
		return jedis;
	}

	/*
	 * 关闭连接Redis
	 */
	public static void closeRedis() {
		// 关闭Redis连接
		jedis.close();
	}
}
