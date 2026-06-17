import {
  Component,
  OnInit,
  ChangeDetectorRef
} from '@angular/core';
import { interval } from 'rxjs';
import { NotificationService }
from '../../services/notification.service';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './notifications.html',
  styleUrls: ['./notifications.css']
})
export class Notifications
implements OnInit {

  notifications: any[] = [];

  constructor(
    private notificationService:
      NotificationService,
    private cdr:
      ChangeDetectorRef
  ) {}

ngOnInit() {

  this.loadNotifications();
this.cdr.detectChanges();
  interval(30000)
      .subscribe(() => {

        this.loadNotifications();

      });

}

  loadNotifications() {

    this.notificationService
      .getNotifications()
      .subscribe({

        next: (data: any) => {

          this.notifications =
            data;

          this.cdr
              .detectChanges();

        },

        error: (err) => {

          alert(
            err.error.message
          );

        }

      });

  }
  markAsRead(id: number) {

  this.notificationService
      .markAsRead(id)
      .subscribe({

        next: () => {

          this.loadNotifications();
this.cdr.detectChanges();
        },

        error: (err) => {

          alert(
            err.error.message
          );

        }

      });

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
markAllAsRead() {

  this.notificationService
      .markAllAsRead()
      .subscribe({

        next: () => {

          this.loadNotifications();

        },

        error: (err) => {

          alert(
            err.error.message
          );

        }

      });

}
}