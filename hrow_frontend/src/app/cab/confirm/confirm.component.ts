import { Component, OnInit } from '@angular/core';
import { TransporterService } from '../transporter.service';
import { CabService } from 'src/app/services/cab.service';
import { Router } from '@angular/router';
import { Cab } from 'src/app/z-interfaces/Cab';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  constructor(private transporter:TransporterService,private cabService:CabService,private router:Router) {  }
  cur_booking_object:Cab=this.transporter.bookingObject;

  confirm()
  {
    this.cabService.createBooking(this.cur_booking_object).subscribe({
        next:(success:any)=>{
          this.router.navigateByUrl("/cab/bookings")
        },
        error:(e:any)=>{
          window.alert(e);
        }
    })
  }
  ngOnInit(): void {
      if(this.transporter.bookingObject.fromPlace=="")
      {
        this.router.navigateByUrl("/cab/bookcab");
      }
          
  }

}
