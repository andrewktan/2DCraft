import javax.swing.*;
import java.awt.*;

public class MainGui extends JFrame {

    Map mainmap;
    //Player player;

    public MainGui(int w) {
        // declare and initialize
        mainmap = new Map(w);
        mainmap.save("map1");
        //player = new Player(mainmap);
        MapPanel mp = new MapPanel(mainmap);
        //PlayerPanel pp = new PlayerPanel(player);
        //pp.setOpaque(false);

        // add to panel
        add(mp, 0);
        //add(pp, 0);

        // position and size panels
        Insets insets = getInsets();
        mp.setBounds(insets.left, insets.top, 1024, 512);
        //pp.setBounds(insets.left, insets.top, 1024, 512);

        // JFrame options
        setLayout(null);
        setSize(1024, 512);
        setResizable(false);
        setVisible(true);
        setFocusable(true);
        //addKeyListener(player);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
