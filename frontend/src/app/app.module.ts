import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { UserService } from './services/user.service';
import { EventService } from './services/event.service';
import { FeedbackService } from './services/feedback.service';
import { RegistrationService } from './services/registration.service';
import { HttpClientModule } from '@angular/common/http';
import { EventListComponent } from './components/event-list/event-list.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EventListComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule 
  ],
  providers: [
    UserService,
    EventService,
    FeedbackService,
    RegistrationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
