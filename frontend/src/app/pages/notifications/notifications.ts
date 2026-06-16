import {
  Component,
  OnInit,
  ChangeDetectorRef
} from '@angular/core';

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
      .subscribe(() => {

        this.loadNotifications();
        this.cdr.detectChanges();
      });

}

}