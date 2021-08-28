import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {LoginService} from "../service/login.service";
import {tap} from "rxjs/operators";
import {HttpEvent, HttpResponse} from "@angular/common/http";
import {User} from "../shared/user";

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
    console.log("onLogin()...");
    this.loginService.login(this.username, this.password).subscribe((res: HttpResponse<User>) => {
      console.log(res.body);
      console.log(res.headers.get("authorization"));
      localStorage.setItem('isLoggedin', 'true');
      this.loginService.setJwtToken(res.headers.get("authorization"))
        .setUser(res.body);
      this.router.navigate(['/home']);

    }, error => {
      console.log(error);
      this.message = error.error.message;
    });



  }
}
