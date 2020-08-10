package tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameClient extends JComponent {

    public Tank playerTank;
    ArrayList<Tank> enemyList;
    private List<Wall> walls;

    public GameClient(){
        this.playerTank = new Tank(400, 100, Direction.DOWN);
        this.enemyList = new ArrayList<Tank>(12);
        this.walls = Arrays.asList(
            new Wall(200,140,true,15),
            new Wall(200,540,true,15),
            new Wall(100,80,false,15),
            new Wall(700,80,false,15)
        );

        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++){
                this.enemyList.add(new Tank(200+j*60,300+i*60,true, Direction.UP));
            }
        }

        this.setPreferredSize(new Dimension(800,600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        playerTank.draw(g);
        for(Tank tank: enemyList){
            tank.draw(g);
        }
        for(Wall wall: walls){
            wall.draw(g);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Tank War");
        GameClient client = new GameClient();
        client.repaint();
        frame.add(client);
        frame.setIconImage(new ImageIcon("assert/images/icon.png").getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                client.playerTank.KeyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                client.playerTank.KeyPressed(e);

            }
        });
        while(true){
            client.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
