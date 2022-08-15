export interface User {
  name: string;
  address: string;
  phone: string;
  roles: string[];
}

export interface UserState {
  loggedInUser: User | undefined;
}