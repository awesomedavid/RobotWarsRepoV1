package display.duo;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import actions.Reload;
import core.Game;
import display.Camera;
import display.duo.bar.Healthbar;
import display.duo.bar.Heatbar;
import display.duo.bar.OverclockBar;
import display.duo.bar.Platingbar;
import display.duo.bar.Shieldbar;
import files.Fonts;
import item.weapon.Weapon;
import objects.Robot;
import values.DisplayValues;
import values.RobotValues;
import values.Settings;
import values.CoreValues;


public class DisplayDuoRobot implements DisplayValues
{
	Robot r;
	
	int xPos;
	int yPos;
	
	private Color background;
	private Color border;
	private Color dead;
	private Color backgroundStatusBars;
	private Color backgroundName;
	private Color deathCountColor;
	private Image icon;
	Healthbar health;
	Platingbar plating;
	Shieldbar shield;
	Heatbar heat;
	OverclockBar overclock;
	
	
	public DisplayDuoRobot(Robot r, int xPos, int yPos)
	{
		this.r = r;
		this.xPos = xPos;
		this.yPos = yPos;
		
		background = new Color(30, 30, 30);
		background.add(r.getTeam().getColor().darker(.85f));
		background = new Color(background.getRed(), background.getGreen(), background.getBlue(), 200);
		border = new Color(90, 90, 90);
		border.add(r.getTeam().getColor().darker(.65f));
		border = new Color(border.getRed(), border.getGreen(), border.getBlue(), 200);

		dead = new Color(10, 10, 10, 185);
		backgroundStatusBars = new Color(255, 255, 255, 30);
		backgroundName = new Color(10, 10, 10, 120);
		
		shield = new Shieldbar(r, xPos, yPos+TITLE_INDENT + STATUS_BAR_INDENT);
		plating = new Platingbar(r, xPos, yPos+TITLE_INDENT +STATUS_BAR_INDENT+DisplayValues.STATUS_BAR_HORIZONTAL_HEIGHT*1.2f);
		health = new Healthbar(r, xPos, yPos+TITLE_INDENT + STATUS_BAR_INDENT+DisplayValues.STATUS_BAR_HORIZONTAL_HEIGHT*2.4f);
		heat = new Heatbar(r, xPos + DisplayValues.ROBOT_CARD_WIDTH - STATUS_BAR_INDENT * 2 - HEAT_BAR_WIDTH, yPos + DisplayValues.ROBOT_CARD_HEIGHT - STATUS_BAR_INDENT);
		overclock = new OverclockBar(r, xPos + DisplayValues.ROBOT_CARD_WIDTH - STATUS_BAR_INDENT * 2.5f - HEAT_BAR_WIDTH - OVERCLOCK_BAR_WIDTH, yPos + DisplayValues.ROBOT_CARD_HEIGHT - STATUS_BAR_INDENT);
		deathCountColor = new Color(255, 255, 255, 180);
		
		icon = r.getSheet().getSprite(1, 0).getScaledCopy(1.0f);
		icon.setImageColor(r.getTeam().getColor().getRed()/255.0f, 
				r.getTeam().getColor().getGreen()/255.0f, 
				r.getTeam().getColor().getBlue()/255.0f);

	}
	
	public void update()
	{
		shield.update();
		plating.update();
		health.update();
		heat.update();
		overclock.update();
	}
	
	public void render(Graphics g)
	{
		renderBox(g);
		renderName(g);
		renderBars(g);
		renderImage(g);
		renderRecentLatency(g);
		renderUpgrades(g);
		
		if(r.hasPrimary())
		{
			renderWeapon(r.getPrimary(), 0, g);
		}
		if(r.hasSecondary())
		{
			renderWeapon(r.getSecondary(), ROBOT_CARD_HEIGHT/4, g);
		}
		
		
		renderDeadOverlay(g);
	}
	
	public void renderUpgrades(Graphics g)
	{
		if(r.isAlive())
		{
			int size = 28;
			float x = xPos + Camera.getX() - size/4;
			float y = yPos + Camera.getY() - size/4;
		
			
			g.setFont(Fonts.arial16Bold);
			String message = ""+ r.getInventory().getWeightCarried();
			float w = Fonts.arial16Bold.getWidth(message);
			float h = Fonts.arial16Bold.getHeight(message);
	
			g.setColor(r.getTeam().getColor().darker());
			g.fillOval(x,  y,  size, size);
			g.setColor(Color.black);
			g.drawOval(x,  y, size, size);
			
			
			g.setColor(Color.black);
			g.drawString(message,  x - w/2 + 2 + size/2, y + size/2 + 2 - h/2);
			
			if(r.getInventory().getWeightSpeedPenalty() >= 1)
			{
				g.setColor(Color.red);
			}
			else if(r.getInventory().getWeightCapacity() == r.getInventory().getWeightCarried())
			{
				g.setColor(Color.green);
			}
			else if(r.getInventory().getWeightSpeedPenalty() > 0)
			{
				g.setColor(Color.yellow);
			}
			else
			{
				g.setColor(Color.white);
			}
			g.drawString(message,  x - w/2 + size/2, y + size/2 - h/2);
		}
	}
	
	public void renderWeapon(Weapon weapon, int indent, Graphics g)
	{
		float x = xPos + Camera.getX() + ROBOT_CARD_WIDTH / 8;
		float y = yPos + Camera.getY() + indent + Game.getScreenWidth() * .035f;
		
		if(weapon.getImage() == null)
		{
			g.setFont(Fonts.calibri12Bold);
			g.setColor(Color.white);
			float w = Fonts.calibri12Bold.getWidth(weapon.toString());
			g.drawString(weapon.toString(),  x - w/2, (int) (y + 2));
		}
		else
		{
			Image i = weapon.getImage();
			i.draw(x-i.getWidth()/2, y);
		}		

		if(weapon.usesAmmo())
		{
			x = x + 30;
			y = y + 7;
			int barWidth = 45;

			g.setFont(Fonts.arial16Bold);
			String message = "";

			if(r.hasAction() && r.getAction() instanceof Reload && ((Reload)(r.getAction())).getWeapon() == weapon)
			{
				g.setColor(new Color(60, 60, 60, 150));
				g.fillRect(x+ 20, y, barWidth, DisplayValues.STATUS_BAR_HORIZONTAL_HEIGHT);
				g.setColor(new Color(150, 150, 150));
				g.fillRect(x + 20, y, barWidth * r.getAction().getPercentComplete(), DisplayValues.STATUS_BAR_HORIZONTAL_HEIGHT);
				
				g.setColor(Color.black);
				g.drawRect(x + 20, y, barWidth, DisplayValues.STATUS_BAR_HORIZONTAL_HEIGHT);
				g.setColor(Color.black);
				g.drawRect(x + 20, y, barWidth * r.getAction().getPercentComplete(), DisplayValues.STATUS_BAR_HORIZONTAL_HEIGHT);			

				message = "-";
			}
			else
			{
				if(weapon.canReload())
				{
					g.setColor(new Color(60, 60, 60, 150));
					g.fillRect(x+20, y, barWidth, DisplayValues.STATUS_BAR_HORIZONTAL_HEIGHT);
					g.setColor(new Color(150, 150, 150));
					g.fillRect(x+20, y, barWidth * ((float) weapon.getCurAmmo() / (float) weapon.getMaxAmmo()), DisplayValues.STATUS_BAR_HORIZONTAL_HEIGHT);
				
					g.setColor(Color.black);
					g.drawRect(x+20, y, barWidth, DisplayValues.STATUS_BAR_HORIZONTAL_HEIGHT);
					g.setColor(Color.black);
					g.drawRect(x+20, y, barWidth * ((float) weapon.getCurAmmo() / (float) weapon.getMaxAmmo()), DisplayValues.STATUS_BAR_HORIZONTAL_HEIGHT);

				}
		
				 message = ""+weapon.getCurAmmo();
			}
			
			float w = Fonts.arial16Bold.getWidth(message);
			
			g.setColor(Color.black);
			g.drawString(message,  x - w/2 + 2, (int) (y - 3));
			g.setColor(Color.white);
			g.drawString(message,  x - w/2, (int) (y - 5));
		}
		
	}
	
	public void renderImage(Graphics g)
	{
		float x = xPos + Camera.getX() + ROBOT_CARD_WIDTH - 42;
		float y = yPos + Camera.getY();
		
		// That puts y at the top of the status bar; center it
			
		if(icon != null)
		{
			icon.draw(x, y);
		}
	}
	
	public void renderBox(Graphics g)
	{
		float x = xPos + Camera.getX();
		float y = yPos + Camera.getY();
		
		// Draw box
		g.setColor(background);
		g.fillRoundRect(x, y, ROBOT_CARD_WIDTH, ROBOT_CARD_HEIGHT, 13);
		g.setColor(border);
		g.setLineWidth(2);
		g.drawRoundRect(x, y, ROBOT_CARD_WIDTH, ROBOT_CARD_HEIGHT, 13);
		g.setColor(Color.black);
		g.drawRoundRect(x-2, y-2, ROBOT_CARD_WIDTH+4, ROBOT_CARD_HEIGHT+4, 13);
		g.setLineWidth(1);
		
		// Background Colors
		float statusBarStart = shield.getY() + Camera.getY() - STATUS_BAR_INDENT;
		g.setColor(backgroundStatusBars);
		g.fillRect(x+1, statusBarStart, ROBOT_CARD_WIDTH-1, STATUS_ZONE_HEIGHT);
	}
	
	public void renderName(Graphics g)
	{
		float x = xPos + Camera.getX();
		float y = yPos + Camera.getY();
	//	float center = (STATUS_BAR_WIDTH+STATUS_BAR_INDENT*2)/2;
		
		g.setColor(Color.white);
		g.setFont(Fonts.calibri12Bold);
		float w = Fonts.calibri12Bold.getWidth(r.toString());
		g.drawString(r.toString(), (int) (x + ROBOT_CARD_WIDTH/2 - CoreValues.CELL_SIZE/2 - w/2), (int) (y + 2));
	}
	
	public void renderBars(Graphics g)
	{
		// Draw status bars
		shield.render(g);
		plating.render(g);
		health.render(g);
		heat.render(g);
		overclock.render(g);
	}
	
	public void renderDeadOverlay(Graphics g)
	{
		float x = xPos + Camera.getX();
		float y = yPos + Camera.getY();
		
		// Overlay for dead robots
		if(!r.isAlive())
		{
			g.setColor(dead);
			g.fillRoundRect(x-1, y-1, ROBOT_CARD_WIDTH+2, ROBOT_CARD_HEIGHT+2, 13);
			
	
			g.setFont(Fonts.arialblack48);
			int time = r.respawnTimeLeft() / CoreValues.FRAMES_PER_SECOND + 1;
			String text = ""+(int)(time);
			float w = Fonts.arialblack48.getWidth(text);
			float h = Fonts.arialblack48.getHeight(text);
			
			g.setColor(Color.black);
			g.drawString(text, 
				     (int) (x + ROBOT_CARD_WIDTH / 2 - w/2+2), 
				     (int) (y + ROBOT_CARD_HEIGHT/ 2 - h/2+2));
			
			float mod = 1 - (float) Math.pow((float) r.respawnTimeLeft() / (float) r.respawnTime(), 2);
						
			float lightenAmount = 35;
			
			Color c = r.getTeam().getColor();
			float red = c.getRed() * mod + lightenAmount;
			float green = c.getGreen() * mod + lightenAmount;
			float blue = c.getBlue() * mod + lightenAmount;
			
			g.setColor(new Color((int) red, (int) green, (int) blue, 180));
			
			g.drawString(text, (int) (x + ROBOT_CARD_WIDTH / 2 - w/2), 
						       (int) (y + ROBOT_CARD_HEIGHT/ 2 - h/2));
			
			g.setColor(deathCountColor);
			
			String deaths = "Deaths: " + r.getDeaths();
			g.setFont(Fonts.arial14Bold);
			w = Fonts.arial14Bold.getWidth(deaths);
			h = Fonts.arial14Bold.getHeight(deaths);
			g.drawString(deaths, (int) (x + ROBOT_CARD_WIDTH / 2 - w/2), 
				               (int) (y + ROBOT_CARD_HEIGHT - 12 - h/2));

						
		}
	}
	
	public void renderRecentLatency(Graphics g)
	{
		if(Settings.showLatencyValue)
		{
			Font f = Fonts.arial20Bold;	
			g.setFont(f);
			String message = ""+r.getRecentLatency();
			int w = f.getWidth(message);
			int h = f.getHeight(message);

			if(r.getRecentLatency() > RobotValues.OVERCLOCK_LEVEL_PER_PROCESSOR)
			{
				g.setColor(Color.red);
			}
			else
			{
				g.setColor(Color.white);
			}
			
			g.drawString(message, xPos - w/2 + Camera.getX() + DisplayValues.ROBOT_CARD_WIDTH/2, yPos - h / 2 + Camera.getY() - 20);
		}
	
		
	}
	

}
