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

  getAllRegistrations(): Observable<Registration[]> {
    return this.http.get<Registration[]>(`${this.baseUrl}/`);
  }

  getRegistrationById(id: number): Observable<Registration> {
    return this.http.get<Registration>(`${this.baseUrl}/${id}`);
  }

  createRegistration(registrationRequest: RegistrationRequest): Observable<Registration> {
    return this.http.post<Registration>(`${this.baseUrl}/`, registrationRequest);
  }

  updateRegistration(id: number, registrationRequest: RegistrationRequest): Observable<Registration> {
    return this.http.put<Registration>(`${this.baseUrl}/${id}`, registrationRequest);
  }

  deleteRegistration(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}