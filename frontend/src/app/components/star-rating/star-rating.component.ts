import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-star-rating',
  templateUrl: './star-rating.component.html',
  styleUrls: ['./star-rating.component.css']
})
export class StarRatingComponent implements OnInit {
  @Input() total = 5;
  @Input() rating = 0;
  @Input() readonly = false;
  @Input() size = '24px';
  @Input() type: 'filled' | 'hollow' = 'hollow';
  @Input() filledColor = '#FFE234';
  @Input() emptyColor = '#e0e0e0';
  @Input() eventId:number|undefined = -1;
  @Output() rated = new EventEmitter<number>();
  ratings: number[] = [];
  userId : number = -1;
  feedback:Feedback | null = null;
  averageRating: number =0;
  constructor(private feedbackService: FeedbackService,public userService:UserService) {}

  ngOnInit() {
    for (let i = 1; i <= this.total; i++) {
      this.ratings.push(i);
    }
    this.userService.getCurrentUser().subscribe(user => {
      this.userId = user?.id ? user?.id: -1;
    })
    this.loadUserFeedback();
    this.updateAverageRating();
  }

  getRatingColor(index: number): string {
    return this.rating >= index || this.rating >= index - 0.5
      ? this.filledColor
      : this.emptyColor;
  }

  getIcon(index: number): string {
    if (this.rating >= index) {
      return 'star';
    } else {
      if (this.rating >= index - 0.5) {
        return 'star_half';
      } else {
        return this.type === 'hollow' ? 'star_border' : 'star';
      }
    }
  }
   updateAverageRating(){
    this.feedbackService.getEventAverageRating(this.eventId).subscribe(rating => {
      this.averageRating= rating;
    })
   }

  rate(index: number): void {
    if (!this.readonly) {
      this.rating = index;
      if (this.feedback ) this.feedback.rating = index;
      this.rated.emit(index);
      this.submitFeedback();
    }
  }

  loadUserFeedback(): void {
    this.feedbackService.getUserFeedbackOnEvent(this.eventId, this.userId).subscribe((feedback: Feedback) => {
      if (feedback) {
        this.feedback = feedback;
      }
      else{
        let self = this;
        this.feedback = {
          userId: self.userId,
          eventId: self.eventId,
          rating: 0,
        }
      }
      this.rating = this.feedback.rating;
    });
  }

  submitFeedback(): void {
    this.feedbackService.createOrUpdateUserFeedback(this.eventId, this.userId, this.feedback).subscribe((feedback: Feedback) => {
      this.feedback = feedback;
      this.updateAverageRating();
    });
}
}