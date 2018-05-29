package animations.projectile;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import animations.AnimTargeted;
import animations.Animation;
import core.Utility;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;

public abstract class AnimArc extends AnimTargeted
{	
	int imageSize;
	float xShadow;
	float yShadow;
	float imageSizeScaled;
	float shadowSize;
	float shadowSizeScaled;
	Image i;
	Color c;
	int spinRate;
	int theta;
	
	public AnimArc(Unit actor, Point targetPoint, int animationSpeed) {
		super(actor.getPoint(), actor, targetPoint, null, Utility.distance(actor.getPoint(), targetPoint) * CoreValues.CELL_SIZE / animationSpeed, true);
		
		c= actor.getTeam().getColor();
		theta = 0;
		spinRate = Utility.random(-5, 5);
		
	}
		
	public void update() {
		super.update();
		
		// Moving Object
		float xDiff = targetPoint.getX() - xOrigin;
		float yDiff =  targetPoint.getY() - yOrigin;
		float progress = (float) ticks / (float) duration;
		
		x = xOrigin + CELL_SIZE/2 + xDiff * progress;
		xShadow = x;
	
		y = yOrigin + CELL_SIZE/2 + yDiff * progress;
		yShadow = y;

		// Arc effect
		float arcPercent = (float) Math.sqrt(Math.min(progress, 1-progress));
		float arcAbsolute = arcPercent * Utility.distance(xOrigin,yOrigin, targetPoint.getX(), targetPoint.getY()) / 2;
		y = y - arcAbsolute;
		imageSizeScaled = imageSize * Math.max(.7f,1-arcPercent);
		shadowSizeScaled = imageSizeScaled * .6f;

		
		// Spinning Image
		theta += spinRate;
		if(theta > 360)
		{
			theta = theta % 360;
		}
	}	
	
	public void render(Graphics g)
	{
		// Shadow
		g.setColor(new Color(20, 20,20, 150));
		g.fillOval(xShadow-imageSize/2, yShadow-imageSize/2, shadowSizeScaled, shadowSizeScaled);
		
		// Projectile
		i = i.getScaledCopy((int)imageSizeScaled, (int)imageSizeScaled);
		i.setImageColor(c.getRed()/255.0f, c.getGreen()/255.0f, c.getBlue()/255.0f);
		i.setCenterOfRotation(i.getWidth() / 2, i.getHeight() / 2);
		i.setRotation(theta);
		i.draw(x-imageSize/2, y-imageSize/2);

		

		
	}
	
}
