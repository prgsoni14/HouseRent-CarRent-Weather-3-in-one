import { Component, OnInit } from '@angular/core';
import { WeatherService } from 'src/app/services/weather.service';


@Component({
  selector: 'app-w-starter',
  templateUrl: './w-starter.component.html',
  styleUrls: ['./w-starter.component.css']
})
export class WStarterComponent implements OnInit {

  constructor(private weatherService:WeatherService) { }
  city:string="Bangalore";

  weatherInfo:any=[]
  // successFor1Day:boolean=false;
  // showWeather()
  // {
  //     this.weatherService.getWeatherData(this.city).subscribe({
  //         next:(s)=>{
  //             this.weatherInfo=s;
  //             this.successFor1Day=true
  //             this.successFor5Days=false
  //         },
  //         error:(e)=>{
  //           window.alert("City Name wrong or OpenWeatherApi is not responding");
  //         }
  //     });
  // }

  successFor5Days:boolean=false;
  weatherArrayIndex:number=0;
  isDay:boolean=false;
  isRaining:boolean=false;
  isHot:boolean=false;

  showWeather5Days()
  {
    this.weatherService.getWeather5Days(this.city).subscribe({
      next:(s)=>{
          this.weatherInfo=s;
          this.successFor5Days=true;
          this.checkDayNightAndRain();
      },
      error:(e)=>{
        window.alert("City name wrong or OpenWeatherApi is not responding");
      }
  });
  }



checkDayNightAndRain()
{
    
    let time:number=this.weatherInfo.list[this.weatherArrayIndex].dt*1000;
    let date = new Date(time);
    let hour = date.getHours();

    if (hour >= 6 && hour < 18) {
      this.isDay=true;
    } else {
      this.isDay=false;
    }

    this.isRaining = this.weatherInfo.list[this.weatherArrayIndex].weather[0].description.includes('rain');
    this.isHot = (this.weatherInfo.list[this.weatherArrayIndex].main.temp -273.15) > 33 ? true : false; 
}

onSliderChange(event:any)
  {
    this.weatherArrayIndex = event.target.value;
    this.checkDayNightAndRain();
  }
  
  ngOnInit(): void {

    this.showWeather5Days();

  }


}
