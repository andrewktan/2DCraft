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
        // check if it's a number
        if (e.getKeyChar() > '0' && e.getKeyChar() < '9')
            map.placeBlock((int) Math.round(player.getRx()),
                    (int) Math.round(player.getRy() + 2),
                    (int) e.getKeyChar() - '0', // get numerical value
                    !e.isAltDown());
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
            map.removeBlock((int) Math.round(player.getRx()),
                    (int) Math.round(player.getRy() + 2)); // remove block below player
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
        map.placeBlock(x, y, 0, false);
    }
}
