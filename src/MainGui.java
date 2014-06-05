import javax.swing.*;
import java.awt.*;
public class MainGui extends JFrame{

	Map mainmap;
	public MainGui(int w)
	{
		setSize(1000,500);
		setLayout(new BorderLayout());
		mainmap = new Map (w);
		DrawArea gamescreen = new DrawArea(1000,500);
		JPanel game = new JPanel();
		game.add(gamescreen);
		add(game,BorderLayout.NORTH);
		repaint();
	}
	
	class DrawArea extends JPanel
    {
            public DrawArea (int width, int height)
            {
                    this.setPreferredSize (new Dimension (width, height)); // size
            }
            public void paintComponent (Graphics g)
            {
                    mainmap.show (g,0,0);
            }
    }
}
