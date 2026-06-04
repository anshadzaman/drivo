import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive,Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    FormsModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  constructor(
  private router:
  Router
) {}
  protected readonly title = signal('frontend');
  logout() {

  localStorage.removeItem(
    'token'
  );

  this.router.navigate(
    ['/login']
  );

}
isLoggedIn(): boolean {

  return !!localStorage.getItem(
    'token'
  );

}
getRole(): string {

  const token =
    localStorage.getItem(
      'token'
    );

  if (!token) {

    return '';

  }

  const payload =
    JSON.parse(
      atob(
        token.split('.')[1]
      )
    );

  return payload.role;

}
}
