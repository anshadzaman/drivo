import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ShipmentService }
from '../../services/shipment.service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-my-shipments',
  imports: [],
  templateUrl: './my-shipments.html',
  styleUrl: './my-shipments.css'
})
export class MyShipments
implements OnInit {

  shipments: any[] = [];

 constructor(
  private shipmentService: ShipmentService,
  private cdr: ChangeDetectorRef
) {} 

  ngOnInit() {

  console.log("MyShipments loaded");

 this.shipmentService
    .getMyShipments()
    .subscribe((data: any) => {

      console.log("API DATA:", data);

      this.shipments = data;

      this.cdr.detectChanges();

    });

}
markDelivered(
  id: number
) {

  this.shipmentService
      .markMyShipmentDelivered(
        id
      )
      .subscribe(() => {

        this.shipmentService
            .getMyShipments()
            .subscribe(
              (data: any) => {

                this.shipments =
                  data;

                this.cdr
                    .detectChanges();

              }
            );

      });

} 

}