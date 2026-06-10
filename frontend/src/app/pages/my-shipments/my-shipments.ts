import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ShipmentService }
  from '../../services/shipment.service';
import { ChangeDetectorRef } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Shipment } from '../../models/shipment';
import { NgClass } from '@angular/common';
@Component({
  selector: 'app-my-shipments',
  imports: [RouterLink, RouterLinkActive, NgClass],
  templateUrl: './my-shipments.html',
  styleUrl: './my-shipments.css'
})
export class MyShipments
  implements OnInit {

  shipments: any[] = [];
  filter = 'ALL';
  constructor(
    private shipmentService: ShipmentService,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit() {

    console.log("MyShipments loaded");
  this.loadMyShipments();

        this.cdr.detectChanges();

      

  }
markDelivered(id: number) {

  this.shipmentService
      .markMyShipmentDelivered(id)
      .subscribe({

        next: () => {

          this.loadMyShipments();

        },

        error: (err) => {

          alert(
            err.error.message
          );

        }

      });

}
  get filteredShipments() {

    if (this.filter === 'ALL') {
      return this.shipments;
    }

    return this.shipments.filter(
      s => s.status === this.filter
    );

  }
  loadMyShipments() {

  this.shipmentService
      .getMyShipments()
      .subscribe({

        next: (data: any) => {

          this.shipments = data;

          this.cdr.detectChanges();

        },

        error: (err) => {

          alert(
            err.error.message
          );

        }

      });

}
}