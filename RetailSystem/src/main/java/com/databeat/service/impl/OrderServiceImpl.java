package com.databeat.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.databeat.dto.orderDTO;
import com.databeat.model.Order;
import com.databeat.repo.OrderRepository;
import com.databeat.res.OrderResp;
import com.databeat.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository repo;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;
	
	private RabbitTemplate rabbitTemplate;
	
	public OrderServiceImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendJsonMessage(orderDTO order) {
		rabbitTemplate.convertAndSend(exchange, routingJsonKey, order);
	}
	
	public void sendJsonMessage(String message) {
		rabbitTemplate.convertAndSend(exchange, routingJsonKey, message);
	}
	
	@Override
	@Transactional
	public Integer saveOrder(orderDTO order) throws SQLException {
		Order ord = new Order();
		ord.setId(order.getId());
		ord.setCustomerName(order.getCustomerName());
		ord.setDate(order.getDate());
		ord.setTotalAmount(order.getTotalAmount());
		ord.setStatus(order.getStatus());
		ord.setActive(order.getActive());
		
		return repo.save(ord).getId();
	}

	@Override
	@Transactional
	public void updateOrder(Order order) throws SQLException {
		repo.save(order);

	}

	@Override
	@Transactional
	public void deleteOrder(Integer id) throws SQLException {
		repo.deleteById(id);

	}

	@Override
	@Transactional(readOnly=true)
	public OrderResp getOneOrder(Integer id) {
		OrderResp resp = new OrderResp();
		Optional<Order> order  = repo.findById(id);
		if(order.isPresent()) {
			resp.setOrder(order.get());
		}else {
			return null;
		}
		return resp;
				
	}

	@Override
	@Transactional(readOnly=true)
	public OrderResp getAllOrder() {
		OrderResp resp = new OrderResp();
		List<Order> list = new ArrayList<Order>();
		list = repo.findAll();
		
		resp.setList(list);
		return resp;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isOrderExist(Integer id) {
		return repo.existsById(id);
	}

}
