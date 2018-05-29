package display;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;
import display.duo.DisplayDuoResources;
import objects.Team;
import values.CoreValues;
import values.DisplayValues;
import values.Settings;

public class CaptureBar implements DisplayValues
{
	int xPos;
	int yPos;
	Team team;
	int dir = 1;
	float percent;
	
	Color neutral;
	
	public CaptureBar(Team team)
	{
		neutral = new Color(180, 180, 180, 255);
		this.team = team;
		if(Settings.tournamentMode)
		{
			yPos = Game.getScreenHeight()-CAPTURE_BAR_HEIGHT-10;
			
			if(team.getID() == 0)
			{
				xPos = CARD_INDENT + 4 * CARD_SPACING;
			}
			else
			{
				xPos = Game.getScreenWidth() - CARD_INDENT - 4 * CARD_SPACING-10;
				dir = -1;
			}
		}
	}
	
	
	public void render(Graphics g)
	{
		g.setLineWidth(2);
		// Background Bar
		g.setColor(neutral);
		g.fillRect(xPos+Camera.getX(), yPos+Camera.getY(), CAPTURE_BAR_WIDTH * dir, CAPTURE_BAR_HEIGHT);
		
		g.setColor(Color.black);
		g.drawRect(xPos+Camera.getX(), yPos+Camera.getY(), CAPTURE_BAR_WIDTH * dir, CAPTURE_BAR_HEIGHT);

		if(team.getScorePercent() > .01)
		{
			// Progress		
			g.setColor(team.getColor());
			g.fillRect(xPos+Camera.getX(), yPos+Camera.getY(), team.getScorePercent() * CAPTURE_BAR_WIDTH * dir, CAPTURE_BAR_HEIGHT);
			
			g.setColor(Color.black);
			g.drawRect(xPos+Camera.getX(), yPos+Camera.getY(), team.getScorePercent() * CAPTURE_BAR_WIDTH * dir, CAPTURE_BAR_HEIGHT);
		}
		
		g.setLineWidth(1);
	}
	
	
	
	
}
