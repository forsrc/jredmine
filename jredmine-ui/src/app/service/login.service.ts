import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {environment} from "../../environments/environment";
import {HttpClient, HttpEvent, HttpEventType, HttpResponse} from "@angular/common/http";
import {User} from "../shared/user";
import {Observable} from "rxjs";
import { CookieService } from 'ngx-cookie-service';

(window as any).global = window;


@Injectable({
  providedIn: 'root',
})
export class LoginService {

  jwtToken!: string | null;
  sessionId!: string | null;
  expiresAt!: number;
  isLogined = false;
  user!: User | null;

  constructor(public router: Router, private http: HttpClient, private cookieService: CookieService) {
  }

  public login(username: string, password: string) : Observable<HttpResponse<User>>{
    return this.http.post<User>(`${environment.baseUrl}/jwt/login`, {username: username, password: password}, { observe: 'response' });
  }
  public toLogin(): void {
    console.log("this.sessionId", this.sessionId);
    if(!this.isAuthenticated()) {
      this.router.navigate(['/login']);
    }
  }

  public logout(): void {

    this.jwtToken = null;
    this.sessionId = null;
    this.expiresAt = 0;
    this.isLogined = false;
    this.router.navigate(['/login']);
  }

  public isAuthenticated(): boolean {
    // return new Date().getTime() < this.expiresAt;
    this.sessionId = this.cookieService.get("JREDMINE_SERVER_SESSION");
    return this.sessionId != null;
  }

  public setJwtToken(jwtToken: string | null): LoginService {
    this.jwtToken = jwtToken;
    return this;
  }

  public setUser(user: User | null): LoginService {
    this.user = user;
    return this;
  }

  public setSessionId(sessionId: string | null): LoginService {
    this.sessionId = sessionId;
    if (sessionId != null) {
      this.cookieService.set("JREDMINE_SERVER_SESSION", sessionId);
    }
    console.log("Session:", this.sessionId);
    return this;
  }

  public getSessionUrl() : string {
    return ";JREDMINE_SERVER_SESSION=" + this.cookieService.get("JREDMINE_SERVER_SESSION");
  }
}
