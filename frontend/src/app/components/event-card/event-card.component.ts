import { Component, Input, OnInit } from '@angular/core';
import {Event} from '../../models/event.model';
import { Feedback } from '../../models/feedback.model';

import { UserService } from 'src/app/services/user.service';
import { FeedbackService } from 'src/app/services/feedback.service';
import { User } from 'src/app/models/user.model';

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

  constructor(public userService:UserService) {
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
    }

    handleRefuse(): void {
      this.event.isRegistered = false;
    }

    modify() {
      }

}



