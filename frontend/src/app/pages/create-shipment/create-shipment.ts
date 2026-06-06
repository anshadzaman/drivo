import { Component, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { ShipmentService } from '../../services/shipment.service';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-create-shipment',
  standalone: true,
  imports: [FormsModule,RouterLink],
  templateUrl: './create-shipment.html',
  styleUrl: './create-shipment.css'
})
export class CreateShipment {

  pickupLocation = '';
  dropLocation = '';
  itemName = '';

  constructor(
    private shipmentService: ShipmentService,
    private cdr: ChangeDetectorRef
  ) {}

  createShipment() {

    const shipment = {

      pickupLocation: this.pickupLocation,

      dropLocation: this.dropLocation,

      itemName: this.itemName

    };

    this.shipmentService
      .createShipment(shipment)
      .subscribe(() => {

        this.pickupLocation = '';
        this.dropLocation = '';
        this.itemName = '';

        this.cdr.detectChanges();

      });

  }

}