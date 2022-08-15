import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StoreModule } from '@ngrx/store';
import { userReducer, userKey } from './+state/user-reducer';

@NgModule({
  imports: [
    CommonModule,
    StoreModule.forFeature(userKey, userReducer)
  ],
})
export class SharedStoreModule {}
