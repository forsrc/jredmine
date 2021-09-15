import { Component } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Inject } from '@angular/core';
import { OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';

import Authorit from '../../shared/authority';

@Component({
  selector: 'app-authority-edit',
  templateUrl: './authority-edit-dialog.component.html',
  styleUrls: ['./authority-edit-dialog.component.scss']
})
export class AuthorityEditDialogComponent implements OnInit {

  title!: string;
  authority!: Authorit;

  public event: EventEmitter<any> = new EventEmitter();

  constructor(private router: Router,
    public dialogRef: MatDialogRef<AuthorityEditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.title = this.data.title;
    this.authority = this.data.authority;

  }

  submit() {

  }
}
