import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private api =
  `${environment.apiUrl}/users`;

  constructor(
    private http: HttpClient
  ) {}

  login(
    email: string,
    password: string
  ) {

    return this.http.post(
      `${this.api}/login`,
      {
        email,
        password
      }
    );

  }
  register(
  name: string,
  email: string,
  password: string,
  role: string
) {

  return this.http.post(
    this.api,
    {
      name,
      email,
      password,
      role
    }
  );

}

}