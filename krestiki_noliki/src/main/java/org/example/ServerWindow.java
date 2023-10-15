package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final int WINDOW_HIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;

    JButton btnStart = new JButton("Start server");
    JButton btnExit = new JButton("Stop server");
    JTextArea serverMsg = new JTextArea();
    ChatServer chatServer = new ChatServer(false);
    StartServerCommand startServerCommand = new StartServerCommand(this);
    StopServerCommand stopServerCommand = new StopServerCommand(this);

    ServerWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HIGHT);
        setLocation(WINDOW_POSX,WINDOW_POSY);
        setSize(WINDOW_WIDTH,WINDOW_HIGHT);
        setResizable(false);
        setTitle("Server");

        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(btnStart);
        buttonPanel.add(btnExit);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServerCommand.onMessageRecived(chatServer);
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopServerCommand.onMessageRecived(chatServer);
            }
        });



        mainPanel.add(buttonPanel);
        mainPanel.add(serverMsg);
        serverMsg.setEditable(false);
        add(mainPanel);

        setVisible(true);
    }

}
//        Создать интерфейсы ServerSocketThreadListener и
//        SocketThreadListener, содержащие методы, соответствующие событиям сервера и
//        клиента чата. Реализовать созданные интерфейсы простым логированием.
//        Со стороны клиента – только SocketThreadListener,
//        со стороны сервера – оба интерфейса.

// Сервер - лог запуск сервера
//          лог сообщение состояния
// Клиент - лог сообщения состояния

