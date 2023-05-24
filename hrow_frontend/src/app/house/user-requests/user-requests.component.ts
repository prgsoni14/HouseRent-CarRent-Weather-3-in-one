import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ImageService } from 'src/app/services/image.service';
import { PurchaseService } from 'src/app/services/purchase.service';

@Component({
  selector: 'app-user-requests',
  templateUrl: './user-requests.component.html',
  styleUrls: ['./user-requests.component.css']
})
export class UserRequestsComponent implements OnInit {

  constructor(private purchaseService:PurchaseService, private router:Router, private imageService:ImageService) { }
  purchases:any=[]
  ngOnInit(): void {
    this.purchaseService.getPurchasesByUser().subscribe({
      next:(success:any)=>{
        this.purchases=success.purchases;
        for(let i=0;i<success.purchases.length;i++)
        {
            this.purchases[i].imageFile = this.imageService.handleImage(success.imagesBytes[i],this.purchases[i].house.images[0]);
        }
        this.purchases.reverse();
      }
    })
  }
  cancelPurchase(id:number,index:number)
  {
      this.purchaseService.deletePurchase(id).subscribe({
        next:(success:any)=>{
            console.log(success);
            this.purchases.splice(index,1);
            
        },
        
        error:(e)=>{
          window.alert("Something went wrong in SERVER");
        }
      })
  }

}
