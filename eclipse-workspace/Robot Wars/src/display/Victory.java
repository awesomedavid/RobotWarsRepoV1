package display;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import core.Game;
import files.Fonts;
import objects.Team;
import values.CoreValues;
import values.Settings;

public class Victory 
{
	float xPos;
	float yPos;
	String message;
	Font bigFont;
	Font smallFont;
	int longestNameLength;
	int[] scores;
	
	Victory()
	{
		xPos = Game.getScreenWidth() * .5f;
		yPos = Game.getScreenHeight() * .5f;
		message = "";
		bigFont = Fonts.arialblack48;
		smallFont = Fonts.arialblack28;
		scores = new int[Game.getTeams().size()];
		
		for(Team t : Game.getTeams())
		{
			if(longestNameLength < t.getName().length())
			{
				longestNameLength = t.getName().length();
			}
		}
	}
	
	public void update()
	{
		message = Game.getWinner() + " Wins!";
		
		for(int i = 0; i < scores.length; i++)
		{
			scores[i] = (int) Math.min(CoreValues.POINTS_TO_WIN, Game.getTeam(i).getScore());
		}
	}
	
	public void render(Graphics g)
	{
		int yOffset = -50;
		int w = 600;
		int h = 100;
		
		Color c = Game.getWinner().getColor().darker(.5f);
		c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 150);
		
		int textW =  bigFont.getWidth(message);
		int textH =  bigFont.getHeight(message);
		
		g.setLineWidth(4);
		g.setColor(c);
		g.fillRoundRect(xPos-w/2+Camera.getX(), yPos-h/2+Camera.getY()+yOffset, w, h, 10);
		g.setColor(Game.getWinner().getColor());
		g.drawRoundRect(xPos-w/2+Camera.getX(), yPos-h/2+Camera.getY()+yOffset, w, h, 10);
		g.setLineWidth(1);

		g.setFont(bigFont);
		g.setColor(new Color(40, 40, 40, 255));
		g.drawString(message, xPos+Camera.getX() -textW/2+2, yPos+Camera.getY()-textH/2+yOffset+2);
		g.setColor(Color.white);
		g.drawString(message, xPos+Camera.getX() -textW/2, yPos+Camera.getY()-textH/2+yOffset);

		

		
		int spacing = longestNameLength * 15;
		float scoreX = xPos - (spacing * (scores.length-1))/2;
		float scoreY = yPos + 50;
		
		
//		w = 250 * scores.length;
//		h = 100;
//		
//		g.setLineWidth(4);
//		g.setColor(Color.black);
//		g.fillRoundRect(scoreX-w/2+Camera.getX(), scoreY-h/2+Camera.getY()+50, w, h, 10);
//		g.setColor(new Color(140, 140, 140, 150));
//		g.drawRoundRect(scoreX-w/2+Camera.getX(), scoreY-h/2+Camera.getY()+50, w, h, 10);
//		g.setLineWidth(1);
//		
		
		for(int i = 0; i < scores.length; i++)
		{
			g.setFont(smallFont);
			g.setColor(new Color(40, 40, 40, 255));
			g.drawString(""+scores[i], scoreX+Camera.getX() - smallFont.getWidth(""+scores[i])/2+2 + i*spacing , scoreY+Camera.getY()+2+50);
			g.setColor(Game.getTeam(i).getColor());
			g.drawString(""+scores[i], scoreX+Camera.getX() - smallFont.getWidth(""+scores[i])/2 + i*spacing, scoreY+Camera.getY()+50);
			
			g.setColor(new Color(40, 40, 40, 255));
			g.drawString(""+Game.getTeam(i), scoreX+Camera.getX() - smallFont.getWidth(""+Game.getTeam(i))/2+2 + i*spacing , scoreY+Camera.getY()+2);
			g.setColor(Game.getTeam(i).getColor());
			g.drawString(""+Game.getTeam(i), scoreX+Camera.getX() - smallFont.getWidth(""+Game.getTeam(i))/2 + i*spacing, scoreY+Camera.getY());
		}
	}
}
