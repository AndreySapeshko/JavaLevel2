package ru.gb.jtwo.la.online.circles;

import java.awt.*;
import java.util.HashMap;

public class Background {
    private int r;
    private int g;
    private int b;
    private Character keyR;
    private Character keyG;
    private Character keyB;
    private HashMap<Character, Integer> speedOfColorChange;

    public Background(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        keyR = 'r';
        keyG = 'g';
        keyB = 'b';
        speedOfColorChange = new HashMap<>();
        speedOfColorChange.put(keyR, 50);
        speedOfColorChange.put(keyG, 50);
        speedOfColorChange.put(keyB, 50);
    }

    public Color colorBackground() {
        return new Color(r, g, b);
    }

    public int changeColor(int rgb, float time, Character keyRGB) {
        int speed = speedOfColorChange.get(keyRGB);
        rgb += speed * time;
        if (rgb > 255) {
            rgb = 255;
            speedOfColorChange.put(keyRGB, -speed);
        } else if (rgb < 10) {
            rgb = 10;
            speedOfColorChange.put(keyRGB, -speed);
        }
        return rgb;
    }

    public void update(float deltaTime) {
        r = changeColor(r, deltaTime, keyR);
        g = changeColor(g, deltaTime, keyG);
        b = changeColor(b, deltaTime, keyB);
    }

    public void render(MainCanvas canvas) {
        canvas.setBackground(colorBackground());
    }
}
