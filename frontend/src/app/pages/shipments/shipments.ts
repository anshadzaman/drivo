
import { Component, OnInit } from '@angular/core';
import { ShipmentService } from '../../services/shipment.service';
import { ChangeDetectorRef } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-shipments',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './shipments.html',
  styleUrl: './shipments.css',
})
export class Shipments implements OnInit {

  shipments: any[] = [];

  constructor(
    private shipmentService: ShipmentService,
    private cdr: ChangeDetectorRef
  ) {}

ngOnInit(): void {

  this.loadShipments();

}
loadShipments() {

  this.shipmentService
      .getShipments()
      .subscribe(data => {

        this.shipments = data as any[];

        this.shipments = [...this.shipments];
   this.cdr.detectChanges();
      });

}
deleteShipment(id: number) {

  this.shipmentService
      .deleteShipment(id)
      .subscribe({

        next: () => {

          console.log('Deleted');
           this.loadShipments();

        },

        error: error => {

          console.log(error);

        }

      });

}
markAsDelivered(id: number) {

  this.shipmentService
      .markAsDelivered(id)
      .subscribe(() => {

        this.loadShipments();
       
      });

}
}