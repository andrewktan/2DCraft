import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel implements Runnable {

    Map map;
    Player player;

    public MapPanel(Map map, Player player) {
        this.map = map;
        this.player = player;
        new Thread(this).start(); // start thread
    }

    public void paint(Graphics g) {
        super.paint(g);
        Tile[][] twodarray = map.getMapArray();
        //looping across the array to display images
        /*for(int h=0; h<map.getHeight(); h++) {
            for(int x=0; x<map.getWidth(); x++) {
                g.drawImage(twodarray[h][x].getDisplaypic(), x*16, h*16,null);
            }
        }*/
        player.show(g);
    }

    public void run() {
        while (true) {
            // refresh display at 30Hz
            repaint();
            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
