
public class Map {

	private int [][] twodarray;
	//reminder to self: twodarray[row][column]
	
	
	private int h,w;
	public Map(int width)
	{	
		h=100;
		w=width;
		//#space created
		twodarray= new int[h][width];
		//stone and dirts
		int stoneend=(int)(Math.random()*3)+h/2+10;
		int dirtend= (int)(stoneend-(Math.random()*2+4));
		for(int i=0;i<width;i++)
		{
			int stoneincrement=(int)(Math.random()*3)-1;
			stoneend=stoneend+stoneincrement;
			dirtend=dirtend+stoneincrement;
			for(int j=h-1;j>stoneend;j--)
			{
				twodarray[j][i]=3;
			}
			for(int j=stoneend;j>dirtend;j--)
			{
				twodarray[j][i]=1;
			}
			//grass layer
			twodarray[dirtend][i]=2;
			//bedrock layer
			twodarray[h-1][i]=6;
		}
	}
	
	public void debugDraw()
	{
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				System.out.print(" "+twodarray[i][j]);
			}
			System.out.println("");
		}
	}
}
