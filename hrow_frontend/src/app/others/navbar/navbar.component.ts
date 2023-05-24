import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ImageService } from 'src/app/services/image.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public userService:UserService,private router:Router) {  }

  logOut()
  {
    this.userService.logOut();
    this.router.navigateByUrl("/");
  }

  profile:string="";
  handler:number=0;


  ngOnInit(): void {
    this.userService.setUser();
    this.userService.setUserPic();
  }

}
