import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FlexLayoutModule } from '@angular/flex-layout';

import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatCardModule } from '@angular/material/card';


import { UserRoutingModule } from './user.routing.module';
import { UserComponent } from './user.component';

import { UserService } from '../service/user.service';
import { UserEditDialogComponent } from './edit/user-edit-dialog.component';
import { LoginService } from '../service/login.service';
import { DialogConfirmedModule } from '../dialog-confirmed/dialog-confirmed.module';

@NgModule({
  imports: [
    MatButtonModule,
    MatCheckboxModule,
    MatInputModule,
    MatRadioModule,
    MatCardModule,
    CommonModule,
    FormsModule,
    FlexLayoutModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatDialogModule,
    UserRoutingModule,
    DialogConfirmedModule
  ],
  declarations: [
    UserComponent,
    UserEditDialogComponent
  ],
  providers: [UserService, LoginService],
  entryComponents: [UserComponent, UserEditDialogComponent]
})
export class UserModule {
}
