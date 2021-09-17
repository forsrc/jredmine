import { Component } from '@angular/core';
import { Inject } from '@angular/core';
import { OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

/**

import { DialogConfirmedComponent } from '../dialog-confirmed/dialog-confirmed.component';

@NgModule({
  imports: [
    MatDialogModule
  ],
  declarations: [
    DialogConfirmedComponent
  ],
  entryComponents: [DialogConfirmedComponent]
})
export class UserModule {
}

 */

@Component({
  selector: 'dialog-confirmed.component',
  templateUrl: 'dialog-confirmed.component.html',
  styleUrls: ['./dialog-confirmed.component.scss'],
})
export class DialogConfirmedComponent implements OnInit {
  title!: string;
  icon!: string;
  key!: string;
  message!: string;
  type!: string;
  confirm!:string;


  constructor(
    public dialogRef: MatDialogRef<DialogConfirmedComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private snackBar: MatSnackBar) {

  }

  ngOnInit() {
    this.title = this.data.title;
    this.key = this.data.key || "";
    this.icon = this.data.icon || "info";
    this.type = this.data.type || "info";
    this.message = this.data.message || "Are you sure?";

  }

  onConfirmed() {
    if (this.type === "delete") {
      if (this.confirm === this.data.key) {
        this.data.confirm = this.confirm;
        this.data.callback((message: any) => {
          this.snackBar.open(message.message, "CLOSE");
          this.dialogRef.close(this.data);
        });
      }
      return;
    }
    this.dialogRef.close(this.data);
  }
}
