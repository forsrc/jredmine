import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { HttpClient, HttpResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import { User } from '../shared/user';
import { environment } from '../../environments/environment';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  endpoint = `${environment.baseUrl}/api/user`;
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient, private loginService: LoginService) { }

  getEndpoint(username?: string): string {
    return (username ? this.endpoint + "/" + username: this.endpoint) + this.loginService.getSessionUrl();
  }

  list(page: number, size: number) : Observable<any>{
    return this.http.get(this.getEndpoint() + `?page=${page}&size=${size}`);
  }

  update(user: User) : Observable<HttpResponse<User>>{
    return this.http.post<User >(this.getEndpoint(), user, { observe: 'response' });
  }

  delete(user: User) : Observable<HttpResponse<void>>{
    return this.http.delete<void>(`${this.getEndpoint(user.username)}`, { observe: 'response' });
  }
}
