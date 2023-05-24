import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PurchaseService } from 'src/app/services/purchase.service';
import { Purchase } from 'src/app/z-interfaces/Purchase';
import { HouseService } from 'src/app/services/house.service';
import { ImageService } from 'src/app/services/image.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-h-house',
  templateUrl: './h-house.component.html',
  styleUrls: ['./h-house.component.css']
})
export class HHouseComponent implements OnInit {

  constructor(private router:Router, private purchaseService:PurchaseService, private route:ActivatedRoute, private houseService:HouseService, private imageService:ImageService, public userService:UserService) {  }
  
  item:any={}
  boughtVariable:number=0;
  


  showConfirmationForm:boolean = false;
  showForm()
  {
    this.showConfirmationForm=!this.showConfirmationForm;
  }
  confirmPurchase(data:Purchase)
  {
      this.purchaseService.makePurchase(data,this.item.id).subscribe({
          next:(success:any)=>{
            if(success.leaving!=null)
            {
              this.router.navigateByUrl("/house/userrequests");
            }
            else
            {
              window.alert(success.buyer)
            }
          },
          error:(e)=>{
            window.alert("Something wrong in SERVER");
          }
      })
  }
  
  houseImages:any = []
  imageNumber:number=0;
  getImages(imagesBA:any, imagesDetails:any)
  {
      for(let i=0;i<imagesBA.length;i++)
      {
          this.houseImages[i]=this.imageService.handleImage(imagesBA[i],imagesDetails[i]);
      }
  }
  swipeRight()
  {
      if(this.imageNumber<this.houseImages.length-1)
      this.imageNumber++;
      else
      this.imageNumber=0;
  }
  swipeLeft()
  {
      if(this.imageNumber!=0)
      this.imageNumber--;
      else
      this.imageNumber==this.houseImages.length-1;
  }

  getCurrentDate(): string {
    const today = new Date();
    return today.toISOString().split('T')[0];
  }





  houseId:any = -1;
  ngOnInit(): void {
      if(this.route.snapshot.paramMap.get("id")==null)
          this.router.navigateByUrl("/house/explore");
      else
          this.houseId=this.route.snapshot.paramMap.get("id");
      
      this.houseService.oneHouse(this.houseId).subscribe({
        next:(success:any)=>{
            this.item=success.house;
            this.getImages(success.images, success.house.images);
        },
        error:(e)=>{
          window.alert("Something is wrong in server");
        }
        
      })
  }


}
