package mx.food.marketapp.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqListner implements MessageListener{
  
  public void onMessage(Message message) {
		System.out.println( System.currentTimeMillis() + " Consuming Message - " + new String(message.getBody()));
	}

}
