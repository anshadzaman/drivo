import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private client!: Client;

  connect(
    userId: number,
    callback: (message: string) => void
  ) {

    console.log(
      'Connecting WebSocket for user:',
      userId
    );

    this.client = new Client({

      brokerURL:
        'ws://localhost:8080/ws',

      reconnectDelay:
        5000,

      debug: (str) => {

        console.log(
          'STOMP:',
          str
        );

      }

    });

    this.client.onConnect =
      () => {

        console.log(
          'WebSocket Connected'
        );

        const topic =
          `/topic/notifications/${userId}`;

        console.log(
          'Subscribing to:',
          topic
        );

        this.client.subscribe(

          topic,

          notification => {

            console.log(
              'Notification Received:',
              notification.body
            );
            console.log(
  'Notification Received:',
  notification.body
);

            callback(
              notification.body
            );

          }

        );

      };

    this.client.onStompError =
      frame => {

        console.error(
          'STOMP Error:',
          frame
        );

      };

    this.client.onWebSocketError =
      error => {

        console.error(
          'WebSocket Error:',
          error
        );

      };

    this.client.onDisconnect =
      () => {

        console.log(
          'WebSocket Disconnected'
        );

      };

    this.client.activate();

  }

  disconnect() {

    if (this.client) {

      this.client.deactivate();

    }

  }

  getUserId() {

    const token =
      localStorage.getItem(
        'token'
      );

    if (!token) {

      return 0;

    }

    const payload =
      JSON.parse(
        atob(
          token.split('.')[1]
        )
      );

    console.log(
      'JWT Payload:',
      payload
    );

    return payload.id;

  }

} 