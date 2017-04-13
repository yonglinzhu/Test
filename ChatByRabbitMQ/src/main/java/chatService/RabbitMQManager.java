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
public class RabbitMQManager {
	private String exchange_name;// 交换机名称
	private String exchange_type;// 交换机类型
	private Channel channel = null;// 通道
	private Connection connection = null;// 连接
	private QueueingConsumer consumer = new QueueingConsumer(channel);// 消费者

	/*
	 * 有参构造
	 */
	public RabbitMQManager(String exchange_name, String exchange_type) {
		super();
		this.exchange_name = exchange_name;
		this.exchange_type = exchange_type;
	}

	/*
	 * 创建RabbitMQ 连接和通道
	 */
	public void createRabbitMQ() {
		// 创建连接和频道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");// 设置RabbitMQ所在主机ip或者主机名

		try {
			connection = factory.newConnection();// 创建一个连接
			channel = connection.createChannel();// 创建一个频道
			channel.exchangeDeclare(exchange_name, exchange_type);// 类型为 fanout
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 交换机绑定队列
	 */
	public void binding() {
		// 创建一个非持久的、唯一的且自动删除的队列
		String queueName = null;

		try {
			// 使用queueDeclare()方法，不传递任何参数，来创建一个非持久的、
			// 唯一的、自动删除的队列且队列名称由服务器随机产生
			queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, exchange_name, "");
			// 指定接收者，第二个参数为自动应答，无需手动应答
			channel.basicConsume(queueName, true, consumer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 发送消息
	 */
	public void publishMsg(String Msg) {
		// 往队列中发出一条消息
		try {
			channel.basicPublish(exchange_name, "", null, Msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 接收消息
	 */
	public void receiveMsg() {
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

	/*
	 * 关闭连接
	 */
	public void closeRabbitMQ() {
		// 关闭频道和连接
		try {
			channel.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

	}
}
