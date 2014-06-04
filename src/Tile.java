import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Tile {

private Image displaypic;

public Tile(int tileid)
{
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

}
