package com.databeat.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="orders_table")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="total_Amount")
	private Double totalAmount;
	
	@Column(name="status")
	private String status;
	
	@Column(name="active")
	private Integer active;
}
