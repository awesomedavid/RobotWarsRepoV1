package world.cells.feature;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import files.Images;
import item.StoneBlock;
import objects.Point;
import objects.Unit;
import world.World;
import world.cells.Cell;

public class Mountain extends Harvestable
{

	Point spritePos;
	public Mountain(Cell owner)
	{
		super(owner);
		isObstacle = true;
		blocksSight = true;
		curHealth = MOUNTAIN_HEALTH;
		maxHealth = curHealth;
		sheet = Images.mountain;
		spritePos = new Point(-1, -1);
		canMine = true;
		isCover = true;

	}
	
	public void update()
	{
		super.update();
		
		if(curHealth <= maxHealth / 2 && sheet != Images.mountainDamaged)
		{			
			sheet = Images.mountainDamaged;
			updateImage();
		}

		if(!spritePos.equals(getSpriteCoordinate()))
		{
			updateImage();
		}
	

	}
	
	public void updateImage()
	{
		spritePos = getSpriteCoordinate();
		image = sheet.getSprite(spritePos.getX(), spritePos.getY());
		
		float r = .8f - ((1-owner.getMoisture())*.2f);
		float g = .8f - ((1-owner.getMoisture())*.35f);
		float b = .8f - ((1-owner.getMoisture()) * .45f);
		image.setImageColor(r, g ,b );

	}
		
	public Color getHiddenColor()
	{
		
		float rMod = .9f - ((1-owner.getMoisture())*.2f);
		float gMod = .9f - ((1-owner.getMoisture())*.35f);
		float bMod = .9f - ((1-owner.getMoisture()) * .45f);
		return new Color((int) (110 * rMod), (int) (115 * gMod), (int)(110 * bMod));
	}

	@Override
	public Color getColor() {
		
		float rMod = .9f - ((1-owner.getMoisture())*.2f);
		float gMod = .9f - ((1-owner.getMoisture())*.35f);
		float bMod = .9f - ((1-owner.getMoisture()) * .45f);
		return new Color((int) (130 * rMod), (int) (125 * gMod), (int)(120 * bMod));
	}
	

	

	
	

}
