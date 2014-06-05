import javax.swing.*;
import java.awt.*;
public class MainGui extends JFrame{

	Map mainmap;

	public MainGui(int w)
	{
		mainmap = new Map (w);
		mainmap.debugDraw();
	}
}
