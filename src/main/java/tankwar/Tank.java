package tankwar;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class Tank {

    private int x;
    private int y;


    private Direction direction;
    boolean enemy;

    public Tank(int x, int y, boolean enemy, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = false;
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
        String prefix = enemy? "e" : "";
        switch (direction) {
            case UP:
                return new ImageIcon("assert/images/"+prefix+"tankU.gif").getImage();
            case DOWN:
                return new ImageIcon("assert/images/"+prefix+"tankD.gif").getImage();
            case LEFT:
                return new ImageIcon("assert/images/"+prefix+"tankL.gif").getImage();
            case RIGHT:
                return new ImageIcon("assert/images/"+prefix+"tankR.gif").getImage();
            case UPLEFT:
                return new ImageIcon("assert/images/"+prefix+"tankLU.gif").getImage();
            case DOWNLEFT:
                return new ImageIcon("assert/images/"+prefix+"tankLD.gif").getImage();
            case UPRIGHT:
                return new ImageIcon("assert/images/"+prefix+"tankRU.gif").getImage();
            case DOWNRIGHT:
                return new ImageIcon("assert/images/"+prefix+"tankRD.gif").getImage();
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
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }
    }

    private void fire() {
        Missile missile = new Missile(x+getImage().getHeight(null)/2-6, y+getImage().getWidth(null)/2-6,enemy,direction);
        GameClient.getInstance().getMissiles().add(missile);

        Media sound = new Media(new File("assert/audios/shoot.wav").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

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

    public Rectangle getRectangle(){
        return new Rectangle(x,y,getImage().getWidth(null),getImage().getHeight(null));
    }


    void draw(Graphics g) {
        int prevx = x;
        int prevy = y;
        this.determineDirection();
        this.move();

        if (x < 0) x = 0;
        else if (x > 800 - getImage().getWidth(null)) x = 800 - getImage().getWidth(null);
        if (y < 0) y = 0;
        else if (y > 600 - getImage().getHeight(null)) y = 600 - getImage().getHeight(null);


        Rectangle rec = this.getRectangle();
        for (Wall wall : GameClient.getInstance().getWalls()) {
            if(rec.intersects(wall.getRectangle())){
                x = prevx;
                y = prevy;
                break;
            }
        }

        for(Tank tank : GameClient.getInstance().getEnemyList()){
            if(rec.intersects(tank.getRectangle())){
                x = prevx;
                y = prevy;
                break;
            }
        }


        g.drawImage(this.getImage(), this.x, this.y, null);
    }

}
