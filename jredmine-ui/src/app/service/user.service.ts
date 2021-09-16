import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';

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

  list() : Observable<any>{
    return this.http.get(this.endpoint + this.loginService.getSessionUrl());
  }

}
