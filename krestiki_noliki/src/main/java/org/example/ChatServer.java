package org.example;

import javax.swing.*;

public class ChatServer {
    boolean statusServer;
    private ChatServerListener chatServerListener = new ChatServerListener();
    ServerSocketThread serverSocketThread = new ServerSocketThread(this);

    public ChatServer(boolean statusServer) {
        this.statusServer = statusServer;
    }

    void start(){
        if (statusServer){
            chatServerListener.onMessageRecived("ServerMSG: Server already run");
        }
        else {
            chatServerListener.onMessageRecived("ServerMSG: Server run");
            serverSocketThread.onStart();
            statusServer = true;
        }
    }

    void stop(){
        if (!statusServer){
            chatServerListener.onMessageRecived("ServerMSG: Server already stop");
        }
        else {
            chatServerListener.onMessageRecived("ServerMSG: Server stop");
            serverSocketThread.onStop();
            statusServer = false;
        }
    }



}

interface ChatServerCommandListener {
    void onMessageRecived(String message);
}

interface ServerSocketThreadListener{
    void onStart();
    void onStop();
}

interface SocketThreadListener{
    void prsBtn(JButton btn);
}

