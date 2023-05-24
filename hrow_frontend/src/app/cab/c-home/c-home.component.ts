import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-c-home',
  templateUrl: './c-home.component.html',
  styleUrls: ['./c-home.component.css']
})
export class CHomeComponent implements OnInit {

  constructor(public userService:UserService) { }

  ngOnInit(): void {
  }

}
