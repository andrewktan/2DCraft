import javax.swing.*;
import java.awt.*;
public class MainGui extends JFrame{

	Map mainmap;

	public MainGui(int w)
	{
		mainmap = new Map (w);

        MapPanel mp = new MapPanel(mainmap, new Player());

        setSize(1024, 512);
        setResizable(false);
        setContentPane(mp);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
