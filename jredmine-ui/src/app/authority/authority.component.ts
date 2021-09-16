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

  constructor(private router: Router, private authorityService: AuthorityService, public dialog: MatDialog) {
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim();
  }

  ngOnInit() {

    this.onPage(this.page);

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
    dialogConfig.height = '500px';
    dialogConfig.width = '800px';
    dialogConfig.data = {
      title: "Edit",
      icon: "edit",
      authority: JSON.parse(JSON.stringify(authority))
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

  onPage(page: any): void {
    if(page.length != 0 && page.pageSize >= page.length) {
      return;
    }
    this.authorities = [];
    this.authorityService.list(page.pageIndex, page.pageSize).subscribe(data => {
      this.authorities = data.content || [];
      let length = this.authorities.length;
      for (let index = 0; index <  length; index++) {
        this.authorities[index].index = index + 1;
      }
      this.dataSource = new MatTableDataSource<Authority>(this.authorities);
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

  filterPredicate(target: Authority, filter: string) {
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
