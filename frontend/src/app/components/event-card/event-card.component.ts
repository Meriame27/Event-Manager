import { Component, Input, OnInit } from '@angular/core';
import {Event} from '../../models/event.model';
import { UserService } from 'src/app/services/user.service';

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

  constructor(public userService:UserService) {
   }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(user => {
      this.isOwner = user?.id == this.event.organizer.id;
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

    sendFeedback($event:number) {
      console.log($event)
    }
    modify() {
      }
}

