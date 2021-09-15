import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';

import { AuthorityEditRoutingModule } from './authority-edit-dialog.routing';
import { AuthorityEditDialogComponent } from './authority-edit-dialog.component';
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
    AuthorityEditRoutingModule
  ]
})
export class AuthorityEditDialogModule { }
