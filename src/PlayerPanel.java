import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel implements Runnable {
    Player player;
    JLabel coord = new JLabel();
    public PlayerPanel(Player player) {
        this.player = player;
        new Thread(this).start(); // run thread
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.show(g);
    }

    public void run() {
        while (true) {
            // refresh display at 30Hz
            coord.setText(String.format("x: %.2f | y: %.2f", player.getRx(), player.getRy()));
            add(coord);
            repaint();
            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
