import { createReducer, on } from '@ngrx/store';
import { UserState } from './user';
import { userActions } from './user-actions';

export const userKey = 'userStore';

export const initialState: UserState = {
  loggedInUser: undefined
}

export const userReducer = createReducer(
  initialState,
  on(userActions.CreateUser, (state, { user }) => {
    return {
      ...state,
      loggedInUser: user
    }
  })
)