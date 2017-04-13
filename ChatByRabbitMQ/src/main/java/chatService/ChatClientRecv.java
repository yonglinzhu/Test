package chatService;

public class ChatClientRecv extends Thread {
	@Override
	public void run() {
		// 获取RabbitMQ对象
		RabbitMQManager rabbitMQ = new RabbitMQManager("chat","fanout");
		rabbitMQ.createRabbitMQ();
		// 绑定交换机与队列
		rabbitMQ.binding();
		// 获取消息并打印
		rabbitMQ.receiveMsg();

	}
}
