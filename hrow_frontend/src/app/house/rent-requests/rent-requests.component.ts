import { Component, OnInit } from '@angular/core';
import { ImageService } from 'src/app/services/image.service';
import { PurchaseService } from 'src/app/services/purchase.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-rent-requests',
  templateUrl: './rent-requests.component.html',
  styleUrls: ['./rent-requests.component.css']
})
export class RentRequestsComponent implements OnInit {

  constructor(private purchaseService:PurchaseService, private userService:UserService, private imageService:ImageService) { }

  rentRequests:any = [];

  confirm(id:number,hid:number,index:number)
  {
      this.purchaseService.updatePurchaseByAdmin(id,hid).subscribe(
        {
              next:(success:any)=>{
                if(success.buyer=="Successful")
                    this.ngOnInit();
                else
                  window.alert(success.buyer)
              },
              error:(e)=>{
                  console.log(e);
              }
        }
      )
  }
  reject(id:number,hid:number, index:number)
  {
      this.purchaseService.rejectPurchaseByAdmin(id,hid).subscribe(
        {
              next:(success:any)=>{
                this.rentRequests[index].pstatus="rejected"
                this.rentRequests.splice(index,1);
              },
              error:(e)=>{
                  window.alert(e);
              }
        }
      )
  }


  ngOnInit(): void {
    this.purchaseService.getPurchasesForAdmin().subscribe({
      next:(success:any)=>{
        this.rentRequests=success.purchases;
        for(let i=0;i<success.purchases.length;i++)
        {
            this.rentRequests[i].imageFile = this.imageService.handleImage(success.imagesBytes[i],this.rentRequests[i].house.images[0]);
        }
        this.rentRequests.reverse();
      },
      error:(e)=>{
        window.alert("Something went wrong in SERVER")
      }
    })
  }

}
