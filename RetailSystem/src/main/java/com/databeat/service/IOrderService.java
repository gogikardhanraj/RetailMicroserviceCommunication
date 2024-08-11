package com.databeat.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.databeat.dto.orderDTO;
import com.databeat.model.Order;
import com.databeat.res.OrderResp;


public interface IOrderService {

	Integer saveOrder(orderDTO order) throws SQLException;
	void updateOrder(Order order) throws SQLException;
	
	void deleteOrder(Integer id) throws SQLException;
	OrderResp getOneOrder(Integer id);
	
	OrderResp getAllOrder();
	boolean isOrderExist(Integer id);
}
