import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatProgressBarModule } from '@angular/material/progress-bar';


import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';

import { AppRoutingModule } from './app.routing';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';


import { LoginService } from './service/login.service';
import { NavBarModule } from './navbar/navbar.module';
import { FooterModule } from './footer/footer.module';
import { CookieService } from 'ngx-cookie-service';
import { DialogConfirmedComponent } from './dialog-confirmed/dialog-confirmed.component';


@NgModule({
  declarations: [
    AppComponent,
    DialogConfirmedComponent
  ],
  imports: [
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatTableModule,
    MatDialogModule,
    MatInputModule,
    MatSelectModule,
    MatProgressBarModule,

    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FlexLayoutModule,

    NavBarModule,
    FooterModule,
  ],
  providers: [LoginService, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
