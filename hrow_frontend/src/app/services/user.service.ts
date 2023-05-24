import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../z-interfaces/User';
import { base } from '../urls';
import { ImageService } from './image.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient, private imageService:ImageService) { }

  isAdmin:boolean=false;
  isUser:boolean=false;
  isLoggedin:boolean=false;
  url:String=base
  username:string="";
  user:any=""
  userPic:string=""

  createUser(data:FormData)
  {
     return this.http.post(this.url + "/user/signup",data);
  }

  generateToken(data:User)
  {
     return this.http.post(this.url + "/user/generate-token" , data , {responseType:'text'});
  }

  currentUser()
  {
     return this.http.get(this.url + "/user/current-user");
  }

  setUser()
  {
      let currentUserString =  localStorage.getItem("current-user");
      if(currentUserString!=null)
      {
          this.user = JSON.parse(currentUserString);
          this.username = this.user.username;
          if(this.user.role == "Admin") 
              this.isAdmin=true
          else
              this.isUser=true;

          this.isLoggedin=true
      }  
  }
  setUserPic()
  {
    if(this.user!="" && this.user.picBytes.length>0)
    {
            const imageFileReceiver =  this.imageService.handleImage(this.user.picBytes,this.user.profilePic);
            this.userPic=imageFileReceiver.url;
    }
  }

  updateUser(data:User, id:number)
  {
    this.setUser();
    return this.http.put(this.url + "/user/update/" + this.username + "/" + id ,data);
  }

  deleteUser(id:number)
  {
    this.setUser();
    return this.http.delete(this.url + "/user/delete/" + this.username + "/"  + id);
  }

  logOut()
  {
      this.isLoggedin=false;
      this.isAdmin=false;
      this.isUser=false;

      localStorage.removeItem("hrowToken");
      localStorage.removeItem("current-user");
  }

  test()
  {
    return this.http.get(this.url + "/",{ responseType: 'text' });
  }

}
