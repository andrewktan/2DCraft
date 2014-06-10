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
        int fx = (int) player.getFx();
        int fy = (int) player.getFy();
        //looping across the array to display images
        for (int h = fy; h < fy + 32; h++) {
            for (int x = fx; x < fx + 64; x++) {
                g.drawImage(twodarray[h][x].getDisplaypic(), (x - fx) * 16, (h - fy) * 16, null);
            }
        }
    }

    public void run() {

    }
}
