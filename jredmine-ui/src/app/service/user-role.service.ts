import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserRoleService {
  endpoint = `${environment.baseUrl}/assets/json/user-role.json`;
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) {}

  list() : Observable<any>{
    return this.http.get(`${this.endpoint}`)
  }
}
