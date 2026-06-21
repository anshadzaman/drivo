import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class NotificationService {

private api =
  `${environment.apiUrl}/notifications`;

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
deleteNotification(id : number){
  return this.http.delete(`${this.api}/${id}`)
}
clearAllNotifications() {

  return this.http.delete(
    `${this.api}/clear-all`
  );

}
}