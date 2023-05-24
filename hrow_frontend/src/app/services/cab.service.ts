import { Injectable } from '@angular/core';
import { Cab } from '../z-interfaces/Cab';
import { HttpClient } from '@angular/common/http';
import { base } from '../urls';

@Injectable({
  providedIn: 'root'
})
export class CabService {

  url:string = base;
  username:string="";
  constructor(private http:HttpClient) {}
   
  setUsername()
  {
    let jsonString = localStorage.getItem("current-user");
    if(jsonString!=null)
    {
       this.username=JSON.parse(jsonString).username;
    }
  }

  getBookings()
  {
      this.setUsername();
      return this.http.get(this.url + "/cab/bookings/" + this.username);
  }

  createBooking(data:Cab)
  {
    this.setUsername();
    return this.http.post(this.url + "/cab/create/" + this.username,data);
  }

  deleteBooking(id:number)
  {
    this.setUsername();
    return this.http.delete(this.url+ "/cab/delete/" +  this.username + "/" + id);
  }

  getBookingRequests()
  {
    this.setUsername();
    return this.http.get(this.url + "/adminr/cab/requests/" + this.username);
  }

  approveBookingRequests(id:number)
  {
    this.setUsername();
    return this.http.put(this.url + "/adminr/cab/approve/" + this.username + "/" + id, {});
  }

  approvedCabsByMe()
  {
    this.setUsername();
    return this.http.get(this.url + "/adminr/cab/approvals/" + this.username);
  }
}
 