import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';
import { ShipmentService } from '../../services/shipment.service';

@Component({
  selector: 'app-edit-shipment',
  standalone: true,
  imports: [FormsModule,RouterLinkActive,RouterLink],
  templateUrl: './edit-shipment.html',
  styleUrl: './edit-shipment.css'
})
export class EditShipment implements OnInit {

  id = 0;

  pickupLocation = '';
  dropLocation = '';
  itemName = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private shipmentService: ShipmentService,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit(): void {

    this.id = Number(
      this.route.snapshot.paramMap.get('id')
    );

    this.loadShipment();

  }

 loadShipment() {

  this.shipmentService
      .getShipmentById(this.id)
      .subscribe({

        next: (shipment: any) => {

          this.pickupLocation =
            shipment.pickupLocation;

          this.dropLocation =
            shipment.dropLocation;

          this.itemName =
            shipment.itemName;

          this.cdr.detectChanges();

        },

        error: (err) => {

          alert(
            err.error.message
          );

          this.router.navigate(
            ['/shipments']
          );

        }

      });

}

  updateShipment() {

  const shipment = {

    pickupLocation:
      this.pickupLocation,

    dropLocation:
      this.dropLocation,

    itemName:
      this.itemName

  };

  this.shipmentService
      .updateShipment(
        this.id,
        shipment
      )
      .subscribe({

        next: () => {

          this.router.navigate(
            ['/shipments']
          );

        },

        error: (err) => {

          alert(
            err.error.message
          );

        }

      });

}

}