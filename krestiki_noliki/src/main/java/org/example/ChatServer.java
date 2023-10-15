package org.example;

public class ChatServer {
    boolean statusServer;
    private chatServerCommandListener serverListener;

    public ChatServer(boolean statusServer) {
        this.statusServer = statusServer;
    }



}

interface chatServerCommandListener {
    void onMessageRecived(ChatServer chatServer);
}

class StartServerCommand implements chatServerCommandListener{
    ServerWindow serverWindow;
    StartServerCommand(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
    }

    @Override
    public void onMessageRecived(ChatServer chatServer) {
        if(chatServer.statusServer)
            serverWindow.serverMsg.append("server already start\n");
        else {
            serverWindow.serverMsg.append("server start\n");
            chatServer.statusServer = true;
        }
    }
}

class StopServerCommand implements chatServerCommandListener{

    ServerWindow serverWindow;
    StopServerCommand(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
    }
    @Override
    public void onMessageRecived(ChatServer chatServer) {
        if(!chatServer.statusServer)
            serverWindow.serverMsg.append("server already stop\n");
        else {
            serverWindow.serverMsg.append("server stop\n");
            chatServer.statusServer = false;
        }
    }
}