import { Component } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import { Router } from '@angular/router';
import { EventService } from 'src/app/services/event.service';
import { Observer } from 'rxjs';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css','../../app.component.css']
})
export class AddEventComponent {

  add_event_form = this.fb.group({
    name: [
      '',
      {
        validators: [Validators.required, Validators.minLength(5) ,Validators.maxLength(150)],
        updateOn: 'blur'
      }
    ],
    category: [
      '',
      {
        validators: [Validators.required, Validators.minLength(5) ,Validators.maxLength(150)],
        updateOn: 'blur'
      }
    ],
    date: ['', Validators.required],
    time: ['', Validators.required],
    location: ['', Validators.required],
});

  constructor(private fb: FormBuilder, private router: Router,private eventService:EventService,private userService:UserService) {}

  ngOnInit(): void {
  }

  get name() {
    return this.add_event_form.get('name');
  }

  get category() {
    return this.add_event_form.get('category');
  }

  get time() {
    return this.add_event_form.get('time');
  }

  get date() {
    return this.add_event_form.get('date');
  }

  get location() {
    return this.add_event_form.get('location');
  }


  goToHomePage(): void {
	  this.router.navigate(['events/list']);
  }

	onSubmit(): void {
	if (this.add_event_form.valid) {
		const observer: Observer<any> = {
			next: () => {
				alert("Formulaire soumis avec succès!");
        this.goToHomePage();
			},
			error: () => {
				alert("Erreur lors de la soumission du formulaire");
			},
      complete: () => {
			}
		};
    this.userService.getCurrentUser().subscribe(user=>{
      this.eventService.createEvent({...this.add_event_form.value,organizer:user}).subscribe(observer);
    })
	};   
}}