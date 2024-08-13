import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  constructor(private http:HttpClient) { }

  postOrderDetails(url:string, data:any){
    let temp = this.http.post(url,data);
    return temp;
  }

  handlePostError(error:HttpErrorResponse){
    console.log(error);
    return throwError(error.message || "Server Error")
  }

}
