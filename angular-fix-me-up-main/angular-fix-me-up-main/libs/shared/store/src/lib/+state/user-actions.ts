import { createAction, props } from '@ngrx/store';
import { User } from './user';

const CreateUser = createAction(
  '[User] Create User',
  props<{ user: User }>()
);


export const userActions = {
  CreateUser
}