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
  activeRole = 'ALL';

  adminCount = 0;
  shopCount = 0;
  driverCount = 0;

  activeForm = '';

  shop = {
    name: '',
    email: '',
    password: '',
    role: 'SHOP'
  };

  driver = {
    name: '',
    email: '',
    password: '',
    role: 'DRIVER'
  };

  constructor(
    private userService: UserService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {

    this.loadUsers();

  }

  openForm(form: string) {

    if (this.activeForm === form) {

      this.activeForm = '';

    } else {

      this.activeForm = form;

    }

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

      this.filteredUsers = this.users;
      return;

    }

    this.filteredUsers =
      this.users.filter(
        user => user.role === role
      );

  }

  createShop() {

    this.userService
      .createUser(this.shop)
      .subscribe(() => {

        this.shop = {
          name: '',
          email: '',
          password: '',
          role: 'SHOP'
        };

        this.activeForm = '';

        this.loadUsers();

      });

  }

  createDriver() {

    this.userService
      .createUser(this.driver)
      .subscribe(() => {

        this.driver = {
          name: '',
          email: '',
          password: '',
          role: 'DRIVER'
        };

        this.activeForm = '';

        this.loadUsers();

      });

  }
deleteUser(id: number) {

  if (
    !confirm(
      'Delete this user?'
    )
  ) {

    return;

  }

  this.userService
      .deleteUser(id)
      .subscribe(() => {

        this.loadUsers();

      });

}
}