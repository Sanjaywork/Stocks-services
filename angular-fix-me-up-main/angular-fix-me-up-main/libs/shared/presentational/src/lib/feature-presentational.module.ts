import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SideNavComponent } from './side-nav/side-nav.component';
import { RouterModule } from '@angular/router';

const EXPORTS = [
  HeaderComponent, FooterComponent, SideNavComponent
];

@NgModule({
  // remove RouterModule
  imports: [CommonModule, RouterModule],
  declarations: [...EXPORTS],
  exports: [...EXPORTS]
})
export class FeaturePresentationalModule {}
