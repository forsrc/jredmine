import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogConfig } from '@angular/material/dialog';


import { User } from '../shared/user';
import { UserService } from '../service/user.service';
import { DialogConfirmedComponent } from '../dialog-confirmed/dialog-confirmed.component';
import { UserEditDialogComponent } from './edit/user-edit-dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  users: any = [];
  displayedColumns: string[] = ['index', 'username', 'enabled', 'version', 'createdAt', "updatedAt", 'action'];

  dataSource!: MatTableDataSource<User>;
  page = {
    length: 0,
    pageSize: 10,
    pageSizeOptions: [10, 20, 50, 100],
    previousPageIndex: 0,
    pageIndex: 0
  }

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private router: Router, private userService: UserService, public dialog: MatDialog) {
  }

  applyFilter(event: Event) {
    let filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim();
  }

  ngOnInit() {


    this.onPage(this.page);

  }

  onLogin() {
    this.router.navigate(['/user']);
  }

  delete(user: User): void {
    // Create configuration for the dialog
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '300px';
    dialogConfig.width = '400px';
    dialogConfig.data = {
      title: "Delete",
      icon: "delete_forever",
      key: user.username,
      message: 'Are you sure?',
      type: "delete",
      confirm: ""
    };

    const dialogRef = this.dialog.open(DialogConfirmedComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      console.log("dialogRef.afterClosed()", result)
      if (result && result.confirm === user.username) {
        this.userService.delete(user).subscribe(data => {

          this.dataSource.data = this.dataSource.data.filter(e => e.username !== user.username);
        });
        //this.userServicr.delete(user.username).subscribe();
      }
    });
  }

  edit(user: User): void {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '600px';
    dialogConfig.width = '800px';
    dialogConfig.data = {
      title: "Edit",
      icon: "edit",
      user: JSON.parse(JSON.stringify(user))
    };
    const dialogRef = this.dialog.open(UserEditDialogComponent, dialogConfig);


    dialogRef.afterClosed().subscribe(result => {
      // console.log("dialogRef.afterClosed()", result);
      if (!result) {
        return
      }
      this.dataSource.data.forEach((data: User) => {
        if (result.username === data.username) {
          // user.enabled = result.enabled;
          Object.assign(data, result);
          data.enabledValue = data.enabled == 1 ? "true" : "false";
        }
      });
    });
  }


  onPage(page: any): void {
    if(page.length != 0 && page.pageSize >= page.length) {
      return;
    }
    this.users = [];
    this.userService.list(page.pageIndex, page.pageSize).subscribe(data => {
      this.users = data.content || [];
      let length = this.users.length;
      for (let index = 0; index <  length; index++) {
        this.users[index].index = index + 1;
        this.users[index].enabledValue = this.users[index].enabled == 1 ? "true" : "false";

      }
      this.dataSource = new MatTableDataSource<User>(this.users);
      this.page.length = data.totalElements;
      this.page.pageSize = data.pageable.size;
      this.page.pageIndex = data.pageable.pageNumber;
      this.dataSource.filterPredicate = this.filterPredicate;
      this.dataSource.sort = this.sort;
      // setTimeout(() => {
      //   //this.dataSource.paginator = this.paginator;
      //   this.dataSource.sort = this.sort;
      // }, 0);
    })
  }

  filterPredicate(target: User, filter: string) {
    let json = JSON.parse(JSON.stringify(target));
        for(let key in json) {
          if((json[key] + "").indexOf(filter) >= 0) {
            console.log(json[key]);
            return true;
          }
        }
    return false;
  }
}
