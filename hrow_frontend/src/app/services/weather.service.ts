import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http:HttpClient) { }
  
  ApiKey:String="78bc6b3dffd5f8ca70097aaf4f02340b"
  getWeatherData(city:String)
  {
        return this.http.get("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + this.ApiKey);
  }

  getWeather5Days(city:String)
  {
        return this.http.get("https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + this.ApiKey + "&cnt=40")
  }
}
