package tankwar;

import javax.swing.*;
import java.awt.*;

public class Missile {

    private static final int SPEED = 10;

    private int x;
    private int y;

    private final boolean enemy;

    private final Direction direction;

    public Missile(int x, int y, boolean enemy, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }

    Image getImage() {
        return direction.getImage("missile");
    }

    void move(){
        switch(this.direction){
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case UPRIGHT:
                x+=SPEED;
                y-=SPEED;
                break;
            case DOWNRIGHT:
                x+=SPEED;
                y+=SPEED;
                break;
            case UPLEFT:
                x-=SPEED;
                y-=SPEED;
                break;
            case DOWNLEFT:
                x-=SPEED;
                y+=SPEED;
        }
    }

    public void draw(Graphics g) {
        move();
        if(x<0||x>800||y<0||y>800){
            //GameClient.getInstance().getMissiles().remove(this);
            return;
        }
        g.drawImage(this.getImage(), this.x, this.y, null);
    }
}
