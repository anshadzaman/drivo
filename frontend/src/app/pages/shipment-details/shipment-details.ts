import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { ShipmentService }
  from '../../services/shipment.service';
import { ChangeDetectorRef }
  from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-shipment-details',
  imports: [FormsModule,NgClass,RouterLink,RouterLinkActive],
  templateUrl: './shipment-details.html',
  styleUrl: './shipment-details.css',
})
export class ShipmentDetails
  implements OnInit {
statuses = [
  'PENDING',
  'ASSIGNED',
  'PICKED_UP',
  'IN_TRANSIT',
  'DELIVERED'
];
  shipment: any;

  constructor(

    private route:
      ActivatedRoute,

    private shipmentService:
      ShipmentService,

    private cdr:
      ChangeDetectorRef

  ) { }

ngOnInit() {

  const id =
    Number(
      this.route
        .snapshot
        .paramMap
        .get('id')
    );

  this.shipmentService
      .getShipmentById(id)
      .subscribe({

        next: (data) => {

          this.shipment =
            data;

          this.cdr
              .detectChanges();

        },

        error: (err) => {

          alert(
            err.error.message
          );

        }

      });

}
isCompleted(status: string) {

  return (

    this.statuses.indexOf(
      status
    )

    <=

    this.statuses.indexOf(
      this.shipment.status
    )

  );

}
  }