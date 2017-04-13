package chatService;

import java.util.Scanner;

public class ChatClientSend extends Thread {
	private Scanner scanner;
	private String name;// 用户名

	@Override
	public void run() {
		// 获取RabbitMQ对象
		RabbitMQManager rabbitMQ = new RabbitMQManager("chat", "fanout");
		rabbitMQ.createRabbitMQ();
		// 获取用户输入昵称
		this.InputName();
		scanner = new Scanner(System.in);
		String line = "";
		// 阻塞式发送信息
		while ((line = scanner.nextLine()) != null) {
			if ("q".equals(line)) {
				// 往转发器上发送消息
				line = "SYSTEM: " + name + " is off the line !";
				rabbitMQ.publishMsg(line);
				// 关闭RabbitMQ连接
				rabbitMQ.closeRabbitMQ();
				System.exit(0);// 退出程序
			} else if ("".equals(line)) {// 判断聊天内容为空
				continue;
			}
			// 往转发器上发送消息
			line = name + " said: [" + line + "]";
			// 往队列中发出一条消息
			rabbitMQ.publishMsg(line);
		} // end while
	}

	/*
	 * 获取用户的NickName
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
