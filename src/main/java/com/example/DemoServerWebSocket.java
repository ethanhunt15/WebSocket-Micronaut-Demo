package com.example;

import io.micronaut.websocket.WebSocketBroadcaster;
        import io.micronaut.websocket.WebSocketSession;
        import io.micronaut.websocket.annotation.OnClose;
        import io.micronaut.websocket.annotation.OnMessage;
        import io.micronaut.websocket.annotation.OnOpen;
        import io.micronaut.websocket.annotation.ServerWebSocket;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

@ServerWebSocket("/chat/{topic}/{username}")
public class DemoServerWebSocket {

    private final WebSocketBroadcaster broadcaster;

    public DemoServerWebSocket(WebSocketBroadcaster broadcaster) {
        System.out.println("DemoServerWebSocket::DemoServerWebSocket START");
        this.broadcaster = broadcaster;
    }

    @OnOpen
    public void onOpen(String topic, String username, WebSocketSession session) {
        System.out.println("DemoServerWebSocket::onOpen START");
        String msg = "[" + username + "] Joined! + for topic:: " + topic;
        System.out.println("DemoServerWebSocket::onOpen " + msg);
        //To send async message, without any message from client
        sendMessageFrmServerAsync(session, username, topic);
    }

    //This function will NOT be called
    @OnMessage
    public void onMessage(String topic, String username,
                          String message, WebSocketSession session) {
        System.out.println("DemoServerWebSocket::onMessage VOID START");
        String msg = "[" + username + "] " + message;
        System.out.println("DemoServerWebSocket::onMessage " + msg);
        broadcaster.broadcastSync(msg, isValid(topic, session));
    }

    @OnMessage
    public CompletableFuture<String> onMessage(String message, WebSocketSession session) throws InterruptedException {
        System.out.println("DemoServerWebSocket::onMessage Publisher START");
        System.out.println("message received msg= " + message);

        //Send back acknowledgement
        CompletableFuture<String> calculateAsync = session.sendAsync("Thanks for the message, this is response from server");
        return calculateAsync;
    }


    @OnClose
    public void onClose(String topic, String username, WebSocketSession session) {
        System.out.println("DemoServerWebSocket::onClose START");
        String msg = "[" + username + "] Disconnected!";
        System.out.println("DemoServerWebSocket::onClose " + msg);
        broadcaster.broadcastSync(msg, isValid(topic, session));
    }

    private Predicate<WebSocketSession> isValid(String topic, WebSocketSession session) {
        System.out.println("DemoServerWebSocket::isValid START");
        return s -> s != session &&
                topic.equalsIgnoreCase(s.getUriVariables().get("topic", String.class, null));
    }

    public Future<String> sendMessageFrmServerAsync(WebSocketSession session, String user, String topic) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(2000);
            session.sendAsync("Thanks for the message, this is response from server calculateAsync " + user + " on " + topic);
            completableFuture.complete("completableFuture task is complete");
            return null;
        });

        return completableFuture;
    }
}