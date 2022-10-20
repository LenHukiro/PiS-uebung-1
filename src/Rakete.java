import processing.core.PImage;

public class Rakete {

    int x, y,height,width;
    PImage photo;

    public Rakete(int x, int y) {
     this(x,y,30,30);
    }

    public Rakete(int x, int y,int height, int width) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        photo = Billiardtisch.getBilliardtisch().loadImage("rocket.png");
    }

    public void draw() {
    Billiardtisch.getBilliardtisch().image(photo,x,y-height,30,40);
    }


    public void up() {
        if(y-height> 0){
            y-=2;
        }

    }
    public void down() {
        if(y< Billiardtisch.getHeight()){
            y+=2;
        }
    }
}
