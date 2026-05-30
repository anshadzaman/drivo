import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Shipment } from '../models/shipment';

@Injectable({
  providedIn: 'root'
})
export class ShipmentService {

  private api =
    'http://localhost:8080/shipments';

  constructor(
    private http: HttpClient
  ) {}

  createShipment(
    shipment: Shipment
  ) {

    return this.http.post(
      this.api,
      shipment
    );

  }
  getShipments() {

  return this.http.get(this.api);

}
}