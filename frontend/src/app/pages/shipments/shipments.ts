
import { Component, OnInit } from '@angular/core';
import { ShipmentService } from '../../services/shipment.service';

@Component({
  selector: 'app-shipments',
  standalone: true,
  imports: [],
  templateUrl: './shipments.html',
  styleUrl: './shipments.css',
})
export class Shipments implements OnInit {

  shipments: any[] = [];

  constructor(
    private shipmentService: ShipmentService
  ) {}

  ngOnInit(): void {

    this.shipmentService
    .getShipments()
    .subscribe(data => {

      this.shipments = data as any[];

      console.log(this.shipments);

    });
  }

}