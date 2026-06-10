import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  constructor(
    private authService:
      AuthService,
    private router: Router
  ) { }
  email = '';

  password = '';
login() {

  console.log("Login clicked");

  this.authService
    .login(
      this.email,
      this.password
    )
    .subscribe({

      next: (response: any) => {

        console.log(response);

        localStorage.setItem(
          'token',
          response.token
        );

        this.router.navigate(
          ['/']
        );

      },

      error: (err) => {

        alert(
          "Invalid email or Password"
        );

      }

    });

}
}
