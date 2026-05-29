import { Routes } from '@angular/router';

import { Dashboard } from './pages/dashboard/dashboard';
import { Users } from './pages/users/users';
import { Shipments } from './pages/shipments/shipments';
import { CreateShipment } from './pages/create-shipment/create-shipment';

export const routes: Routes = [

  {
    path: '',
    component: Dashboard
  },

  {
    path: 'users',
    component: Users
  },

  {
    path: 'shipments',
    component: Shipments
  },

  {
    path: 'create-shipment',
    component: CreateShipment
  }
];