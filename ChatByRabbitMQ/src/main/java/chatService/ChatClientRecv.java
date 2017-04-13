package chatService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

@SuppressWarnings("deprecation")
public class ChatClientRecv extends Thread {
	private final static String EXCHANGE_NAME = "chat";

	@Override
	public void run() {
		// 创建连接和频道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = null;
		Channel channel = null;
		QueueingConsumer consumer = new QueueingConsumer(channel);
		// 创建一个非持久的、唯一的且自动删除的队列
		String queueName = null;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
			// 使用queueDeclare()方法，不传递任何参数，来创建一个非持久的、
			// 唯一的、自动删除的队列且队列名称由服务器随机产生
			queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, EXCHANGE_NAME, "");
			// 指定接收者，第二个参数为自动应答，无需手动应答

			channel.basicConsume(queueName, true, consumer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

		while (true) {
			QueueingConsumer.Delivery delivery = null;
			try {
				delivery = consumer.nextDelivery();
			} catch (ShutdownSignalException e) {
				e.printStackTrace();
			} catch (ConsumerCancelledException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String message = new String(delivery.getBody());
			System.out.println(message);

		}

	}
}
