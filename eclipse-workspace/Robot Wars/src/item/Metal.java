package item;

import org.newdawn.slick.Color;

import core.Utility;
import files.Images;

public class Metal extends Resource 
{
	public Metal()
	{
		super();
		setImage();
	}

	public Metal(Color c)
	{
		super();
		setImage();
		image.setImageColor(c.getRed()/255f, c.getGreen()/255f, c.getBlue()/255f);
	}
	
	void setImage()
	{
		int r = (int) (Math.random() * Images.metalGrey.length);
		image = Images.metalGrey[r].copy();
		image.rotate(Utility.random(0, 360));
	}
	
}
