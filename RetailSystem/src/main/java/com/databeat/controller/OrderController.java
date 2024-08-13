package com.databeat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.databeat.dto.orderDTO;
import com.databeat.exception.orderException;
import com.databeat.res.OrderResp;
import com.databeat.service.IOrderService;
import com.databeat.service.impl.OrderServiceImpl;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class OrderController {

	@Autowired
	private IOrderService service;
	
	private OrderServiceImpl servImpl;
	
	public OrderController(OrderServiceImpl servImpl) {
		this.servImpl = servImpl;
	}
	
	@GetMapping("/register")
	public ResponseEntity<String> sendJsonMessage(){
		servImpl.sendJsonMessage("Welcome to Order Page");
		return new ResponseEntity<String>("Welcome to Order Page", HttpStatus.OK);
	}
	
	//1./orders POST 
	@PostMapping("/orders")
	public ResponseEntity<String> saveOrder(@RequestBody orderDTO order){
		
		String message =  null;
			try {
				Integer id = service.saveOrder(order);
				message = "record with "+id+" saved successfully";
				return new ResponseEntity<String>(message, HttpStatus.OK);
			}catch(Exception e){
				e.printStackTrace();
				throw new orderException("failed to save record");
			}
	}
	
	//2. /orders/{id} GET
	@GetMapping("/orders/{id}")
	public ResponseEntity<?> getOneOrder(@PathVariable("id") Integer id){
		try {
			OrderResp resp = service.getOneOrder(id);
			if(null !=resp) {
				return new ResponseEntity<OrderResp>(resp, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Order with given id does not exist.", HttpStatus.BAD_REQUEST);
			}
		}catch(NullPointerException e) {
			throw new orderException("Order with given id does not exist.");
		}
	}
	
	//3. /orders/{id} PUT
	@PutMapping("/orders/{id}")
	public ResponseEntity<?> updateOrder(@PathVariable("id") Integer id){
		try {
			orderDTO orderDTO = new orderDTO();
			OrderResp resp = service.getOneOrder(id);
			if(null != resp) {
				orderDTO.setId(resp.getOrder().getId());
				orderDTO.setCustomerName(resp.getOrder().getCustomerName());
				orderDTO.setDate(resp.getOrder().getDate());
				orderDTO.setTotalAmount(resp.getOrder().getTotalAmount());
				orderDTO.setStatus(resp.getOrder().getStatus());
				orderDTO.setActive(resp.getOrder().getActive());
				//now performing update operation.
			}
			servImpl.sendJsonMessage(orderDTO);
			return new ResponseEntity<orderDTO>(orderDTO, HttpStatus.OK);
		}catch(Exception e) {
			throw new orderException("unable to perform update operation on given record");
		}
	}
	
	//4. /orders/{id} DELETE
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") Integer id){
		String message = "";
		boolean flag = false;
		try {
			flag = service.isOrderExist(id);
			if(flag) {
				service.deleteOrder(id);
				message = "record with "+id+" has deleted successfully";
			}
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}catch(Exception e) {
			throw new orderException("unable to delete the record with given "+id);
		}
	}
	
	//5. /orders
	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrder(){
		try {
			OrderResp resp = new OrderResp();
			resp = service.getAllOrder();
			return new ResponseEntity<OrderResp>(resp, HttpStatus.OK);
		}catch(Exception e ) {
			throw new orderException("unable to fetch the records");
		}
	}
	
}
