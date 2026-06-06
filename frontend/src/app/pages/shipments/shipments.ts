
import { Component, OnInit } from '@angular/core';
import { ShipmentService } from '../../services/shipment.service';
import { ChangeDetectorRef } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-shipments',
  standalone: true,
  imports: [RouterLink,FormsModule,NgClass],
  templateUrl: './shipments.html',
  styleUrl: './shipments.css',
})
export class Shipments implements OnInit {

  shipments: any[] = [];
  drivers: any[] = [];
filteredShipments: any[] = [];

searchText = '';
  constructor(
    private shipmentService: ShipmentService,
    private cdr: ChangeDetectorRef,
    private userService : UserService
  ) {}

ngOnInit(): void {

  this.loadShipments();


  this.loadDrivers();
  


}
loadShipments() {

  this.shipmentService
      .getShipments()
      .subscribe(data => {

        this.shipments =
  data as any[];

this.filteredShipments =
  [...this.shipments];
   this.cdr.detectChanges();
      });

}
searchShipments() {

  const search =
    this.searchText
        .toLowerCase();

  this.filteredShipments =
    this.shipments.filter(
      shipment =>

        shipment.itemName
          .toLowerCase()
          .includes(search)

        ||

        shipment.pickupLocation
          .toLowerCase()
          .includes(search)

        ||

        shipment.dropLocation
          .toLowerCase()
          .includes(search)

    );

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
loadDrivers() {
  
  this.userService
      .getUsers()
      .subscribe((users: any) => {

        this.drivers =
          users.filter(
            (u: any) =>
              u.role ===
              'DRIVER'
          );
this.cdr.detectChanges();
      });

}


assignDriver(
  shipmentId: number,
  driverId: number
) {

  this.shipmentService
      .assignDriver(
        shipmentId,
        driverId
      )
      .subscribe(() => {

        this.loadShipments();
        this.cdr.detectChanges()

      });

}
}