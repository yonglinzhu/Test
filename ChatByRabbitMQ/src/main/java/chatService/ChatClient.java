package chatService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class ChatClient {

	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException {
		// 创建发送信息的线程
		new ChatClientSend().start();
		// 创建接收信息的线程
		new ChatClientRecv().start();

	}

	
}
