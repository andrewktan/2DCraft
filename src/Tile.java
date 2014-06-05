import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;

import java.util.*;

public class Tile {
	private int id;
	private Image displaypic;
	//previously loaded images
	private static Image[] baseimages;
	//figure out if its gone through the initialize stage
	private boolean ini = false;
	
	public Tile(int tileid)
	{
		id=tileid;
		if(!ini)
		{
			initialize();
			ini=true;
		}
	}

	public void initialize()
	{
		//finding out number of possible tiles
		File tilelist= new File("D:\\2DCraft\\useful minecraft\\tiles.txt");
		int tilelength=0;
		Scanner scan;

		try {
			scan = new Scanner(tilelist);
			while(scan.hasNextLine())
			{
				if (!(scan.nextLine ()).isEmpty ())
				{
					tilelength++;
				}
			}
			scan.close();

		} catch (FileNotFoundException e1) {System.out.println("Tile declarations not found");
		}
		//setting image array size to number of tiles
		baseimages=new Image[tilelength];
		for(int i=0;i<tilelength;i++)
		{
			try{
				baseimages[i]=ImageIO.read(new File("D:\\2DCraft\\useful minecraft\\"+i+".png"));
			}
			catch(IOException e)
			{
				System.out.println("Image not found");
			}
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
