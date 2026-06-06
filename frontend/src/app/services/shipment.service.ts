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
deleteShipment(id: number) {

  return this.http.delete(
    `${this.api}/${id}`
  );

}
getShipmentById(id: number) {

  return this.http.get(
    `${this.api}/${id}`
  );

}
updateShipment(
  id: number,
  shipment: any
) {

  return this.http.put(
    `${this.api}/${id}`,
    shipment
  );

}
getShipmentCount() {

  return this.http.get(
    `${this.api}/count`
  );

}
markAsDelivered(id: number) {

  return this.http.put(
    `${this.api}/${id}/deliver`,
    {}
  );

}
getPendingCount(){
  return this.http.get(
    `${this.api}/count/pending`
  );
}
getDeliveredCount(){
  return this.http.get(
    `${this.api}/count/delivered`
  );
}
getMyShipments (){
  return this.http.get(
    `${this.api}/my-shipments`
  );
}

markMyShipmentDelivered(
  id: number
) {

  return this.http.put(
    `${this.api}/${id}/my-deliver`,
    {}
  );

}
assignDriver(
  shipmentId: number,
  driverId: number
) {

  return this.http.put(

    `${this.api}/${shipmentId}/assign-driver/${driverId}`,

    {}

  );

}
getMyAssignedShipmentCount(){
  return this.http.get(
    `${this.api}/my-count`
  );
}
getMyDeliveredShipmentCount(){
  return this.http.get(
    `${this.api}/my-count/delivered`
  );
}
getMyPendingShipmentCount(){
  return this.http.get(
    `${this.api}/my-count/pending`
  );
}
}
