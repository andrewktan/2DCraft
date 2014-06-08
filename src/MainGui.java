import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyListener;
public class MainGui extends JFrame{

	Map mainmap;

	public MainGui(int w)
	{
		mainmap = new Map (w);
		mainmap.save("map1");
        MapPanel mp = new MapPanel(mainmap);
        
        
        
        setSize(1024, 512);
        setResizable(false);
        setContentPane(mp);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
