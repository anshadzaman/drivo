import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { ShipmentService } from '../../services/shipment.service';
import { ChangeDetectorRef } from '@angular/core';
import { count } from 'rxjs';
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  shipmentCount = 0;
  pickupLocation = '';
  pendingCount = 0;
  role='';
driverCount=0;
userCount=0;
shopCount=0;
deliveredCount = 0;
  showPickup() {

   alert(this.pickupLocation);

}
  constructor(
    private userService: UserService,
     private shipmentService: ShipmentService,
     private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

  this.role =
    this.getRole();

if (
  this.role === 'ADMIN'
) {

  this.loadAdminCounts();

}

else if (
  this.role === 'DRIVER'
) {

  this.loadDriverCounts();

}

else {

  this.loadShipmentCount();
  this.loadDeliveredCount();
  this.loadPendingCount();

}


    console.log('dashboard loaded');

    this.userService
      .getUsers()
      .subscribe(data => {

        console.log(data);

      });

  }
    loadShipmentCount() {

  this.shipmentService
      .getShipmentCount()
      .subscribe((count: any) => {

        this.shipmentCount = count;
        this.cdr.detectChanges();
      });

}
loadPendingCount(){
  this.shipmentService
  .getPendingCount()
  .subscribe((count: any) => {
    this.pendingCount = count;
    this.cdr.detectChanges();
  })
}
loadDeliveredCount(){
  this.shipmentService
  .getDeliveredCount()
  .subscribe((count: any) => {
    this.deliveredCount = count;
    this.cdr.detectChanges();
  })
}
getRole() {

  const token =
    localStorage.getItem(
      'token'
    );

  if (!token) {

    return '';

  }

  const payload =
    JSON.parse(
      atob(
        token.split('.')[1]
      )
    );

  return payload.role;

}
loadDriverCounts() {

  this.shipmentService
      .getMyAssignedShipmentCount()
      .subscribe((count: any) => {

        this.shipmentCount =
          count;

        this.cdr
            .detectChanges();

      });

  this.shipmentService
      .getMyPendingShipmentCount()
      .subscribe((count: any) => {

        this.pendingCount =
          count;

        this.cdr
            .detectChanges();

      });

  this.shipmentService
      .getMyDeliveredShipmentCount()
      .subscribe((count: any) => {

        this.deliveredCount =
          count;

        this.cdr
            .detectChanges();

      });

}
loadAdminCounts() {

  this.userService
      .getUserCount()
      .subscribe((count: any) => {

        this.userCount = count;
        this.cdr
            .detectChanges();

      });

  this.userService
      .getDriverCount()
      .subscribe((count: any) => {

        this.driverCount = count;
this.cdr
            .detectChanges();
      });

  this.userService
      .getShopCount()
      .subscribe((count: any) => {

        this.shopCount = count;
        this.cdr
            .detectChanges();

      });

  this.shipmentService
      .getShipmentCount()
      .subscribe((count: any) => {

        this.shipmentCount = count;
        this.cdr
            .detectChanges();

      });

  this.shipmentService
      .getPendingCount()
      .subscribe((count: any) => {

        this.pendingCount = count;
        this.cdr
            .detectChanges();

      });

  this.shipmentService
      .getDeliveredCount()
      .subscribe((count: any) => {

        this.deliveredCount = count;
        this.cdr
            .detectChanges();

      });

}
showError(err: any) {

  alert(
    err.error.message
  );

}
}