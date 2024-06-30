import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './guard/auth.guard';
import { EventListComponent } from './components/event-list/event-list.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'events',  canActivate: [AuthGuard] , children: [
    { path: 'list', component: EventListComponent },
    { path: 'new', component: EventListComponent },
    { path: 'feedbacks/:id', component: EventListComponent },
] },
  { path: '**', redirectTo: '/events/list' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }