import lombok.Getter;

import java.util.Random;

public class Kugel {
    @Getter
    int x, y, diametre;
    double directionX, directionY;
    private final Random random = new Random();
    private boolean flip = false;
    Billiardtisch billiardtisch = Billiardtisch.getBilliardtisch();

    public Kugel() {
        diametre = 10;
        do {
            x = random.nextInt(diametre, Billiardtisch.getWidth() - diametre);
            y = random.nextInt(diametre, Billiardtisch.getHeight() - diametre);
        } while (!spaceIsntFree());

        directionX = getStartDirection();
        directionY = getStartDirection();
    }

    private boolean spaceIsntFree() {
        for (Kugel kugel : billiardtisch.getKugeln()) {
            if (kugel == this) continue;
            if (circleDetection(kugel)) return false;
        }
        return true;
    }

    public void draw() {
        if(!flip) {
            x += directionX;
            y += directionY;
            collisionDetection();
        }
        flip = !flip;
        billiardtisch.fill(billiardtisch.color(65));
        billiardtisch.getGraphics().circle(x, y, diametre);
    }

    private void collisionDetection() {
        int height = Billiardtisch.getHeight();
        int width = Billiardtisch.getWidth();

        for (Kugel kugel : billiardtisch.getKugeln()) {
            if (kugel == this) continue;

            if (circleDetection(kugel)) {
                if (x > kugel.x && y > kugel.y) { // rechts oben auf links unten
                    directionY *= -1;
                    kugel.directionY *=-1;
                } else if (x > kugel.x && y < kugel.y) { //rechs unten auf links oben
                    directionY *= -1;
                    kugel.directionY *=-1;
                } else if (x < kugel.x && y > kugel.y) {//links oben auf rechts unten
                    directionY *= -1;
                    kugel.directionY *=-1;
                } else if (x < kugel.x && y < kugel.y) {
                    directionY *= -1;
                    kugel.directionY *=-1;
                } else if (x == kugel.x) {
                    directionX *= -1;
                    kugel.directionX *=-1;
                } else {
                    directionY *= -1;
                    kugel.directionY *=-1;
                }
            }
        }

        if (x + diametre / 2 == width || x - diametre / 2 == 0) directionX *= -1;// rechts und links
        if (y + diametre / 2 == height || y - diametre / 2 == 0) directionY *= -1;// unten und oben
    }

    private boolean circleDetection(Kugel kugel) {
        int dx = kugel.getX() - x;
        int dy = kugel.getY() - y;

        double distance = Math.sqrt((dx * dx + dy * dy));
        int sumofRadii = (diametre + kugel.diametre)/2;
        return distance <= sumofRadii;
    }

    public double getStartDirection() {
        boolean direction = random.nextBoolean();
        return direction ? 1 : -1;
    }
}
