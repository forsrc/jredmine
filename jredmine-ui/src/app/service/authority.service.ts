import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class AuthorityService {
  endpoint = `${environment.baseUrl}/api/authority`;
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient, private loginService: LoginService) {}

  list(page: number, size: number) : Observable<any>{
    return this.http.get(`${this.endpoint}${this.loginService.getSessionUrl()}`+ `?page=${page}&size=${size}`)
  }
}
