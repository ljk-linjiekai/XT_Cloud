package com.xintu.manager.services.configs;

import com.xintu.common.contants.MQConst;
import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//连接rabbitMQ的基本配置
@Configuration
@EnableRabbit
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPort(port);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    /**
     * @Description(描述): 声明交换机
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MQConst.ITEM.TOPICEXCHANGE);
    }

    /**
     * @Description(描述): 声明队列
     */
    @Bean
    public Queue insertQueue() {
        return new Queue(MQConst.ITEM.INTERSQUEUE);
    }

    @Bean
    public Queue updateQueue() {
        return new Queue(MQConst.ITEM.UPDATEQUEUE);
    }

    @Bean
    public Queue deteleQueue() {
        return new Queue(MQConst.ITEM.DELETEQUEUE);
    }

    /**
     * 将队列Queue与exchange绑定，binding_key为topic.#,模糊匹配
     */
    @Bean
    Binding insertBinding(Queue insertQueue, TopicExchange exchange) {
        return BindingBuilder.bind(insertQueue).to(exchange).with(MQConst.ITEM.ROUTINGKEY);
    }

    @Bean
    Binding updateBinding(Queue updateQueue, TopicExchange exchange) {
        return BindingBuilder.bind(updateQueue).to(exchange).with(MQConst.ITEM.ROUTINGKEY);
    }

    @Bean
    Binding deteleBinding(Queue deteleQueue, TopicExchange exchange) {
        return BindingBuilder.bind(deteleQueue).to(exchange).with(MQConst.ITEM.ROUTINGKEY);
    }

}
