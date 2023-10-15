package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Создать окно клиента чата. Окно должно содержать JtextField для ввода логина, пароля, IP-адреса сервера, порта подключения
//        к серверу, область ввода сообщений, JTextArea область просмотра сообщений чата и JButton подключения к серверу и
//        отправки сообщения в чат. Желательно сразу сгруппировать компоненты, относящиеся
//        к серверу сгруппировать на JPanel сверху экрана, а компоненты, относящиеся к отправке сообщения – на JPanel снизу


public class ChatWindow extends JFrame {
    private static final int WINDOW_HIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton connectBtn = new JButton("Подключится");
    JButton msgButton = new JButton("Отправить сообщение");

    ChatWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HIGHT);
        setTitle("Chat");
        setResizable(false);

        JPanel panelBtn = new JPanel(new GridLayout(2, 2));
        JTextField loginField = new JTextField("test");
        JTextField passwordField = new JTextField("test");
        JTextField ipField = new JTextField("test");
        JTextField portField = new JTextField("test");
        panelBtn.add(loginField);
        panelBtn.add(passwordField);
        panelBtn.add(ipField);
        panelBtn.add(portField);
        add(panelBtn, BorderLayout.NORTH);

        JTextArea chatField = new JTextArea();
        chatField.setEditable(false);
        add(chatField);


        JPanel panelButtonSouth = new JPanel(new GridLayout(2, 1));
        JPanel panelButton = new JPanel(new GridLayout(1, 2));
        JTextField msgField = new JTextField("Введите сообщение");
        panelButtonSouth.add(msgField);
        panelButton.add(connectBtn);
        panelButton.add(msgButton);
        panelButtonSouth.add(panelButton);
        add(panelButtonSouth, BorderLayout.SOUTH);

        connectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chatField.append(ChatIO.readerChat());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        Action chatAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                String msgFromUser = msgField.getText()+"\n";
                chatField.append(msgFromUser);
                try {
                    ChatIO.writeChatToFile(msgFromUser);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        msgButton.addActionListener(chatAction);
        msgField.addActionListener(chatAction);

        setVisible(true);

    }
}
