package animations.projectile;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import animations.Animation;
import core.Utility;
import files.Images;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;

public class AnimArtillery extends AnimArc
{	
	public AnimArtillery(Unit actor, Point targetPoint) {
		super(actor, targetPoint, PRIMARY_ARTILLERY_PROJECTILE_SPEED);
		imageSize = 48;
		
		 i = Images.secondarySmokeGrenade;
	}
	
}
