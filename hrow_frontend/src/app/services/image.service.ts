import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private sanitizer:DomSanitizer) { }

  handleImage(imageBytes:string,imageDetails:any)
  {
      const imageBlob = this.bytesToBlob(imageBytes,imageDetails.type);
      const imageFile = new File([imageBlob],imageDetails.name);

      const finalFile:any = {
        file : imageFile,
        url : this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      };

      return finalFile;
  }

  bytesToBlob(picBytes: any, imageType: any)
  {
      const byteString =  window.atob(picBytes);
      const arrayBuffer = new ArrayBuffer(byteString.length);
      const int8Araay = new Uint8Array(arrayBuffer);

      for(let i=0;i<byteString.length;i++)
      {
        int8Araay[i]=byteString.charCodeAt(i);
      }

      const blob = new Blob( [int8Araay], {type:imageType} )
      
      return blob;
  }
}
