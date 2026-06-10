import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  constructor(
  private authService:
  AuthService,
  private router : Router
) {}
  name = '';

email = '';

password = '';

role = 'SHOP';
register() {

  this.authService
      .register(

        this.name,

        this.email,

        this.password,

        this.role

      )
      .subscribe({

        next: () => {

          alert(
            'Registration Successful'
          );

          this.router.navigate(
            ['login']
          );

        },

        error: (err) => {

          alert(
            err.error.message
          );

        }

      });

}
}
