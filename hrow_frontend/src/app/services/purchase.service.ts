import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Purchase } from '../z-interfaces/Purchase';
import { base } from '../urls';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private http:HttpClient) { }
  username:string=""
  url:string = base

  setUsername()
  {
    let jsonString = localStorage.getItem("current-user");
    if(jsonString!=null)
    {
       this.username=JSON.parse(jsonString).username;
    }
  }

  makePurchase(data:Purchase, houseId:number)
  {
    this.setUsername();
    return this.http.post(this.url + "/purchase/makepurchase/" + this.username + "/" + houseId,data);
  }

  getPurchasesByUser()
  {
    this.setUsername();
    return this.http.get(this.url + "/purchase/purchases/" + this.username);
  }

  deletePurchase(id:number)
  {
    this.setUsername();
    return this.http.delete(this.url + "/purchase/delete/" + this.username + "/" + id);
  }

  getPurchasesForAdmin()
  {
    this.setUsername();
    return this.http.get(this.url + "/adminr/purchase/purchases/" + this.username);
  }

  updatePurchaseByAdmin(id:number, houseId:number)
  {
    this.setUsername();
    return this.http.put(this.url + "/adminr/purchase/update/" + this.username + "/" + id + "/" + houseId,{});
  }

  rejectPurchaseByAdmin(id:number, houseId:number)
  {
    this.setUsername();
    return this.http.put(this.url + "/adminr/purchase/reject/" + this.username + "/" + id + "/" + houseId,{});
  }

  
}
