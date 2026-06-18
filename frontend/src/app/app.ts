import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive,Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NotificationService } from './services/notification.service';
import { ChangeDetectorRef } from '@angular/core';
import { interval } from 'rxjs';
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
  interval(30000)
      .subscribe(() => {

        this.loadUnreadCount();
        
        if (
          this.showNotifications
        ) {

          this.loadNotifications();

        }

      });

}
  protected readonly title = signal('frontend');
  unreadCount = 0;
  showNotifications = false;

notifications: any[] = [];
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
loadNotifications() {

  this.notificationService
      .getNotifications()
      .subscribe({

        next: (data: any) => {

          this.notifications =
            data.slice(0, 5);
this.cdr.detectChanges();
        },

        error: (err) => {

          console.log(err);

        }

      });

}
toggleNotifications() {

  this.showNotifications =
    !this.showNotifications;

  if (
    this.showNotifications
  ) {

    this.loadNotifications();
      
  }

}
getTimeAgo(
  dateString: string
) {

  const now =
    new Date();

  const date =
    new Date(
      dateString
    );

  const seconds =
    Math.floor(
      (now.getTime()
        -
       date.getTime())
      / 1000
    );

  if (seconds < 60) {

    return 'Just now';

  }

  const minutes =
    Math.floor(
      seconds / 60
    );

  if (minutes < 60) {

    return minutes +
      ' minute' +
      (minutes > 1 ? 's' : '') +
      ' ago';

  }

  const hours =
    Math.floor(
      minutes / 60
    );

  if (hours < 24) {

    return hours +
      ' hour' +
      (hours > 1 ? 's' : '') +
      ' ago';

  }

  const days =
    Math.floor(
      hours / 24
    );

  if (days === 1) {

    return 'Yesterday';

  }

  return days +
    ' days ago';

}
closeDropdown(): void {
  setTimeout(() => (this.showNotifications = false), 150);
}
}
