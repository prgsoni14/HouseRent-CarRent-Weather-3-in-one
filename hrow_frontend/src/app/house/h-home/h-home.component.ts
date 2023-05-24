import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-h-home',
  templateUrl: './h-home.component.html',
  styleUrls: ['./h-home.component.css']
})
export class HHomeComponent implements OnInit {

  constructor(public userService:UserService) { }

  ngOnInit(): void {
  }

}
