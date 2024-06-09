import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback.model';
import { FeedbackRequest } from '../models/feedback-request.model';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private baseUrl = 'http://localhost:8080/api/feedbacks';

  constructor(private http: HttpClient) { }

  getAllFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.baseUrl}/`);
  }

  getFeedbackById(id: number): Observable<Feedback> {
    return this.http.get<Feedback>(`${this.baseUrl}/${id}`);
  }

  createFeedback(feedbackRequest: FeedbackRequest): Observable<Feedback> {
    return this.http.post<Feedback>(`${this.baseUrl}/`, feedbackRequest);
  }

  updateFeedback(id: number, feedbackRequest: FeedbackRequest): Observable<Feedback> {
    return this.http.put<Feedback>(`${this.baseUrl}/${id}`, feedbackRequest);
  }

  deleteFeedback(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}