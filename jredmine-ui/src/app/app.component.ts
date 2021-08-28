import { AfterContentInit } from '@angular/core';
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { LoginService } from './service/login.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterContentInit {
  title = 'MyAngularMaterial';
  isLoading = true;

  constructor(public loginService: LoginService, private router: Router) {
  }

  ngOnInit(): void {

  }

  ngAfterContentInit(): void {
    setTimeout(() => {
      this.isLoading = false;
    }, 2000);
    this.loginService.toLogin();
    // if(!this.loginService.isAuthenticated()) {
    //   this.router.navigate(['/login']);
    // }
  }
}
