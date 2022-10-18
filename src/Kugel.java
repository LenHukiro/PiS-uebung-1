import lombok.Getter;
import processing.core.PApplet;
import java.util.Random;

public class Kugel{
    @Getter
    int x, y, diameter, directionX, directionY;
    private final Random random = new Random();
    Billiardtisch billiardtisch = Billiardtisch.getMainClass();

    public Kugel() {
        diameter = 100;
        do{
            x = random.nextInt(0, Billiardtisch.getWidth());
            y = random.nextInt(0, Billiardtisch.getHeight());
        }while(!spaceIsntFree(x,y));

        directionX = getStartDirection();
        directionY = getStartDirection();
    }

    private boolean spaceIsntFree(int x, int y) {
        boolean isSpaceFree = true;
        for (Kugel kugel : billiardtisch.getKugeln()) {
            if(kugel == this) continue;
            if(PApplet.dist(x,y,kugel.x,kugel.y) <= diameter) isSpaceFree = false;
        }
    return isSpaceFree;
    }

    public void draw() {
        x += directionX;
        y += directionY;
        collisionDetection();
        billiardtisch.fill(billiardtisch.color(65));
        billiardtisch.getGraphics().ellipse(x, y, diameter, diameter);
    }

    private void collisionDetection() {
        int height = Billiardtisch.getHeight();
        int width = Billiardtisch.getWidth();

        for (Kugel kugel : billiardtisch.getKugeln()) {
            if (kugel == this) continue;

            int otherX = kugel.getX();
            int otherY = kugel.getY();

            if (PApplet.dist(x,y,otherX,otherY) <= diameter) {
                kugel.directionX *=-1;
                directionX *=-1;
                kugel.directionY *=-1;
                directionY *=-1;
            }

        }

        if (x + diameter / 2 >= width || x - diameter / 2 <= 0) directionX *= -1;// rechts und links
        if (y + diameter / 2 >= height || y - diameter / 2 <= 0) directionY *= -1;// unten und oben
    }

    public int getStartDirection() {
        boolean direction = random.nextBoolean();
        return direction ? 1 : -1;
    }
}
