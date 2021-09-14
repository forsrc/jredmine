import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {environment} from "../../environments/environment";
import {HttpClient, HttpEvent, HttpEventType, HttpResponse} from "@angular/common/http";
import {User} from "../shared/user";
import {forkJoin, Observable} from "rxjs";
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


  public toPage() {
    let url = this.cookieService.get("TO_PAGE");
    if (url != null) {
      this.router.navigate([url]);
      return;
    }
    this.router.navigate(['/home']);
  }

  public toLogin(url? : string | null): void {
    this.cookieService.delete("TO_PAGE");
    if(!this.isAuthenticated()) {
      this.router.navigate(['/login']);
    }

    let promise = new Promise((resolve, rejects) => {
      this.http.get<any>(`${environment.baseUrl}/jwt/status/${this.getSessionUrl()}`, { observe: 'response' }).toPromise().then(
        (res: HttpResponse<any>) => {
          resolve(true);
        }, error => {

          rejects(error)
        });
    });
    promise.then(status => {
      console.log(status);
    }).catch(error => {
      console.log(error);
      if (url != null && url != "/login") {
        this.cookieService.set("TO_PAGE", url);
      }
      this.router.navigate(['/login']);
    })
  }

  public logout(): void {

    this.jwtToken = null;
    this.sessionId = null;
    this.expiresAt = 0;
    this.isLogined = false;
    this.cookieService.delete("JREDMINE_SERVER_SESSION");
    this.router.navigate(['/login']);
  }

  public isAuthenticated(): boolean {

    this.sessionId = this.cookieService.get("JREDMINE_SERVER_SESSION");
    return this.sessionId != null && this.sessionId != '';
  }

  public checkStatus() : Observable<HttpResponse<any>> {
    return this.http.get<any>(`${environment.baseUrl}/jwt/status/${this.getSessionUrl()}`, { observe: 'response' });
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
