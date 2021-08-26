import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogConfig } from '@angular/material/dialog';


import UserRole from '../shared/user-role';
import { UserRoleService } from '../service/user-role.service';
import { DialogConfirmedComponent } from '../dialog-confirmed/dialog-confirmed.component';
import { UserRoleEditDialogComponent } from './edit/user-role-edit-dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: './user-role.component.html',
  styleUrls: ['./user-role.component.scss']
})
export class UserRoleComponent implements OnInit {

  userRoles: any = [];
  displayedColumns: string[] = ['index', 'username', 'role', 'action'];

  dataSource!: MatTableDataSource<UserRole>;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private router: Router, private userRoleService: UserRoleService, public dialog: MatDialog) {
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  ngOnInit() {

    this.userRoleService.list().subscribe(data => {
      this.userRoles = data.data|| [];
      for (let index = 0; index <  this.userRoles.length; index++) {
        this.userRoles[index].index = index + 1;
      }
      
      this.dataSource = new MatTableDataSource<UserRole>(this.userRoles);
      setTimeout(() => {
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }, 0);
    })

  }

  onLogin() {
    //localStorage.setItem('isLoggedin', 'true');
    this.router.navigate(['/user-role']);
  }

  delete(userRole: UserRole): void {
    // Create configuration for the dialog
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '200px';
    dialogConfig.width = '400px';
    dialogConfig.data = {
      title: "Delete",
      icon: "delete_forever",
      key: userRole.username,
      message: 'Are you sure?'
    };

    const dialogRef = this.dialog.open(DialogConfirmedComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      //console.log("dialogRef.afterClosed()", result)
      if (result) {
        this.dataSource.data = this.dataSource.data.filter(e => e.username !== userRole.username);
        //this.userServicr.delete(user.username).subscribe();
      }
    });
  }

  edit(userRole: UserRole): void {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '400px';
    dialogConfig.width = '800px';
    dialogConfig.data = {
      title: "Edit",
      icon: "edit",
      userRole: userRole
    };
    const dialogRef = this.dialog.open(UserRoleEditDialogComponent, dialogConfig);

    
    dialogRef.afterClosed().subscribe(result => {
      //console.log("dialogRef.afterClosed()", result)
      //console.log("--->", result);
      if (result) {
        this.dataSource.data.map((user , i) => {
          //console.log(user , i);
          if (result.username === userRole.username) {
            userRole.role = result.role;
          }
        });
        //this.userServicr.delete(user.username).subscribe();
      }
    });
  }
}
