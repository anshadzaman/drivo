import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  pickupLocation = '';
  showPickup() {

   alert(this.pickupLocation);

}
  constructor(
    private userService: UserService
  ) {}

  ngOnInit(): void {

    console.log('dashboard loaded');

    this.userService
      .getUsers()
      .subscribe(data => {

        console.log(data);

      });

  }

}