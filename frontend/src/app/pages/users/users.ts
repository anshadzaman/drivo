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
  selectedRole = 'ALL';
  activeRole = 'ALL'
  adminCount = 0;
  shopCount = 0;
  driverCount = 0;
  showCreateDriver = false;

  driver = {
    name: '',
    email: '',
    password: '',
    role: 'DRIVER'
  };
  constructor(
    private userService: UserService,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit() {

this.loadUsers();

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
  filterByRole(role: string) {

    this.selectedRole = role;
    this.activeRole = role;
    if (role === 'ALL') {

      this.filteredUsers =
        this.users;

      return;

    }

    this.filteredUsers =
      this.users.filter(
        user =>
          user.role === role
      );

  }
createDriver() {

  this.userService
      .createUser(this.driver)
      .subscribe(() => {

        this.showCreateDriver = false;

        this.driver = {
          name: '',
          email: '',
          password: '',
          role: 'DRIVER'
        };

        this.loadUsers();

      });

}
loadUsers() {

  this.userService
      .getUsers()
      .subscribe((data: any) => {

        this.users = data;
        this.filteredUsers = data;

        this.adminCount =
          this.users.filter(
            u => u.role === 'ADMIN'
          ).length;

        this.shopCount =
          this.users.filter(
            u => u.role === 'SHOP'
          ).length;

        this.driverCount =
          this.users.filter(
            u => u.role === 'DRIVER'
          ).length;
this.cdr.detectChanges();
      });

}
}