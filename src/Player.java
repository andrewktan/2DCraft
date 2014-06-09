import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Player implements KeyListener {

    // position and velocity variables
    private double rx = 0, ry = 0, vx = 0, vy = 0;

    // images
    private Image left, right, still;

    /**
     * Default constructor for player
     */
    public Player() {
        loadImages();
    }

    /**
     * Constructor given starting position
     * @param rx
     * @param ry
     */
    public Player(double rx, double ry) {
        loadImages();
        this.rx = rx;
        this.ry = ry;
    }

    /**
     * Load images
     */
    private void loadImages() {
        try {
            // load player images
            left = ImageIO.read(getClass().getResource("resources/pl.png"));
            right = ImageIO.read(getClass().getResource("resources/pr.png"));
            still = ImageIO.read(getClass().getResource("resources/ps.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add player to JPanel
     * @param g
     * @return
     */
    public void show(Graphics g) {
        // calculate position in terms of pixels
        int posx = (int) rx*15;
        int posy = (int) ry*15;

        // choose image
        Image player = (vx > 0) ? right : left;

        // draw image
        g.drawImage(player, posx, posy, null);
    }

    /**
     * Part of the KeyListener interface
     * @param e
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Part of the KeyListener interface
     * @param e
     */
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Part of the KeyListener interface
     * @param e
     */
    public void keyReleased(KeyEvent e) {

    }
}
