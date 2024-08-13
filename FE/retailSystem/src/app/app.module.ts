import { NgModule } from '@angular/core';
import { BrowserModule, HammerModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { OrderComponent } from './component/order/order.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { IgxDatePickerModule } from 'igniteui-angular';
import {OrderServiceService } from './services/order-service.service' 
import { HttpClient, HttpClientModule, HttpErrorResponse } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    OrderComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    IgxDatePickerModule,
    HammerModule,
    HttpClientModule,
    
  ],
  providers: [OrderServiceService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
