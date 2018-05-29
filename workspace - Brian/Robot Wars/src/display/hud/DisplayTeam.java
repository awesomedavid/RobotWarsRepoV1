package display.hud;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;
import display.Camera;
import display.duo.DisplayDuoResources;
import display.duo.DisplayDuoRobot;
import objects.Robot;
import objects.Team;
import values.DisplayValues;
import values.Settings;
import values.CoreValues;

public abstract class DisplayTeam implements DisplayValues{

	protected Team team;

	protected List<DisplayDuoRobot> robots;
	protected DisplayDuoResources resources;
	
	protected int xPos;
	protected int yPos;
	
	Color background;

	public DisplayTeam(Team team)
	{
		this.team = team;
		robots = new ArrayList<DisplayDuoRobot>();
		background = new Color(10, 10, 10, 120);
		background.multiply(team.getColor().darker(.85f));
		
	}
	

	public abstract void update();
	
	public abstract void render(Graphics g);

	
}
