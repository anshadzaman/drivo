import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ShipmentService } from '../../services/shipment.service';
@Component({
  selector: 'app-create-shipment',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-shipment.html',
  styleUrl: './create-shipment.css',
})
export class CreateShipment {


  constructor(
    private shipmentService: ShipmentService
  ) {}
  pickupLocation = '';
  
  dropLocation = '';
  
  itemName = '';
  createShipment() {
  
    const shipment = {
  
      pickupLocation:
        this.pickupLocation,
  
      dropLocation:
        this.dropLocation,
  
      itemName:
        this.itemName
  
    };
  
   this.shipmentService
      .createShipment(shipment)
     .subscribe({
  
    next: response => {
  
      console.log('Shipment Created');
  
      console.log(response);
      
  
    this.pickupLocation = '';
  
    this.dropLocation = '';
  
    this.itemName = '';
  
  
    },
  
    error: error => {
  
      console.log('Error');
  
      console.log(error);
  
    }
  
  });
  
  }
  }