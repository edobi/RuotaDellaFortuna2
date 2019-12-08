package RdF;

import java.util.ArrayList;

public class GuiUpd 
{
	Character letter;
	ArrayList<Integer> poslett;
	int[] points;
	
	public GuiUpd(Character let,ArrayList<Integer> pos,int poi0,int poi1,int poi2) 
	{
		letter=let;
		poslett=pos;
		points[0]=poi0;
		points[1]=poi1;
		points[2]=poi2;
	}

}