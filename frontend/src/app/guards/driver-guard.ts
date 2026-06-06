import { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';
import { inject } from '@angular/core';

export const driverGuard:
CanActivateFn = () => {

  const token =
    localStorage.getItem(
      'token'
    );

  if (!token) {

    return false;

  }

  const payload =
    JSON.parse(
      atob(
        token.split('.')[1]
      )
    );

  if (
    payload.role ===
    'DRIVER'
  ) {

    return true;

  }

  inject(Router)
      .navigate(['/']);

  return false;

};