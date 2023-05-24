
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/z-interfaces/User';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService:UserService, private router:Router) { }
  profilePic:any = ""
  submit(data:User){
        this.userService.createUser(this.prepareFormData(data)).subscribe({
            next:(success)=>{
              this.router.navigateByUrl("/user/login");
            },
            error:(e)=>{
              window.alert("Something went wrong in SERVER");
            }
        })
  }
   
  prepareFormData(data:User) :FormData
  {
      const formData = new FormData();
      formData.append("user", new Blob([JSON.stringify(data)], {type:'application/json'}));
      formData.append("profilePic", this.profilePic);
      return formData;
  }

  onProfilePic(event:any)
  {
    if(event.target.files != null)
    {
       this.profilePic = event.target.files[0];
    } 
  }

  ngOnInit(): void {
  }

}
