package com.databeat.res;

import java.util.List;

import com.databeat.model.Order;

public class OrderResp {

	private Order order;
	
	private List<Order> list;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getList() {
		return list;
	}

	public void setList(List<Order> list) {
		this.list = list;
	}
	
	
}
