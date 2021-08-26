import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';
import { UserRoleEditDialogComponent } from './user-role-edit-dialog.component';

const routes: Routes = [
  {
    path: '/',
    component: UserRoleEditDialogComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoleEditRoutingModule {
}
