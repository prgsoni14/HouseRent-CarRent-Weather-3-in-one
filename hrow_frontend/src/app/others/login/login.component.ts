import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router:Router,private userService:UserService) { }

  submit(data:any)
  {
      this.userService.logOut();
      this.userService.generateToken(data).subscribe({
        next:(success)=>{
            localStorage.setItem("hrowToken", success);

            //Collecting the object of current user
            this.userService.currentUser().subscribe({
                next:(success2:any)=>{
                  success2.user.picBytes = success2.profilePic;
                  localStorage.setItem("current-user", JSON.stringify(success2.user));
                  this.userService.setUser();
                  this.userService.setUserPic();
                  this.router.navigateByUrl("/");
                },
                error:(e2)=>{
                  window.alert("Something went wrong at Server")
                }
            });
            
        },
        error:(e)=>{
            window.alert("Invalid username/password");
        }
      })
  }
  

  ngOnInit(): void {
  }

}