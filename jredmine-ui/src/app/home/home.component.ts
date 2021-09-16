import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  constructor(private router: Router, private loginService: LoginService) {
  }

  ngOnInit() {
  }

  onLogin() {
    localStorage.setItem('isLoggedin', 'true');

    this.router.navigate(['/home']);
  }
}
