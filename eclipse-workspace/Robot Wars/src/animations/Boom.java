package animations;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import core.Game;
import core.Utility;
import files.Images;

public class Boom extends Animation {
	Image image;

	float w;
	float h;

	public Boom(float x, float y, float size) {
		super(x, y, 1);
		image = Images.boom;

		this.w = image.getWidth() * size;
		this.h = image.getHeight() * size;
		
		x = x - w/2;
		y = y - h/2;
		this.size = size;
		duration = (int) (Utility.random(3, 6) * size);
	}
	
	
	public Point scatter(float radius)
	{
		double a = Math.random() * 2 * Math.PI;
		double r = radius * Math.sqrt(Math.random());

		double newX = x + r * Math.cos(a) + r/2;
		double newY = y + r * Math.sin(a) + r/2;
		
		return new Point((float)newX, (float) newY);
	}


	public void update() {
		super.update();

		if (isDone()) {
			return;
		}

		else if (ticks == duration - 1 && size >= .8) {
			for(int i = 0; i < 4; i++)
			{
				Point p = scatter(w/2);
				Game.addAnimation(new Boom(p.getX(), p.getY(), size * .60f));
			}
		}

	}

	public void render(Graphics g) {
		if (image != null) {
			//image.setCenterOfRotation(image.getWidth() / 2 * size, image.getHeight() / 2 * size);
			// image.setRotation(theta);
			
			image.draw(x, y, size);
		}
	}
}