import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogConfig } from '@angular/material/dialog';


import Authority from '../shared/authority';
import { AuthorityService } from '../service/authority.service';
import { DialogConfirmedComponent } from '../dialog-confirmed/dialog-confirmed.component';
import { AuthorityEditDialogComponent } from './edit/authority-edit-dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: './authority.component.html',
  styleUrls: ['./authority.component.scss']
})
export class AuthorityComponent implements OnInit {

  authorities: any = [];
  displayedColumns: string[] = ['index', 'username', 'authority', 'version', 'createdAt', "updatedAt", 'action'];

  dataSource!: MatTableDataSource<Authority>;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private router: Router, private authorityService: AuthorityService, public dialog: MatDialog) {
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  ngOnInit() {

    this.authorityService.list().subscribe(data => {
      this.authorities = data.content || [];
      for (let index = 0; index <  this.authorities.length; index++) {
        this.authorities[index].index = index + 1;
      }

      this.dataSource = new MatTableDataSource<Authority>(this.authorities);
      setTimeout(() => {
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }, 0);
    })

  }

  onLogin() {
    //localStorage.setItem('isLoggedin', 'true');
    this.router.navigate(['/authority']);
  }

  delete(authority: Authority): void {
    // Create configuration for the dialog
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '200px';
    dialogConfig.width = '400px';
    dialogConfig.data = {
      title: "Delete",
      icon: "delete_forever",
      key: authority.username,
      message: 'Are you sure?'
    };

    const dialogRef = this.dialog.open(DialogConfirmedComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      //console.log("dialogRef.afterClosed()", result)
      if (result) {
        this.dataSource.data = this.dataSource.data.filter(e => e.username !== authority.username);
        //this.userServicr.delete(user.username).subscribe();
      }
    });
  }

  edit(authority: Authority): void {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '400px';
    dialogConfig.width = '800px';
    dialogConfig.data = {
      title: "Edit",
      icon: "edit",
      authority: authority
    };
    const dialogRef = this.dialog.open(AuthorityEditDialogComponent, dialogConfig);


    dialogRef.afterClosed().subscribe(result => {
      //console.log("dialogRef.afterClosed()", result)
      //console.log("--->", result);
      if (result) {
        this.dataSource.data.map((user , i) => {
          //console.log(user , i);
          if (result.username === authority.username) {
            authority.authority = result.authority;
          }
        });
        //this.userServicr.delete(user.username).subscribe();
      }
    });
  }
}
