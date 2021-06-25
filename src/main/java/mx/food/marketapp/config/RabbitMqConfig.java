package mx.food.marketapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.*;
@Configuration
public class RabbitMqConfig  {
  public static final String QUEUEC = "Commerce_queue";
  public static final String EXCHANGEC = "Commerce";
  public static final String QUEUED = "Deliveryman_queue";
  public static final String EXCHANGED = "Deliveryman";
//   public static final String ROUTING_KEY = "javatechie_routingKey";

  @Bean
  public Queue queueCommerce() {
      return new Queue(QUEUEC);
  }

  @Bean
  public TopicExchange exchangeCommerce() {
      return new TopicExchange(EXCHANGEC);
  }

  @Bean
  public Binding bindingCommerce(Queue queue, TopicExchange exchange) {
      return BindingBuilder.bind(queue).to(exchange).with("commerce.#");
  }
  
  @Bean
  public Queue queueDeliveryman() {
      return new Queue(QUEUED);
  }

  @Bean
  public TopicExchange exchangeDeliveryman() {
      return new TopicExchange(EXCHANGED);
  }

  @Bean
  public Binding bindingDeliveryman(Queue queue, TopicExchange exchange) {
      return BindingBuilder.bind(queue).to(exchange).with("deliveryman");
  }


  @Bean
  public MessageConverter converter() {
      return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate template(ConnectionFactory connectionFactory) {
      final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
      rabbitTemplate.setMessageConverter(converter());
      return rabbitTemplate;
  }
}
