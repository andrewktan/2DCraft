import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;

import java.util.*;

public class Tile {
	private int id;
	private Image displaypic;

	public Tile(int tileid, Image display)
	{
		displaypic=display;
		id=tileid;
	}

	public Image getDisplaypic() {
		return displaypic;
	}

	public void setDisplaypic(Image displaypic) {
		this.displaypic = displaypic;
	}

	public void show (Graphics g, int x, int y)
	{
		g.drawImage(displaypic, x, y, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
