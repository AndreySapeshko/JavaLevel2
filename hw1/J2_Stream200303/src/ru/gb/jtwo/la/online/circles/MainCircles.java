package ru.gb.jtwo.la.online.circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private Sprite[] sprites = new Sprite[10];
    private int numberOfBallsShown;
    private Background background;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        initApplication();
        numberOfBallsShown = 10;
        background = new Background(70, 200, 210);

        MainCanvas canvas = new MainCanvas(this);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    if (numberOfBallsShown > 0) {
                        numberOfBallsShown--;
                        if (numberOfBallsShown < 0) {
                            numberOfBallsShown = 0;
                        }
                    }
                }
                if (e.getButton() == 3) {
                    if (numberOfBallsShown < 10) {
                        numberOfBallsShown++;
                    } else if (numberOfBallsShown > 10) {
                        numberOfBallsShown = 10;
                    }
                }
            }
        });
        add(canvas);
        setVisible(true);
    }

    private void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
    }

    public void onCanvasRepainted(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        background.update(deltaTime);
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        background.render(canvas);
        for (int i = 0; i < numberOfBallsShown; i++) {
            sprites[i].render(canvas, g);
        }
    }

}
