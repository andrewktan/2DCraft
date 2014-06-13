import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Player implements KeyListener, Runnable {

    Map map; // temporary map

    // variables to fix issue with key release
    private boolean released = false;

    // other flags
    private boolean inAir = false;

    // position and velocity variables
    private double rx = 500, ry = 96, vx = 0, vy = 0;
    private double move_vx = 7, jump_vy = 8;

    // field of view
    private double fx = 500, fy = 100;

    // images
    private Image left, right, still;

    /**
     * Default constructor for player
     */
    public Player(Map map) {
        loadImages();
        new Thread(this).start();
        this.map = map;
    }

    /**
     * Constructor given starting position
     *
     * @param rx
     * @param ry
     */
    public Player(double rx, double ry) {
        loadImages();
        this.rx = rx;
        this.ry = ry;
        new Thread(this).start();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add player to JPanel
     *
     * @param g
     * @return
     */
    public void show(Graphics g) {
        // calculate position in terms of pixels
        int posx = (int) ((rx - fx) * 16);
        int posy = (int) ((ry - fy) * 16);
        // choose image
        Image player;
        if (vx > 0)
            player = right;
        else if (vx < 0)
            player = left;
        else
            player = still;

        // draw image
        g.drawImage(player, posx, posy, null);
    }

    /**
     * Accessor for field of view x
     *
     * @return
     */
    public double getFx() {
        return fx;
    }

    /**
     * Accessor for field of view y
     *
     * @return
     */
    public double getFy() {
        return fy;
    }

    /**
     * Accessor for position in x
     *
     * @return
     */
    public double getRx() {
        return rx;
    }

    /**
     * Accessor for position in y
     *
     * @return
     */
    public double getRy() {
        return ry;
    }

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
        released = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                vx = -move_vx;
                break;
            case KeyEvent.VK_D:
                vx = move_vx;
                break;
            case KeyEvent.VK_W:
                if (!inAir)
                    vy = -jump_vy; // hops
                break;
        }
    }

    /**
     * Part of the KeyListener interface
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                released = true;
                break;
        }
    }

    /**
     * Physics thread
     */
    public void run() {
        int timestep = 33; // in ms
        while (true) {
            if (released) {
                vx = 0;
            }
            // x direction
            //if (!map.isSolid((int) (rx + (vx * timestep / 1000)), (int) ry)) {
            if (!map.isSolid((int) rx, (int) ry + 1)) {
                rx += vx * (double) timestep / 1000; // update x-pos
            } else {
                rx = Math.round(rx); // stop movement <FIX>
                vx = 0;
            }

            // y direction
            ry += vy * (double) timestep / 1000; // update y-pos
            if (!map.isSolid((int) rx, (int) (ry + 2))) {
                vy += 20 * (double) timestep / 1000; // gravity
                inAir = true;
            } else {
                ry = Math.floor(ry); // bring to top of block
                vy = 0; // floor
                inAir = false;
            }

            // pan camera
            double vx_pan = 0, vy_pan = 0;
            // x-direction
            if (rx - fx < 10)
                vx_pan = -(Math.abs(vx) + 1);
            else if ((fx + 64) - rx < 10)
                vx_pan = Math.abs(vx) + 1;
            else
                vx_pan = 0;
            fx += vx_pan * (double) timestep / 1000;

            if (fx < 0)
                fx = 0;
            else if (fx + 64 > map.getWidth())
                fx = map.getWidth() - 64 - 1;

            // y-direction
            if (ry - fy < 10)
                vy_pan = -(Math.abs(vy) + 5);
            else if ((fy + 32) - ry < 10)
                vy_pan = Math.abs(vy) + 5;

            fy += vy_pan * (double) timestep / 1000;

            if (fy < 0)
                fy = 0;
            else if (fy + 32 > map.getHeight())
                fy = map.getHeight() - 32 - 1;

            try {
                Thread.sleep(timestep); // 30Hz refresh rate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
