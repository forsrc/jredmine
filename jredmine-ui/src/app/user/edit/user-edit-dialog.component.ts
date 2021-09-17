import { HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Inject } from '@angular/core';
import { OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

import { User } from '../../shared/user';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit-dialog.component.html',
  styleUrls: ['./user-edit-dialog.component.scss']
})
export class UserEditDialogComponent implements OnInit {

  title!: string;
  user!: User;
  message!: string;

  public event: EventEmitter<any> = new EventEmitter();

  constructor(private router: Router,
    public dialogRef: MatDialogRef<UserEditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private userService: UserService) {
  }

  ngOnInit() {
    this.title = this.data.title;
    this.user = this.data.user;

  }

  submit() {

  }

  onUpdate() {
    console.log("onLogin()...", this.router.url);
    this.userService.update(this.user).subscribe((res: HttpResponse<User>) => {
      console.log(res.body);
      if (res.body) {
        this.user = res.body;
        this.dialogRef.close(this.user);
      }
    }, error => {
      console.log(error);
      this.message = error.error.message;
    });
  }
}
