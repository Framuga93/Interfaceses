package org.example;

import java.sql.SQLOutput;

public class ServerSocketThread implements ServerSocketThreadListener {

    ChatServer chatServer;

    public ServerSocketThread(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    @Override
    public void onStart() {
        System.out.println("ServerSocketListener: Server run\n");
    }

    @Override
    public void onStop() {
        System.out.println("ServerSocketListener: Server stop\n");
    }
}
