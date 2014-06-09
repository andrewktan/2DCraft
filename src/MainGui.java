import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyListener;
public class MainGui extends JFrame{

	Map mainmap;
    Player player;

	public MainGui(int w)
	{
		mainmap = new Map (w);
        player = new Player();
        MapPanel mp = new MapPanel(mainmap, player);

        setSize(1024, 512);
        setResizable(false);
        setContentPane(mp);
        setVisible(true);
        setFocusable(true);
        addKeyListener(player);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
