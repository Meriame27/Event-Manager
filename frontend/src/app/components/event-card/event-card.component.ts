import { Component, Input, OnInit } from '@angular/core';
import {Event} from '../../models/event.model';

import { UserService } from 'src/app/services/user.service';
import { RegistrationService } from 'src/app/services/registration.service';
import { FeedbackService } from 'src/app/services/feedback.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-event-card',
  templateUrl: './event-card.component.html',
  styleUrls: ['./event-card.component.css','../../app.component.css'
    
  ]
})
export class EventCardComponent implements OnInit {

  @Input() event!: Event;
  @Input() removeEvent!: ()=>void;

  isOwner: boolean = false;
  userId: number = -1;

  constructor(public userService:UserService,private registrationService:RegistrationService, private feedbackService:FeedbackService, private router:Router) {
   }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(user => {
      this.isOwner = user?.id == this.event.organizerId;
      this.userId = user?.id ? user?.id: -1;
    })
    

  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('fr-FR', {
      weekday: 'short',
      year: 'numeric',
      month: 'numeric',
      day: 'numeric'
    });
  }

    handleAccept(): void {
      this.event.isRegistered = true;
      this.registrationService.registerUserToEvent(this.event.id, this.userId).subscribe(() => {
        alert('Registered successfully');
      });
    }

    handleRefuse(): void {
      this.event.isRegistered = false;
      this.registrationService.unregisterUserFromEvent(this.event.id, this.userId).subscribe(() => {
        alert('Unregistered successfully');
      });
    }

//     submitComment() {
//       const comment = window.prompt('Entrer votre avis:');
  
//       if (comment) {
//         this.feedbackService.updateOrCreateComment(this.event.id, this.userId, comment)
//           .subscribe(response => {
//             console.log('Feedback submitted:', response);
//           }, error => {
//             console.error('Error submitting feedback:', error);
//           });
//       } else {
//         console.error('Invalid input for comments or rating');
//       }
// }

    goToFeedback(){
        this.router.navigate(['events/feedbacks/'+this.event.id]);
    }

}


