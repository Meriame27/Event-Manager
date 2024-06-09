import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';

  constructor(private userService: UserService, private router: Router) { }

  login() {
    if (this.username) {
      const user: User = { id: Date.now(), username: this.username };
      this.userService.setCurrentUser(user);
      this.router.navigate(['/events']);
    }
  }
}
