import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { Account } from './account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  getAccounts(): Observable<Account[]> {
    const accounts: Account[] = [
      { id: "1234", balance: 7500, currency: "cad" },
      { id: "1235", balance: 4500, currency: "cad" },
      { id: "1236", balance: 2102, currency: "usd" },
    ];
    return of(accounts);
  }
}
