import { Routes } from '@angular/router';

import { Dashboard } from './pages/dashboard/dashboard';
import { Users } from './pages/users/users';
import { Shipments } from './pages/shipments/shipments';
import { CreateShipment } from './pages/create-shipment/create-shipment';
import { EditShipment } from './pages/edit-shipment/edit-shipment';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { authGuard } from './guards/auth-guard';
import { MyShipments }
from './pages/my-shipments/my-shipments';
import {
  adminGuard
}
from './guards/admin-guard';
import { shopGuard } from './guards/shop-guard';
import { guestGuard } from './guards/guest-guard';
import { driverGuard } from './guards/driver-guard';
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
    authGuard,
    adminGuard
  ]
  },

  {
    path: 'shipments',
    component: Shipments,
    canActivate: [
    authGuard,
    shopGuard
  ]
  },

  {
    path: 'create-shipment',
    component: CreateShipment,
    canActivate: [
    authGuard,
    shopGuard
  ]
  },
  {
    path: 'edit-shipment/:id',
    component: EditShipment,
    canActivate: [
    authGuard,
    shopGuard
  ]
  },
  {
    path: 'login',
    component: Login,
    canActivate : [
      guestGuard
    ]
  },
  {
    path: 'register',
    component: Register,
    canActivate : [
      guestGuard
    ]
  },
  {
  path: 'my-shipments',
  component: MyShipments,
  canActivate: [
    authGuard,
    driverGuard
  ]
}

];