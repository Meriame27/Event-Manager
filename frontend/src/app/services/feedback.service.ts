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

  getEventAverageRating(eventId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/average-rating?eventId=${eventId}`);
  }

  getFeedbackById(id: number): Observable<Feedback> {
    return this.http.get<Feedback>(`${this.baseUrl}/${id}`);
  }

  createOrUpdateFeedback(feedbackRequest: Feedback): Observable<Feedback> {
    return this.http.post<Feedback>(`${this.baseUrl}/`, feedbackRequest);
  }

  

}