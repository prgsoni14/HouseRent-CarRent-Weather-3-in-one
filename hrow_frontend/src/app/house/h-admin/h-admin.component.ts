import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HouseService } from 'src/app/services/house.service';
import { ImageService } from 'src/app/services/image.service';
import { House } from 'src/app/z-interfaces/House';

@Component({
  selector: 'app-h-admin',
  templateUrl: './h-admin.component.html',
  styleUrls: ['./h-admin.component.css']
})
export class HAdminComponent implements OnInit {

  constructor(private houseService:HouseService, private router:Router, private imageService:ImageService) { }
  houses:any=[];
  remove(id:number, index:number){
      this.houseService.deleteHouseByAdmin(id).subscribe({
              next:(success:any)=>{
                console.log(success)
                  if(success.name!="house is already booked by user")
                    this.houses[index].status="Unavailable";
                  else
                    window.alert("house is already booked by user")
              },
              error:(e)=>{
                window.alert("You are not authorized")
            }
      })
  }

  toHouse(index:number)
  {
      this.router.navigateByUrl("/house/shouse/" + this.houses[index].id);
  }

  addVariable:boolean=false;
  showAdder()
  {
    this.addVariable= !this.addVariable;
  }

  houseImagesToAdd:any=""
  addHouse(house:House)
  {
          house.images=null;
          this.houseService.addHouseByAdmin(this.prepareFormData(house)).subscribe({
            next:(success:any)=>{ 
                  success.house.imageFile = this.imageService.handleImage(success.imageBytes, success.house.images[0]);
                  this.houses.push(success.house);
            },
            error:(e)=>{
                window.alert("Something went wrong in SERVER");
            }
          });
  }
  onImages(event:any)
  {
      if(event.target.files!=null)
      {
          this.houseImagesToAdd=event.target.files;
      }
  }
  prepareFormData(house:House):FormData
  {
      const formData = new FormData();
      formData.append("house",new Blob([JSON.stringify(house)], {type:"application/json"} ));

      for(let file of this.houseImagesToAdd)
      {
          formData.append("images",file);
      }
      
      return formData;
  }
  
  freeHouse(hId:number, index:number)
  {
    this.houseService.freeHouseByAdmin(hId).subscribe(
      {
            next:(success:any)=>{
                if(success.city!='Invalid Request')
                this.houses[index].status="available";
                else
                window.alert(success.city);
            },
            error:(e)=>{
                window.alert(e);
            }
      }
    )
  }

  
  ngOnInit(): void { 
    this.houseService.housesByAdmin().subscribe({
      next:(success:any)=>{
        this.houses=success.houses;

        for(let i=0;i<success.images.length;i++)
        {
            this.houses[i].imageFile = this.imageService.handleImage(success.images[i], this.houses[i].images[0]);
        }
      },
      error:(e)=>{
        window.alert("Something wrong in SERVER")
      }
    })
}
}
