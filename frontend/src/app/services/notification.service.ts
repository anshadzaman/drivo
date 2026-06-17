import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private api =
    'http://localhost:8080/notifications';

  constructor(
    private http: HttpClient
  ) {}

  getNotifications() {

    return this.http.get(
      this.api
    );

  }
  markAsRead(id : number){
    return this.http.put(
      `${this.api}/${id}/read`,
      {}
    )
  }
getUnreadCount() {

  return this.http.get(
    `${this.api}/unread-count`
  );

}
markAllAsRead(){
    return this.http.put(
     `${this.api}/read-all`,
     {}
    )
}

}