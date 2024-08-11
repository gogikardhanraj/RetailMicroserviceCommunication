package com.databeat.service.Impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.databeat.dto.orderDTO;

@Service
public class OrderServiceConumerImpl {

	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;
	
	private RabbitTemplate rabbitTemplate;
	
	
	// public OrderServiceConumerImpl(String message) {
	//	rabbitTemplate.convertAndSend(exchange, routingJsonKey, message);
	// }
	
	@RabbitListener(queues= {"${rabbitmq.queue.json.name}"})
	public void consumeJsonMessage(String message) {
		System.out.println("the given message is##### "+message);
	}
	
	@RabbitListener(queues= {"${rabbitmq.queue.json.name}"})
	public void consumeJsonMessage(@RequestBody orderDTO orderDto) {
		System.out.println("the given message is##### "+orderDto);
	}
}
