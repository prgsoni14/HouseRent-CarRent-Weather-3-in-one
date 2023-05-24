import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { House } from '../z-interfaces/House';
import { base } from '../urls';

@Injectable({
  providedIn: 'root'
})
export class HouseService {

  constructor(private http:HttpClient) { }
  username:string = "";
  url:String= base

  setUsername()
  {
    let jsonString = localStorage.getItem("current-user");
    if(jsonString!=null)
    {
       this.username=JSON.parse(jsonString).username;
    }
  }

  addHouseByAdmin(data:FormData)
  {
      this.setUsername();
      return this.http.post(this.url + "/house/addhouse/" + this.username,data);
  }

  deleteHouseByAdmin(id:number)
  {
    this.setUsername();
    return this.http.delete(this.url + "/house/delete/" + this.username + "/" +id);
  }

  freeHouseByAdmin(id:number)
  {
    this.setUsername();
    return this.http.put(this.url + "/house/freehouse/" + this.username + "/" +id,{});
  }


  housesByAdmin()
  {
    this.setUsername();
    return this.http.get(this.url + "/house/houses/" + this.username);
  }

  allHouses()
  {
      return this.http.get(this.url + "/house/houses");
  }

  allAvailableHouses()
  {
      return this.http.get(this.url + "/house/houses/available");
  }

  allHousesByCity(city:string)
  {
      return this.http.get(this.url + "/house/houses/city/" + city);
  }
  oneHouse(id:number)
  {
    return this.http.get(this.url + "/house/oneHouse/"  + id);
  }

}
