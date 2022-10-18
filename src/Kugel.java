import lombok.Getter;
import processing.core.PShape;

import java.util.Random;

public class Kugel{
    PShape shape;
    @Getter
    int x, y, diameter, directionX, directionY;
    private final Random random = new Random();
    Billiardtisch billiardtisch = Billiardtisch.getMainClass();

    public Kugel(PShape shape) {
        this.shape = shape;
        diameter = 100;
        x = random.nextInt(0, Billiardtisch.getHeight());
        y = random.nextInt(0, Billiardtisch.getHeight());
        directionX = getStartDirection();
        directionY = getStartDirection();
    }
    public void draw() {
        x += directionX;
        y += directionY;
        collisionDetection();
        billiardtisch.fill(billiardtisch.color(65));
        billiardtisch.g.ellipse(x, y, diameter, diameter);
    }

    private void collisionDetection() {
        int height = Billiardtisch.getHeight();
        int width = Billiardtisch.getWidth();

        for (Kugel kugel : billiardtisch.getKugeln()) {
            if (kugel == this) continue;

            int otherX = kugel.getX();
            int otherY = kugel.getY();

            if (x-otherX == diameter/2|| x-otherX == -1*diameter/2) directionX *= -1;
            if (y-otherY == diameter/2|| x-otherY == -1*diameter/2) directionY *= -1;
        }

        if (x + diameter / 2 >= width || x - diameter / 2 <= 0) directionX *= -1;// rechts und links
        if (y + diameter / 2 >= height || y - diameter / 2 <= 0) directionY *= -1;// unten und oben
    }

    public int getStartDirection() {
        boolean direction = random.nextBoolean();
        return direction ? 1 : -1;
    }
}
