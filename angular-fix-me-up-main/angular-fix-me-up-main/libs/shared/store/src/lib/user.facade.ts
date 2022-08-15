import { Injectable } from "@angular/core";
import { select, Store } from "@ngrx/store";
import { filter, Observable, pluck } from "rxjs";
import { User, UserState } from "./+state/user";
import { userSelectors } from "./+state/user-selectors";

@Injectable({
  providedIn: 'root'
})
export class UserFacade {
  constructor(private store: Store<UserState>) { }
  
  getUser(): Observable<User> {
    return this.store.pipe(
      select(userSelectors.user),
      filter(Boolean)
    )
  }

  getUserName(): Observable<string> {
    return this.getUser().pipe(
      pluck('name')
    )
  }
}