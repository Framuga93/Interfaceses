package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    private int panelWidth;
    private int panelHeight;
    private int cellHeight;
    private int cellWidth;
    private static final Random RANDOM = new Random();
    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;
    private int fieldSizeY = 3;
    private int fieldSizeX = 3;
    private char[][] field;
    private static final int DOT_PADDING = 5;
    private int DOT_TO_WIN;

    private int gameOverType;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;

    private static final String MSG_WIN_HUMAN = "Human WIN";
    private static final String MSG_WIN_AI = "AI WIN";
    private static final String MSG_DRAW = "DRAW!";

    private boolean isGameOver;
    private boolean isInitialized;

    Map() {
//        setBackground(Color.BLACK); // устанавливаем на панель черный цвет
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
        isInitialized = false;
    }

    public void update(MouseEvent e) {
        if (isGameOver || !isInitialized) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        field[cellY][cellX] = HUMAN_DOT;
        repaint();
        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) return;
        aiTurn();
        repaint();
        checkEndGame(AI_DOT, STATE_WIN_AI);
    }


    void startNewGame(boolean mode, int fSzX, int fSzY, int wLen) {
        DOT_TO_WIN = wLen;
        System.out.printf("Mode: %b;\nSize: x=%d, y=%d;\nWin Length: %d\n",
                mode, fSzX, fSzY, wLen); //
        initMap();
        isGameOver = false;
        isInitialized = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        int cellNum = SettingWindow.sizeGameSlider.getValue();
        if(!isInitialized) return;
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / cellNum;
        cellWidth = panelWidth / cellNum;

        g.setColor(Color.BLACK);
        for (int h = 0; h < cellNum; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int w = 0; w < cellNum; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) continue;

                if (field[y][x] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else if (field[y][x] == AI_DOT) {
                    g.setColor(new Color(0xff0000));
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Unexp value" + field[y][x] + "in cell: x=" + x + " y=" + y);
                }
            }
        }
        if (isGameOver) showMessageGameOver(g);

    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,200,getWidth(),70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 36));
        switch (gameOverType){
            case STATE_DRAW:
                g.drawString(MSG_DRAW,180,getHeight()/2);
                break;
            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI,180,getHeight()/2);
                break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN,180,getHeight()/2);
                break;
            default:
                throw new RuntimeException("Unexp game over state: " + gameOverType);
        }
    }

    private void initMap() {
        fieldSizeY = 3;
        fieldSizeX = 3;
        field = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }

    private void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;
    }

    private boolean checkXY(int x, int y, int dir, int win) {
        char c = field[y][x];
        for (int i = 0; i < win; i++) {
            if (dir > 0 && (!isValidCell(x + i, y) || c != field[y][x+i]))
                return false;
            else if (dir < 0 && (!isValidCell(x, y + i) || c != field[y + i][x]))
                return false;
        }
        return true;
    }

    private boolean checkDiagonal(int x, int y, int dir, int win) {
        char c = field[x][y];
        for (int i = 0; i < win; i++) {
            if (!isValidCell(x + i, y + i * dir) || c != field[x + i][y + i * dir])
                return false;
        }
        return true;
    }

    private boolean checkWin(int dot) {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == dot)
                    if (checkXY(x, y, 1, DOT_TO_WIN) ||
                            checkXY(x, y, -1, DOT_TO_WIN) ||
                            checkDiagonal(x, y, -1, DOT_TO_WIN) ||
                            checkDiagonal(x, y, 1, DOT_TO_WIN))
                        return true;
            }
        }
        return false;
    }

    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    private boolean checkEndGame(int dot, int gameOverType){
        if (checkWin(dot)){
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()){
            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        return false;
    }


}
