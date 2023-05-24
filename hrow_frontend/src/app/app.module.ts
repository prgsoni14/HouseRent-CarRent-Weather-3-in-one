import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { SignupComponent } from './others/signup/signup.component';
import { LoginComponent } from './others/login/login.component';
import { NavbarComponent } from './others/navbar/navbar.component';
import { HomeComponent } from './others/home/home.component';
import { HHomeComponent } from './house/h-home/h-home.component';
import { WHomeComponent } from './weather/w-home/w-home.component';
import { CHomeComponent } from './cab/c-home/c-home.component';
import { CStarterComponent } from './cab/c-starter/c-starter.component';
import { HStarterComponent } from './house/h-starter/h-starter.component';
import { WStarterComponent } from './weather/w-starter/w-starter.component';
import { BookingsComponent } from './cab/bookings/bookings.component';
import { CAdminComponent } from './cab/c-admin/c-admin.component';
import { BookcabComponent } from './cab/bookcab/bookcab.component';
import { ConfirmComponent } from './cab/confirm/confirm.component';
import { FooterComponent } from './others/footer/footer.component';
import { ProfileComponent } from './others/profile/profile.component';
import { NotfoundComponent } from './others/notfound/notfound.component';
import { UseruiComponent } from './userui/userui.component';
import { HAdminComponent } from './house/h-admin/h-admin.component';
import { RentRequestsComponent } from './house/rent-requests/rent-requests.component';
import { HExploreComponent } from './house/h-explore/h-explore.component';
import { UserRequestsComponent } from './house/user-requests/user-requests.component'
import { TokenInterceptorService } from './services/helpers/token-interceptor.service';
import { NgxUiLoaderModule,NgxUiLoaderHttpModule } from 'ngx-ui-loader';
import { HHouseComponent } from './house/h-house/h-house.component';



@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    NavbarComponent,
    HomeComponent,
    HHomeComponent,
    WHomeComponent,
    CHomeComponent,
    CStarterComponent,
    HStarterComponent,
    WStarterComponent,
    BookingsComponent,
    CAdminComponent,
    BookcabComponent,
    ConfirmComponent,
    FooterComponent,
    ProfileComponent,
    NotfoundComponent,
    UseruiComponent,
    HAdminComponent,
    RentRequestsComponent,
    HExploreComponent,
    UserRequestsComponent,
    HHouseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxUiLoaderModule,
    NgxUiLoaderHttpModule.forRoot({
      showForeground:true
    })
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
