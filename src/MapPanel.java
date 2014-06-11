import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel implements Runnable {

    Player player;

    Map map;
    Tile[][] twodarray;

    public MapPanel(Map map, Player player) {
        // initialize map
        this.map = map;
        twodarray = map.getMapArray();
        // initialize player
        this.player = player;
        repaint();
        //new Thread(this).start(); // start thread
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        double fx = player.getFx();
        double fy = player.getFy();
        //looping across the array to display images
        for (int h = (int) fy; h < (int) fy + 32 + 1; h++) {
            for (int x = (int) fx; x < (int) fx + 64 + 1; x++) {
                g.drawImage(twodarray[h][x].getDisplaypic(),
                        (int)((x - fx) * 16),
                        (int)((h - fy) * 16),
                        null);
            }
        }
    }

    public void run() {

    }
}
