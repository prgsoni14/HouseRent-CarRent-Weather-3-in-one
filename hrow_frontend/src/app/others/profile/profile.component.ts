import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ImageService } from 'src/app/services/image.service';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/z-interfaces/User';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private userService:UserService, private router:Router, private imageService:ImageService) { }
  user:any="";
  userPic:string=""
  ngOnInit(): void {
        let jsonString = localStorage.getItem("current-user");
        if(jsonString!=null)
        {
          this.user= JSON.parse(jsonString);
        }  

        if(this.user.picBytes.length>0)
        {
          const imageFileReceiver =  this.imageService.handleImage(this.user.picBytes,this.user.profilePic);
          this.userPic=imageFileReceiver.url;
        }
        
  }

  updateVariable:boolean=false;
  showUpdater()
  {
    this.updateVariable = !this.updateVariable;
  }

  
  deleteAccount(id:number)
  {
      this.userService.deleteUser(id).subscribe({
        next:(success)=>{
            this.deleteVariable=true;
            
            setTimeout(() => {
                this.userService.logOut();
                this.router.navigateByUrl("/");
            }, 2000);
            
        }
        ,
        error:(e)=>{
          window.alert("Error! Check if your house is being booked");
        }
      });
  }

  confirmPassword:string="";
  toastVariable:boolean=false;
  deleteVariable:boolean=false;
  updateUser()
  {
    this.user.password=this.confirmPassword;
    this.userService.updateUser(this.user, this.user.id).subscribe({
      next:(success:any)=>{
        if(success==null)
          window.alert("Something went Wrong in updating user")
        else
        {
          this.toastVariable=true;

          setTimeout(() => {
              this.userService.logOut();
              this.router.navigateByUrl("/user/login");
          }, 2000);
        }
    }
    ,
    error:(e)=>{
        window.alert("Something went wrong in SERVER")
    }
    })
  }
}
