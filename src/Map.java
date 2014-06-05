import java.awt.*;

public class Map {

	private Tile [][] twodarray;
	//reminder to self: twodarray[row][column]	
	private int h,w;
	public Map(int width)
	{	
		h=100;
		w=width;
		//#space created
		twodarray= new Tile[h][width];
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				twodarray[i][j]=new Tile(0);
			}
		}
		//stone and dirts
		//initial elevations
		int stoneend=(int)(Math.random()*3)+h/2+10;
		int dirtend= (int)(stoneend-(Math.random()*2+4));
		
		//left to right, varying elevation
		for(int i=0;i<width;i++)
		{
			//weighted randomly increase or decrease
			double randchange=Math.random()*10;
			if(randchange>8)
			{
				stoneend++;
				dirtend++;
			}
			else if(randchange<2)
			{
				stoneend--;
				dirtend--;
			}
			else{
				//don't do anything
			}
			for(int j=h-1;j>stoneend;j--)
			{
				twodarray[j][i]=new Tile(3);
			}
			for(int j=stoneend;j>dirtend;j--)
			{
				twodarray[j][i]=new Tile(1);
			}
			//grass layer
			twodarray[dirtend][i]=new Tile(2);
			//bedrock layer
			twodarray[h-1][i]=new Tile(6);
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
