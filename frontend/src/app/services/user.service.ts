import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private api =
    'http://localhost:8080/users';

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
}