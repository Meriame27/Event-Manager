import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from 'src/app/models/comment.model';
import { EventService } from 'src/app/services/event.service';
import { FeedbackService } from 'src/app/services/feedback.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-feedbacks-lsit',
  templateUrl: './feedbacks-lsit.component.html',
  styleUrls: ['./feedbacks-lsit.component.css','../../app.component.css']
})
export class FeedbacksLsitComponent implements OnInit {

  feedbacks: Comment[] = [];
  feedbackForm: FormGroup;
  eventId!: number;
  remainingCharacters: number = 50;


  constructor(
    private route: ActivatedRoute,
    private feedbackService: FeedbackService,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router,
    private eventService: EventService
  ) {
    this.feedbackForm = this.formBuilder.group({
      comment: ['', [Validators.required, Validators.maxLength(50)]],

    });
  }

  ngOnInit(): void {
    this.eventId = +this.route.snapshot.paramMap.get('id')!;
    this.eventService.getEventById(this.eventId).subscribe(
      event => {
        if (event) {
          this.loadFeedbacks();
        } else {
          this.router.navigate(['/']);
        }
      },
      () => {
        this.router.navigate(['/']);
      }
    );
  }

  loadFeedbacks(): void {
    this.feedbackService.getEventFeedbacks(this.eventId).subscribe(data => {
      this.feedbacks = data;
    });
  }

  onSubmit(): void {
    if (this.feedbackForm.invalid) {
      return;
    }

    const comment = this.feedbackForm.get('comment')?.value;
    let userId:any;
    this.userService.getCurrentUser().subscribe(user =>{
      userId =user?.id;
    })

    this.feedbackService.updateOrCreateComment(this.eventId, userId, comment).subscribe(() => {
      this.loadFeedbacks();
    });
  }

  updateCharacterCount(): void {
    const commentControl = this.feedbackForm.get('comment');
    if (commentControl) {
      this.remainingCharacters = 50 - commentControl.value.length;
    }
  }

  goBack() {
    this.router.navigate(['/']);
   }
   
}
