package org.example;

import javax.swing.*;

public class ChatSocketThread implements SocketThreadListener{
    ServerWindow serverWindow;

    public ChatSocketThread(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
    }

    @Override
    public void prsBtn(JButton btn) {
        serverWindow.serverMsg.append("SocketThread: press button: "+btn.getText()+"\n");
    }
}
