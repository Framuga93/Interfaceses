package org.example;

public class ChatServerListener implements ChatServerCommandListener {
    @Override
    public void onMessageRecived(String message) {
        System.out.println(message);
    }
}
