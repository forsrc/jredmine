import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from "../service/login.service";
import { HttpResponse } from "@angular/common/http";
import { User } from "../shared/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username: string = 'forsrc';
  password: string = 'forsrc';
  message: string = "";

  constructor(private router: Router, private loginService: LoginService) {
  }

  ngOnInit() {
  }

  onLogin() {
    console.log("onLogin()...", this.router.url);
    this.loginService.login(this.username, this.password).subscribe((res: HttpResponse<User>) => {
      console.log(res.body);
      console.log(res.headers.get("authorization"));
      localStorage.setItem('isLoggedin', 'true');
      this.loginService.setJwtToken(res.headers.get("authorization"))
        .setSessionId(res.headers.get("JREDMINE_SERVER_SESSION"))
        .setUser(res.body);
      this.loginService.toPage();

    }, error => {
      console.log(error);
      this.message = error.error.message;
    });
  }
}
