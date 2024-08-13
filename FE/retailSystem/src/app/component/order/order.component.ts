import { Component, OnInit } from '@angular/core';
import  order  from '../../iterfaces/order'; 
import { FormBuilder, FormGroup } from '@angular/forms';
import {OrderServiceService } from '../../services/order-service.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit{
 
  myOrder: FormGroup;
  public data:order = {
    customerName : "Gaurikanth",
    date: "2024-08-06",
    totalAmount: 1345,
    status: "Available"

  }

  constructor(private fb:FormBuilder,
             private orderService: OrderServiceService
  ){
    this.myOrder = this.fb.group({
      customerName: this.data.customerName,
      date: this.data.date,
      totalAmount: this.data.totalAmount,
      status: this.data.status
    });
   }

  ngOnInit(): void {
    
  }



  submitClick(){ 
    
    const url = "http://localhost:9099/orders";
    let data = this.data;
      this.orderService.postOrderDetails(url,data).subscribe({
        next: (response:any) => console.log(response),
        error: (error:any) => console.log(error),
        complete: () => console.log("Order as been placed successfully!!!")
      })
        console.log(this.myOrder.value);

  }
}
