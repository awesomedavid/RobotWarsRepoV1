package item;

import org.newdawn.slick.Color;

import core.Utility;
import files.Images;

public class StoneBlock extends Resource 
{
	public StoneBlock()
	{
		super();
		image = Images.stoneBlock;
	}
	
	public StoneBlock(Color c)
	{
		super();
		image = Images.stoneBlock;
		image.setImageColor(c.getRed()/255f, c.getGreen()/255f, c.getGreen()/255f);
	}

}
