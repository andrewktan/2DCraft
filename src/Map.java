import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Map {

	private Tile[][] twodarray;
	//reminder to self: twodarray[row][column]
	private int h, w;
	//preloaded images
	public static Image[] baseimages;

	public Map(int width) {
		//parse in images
		initialize();
		h = 256;
		w = width;
		//space created
		twodarray = new Tile[h][width];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				twodarray[i][j] = new Tile(0, baseimages[0], false);
			}
		}
		//stone and dirts
		//initial elevations
		int stoneend = (int) (Math.random() * 3) + h / 2;
		int dirtend = (int) (stoneend - (Math.random() * 2 + 3));
		//keeping track of changes
		int netchange = 0;
		char updown = 'n';

		//left to right, varying elevation
		for (int i = 0; i < width; i++) {
			//dirt and stone generation
			//weighted randomly increase or decrease
			double randchange = Math.random() * 10;
			int dirtchange = 0;
			int stonechange = 0;
			if (randchange > 8) {
				stonechange++;
				dirtchange++;
				netchange++;
				updown = 'd';
			} else if (randchange < 2) {
				stonechange--;
				dirtchange--;
				netchange--;
				updown = 'u';
			} else {
				//don't do anything
			}
			//adjusting for elevation
			double randhchange = Math.random() * netchange;
			if (randhchange > h / 8) {
				stonechange--;
				dirtchange--;
				netchange--;
			} else if (randhchange < -1 * h / 8) {
				stonechange++;
				dirtchange++;
				netchange++;
			} else {
				//do nothing
			}
			stoneend = stoneend + stonechange;
			dirtend = dirtend + dirtchange;
			for (int j = h - 1; j > stoneend; j--) {
				twodarray[j][i] = new Tile(3, baseimages[3]);
			}
			for (int j = stoneend; j > dirtend; j--) {
				try {
					twodarray[j][i] = new Tile(1, baseimages[1]);
				} catch (Exception e) {
					System.out.println("j" + j + "i" + i);
				}
			}
			//grass layer
			twodarray[dirtend][i] = new Tile(2, baseimages[2]);
			//bedrock layer
			twodarray[h - 1][i] = new Tile(6, baseimages[6]);
		}
		
		//getting rid of the weird 1block /dip things
		for (int i = 1; i < width-1; i++) {
			int a=getSurface(i-1);
			int b=getSurface(i);
			int c=getSurface(i+1);
			if(b-a==1 && b-c==1)
			{
				twodarray[b-1][i]=new Tile(2, baseimages[2]);
				twodarray[b][i]=new Tile(1,baseimages[1]);
			}
			if(b-a==-1 && b-c==-1)
			{
				twodarray[b+1][i]=new Tile(2, baseimages[2]);
				twodarray[b][i]=new Tile(0,baseimages[0]);
			}
		}

		//forest generation
		//Given a map is of size "n", the best way to equally space a number of forests "m"
		//is to start them roughly at locations of n/(m+1)*index of forest
		//technically the center should be at that location, but as map size
		//increases, relative error goes down so its okay

		//determining initial values
		int forestnum = (int) (Math.random() * w / 250) + 8;                        //number of forests
		int[] foreststarts = new int[forestnum];                                //start location of forest
		int[] forestsize = new int[forestnum];                                //"area" of forest
		int[] treenum = new int[forestnum];                                    //number of trees in a forest

		foreststarts[0] = w / (forestnum + 1) + (int) (Math.random() * 5);            //first forest start location
		forestsize[0] = (int) (Math.random() * 20) + 20;                            //first forest size
		treenum[0] = forestsize[0] / 8 + (int) (Math.random() * 5) - 2;                //number of trees based on forest size

		for (int k = 1; k < forestnum; k++) {
			foreststarts[k] = (k + 1) * w / (forestnum + 1) + (int) (Math.random() * 30) - 15;
			forestsize[k] = (int) (Math.random() * 20) + 20;
			treenum[k] = forestsize[k] / 8 + (int) (Math.random() * 4) - 1;
		}

		//debugging
		//for(int i=0;i<forestnum;i++)
		//{
		//	System.out.println("Start"+foreststarts[i]+"size"+forestsize[i]+"num"+treenum[i]);
		//}
		
		//looping per forest, per tree
		for (int i = 0; i < forestnum; i++) {
			for (int j = 0; j < treenum[i]; j++) {
				
				//leaf generation parameterss
				int centerx = foreststarts[i] + j * 8 + (int) (Math.random() * 3);
				int surface = getSurface(centerx);
				int height=(int) (Math.random() * 3) + 4;	
				int topy= surface-height;
				int leafradius=(int)(Math.random()*3)+4;
				//actual generation in an area around the tree
				for(int x=centerx-height/2;x<centerx+height/2+1;x++)
				{
					for(int y=topy-height/2;y<topy+height/2+1;y++)
					{
						if(Math.sqrt((double)(Math.pow((double)(y-topy),2)+Math.pow((double)(x-centerx), 2)))<(double)(leafradius))
								{
								twodarray[y][x]=new Tile(5,baseimages[5], false);
								}
					}
				}
				twodarray[topy-1][centerx]=new Tile(5,baseimages[5], false);
				
				//log generation
				for (int k = 0; k < height; k++) {
					twodarray[surface-k-1][centerx]=new Tile(4,baseimages[4]);
				}
			}
		}

		
}

public void initialize() {
	//finding out number of possible tiles
	File tilelist = new File("resources/tiles.txt");
	int tilelength = 0;
	Scanner scan;
	try {
		scan = new Scanner(tilelist);
		while (scan.hasNextLine()) {
			if (!(scan.nextLine()).isEmpty()) {
				tilelength++;
			}
		}
		scan.close();

	} catch (FileNotFoundException e1) {
		System.out.println("Tile declarations not found");
	}
	//setting image array size to number of tiles
	baseimages = new Image[tilelength];
	for (int i = 0; i < tilelength; i++) {
		try {
			baseimages[i] = ImageIO.read(new File("resources/" + i + ".png"));
		} catch (IOException e) {
			System.out.println("Image not found");
		}
	}

}

public void debugDraw() {
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			System.out.print(" " + twodarray[i][j].getId());
		}
		System.out.println("");
	}
}

public void save(String fname) {
	File towrite = new File("resources/" + fname + ".txt");
	try {
		PrintWriter goin = new PrintWriter(towrite);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				goin.print(twodarray[i][j].getId());
			}
			goin.println("");
		}
		goin.close();
	} catch (IOException e) {
	}

}

/**
 * Accessor for map array
 *
 * @return
 */
public Tile[][] getMapArray() {
	return twodarray;
}

/**
 * Accessor for map height
 *
 * @return
 */
public int getHeight() {
	return h;
}

/**
 * Accessor for map width
 *
 * @return
 */
public int getWidth() {
	return w;
}

public Image getImage(int x, int y) {
	return twodarray[y][x].getDisplaypic();
}

public int getSurface(int x) {
	for (int i = 0; i < h - 1; i++) {
		if (twodarray[i][x].getId() != 0) {
			return i;
		}
	}
	return h - 1;
}

public void removeBlock(int x, int y) {
    if (isValid(x, y))
        twodarray[y][x] = new Tile(0, baseimages[0], false);
}

public void placeBlock(int x, int y, int n, boolean solid) {
    if (isValid(x, y)) {
        twodarray[y][x] = new Tile(n, baseimages[n], solid);
    }
}


public boolean isSolid(int x, int y) {
    if (isValid(x, y))
        return twodarray[y][x].isSolid();
    else
        return true;

}

protected boolean isValid(int x, int y) {
    return !(x < 0 || x >= w || y < 0 || y >= h);
}
}
