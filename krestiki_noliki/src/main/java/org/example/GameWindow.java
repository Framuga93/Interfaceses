package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final int WINDOW_HIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;

    JButton btnStart = new JButton("New game"); // экземляр класса кнопки. Его инициализация
    JButton btnExit = new JButton("Exit"); // same

    Map map;
    SettingWindow settingWindow;


    GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY); //размещение
        setSize(WINDOW_WIDTH, WINDOW_HIGHT); //размер
        setTitle("TicToeGame"); // Тайтл
        setResizable(false); // запрет пользователю на изменения окна
//        add(btnStart); // добавление кнопки на окно

        map = new Map(); // создаем объект поля игры
        settingWindow = new SettingWindow(this); // создаем стартовое окно

        btnExit.addActionListener(new ActionListener() {  // Обработчик событий
            @Override
            public void actionPerformed(ActionEvent e) {    //  действие произошло
                System.exit(0);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingWindow.setVisible(true); // устанавливаем видимость (запускаем окно)
            }
        });


        JPanel ponBottom = new JPanel(new GridLayout(1, 2)); // панель для размещения других элементов.
        // Внутри панели для элементов можно импользовать собственный Layout компановщик
        ponBottom.add(btnStart); // размещаем кнопку на панели
        ponBottom.add(btnExit);
        add(ponBottom, BorderLayout.SOUTH); //размещаем панель
        add(map); // добавляем объект поля игры на окно

        setVisible(true); // видимость
    }

    void startNewGame(boolean mode, int fSzX, int fSzY, int wLen) {
        map.startNewGame(mode, fSzX, fSzY, wLen);
    }
}
