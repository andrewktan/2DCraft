import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;


public class Tile {

private Image displaypic;
private int id;

public Tile(int tileid)
{
	setId(tileid);
	try {
		Image displaypic= ImageIO.read(new File("D:\\2DCraft\\useful minecraft\\"+Integer.toString(tileid)+".png"));
	} catch (IOException e) {
	}
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
