import { Injectable } from '@angular/core';
import { Cab } from '../z-interfaces/Cab';

@Injectable({
  providedIn: 'root'
})
export class TransporterService {

  constructor() { }
  bookingObject:Cab={id:-1,fromPlace:"",toPlace:"",contact:-1,departureDate:"",arrivalDate:"",bookingDate:"",carType:"",cost:-1,distance:-1, status:""};
}
