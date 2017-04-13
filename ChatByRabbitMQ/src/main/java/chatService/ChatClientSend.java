package chatService;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ChatClientSend extends Thread {
	private Scanner scanner;
	private String name;// 用户名
	private final static String EXCHANGE_NAME = "chat";

	@Override
	public void run() {
		// 创建连接和频道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");// 设置RabbitMQ所在主机ip或者主机名
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection();// 创建一个连接
			channel = connection.createChannel();// 创建一个频道
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");// 类型为 fanout
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

		// 用户输入昵称
		this.InputName();
		scanner = new Scanner(System.in);
		String line = "";
		// 阻塞式发送信息
		while ((line = scanner.nextLine()) != null) {
			if ("q".equals(line)) {
				try {
					// 往转发器上发送消息
					line = "SYSTEM: " + name + " is off the line !";
					try {
						channel.basicPublish(EXCHANGE_NAME, "", null, line.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
					// 关闭频道和连接
					channel.close();
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}
				System.exit(0);// 退出程序
			} else if ("".equals(line)) {// 判断聊天内容为空
				continue;
			}
			// 往转发器上发送消息
			line = name + " said: [" + line + "]";
			try {
				// 往队列中发出一条消息
				channel.basicPublish(EXCHANGE_NAME, "", null, line.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Title: InputName
	 * @Description: 初始化用户的NickName
	 */
	@SuppressWarnings("resource")
	private void InputName() {
		System.out.println("Welcome to RabbitMQ ChatRoom!\n" + "Press 'q' to exit !\n" + "Input Your NickName First!");
		// 输入NickName
		Scanner s = new Scanner(System.in);
		name = s.nextLine();
		System.out.println("Hello " + name + " , you can chat from now! Enjoy it!");
	}
}
