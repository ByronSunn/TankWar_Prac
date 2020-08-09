package tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent {

    public Tank playerTank;

    public GameClient(){
        this.playerTank = new Tank(400, 100, Direction.DOWN);

        this.setPreferredSize(new Dimension(800,600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        playerTank.draw(g);
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
