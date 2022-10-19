import processing.core.PApplet;

import java.util.ArrayList;

public class Billiardtisch extends PApplet {

    private final ArrayList<Kugel> kugeln = new ArrayList<>();
    private static final int width = 1000;
    private static final int height = 1000;
    private static Billiardtisch billiardtisch = null;

    public static void main(String... args) {
        PApplet.runSketch(new String[]{""}, new Billiardtisch());
    }

    Billiardtisch() {
        super();
        billiardtisch = this;
    }

    public void settings() {
        size(width, height);
    }

    public void setup() {
        for (int i = 0; i < 5; i++) {
            kugeln.add(new Kugel());
        }
    }

    public void draw() {
        background(255);
        for (Kugel kugel : kugeln) {
            kugel.draw();
        }
    }


    public ArrayList<Kugel> getKugeln() {
        return kugeln;
    }

    public static Billiardtisch getBilliardtisch() {
        return billiardtisch;
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

}
