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

public class AnimSmokeGrenade extends AnimArc
{	

	public AnimSmokeGrenade(Unit actor, Point targetPoint) {
		super(actor, targetPoint, SECONDARY_SMOKE_GRENADE_ANIMATION_SPEED);
		imageSize = 48;
		
		 i = Images.secondarySmokeGrenade;
	}
	

}
