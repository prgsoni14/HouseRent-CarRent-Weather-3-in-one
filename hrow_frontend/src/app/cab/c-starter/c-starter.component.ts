import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-c-starter',
  templateUrl: './c-starter.component.html',
  styleUrls: ['./c-starter.component.css']
})
export class CStarterComponent implements OnInit {

  constructor(public userService:UserService) { }

  ngOnInit(): void {
  }

}
