import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel implements Runnable {

    Map map;

    public MapPanel(Map map) {
        this.map = map;
        repaint();
        new Thread(this).start(); // start thread
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Tile[][] twodarray = map.getMapArray();
        //looping across the array to display images
        for(int h=96; h<128; h++) {
            for(int x=0; x<64; x++) {
                g.drawImage(twodarray[h][x].getDisplaypic(), x*16, (h-96)*16 , null);
            }
        }

    }

    public void run() {

    }
}
