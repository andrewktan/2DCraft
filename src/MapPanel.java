import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    Map map;
    Player player;

    public MapPanel(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Tile[][] twodarray = map.getMapArray();

        for(int h=0; h<map.getHeight(); h++) {
            for(int x=0; x<map.getWidth(); x++) {
                g.drawImage(twodarray[h][x].getDisplaypic(), x*16, h*16,null);
            }
        }
        System.out.println("You should see something now");

        player.show(g);
    }
}
