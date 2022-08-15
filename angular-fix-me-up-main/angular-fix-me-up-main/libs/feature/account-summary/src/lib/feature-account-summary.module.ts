import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AccountSummaryRoutingModule } from './account-summary-routing.module';
import { AccountSummaryComponent } from './account-summary/account-summary.component';

@NgModule({
  imports: [CommonModule, AccountSummaryRoutingModule, FormsModule],
  declarations: [AccountSummaryComponent],
  exports: [AccountSummaryComponent]
})
export class FeatureAccountSummaryModule {}
