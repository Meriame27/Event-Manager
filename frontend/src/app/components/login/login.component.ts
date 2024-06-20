import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  loginForm: FormGroup = this.fb.group({
    username: [
      '',
      {
        validators: [Validators.required, Validators.minLength(5) ,Validators.maxLength(150)],
        updateOn: 'blur'
      }
    ]})

  constructor(private userService: UserService, private router: Router, private fb: FormBuilder) { }

  login() {
    if (this.username) {
      const user: User = { id: Date.now(), username: this.username };
      this.userService.setCurrentUser(user);
      this.router.navigate(['/events']);
    }
  }


  ngOnInit(): void {
   this.userService.getCurrentUser().subscribe(user=>{
    this.username = user? user.username:'';
   });
   this.login();
  }

	onSubmit(): void {
    this.userService.getOrCreateUser(this.loginForm.value.username).subscribe(user=>{
      this.userService.setCurrentUser(user);
      this.router.navigate(['/events'])
    })
	}
}
