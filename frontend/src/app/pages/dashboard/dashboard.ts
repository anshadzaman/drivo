import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {

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