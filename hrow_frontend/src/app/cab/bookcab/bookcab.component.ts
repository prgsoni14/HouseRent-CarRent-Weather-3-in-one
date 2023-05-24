import { Component, OnInit } from '@angular/core';
import { TransporterService } from '../transporter.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { Cab } from 'src/app/z-interfaces/Cab';

@Component({
  selector: 'app-bookcab',
  templateUrl: './bookcab.component.html',
  styleUrls: ['./bookcab.component.css']
})
export class BookcabComponent implements OnInit {

  constructor(private transporter:TransporterService,private router:Router, private userService:UserService) { }

  book(data:Cab)
  {
      this.transporter.bookingObject=data;
      this.transporter.bookingObject.distance=Number((Math.random()*1000+88).toFixed(2));
      this.transporter.bookingObject.cost=Number((Math.random()*500+32).toFixed(2));
      this.transporter.bookingObject.status="requested";
      let today = new Date();
      this.transporter.bookingObject.bookingDate =today.getDate().toString() + "/" + today.getMonth().toString() + "/" + today.getFullYear().toString();
      
      this.userService.test().subscribe({
        next:()=>{
            this.router.navigateByUrl("/cab/confirm");
        },
        error:(e)=>{
            window.alert(e);
        }
      })
  }

  getCurrentDate(): string {
    const today = new Date();
    return today.toISOString().split('T')[0];
  }

  getMaxDate(): string {
    const maxDate = new Date();
    maxDate.setMonth(maxDate.getMonth() + 2);
    return maxDate.toISOString().split('T')[0];
  }
  getMaxDate2(): string {
    const maxDate = new Date();
    maxDate.setMonth(maxDate.getMonth() + 2);
    maxDate.setDate(maxDate.getDate() + 7);
    return maxDate.toISOString().split('T')[0];
  }
  

  ngOnInit(): void {

  }

}
