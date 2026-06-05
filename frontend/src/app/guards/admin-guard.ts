import { CanActivateFn }
from '@angular/router';

import { Router }
from '@angular/router';

import { inject }
from '@angular/core';

export const adminGuard:
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

  const role =
    payload.role;

  if (
    role === 'ADMIN'
  ) {

    return true;

  }

  const router =
    inject(Router);

  router.navigate(['/']);

  return false;

};