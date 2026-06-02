import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { ShipmentService } from '../../services/shipment.service';
import { ChangeDetectorRef } from '@angular/core';
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
  showPickup() {

   alert(this.pickupLocation);

}
  constructor(
    private userService: UserService,
     private shipmentService: ShipmentService,
     private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadShipmentCount();
      
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

}