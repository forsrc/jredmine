import { Component } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Inject } from '@angular/core';
import { OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';

import UserRole from '../../shared/user-role';

@Component({
  selector: 'app-user-role-edit',
  templateUrl: './user-role-edit-dialog.component.html',
  styleUrls: ['./user-role-edit-dialog.component.scss']
})
export class UserRoleEditDialogComponent implements OnInit {

  title!: string;
  userRole!: UserRole;

  public event: EventEmitter<any> = new EventEmitter();

  constructor(private router: Router,
    public dialogRef: MatDialogRef<UserRoleEditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.title = this.data.title;
    this.userRole = this.data.userRole;

  }

  submit() {

  }
}
