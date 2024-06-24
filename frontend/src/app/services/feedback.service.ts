import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback.model';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private baseUrl = 'http://localhost:8080/api/feedbacks';

  constructor(private http: HttpClient) { }

  getEventAverageRating(eventId: number|undefined): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/average-rating?eventId=${eventId}`);
  }

  getUserFeedbackOnEvent(eventId: number|undefined, userId: number): Observable<Feedback> {
    return this.http.get<Feedback>(`${this.baseUrl}/event/user-feedback?eventId=${eventId}&userId=${userId}`);
  }

  createOrUpdateUserFeedback(eventId: number|undefined, userId: number, feedback: Feedback|null): Observable<Feedback> {
    return this.http.post<Feedback>(`${this.baseUrl}/event/user-feedback?eventId=${eventId}&userId=${userId}`, feedback);
  }

  

}