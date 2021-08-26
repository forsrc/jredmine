import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';

import { UserRoleEditRoutingModule } from './user-role-edit-dialog.routing';
import { UserRoleEditDialogComponent } from './user-role-edit-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FlexLayoutModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonModule,
    MatDialogModule,
    UserRoleEditRoutingModule
  ],
  declarations: [
    UserRoleEditDialogComponent
  ]
})
export class UserRoleEditDialogModule { }
