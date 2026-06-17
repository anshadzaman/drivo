import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive,Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NotificationService } from './services/notification.service';
import { ChangeDetectorRef } from '@angular/core';
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
  Router,
  private notificationService : NotificationService,
  private cdr : ChangeDetectorRef
) {}
ngOnInit() {
  
  this.loadUnreadCount();
  this.cdr.detectChanges();

}
  protected readonly title = signal('frontend');
  unreadCount = 0;
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
loadUnreadCount() {

  this.notificationService
      .getUnreadCount()
      .subscribe({

        next: (count: any) => {

          this.unreadCount =
            count;
            this.cdr.detectChanges();

        },

        error: (err) => {

          console.log(err);

        }

      });

}
}
