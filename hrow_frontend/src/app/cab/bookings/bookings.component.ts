import { Component, OnInit } from '@angular/core';
import { CabService } from 'src/app/services/cab.service';
import { Cab } from 'src/app/z-interfaces/Cab';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit {

  constructor(private cabService:CabService) { }
  bookingsArray:any=[]; 
  help:number=0;

  delete(id:number,index:number){
        this.cabService.deleteBooking(id).subscribe({
          next:(success:any)=>{
              if(success.fromPlace=="Successfully deleted")
                this.bookingsArray.splice(index,1);
              else
                window.alert(success.fromPlace)
          },
          error:(e:any)=>{
            window.alert("Something went wrong in SERVER")
          }
        })
  }

  ngOnInit(): void {
      this.cabService.getBookings().subscribe({
        next:(success:any)=>{
            this.bookingsArray=success;
        },
        error:(e:any)=>{
          window.alert("Something went wrong in SERVER")
        }
      }); 


      
  }

}
