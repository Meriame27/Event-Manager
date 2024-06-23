import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { EventService } from 'src/app/services/event.service';
import {Event} from '../../models/event.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css','../../app.component.css']
})
export class EventListComponent implements OnInit {

  events: any[] = [];
  showedEvents: any[] = [];
  searchValue: string = '';

  constructor(public router: Router,private eventService:EventService, private userService:UserService) { 
    router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
            if (event.urlAfterRedirects === '/events/list') {
              userService.getCurrentUser().subscribe(user =>{
                if(user)
                this.eventService.getAllEvents(user.username).subscribe(events =>{
                  this.events = events ; 
                  this.updateShowedEvents();
              })
             
            });
          }
      }
  });
  }

  ngOnInit(): void {
    
  }

  updateShowedEvents(){
    this.showedEvents = this.events.filter(event => {
      return event.category.toLowerCase().includes(this.searchValue.toLowerCase())||
      event.name.toLowerCase().includes(this.searchValue.toLowerCase())||
      event.location.toLowerCase().includes(this.searchValue.toLowerCase())
    })
      
  }
  generateRemoveEvent(e:Event){
    let removeEvent = ()=> {
      console.log(e.id)
      if(e.id)this.eventService.deleteEvent(e.id);
      this.events = this.events.filter(event => (event.id !== e.id));
      this.updateShowedEvents();
    }
    return removeEvent;
  }

}
