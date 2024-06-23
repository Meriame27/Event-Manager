import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css','../../app.component.css']
})
export class NavBarComponent {

  constructor(public router: Router, public userService: UserService) { }

  ngOnInit(): void {
  }

  goTOcreateEvent():void{
    this.router.navigate(['events/new']);
  }

  logOut():void{
    this.userService.clearCurrentUser();
    this.router.navigate(['login']);
  }

}