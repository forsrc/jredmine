import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';
import { AuthorityEditDialogComponent } from './authority-edit-dialog.component';

const routes: Routes = [
  {
    path: '/',
    component: AuthorityEditDialogComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthorityEditRoutingModule {
}
