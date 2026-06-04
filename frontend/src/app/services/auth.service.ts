import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private api =
    'http://localhost:8080/users';

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