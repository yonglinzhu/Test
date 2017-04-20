package zale.utils;

import redis.clients.jedis.Jedis;

/**
 * Created with Eclipse.
 * User: ������.
 * Date: 2017��4��19��.
 * Time: ����2:20:40.
 * Explain:
 */
public class RedisManagerUtil {
	private static Jedis jedis = null;

	/*
	 * ��ȡ���� Redis
	 */
	public static Jedis connectionRedis() {
		// ���ӱ��ص�Redis����
		jedis = new Jedis("localhost");
		System.out.println("SUCESSFULLY!");
		// �鿴�����Ƿ�����
		System.out.println("Server is running!" + jedis.ping() + "\n");
		return jedis;
	}

	/*
	 * �ر�����Redis
	 */
	public static void closeRedis() {
		// �ر�Redis����
		jedis.close();
	}
}
