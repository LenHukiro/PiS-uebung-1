import lombok.Getter;

import java.util.Random;

public class Kugel {
    @Getter
    int x, y, radius;
    double directionX, directionY;
    private final Random random = new Random();
    Billiardtisch billiardtisch = Billiardtisch.getBilliardtisch();

    public Kugel() {
        radius = 50;
        do {
            x = random.nextInt(radius, Billiardtisch.getWidth()-radius);
            y = random.nextInt(radius, Billiardtisch.getHeight()-radius);
        } while (!spaceIsntFree());

        directionX = getStartDirection();
        directionY = getStartDirection();
    }

    private boolean spaceIsntFree() {
        for (Kugel kugel : billiardtisch.getKugeln()) {
            if (kugel == this) continue;
            if(circleDetection(kugel)) return false;
        }
        return true;
    }

    public void draw() {
        x += directionX;
        y += directionY;
        collisionDetection();
        billiardtisch.fill(billiardtisch.color(65));
        billiardtisch.getGraphics().circle(x, y, radius);
    }

    private void collisionDetection() {
        int height = Billiardtisch.getHeight();
        int width = Billiardtisch.getWidth();

        for (Kugel kugel : billiardtisch.getKugeln()) {
            if (kugel == this) continue;

            if (circleDetection(kugel)) {
        //test
            }
        }

        if (x + radius / 2 == width || x - radius / 2 == 0) directionX *= -1;// rechts und links
        if (y + radius / 2 == height || y - radius / 2 == 0) directionY *= -1;// unten und oben
    }

    private boolean circleDetection(Kugel kugel) {
        int dx = kugel.getX() - x;
        int dy = kugel.getY() - y;

        double distance = Math.sqrt((double) (dx * dx + dy * dy));
        int sumofRadii = radius + kugel.radius;
        return distance <= sumofRadii;
    }

    public double getStartDirection() {
        boolean direction = random.nextBoolean();
        return direction ? 1 : -1;
    }
}
