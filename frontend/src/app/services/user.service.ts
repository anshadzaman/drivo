import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class UserService {

private api =
  `${environment.apiUrl}/users`;

  constructor(
    private http: HttpClient
  ) {}

  getUsers() {

    return this.http.get(this.api);

  }
  getDriverCount(){
    return this.http.get(
      `${this.api}/count/drivers`
    );
  }
  getUserCount(){
    return this.http.get(
      `${this.api}/count`
    );
  }
    getShopCount(){
    return this.http.get(
      `${this.api}/count/shops`
    );
  }
  createUser(user: any) {

  return this.http.post(
    this.api,
    user
  );

}
deleteUser(id: number) {

  return this.http.delete(
    `${this.api}/${id}`
  );

}
}