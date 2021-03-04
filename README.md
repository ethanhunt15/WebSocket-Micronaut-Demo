Micronaut websocket demo.

DemoServerWebSocket.java
onOpen :  To demonstrate how to send async message after client opens a Websocket
onMessage : To demonstrate receiving a message from client and auto respond from server.

How to test using multiple client:
1. Run the code
2. Install any Websocket client(such as Websocket Test client) on chrome browser (extension)
3. Connect to server i.e. open websocket using ws://localhost:8080/chat/firstTopic/1
4. Use another computer on same network and run another Websocket client on chrome and open new websocket using ws://SERVER_IP:8080/chat/secondTopic/2

So you are now able to send and receive different events on different websocket client.

Enjoy!!

## Micronaut 2.3.3 Documentation

- [User Guide](https://docs.micronaut.io/2.3.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.3.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.3.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature spring documentation

- [Micronaut Spring Framework Annotations documentation](https://micronaut-projects.github.io/micronaut-spring/latest/guide/index.html)

# WebSocket-Micronaut-Demo
