import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  constructor(private loginService: LoginService) {
  }

  public onLogout() {
    this.loginService.logout();
  }
}

