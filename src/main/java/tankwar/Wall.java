package tankwar;

import javax.tools.Tool;
import java.awt.*;

public class Wall {
    int x;
    int y;
    boolean horizontal;
    private int bricks;
    private final Image brickImage;

    public Wall(int x, int y, boolean horizontal, int bricks) {
        this.brickImage = Tools.getImage("brick.png");
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    public void draw(Graphics g){
        if(horizontal){
            Image brickImage = Tools.getImage("brick.png");
            for(int i = 0; i < bricks;i++){
                g.drawImage(brickImage, x+i*brickImage.getWidth(null), y, null);
            }
        }
        else{
            Image brickImage = Tools.getImage("brick.png");
            for(int i = 0; i < bricks;i++){
                g.drawImage(brickImage, x, y+i*brickImage.getHeight(null), null);
            }
        }
    }

    public Rectangle getRectangle(){
        return horizontal?
                new Rectangle(x,y,bricks*brickImage.getWidth(null),brickImage.getHeight(null)):
                new Rectangle(x,y,brickImage.getWidth(null),bricks*brickImage.getHeight(null));
    }
}
