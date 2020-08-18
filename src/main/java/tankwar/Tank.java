package tankwar;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class Tank {

    private int TANK_SPEED = 5;
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
        return direction.getImage(prefix+"tank");

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
            case KeyEvent.VK_A:
                SuperFire();
                break;
        }
    }

    private void SuperFire() {
        Missile missileU = new Missile(x+getImage().getHeight(null)/2-6, y+getImage().getWidth(null)/2-6,enemy,Direction.UP);
        GameClient.getInstance().getMissiles().add(missileU);
        Missile missileD = new Missile(x+getImage().getHeight(null)/2-6, y+getImage().getWidth(null)/2-6,enemy,Direction.DOWN);
        GameClient.getInstance().getMissiles().add(missileD);
        Missile missileL = new Missile(x+getImage().getHeight(null)/2-6, y+getImage().getWidth(null)/2-6,enemy,Direction.LEFT);
        GameClient.getInstance().getMissiles().add(missileL);
        Missile missileR = new Missile(x+getImage().getHeight(null)/2-6, y+getImage().getWidth(null)/2-6,enemy,Direction.RIGHT);
        GameClient.getInstance().getMissiles().add(missileR);
        Missile missileUL = new Missile(x+getImage().getHeight(null)/2-6, y+getImage().getWidth(null)/2-6,enemy,Direction.UPLEFT);
        GameClient.getInstance().getMissiles().add(missileUL);
        Missile missileUR = new Missile(x+getImage().getHeight(null)/2-6, y+getImage().getWidth(null)/2-6,enemy,Direction.UPRIGHT);
        GameClient.getInstance().getMissiles().add(missileUR);
        Missile missileDL = new Missile(x+getImage().getHeight(null)/2-6, y+getImage().getWidth(null)/2-6,enemy,Direction.DOWNLEFT);
        GameClient.getInstance().getMissiles().add(missileDL);
        Missile missileDR = new Missile(x+getImage().getHeight(null)/2-6, y+getImage().getWidth(null)/2-6,enemy,Direction.DOWNRIGHT);
        GameClient.getInstance().getMissiles().add(missileDR);

        playAudio("assert/audios/supershoot.wav");
    }

    private void playAudio(String FileName) {
        Media sound = new Media(new File(FileName).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    private void fire() {
        Missile missile = new Missile(x+getImage().getHeight(null)/2-6, y+getImage().getWidth(null)/2-6,enemy,direction);
        GameClient.getInstance().getMissiles().add(missile);

        playAudio("assert/audios/shoot.wav");

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
        x += direction.xFactor * TANK_SPEED;
        y += direction.yFactor * TANK_SPEED;
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
