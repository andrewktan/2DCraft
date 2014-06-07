import java.awt.*;
import java.io.*;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Map {

	private Tile [][] twodarray;
	//reminder to self: twodarray[row][column]	
	private int h,w;
	//preloaded images
	public static Image[] baseimages;
	
	public Map(int width)
	{	
		//parse in images
		initialize();
		h=32;
		w=width;
		//space created
		twodarray= new Tile[h][width];
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				twodarray[i][j]=new Tile(0,baseimages[0]);
			}
		}
		//stone and dirts
		//initial elevations
		int stoneend=(int)(Math.random()*3)+3*h/4;
		int dirtend= (int)(stoneend-(Math.random()*2+4));
		//keeping track of changes
		int netchange=0;
		char updown='n';
		
		//left to right, varying elevation
		for(int i=0;i<width;i++)
		{
			//weighted randomly increase or decrease
			double randchange=Math.random()*10;
			int dirtchange=0;
			int stonechange=0;
			if(randchange>8)
			{
				stonechange++;
				dirtchange++;
				netchange++;
				updown='d';
			}
			else if(randchange<2)
			{
				stonechange--;
				dirtchange--;
				netchange--;
				updown='u';
			}
			else{
				//don't do anything
			}
			
			//adjusting for elevation
			double randhchange=Math.random()*netchange;
			if(randhchange>h/8)
			{
				stonechange--;
				dirtchange--;
				netchange--;
			}
			else if (randhchange<-1*h/8)
			{
				stonechange++;
				dirtchange++;
				netchange++;
			}
			else{
				//do nothing
			}
			stoneend=stoneend+stonechange;
			dirtend=dirtend+dirtchange;
			
			
			
			for(int j=h-1;j>stoneend;j--)
			{
				twodarray[j][i]=new Tile(3,baseimages[3]);
			}
			for(int j=stoneend;j>dirtend;j--)
			{
				twodarray[j][i]=new Tile(1,baseimages[1]);
			}
			//grass layer
			twodarray[dirtend][i]=new Tile(2,baseimages[2]);
			//bedrock layer
			twodarray[h-1][i]=new Tile(6,baseimages[6]);
		}
	}

	public void initialize()
	{
		//finding out number of possible tiles
		File tilelist= new File("resources/tiles.txt");
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
				baseimages[i]=ImageIO.read(new File("resources/" + i + ".png"));
			}
			catch(IOException e)
			{
				System.out.println("Image not found");
			}
		}

	}

	public void debugDraw()
	{
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				System.out.print(" "+twodarray[i][j].getId());
			}
			System.out.println("");
		}
	}
	
	public void save(String fname)
	{
		File towrite=new File("resources/"+fname+".txt");
		try {
			PrintWriter goin= new PrintWriter(towrite);
			
			for(int i=0;i<h;i++)
			{
				for(int j=0;j<w;j++)
				{
					goin.print(twodarray[i][j].getId());
				}
				goin.println("");
			}
		} catch (IOException e) {
			
		}
				
	}

    /**
     * Accessor for map array
     * @return
     */
    public Tile[][] getMapArray() {
        return twodarray;
    }

    /**
     * Accessor for map height
     * @return
     */
    public int getHeight() {
        return h;
    }

    /**
     * Accessor for map width
     * @return
     */
    public int getWidth() {
        return w;
    }

	public void show (Graphics g, int x, int y)
	{
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				twodarray[i][j].show(g, j*16+x, i*16+y);
			}
		}
	}

	public Image getImage(int x,int y)
	{
		return twodarray[y][x].getDisplaypic();
	}
}
