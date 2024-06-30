import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Registration } from '../models/registration.model';
import { RegistrationRequest } from '../models/registration-request.model';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private baseUrl = 'http://localhost:8080/api/registrations';

  constructor(private http: HttpClient) { }

  registerUserToEvent(eventId: number|undefined, userId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, null, {
      params: {
        eventId: eventId ? eventId.toString() : '0',
        userId: userId.toString()
      }
    });
  }

  unregisterUserFromEvent(eventId: number|undefined, userId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/unregister`, {
      params: {
        eventId: eventId ? eventId.toString() : '0',
        userId: userId.toString()
      }
    });
  }
}