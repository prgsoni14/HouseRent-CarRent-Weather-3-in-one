import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PurchaseService } from 'src/app/services/purchase.service';
import { UserService } from 'src/app/services/user.service';
import { HouseService } from 'src/app/services/house.service';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'app-h-explore',
  templateUrl: './h-explore.component.html',
  styleUrls: ['./h-explore.component.css']
})
export class HExploreComponent implements OnInit {

  constructor(private purchaseService:PurchaseService, private router:Router, public userService:UserService, private houseService:HouseService, private imageService:ImageService) { }
  houses:any=[];
  allHouses:any=[]

  toHouse(index:number)
  {
      this.router.navigateByUrl("/house/shouse/" + this.houses[index].id);
  }

  getByCity(category:string)
  {
      if(category!="All")
      {
          this.houseService.allHousesByCity(category).subscribe({
                next:(success:any)=>{
                  this.houses=success.houses;
                  for(let i=0;i<success.images.length;i++)
                  {
                      this.houses[i].imageFile = this.imageService.handleImage(success.images[i], this.houses[i].images[0]);
                  }
                },
                error:(e)=>{
                  window.alert("Something went Wrong in SERVER");
                }
            }
            )
      }
      else{
        this.houses=this.allHouses;
      }    
  }

  searchKeyword:string="";

  search()
  {
      this.houses = this.allHouses.filter((house:any)=>{
        return house.name.toLowerCase().includes(this.searchKeyword) ||
               house.description.toLowerCase().includes(this.searchKeyword) ||
               house.description2.toLowerCase().includes(this.searchKeyword) ||
               house.city.toLowerCase().includes(this.searchKeyword) ||
               house.location.toLowerCase().includes(this.searchKeyword);
      })
  }   

  ngOnInit(): void { 
    this.houseService.allHouses().subscribe({
      next:(success:any)=>{
        this.houses=success.houses;
        this.allHouses=success.houses;

        for(let i=0;i<success.images.length;i++)
        {
            this.houses[i].imageFile = this.imageService.handleImage(success.images[i], this.houses[i].images[0]);;
            this.allHouses[i].imageFile = this.imageService.handleImage(success.images[i], this.houses[i].images[0]);;
        }
      },
      error:(e)=>{
        window.alert("Something wrong in server");
      }
    })
}

}
