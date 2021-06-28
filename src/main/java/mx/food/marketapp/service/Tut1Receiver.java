package mx.food.marketapp.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import mx.food.marketapp.model.OrderDetailModel;

@RabbitListener(queues = "Commerce_queue")
public class Tut1Receiver {
  @RabbitHandler
  public void receiveMessage(OrderDetailModel item)  {
        System.out.println(" [x] Received '" + item + "'");
    }
}
