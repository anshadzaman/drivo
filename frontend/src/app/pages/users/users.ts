import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';
@Component({
  selector: 'app-users',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './users.html',
  styleUrl: './users.css',
})
export class Users implements OnInit {

  users: any[] = [];

  filteredUsers: any[] = [];

  searchText = '';

  constructor(
    private userService: UserService,
    private cdr : ChangeDetectorRef
  ) {}

  ngOnInit() {

    this.userService
        .getUsers()
        .subscribe((data: any) => {

          this.users = data;

          this.filteredUsers = data;
this.cdr.detectChanges();
        });

  }

  searchUsers() {

    this.filteredUsers =
      this.users.filter(user =>

        user.name
            .toLowerCase()
            .includes(
              this.searchText.toLowerCase()
            )

        ||

        user.email
            .toLowerCase()
            .includes(
              this.searchText.toLowerCase()
            )

      );

  }

}