package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    private static final int WINDOW_HIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final String CHOSE_GAME = "Выберите режим игры";
    private static final String GAME_SIZE = "Размер игрового поля: ";
    private static final String DOTS_TO_WIN = "Количество точек для победы: ";
    private static final int SIZE_GAME_MIN = 3;
    private static final int SIZE_GAME_MAX = 10;
    private static final int GAME_LENGTH_MIN = 3;
    private static final int GAME_LENGTH_MAX = 10;
    static JSlider gameLengthSlider;
    static JSlider sizeGameSlider;
    private GameWindow gameWindow;
    JRadioButton buttonPvp = new JRadioButton("PVP");
    JRadioButton buttonPve = new JRadioButton("PVE",true);

    JButton btnStart = new JButton("Старт игры"); // экземляр класса кнопки. Его инициализация

    SettingWindow(GameWindow gameWindow) { // Стартовой окно. Создаем конструктор который принимает экземпляр игрового окна
        this.gameWindow = gameWindow;
        setLocationRelativeTo(gameWindow); // устанавливаем положение относительным главному окну
        setSize(WINDOW_WIDTH, WINDOW_HIGHT);


        setLayout(new GridLayout(10, 1));


        add(new JLabel(CHOSE_GAME));
        ButtonGroup switchGameOptions = new ButtonGroup();
        switchGameOptions.add(buttonPve);
        switchGameOptions.add(buttonPvp);
        add(buttonPve);
        add(buttonPvp);


        JLabel setGameSizeLabel = new JLabel("Установите размер игрового поля");
        JLabel gameSize = new JLabel(GAME_SIZE);
        add(setGameSizeLabel);
        add(gameSize);
        sizeGameSlider = new JSlider(SIZE_GAME_MIN, SIZE_GAME_MAX, SIZE_GAME_MIN);
        getInteractiveNumbersListener(sizeGameSlider, gameSize, GAME_SIZE);



        JLabel setGameLengthLabel = new JLabel("Установите количество точек для победы");
        JLabel gameLength = new JLabel(DOTS_TO_WIN);
        add(setGameLengthLabel);
        add(gameLength);
        gameLengthSlider = new JSlider(GAME_LENGTH_MIN, GAME_LENGTH_MAX, GAME_LENGTH_MIN);
        getInteractiveNumbersListener(gameLengthSlider, gameLength, DOTS_TO_WIN);


        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean mode = buttonPve.isSelected(); // true - PVE, false - PVP
                int size = sizeGameSlider.getValue();
                int wLen = gameLengthSlider.getValue();
                gameWindow.startNewGame(mode, size, size, wLen);
                setVisible(false);
            }
        });
        add(btnStart);

    }


    private void getInteractiveNumbersListener(JSlider slider, JLabel field, String mssg) {
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                field.setText(mssg + slider.getValue());
            }
        });
        add(slider);
    }
}
