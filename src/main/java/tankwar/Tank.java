package tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {

    private int x;
    private int y;

    private Direction direction;

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    Image getImage() {
        switch (direction) {
            case UP:
                return new ImageIcon("assert/images/tankU.gif").getImage();
            case DOWN:
                return new ImageIcon("assert/images/tankD.gif").getImage();
            case LEFT:
                return new ImageIcon("assert/images/tankL.gif").getImage();
            case RIGHT:
                return new ImageIcon("assert/images/tankR.gif").getImage();
            case UPLEFT:
                return new ImageIcon("assert/images/tankLU.gif").getImage();
            case DOWNLEFT:
                return new ImageIcon("assert/images/tankLD.gif").getImage();
            case UPRIGHT:
                return new ImageIcon("assert/images/tankRU.gif").getImage();
            case DOWNRIGHT:
                return new ImageIcon("assert/images/tankRD.gif").getImage();
        }
        return null;
    }

    private boolean up, down, left, right;

    public void KeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
        }
    }

    public void KeyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
        }
    }

    private boolean stopped;

    public void determineDirection() {
        if (!up && !right && !down && !left) {
            this.stopped = true;
        } else {
            if (up && left && !down && !right) this.direction = Direction.UPLEFT;
            else if (up && !left && !down && right) this.direction = Direction.UPRIGHT;
            else if (!up && !left && down && right) this.direction = Direction.DOWNRIGHT;
            else if (!up && left && down && !right) this.direction = Direction.DOWNLEFT;
            else if (up && !left && !down && !right) this.direction = Direction.UP;
            else if (!up && !left && !down && right) this.direction = Direction.RIGHT;
            else if (!up && !left && down && !right) this.direction = Direction.DOWN;
            else if (!up && left && !down && !right) this.direction = Direction.LEFT;
            this.stopped = false;
        }
    }

    void move(){
        if(this.stopped)
            return;
        switch(this.direction){
            case UP:
                y-=5;
                break;
            case DOWN:
                y+=5;
                break;
            case LEFT:
                x-=5;
                break;
            case RIGHT:
                x+=5;
                break;
            case UPRIGHT:
                x+=5;
                y-=5;
                break;
            case DOWNRIGHT:
                x+=5;
                y+=5;
                break;
            case UPLEFT:
                x-=5;
                y-=5;
                break;
            case DOWNLEFT:
                x-=5;
                y+=5;
        }
    }

    void draw(Graphics g){
        this.determineDirection();
        this.move();
        g.drawImage(this.getImage(),this.x,this.y,null);
    }


}
