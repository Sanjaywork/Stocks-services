import { SideNavItem } from '@angular-anim/shared/presentational';
import { UserFacade } from '@angular-anim/shared/store';
import { Component, OnInit } from '@angular/core';
import { startWith } from 'rxjs';

@Component({
  selector: 'angular-anim-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  sideNavItems: SideNavItem[] = [];
  user$ = this.userFacade.getUser();
  userName$ = this.userFacade.getUserName().pipe(
    startWith('Not Logged In'),
  )

  constructor(private userFacade: UserFacade) { }

  ngOnInit() {
    this.sideNavItems = [
      { title: 'Account Overview', subtitle: '', link: '/' },
      { title: 'Transfers', subtitle: '', link: '/transfers' },
      { title: 'About Challenge', subtitle: '', link: '/about' },
    ]
  }
}
