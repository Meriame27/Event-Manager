import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './guard/auth.guard';
import { EventListComponent } from './components/event-list/event-list.component';
// import { EventDetailComponent } from './event-detail/event-detail.component';
// import { RegistrationComponent } from './registration/registration.component';
// import { FeedbackComponent } from './feedback/feedback.component';
// import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'events', component: EventListComponent , canActivate: [AuthGuard] },
  // { path: 'events/:id', component: EventDetailComponent, canActivate: [AuthGuard] },
  // { path: 'register', component: RegistrationComponent, canActivate: [AuthGuard] },
  // { path: 'feedback', component: FeedbackComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/events', pathMatch: 'full' },
  { path: '**', redirectTo: '/events' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }