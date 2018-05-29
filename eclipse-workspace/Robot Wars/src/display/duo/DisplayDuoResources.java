package display.duo;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import display.Camera;
import files.Fonts;
import files.Images;
import objects.Team;

public class DisplayDuoResources 
{
	Team team;
	int x;
	int y;
	
	public DisplayDuoResources(Team team, int x, int y)
	{
		this.team = team;
		this.x = x;
		this.y = y;
	}
	
	public void update()
	{
		
		
	}
	
	public void render(Graphics g)
	{		
		float x = this.x + Camera.getX();
		float y = this.y + Camera.getY();
		
		drawResource(g, Images.woodLog, team.getWood(), x, y);
		drawResource(g, Images.stoneBlock, team.getStone(), x, y+36);
		drawResource(g, Images.metal[0], team.getMetal(), x, y+72);	

	}
	
	
	void drawResource(Graphics g, Image i, int value, float xPos, float yPos)
	{
		g.drawImage(i, xPos, yPos);
		
		g.setFont(Fonts.calibri18Bold);
		int w = Fonts.calibri18Bold.getWidth(""+value);
		
		g.setColor(new Color(25, 25, 25, 125));
		g.fillOval(xPos + 20-w/2, yPos + 16, 10 + w, 20);
		g.setColor(Color.black);
		g.drawOval(xPos + 20-w/2, yPos + 16, 10 + w, 20);
		
		g.setColor(Color.black);
		g.drawString(""+value, xPos+25-w/2, yPos + 18);
		g.setColor(Color.white);
		g.drawString(""+value, xPos+25-w/2, yPos + 16);
	}
}
