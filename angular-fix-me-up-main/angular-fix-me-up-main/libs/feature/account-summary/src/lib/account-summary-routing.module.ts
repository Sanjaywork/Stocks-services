import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { AccountSummaryComponent } from "./account-summary/account-summary.component";

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
      {path: '', pathMatch: 'full', component: AccountSummaryComponent }
    ]),
  ],
  declarations: [],
  exports: [RouterModule]
})
export class AccountSummaryRoutingModule { }