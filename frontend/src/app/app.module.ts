import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { UserService } from './services/user.service';
import { EventService } from './services/event.service';
import { FeedbackService } from './services/feedback.service';
import { RegistrationService } from './services/registration.service';
import { HttpClientModule } from '@angular/common/http';
import { EventListComponent } from './components/event-list/event-list.component';
import { EventCardComponent } from './components/event-card/event-card.component';
import { StarRatingComponent } from './components/star-rating/star-rating.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { AddEventComponent } from './components/add-event/add-event.component';
import { CommonModule } from '@angular/common';
import { FeedbacksLsitComponent } from './components/feedbacks-lsit/feedbacks-lsit.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EventListComponent,
    EventCardComponent,
    StarRatingComponent,
    NavBarComponent,
    AddEventComponent,
    FeedbacksLsitComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
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
