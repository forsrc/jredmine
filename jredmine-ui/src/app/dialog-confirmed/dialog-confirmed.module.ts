import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';



@NgModule({
  imports: [
    FormsModule,
    MatButtonModule,
    MatInputModule,
    CommonModule,
    FlexLayoutModule
  ],
  declarations: []
})
export class DialogConfirmedModule {
}
