import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './others/login/login.component';
import { SignupComponent } from './others/signup/signup.component';
import { HomeComponent } from './others/home/home.component';
import { ProfileComponent } from './others/profile/profile.component';
import { CHomeComponent } from './cab/c-home/c-home.component';
import { CStarterComponent } from './cab/c-starter/c-starter.component';
import { CAdminComponent } from './cab/c-admin/c-admin.component';
import { BookcabComponent } from './cab/bookcab/bookcab.component';
import { BookingsComponent } from './cab/bookings/bookings.component';
import { ConfirmComponent } from './cab/confirm/confirm.component';
import { UseruiComponent } from './userui/userui.component';
import { HHomeComponent } from './house/h-home/h-home.component';
import { HStarterComponent } from './house/h-starter/h-starter.component';
import { HAdminComponent } from './house/h-admin/h-admin.component';
import { HExploreComponent } from './house/h-explore/h-explore.component';
import { RentRequestsComponent } from './house/rent-requests/rent-requests.component';
import { UserRequestsComponent } from './house/user-requests/user-requests.component';
import { WHomeComponent } from './weather/w-home/w-home.component';
import { WStarterComponent } from './weather/w-starter/w-starter.component';
import { UserGuard } from './services/helpers/user.guard';
import { LoggedinGuard } from './services/helpers/loggedin.guard';
import { AdminGuard } from './services/helpers/admin.guard';
import { HHouseComponent } from './house/h-house/h-house.component';

const routes: Routes = [

    { path:"", component: HomeComponent},
    { path:"weather", component: WHomeComponent},
    { path:"weather/getWeather", component: WStarterComponent},

    { path: "user" ,component: UseruiComponent, children:[
      { path: "profile", component: ProfileComponent, canActivate:[LoggedinGuard]},
      { path: "login", component: LoginComponent },
      { path: "signup", component: SignupComponent },
    ]},

    { path:"cab" , component :CHomeComponent, children:[

          {path:"", component:CStarterComponent},
          {path:"admin", component:CAdminComponent, canActivate:[AdminGuard]},
          {path:"bookcab",component:BookcabComponent,canActivate:[UserGuard]},
          {path:"bookings", component:BookingsComponent,canActivate:[UserGuard]},
          {path:"confirm",component:ConfirmComponent, canActivate:[UserGuard]},
        
    ]},

    { path:"house" , component :HHomeComponent, children:[

      {path:"", component:HStarterComponent},
      {path:"admin", component:HAdminComponent},
      {path:"explore",component:HExploreComponent},
      {path:"adminrequests", component:RentRequestsComponent},
      {path:"userrequests",component:UserRequestsComponent},
      {path:"shouse/:id",component:HHouseComponent}
      
]},
    
 
{ path: "**", redirectTo: "/"},
    
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
