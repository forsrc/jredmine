import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {environment} from "../../environments/environment";
import {HttpClient, HttpEvent, HttpEventType, HttpResponse} from "@angular/common/http";
import {User} from "../shared/user";
import {Observable} from "rxjs";

(window as any).global = window;

@Injectable()
export class LoginService {

  jwtToken!: string | null;
  expiresAt!: number;
  isLogined = false;
  user!: User | null;

  constructor(public router: Router, private http: HttpClient) {
  }

  public login(username: string, password: string) : Observable<HttpResponse<User>>{
    return this.http.post<User>(`${environment.baseUrl}/jwt/login`, {username: username, password: password}, { observe: 'response' });
  }
  public toLogin(): void {
    if(!this.isAuthenticated()) {
      this.router.navigate(['/login']);
    }
  }

  public logout(): void {

    this.jwtToken = null;
    this.expiresAt = 0;
    this.isLogined = false;
    this.router.navigate(['/login']);
  }

  public isAuthenticated(): boolean {
    // return new Date().getTime() < this.expiresAt;
    return this.jwtToken != null && this.isLogined;
  }

  public setJwtToken(jwtToken: string | null): LoginService {
    this.jwtToken = jwtToken;
    return this;
  }

  public setUser(user: User | null): LoginService {
    this.user = user;
    return this;
  }
}
