import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapPanel extends JPanel implements Runnable, MouseListener, KeyListener {

    Player player;

    Map map;
    Tile[][] twodarray;
    private int focusedBlock = 1;

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
        for (int y = (int) fy; y < (int) fy + 32 + 1; y++) {
            for (int x = (int) fx; x < (int) fx + 64 + 1; x++) {
                g.drawImage(twodarray[y][x].getDisplaypic(),
                        (int) ((x - fx) * 16),
                        (int) ((y - fy) * 16),
                        null);
            }
        }
        // draw inventory bar
        int numItems = Map.baseimages.length - 1;
        g.fillRect((32 * 16) - (30 * numItems) + ((focusedBlock - 1) * 60) - 5,
                (27 * 16 - 5),
                60, 60); // show focused block
        for (int i = 1; i < numItems + 1; i++) {
            g.drawImage(Map.baseimages[i], (32 * 16) - (30 * numItems) + ((i - 1) * 60), // to center inventory bar
                    (27 * 16),
                    50, 50, null); // show focused block
            // show amount in inventory
            g.setColor(new Color(255, 220, 0));
            g.drawString(Integer.toString(player.getInventoryAmount(i)),
                    (32 * 16) - (30 * numItems) + ((i - 1) * 60) + 37,
                    (27 * 16 + 15));
        }

    }

    public void run() {

    }

    /** KeyListener Methods **/
    /**
     * Part of the KeyListener interface
     *
     * @param e
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Part of the KeyListener interface
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        // get coordinates of player
        int x = (int) Math.round(player.getRx());
        int y = (int) Math.round(player.getRy() + 2);

        // check if it's a number
        if (e.getKeyChar() > '0' && e.getKeyChar() < '9') {
            int i = (int) e.getKeyChar() - '0';
            if (player.getInventoryAmount(i) > 0 && map.getBlockType(x, y) == 0) {
                map.placeBlock(x, y,
                        i, // get numerical value
                        !e.isAltDown());
                player.takeFromInventory(i);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && map.getBlockType(x, y) != 6) { // cannot remove bedrock
            int i = map.removeBlock(x, y); // remove block below player
            if (i != -1 && i != 0)
                player.addToInventory(i); // add removed block to inventory
        } else if (e.getKeyCode() == KeyEvent.VK_E) {
            if (focusedBlock < Map.baseimages.length - 1) // check boundary
                focusedBlock++;
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            if (focusedBlock > 0) // check boundary
                focusedBlock--;
        }
    }

    /**
     * Part of the KeyListener interface
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {
    }

    /**
     * MouseListener Methods *
     */

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        Point p = e.getLocationOnScreen();

        int x = (int) Math.round(player.getFx() + ((p.getX()) / 16));
        int y = (int) Math.round(player.getFy() + ((p.getY() - 60) / 16));

        if (focusedBlock == 0 && map.getBlockType(x, y) != 6) { // cannot remove bedrock
            int i = map.removeBlock(x, y);
            if (i != 0)
                player.addToInventory(i);
        } else {
            if (player.getInventoryAmount(focusedBlock) > 0 && map.getBlockType(x, y) == 0) {
                map.placeBlock(x, y, focusedBlock, !e.isAltDown());
                player.takeFromInventory(focusedBlock);
            }
        }
    }
}
