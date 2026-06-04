import { Routes } from '@angular/router';

import { Dashboard } from './pages/dashboard/dashboard';
import { Users } from './pages/users/users';
import { Shipments } from './pages/shipments/shipments';
import { CreateShipment } from './pages/create-shipment/create-shipment';
import { EditShipment } from './pages/edit-shipment/edit-shipment';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { authGuard } from './guards/auth-guard';
export const routes: Routes = [

  {
    path: '',
    component: Dashboard,
    canActivate: [
    authGuard
  ]
  },

  {
    path: 'users',
    component: Users,
    canActivate: [
    authGuard
  ]
  },

  {
    path: 'shipments',
    component: Shipments,
    canActivate: [
    authGuard
  ]
  },

  {
    path: 'create-shipment',
    component: CreateShipment,
    canActivate: [
    authGuard
  ]
  },
  {
    path: 'edit-shipment/:id',
    component: EditShipment,
    canActivate: [
    authGuard
  ]
  },
  {
    path: 'login',
    component: Login
  },
  {
    path: 'register',
    component: Register
  }

];