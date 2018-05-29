package core;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import files.Fonts;
import files.Images;
import sound.Audio;
import values.CoreValues;

public class Splash extends BasicGameState 
{
	private static float gameLoad = 0;
	private StateBasedGame sbg;
	private Image splash;
	private Image splashScaled;
	private int id;
	private int step;
	private String message = "";
	private boolean ready = false;
	
	public static void setProgress(float percent)
	{
		gameLoad = percent;
	}
	
	Splash(int id)
	{
		this.id = id;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		this.sbg = sbg;
		gc.setMouseGrabbed(true);

		splash = new Image("res/title/splash.png");
		splashScaled = 	splash.getScaledCopy(Game.getScreenWidth(), Game.getScreenHeight());
		Fonts.calibri16Bold = new TrueTypeFont(new Font("Calibri", Font.BOLD, 16), false);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{

		if(splash != null)
		{  
			splashScaled.draw();
		}

		
		String m = message;
		
		float WIDTH = Game.getScreenWidth() * .8f;
		float HEIGHT = 30;
		int curve = 15;
		float x = (Game.getScreenWidth() - WIDTH)/2;
		float y = Game.getScreenHeight() - HEIGHT/2 - 53;
		
		g.setLineWidth(3);
		g.setColor(new Color(50,50,50));
		g.fillRoundRect(x, y, WIDTH, HEIGHT, curve);
		g.setColor(Color.black);
		g.drawRoundRect(x, y, WIDTH, HEIGHT, curve);

		g.setColor(new Color(50,170,205));
		g.fillRoundRect(x, y, WIDTH * gameLoad, HEIGHT, curve);

		g.setColor(Color.black);
		g.drawRoundRect(x, y, WIDTH * gameLoad, HEIGHT, curve);
		g.setLineWidth(1);
		
		TrueTypeFont f = Fonts.calibri16Bold;
		g.setFont(f);	
		
		g.setColor(Color.black);
		g.drawString(m, Game.getScreenWidth()/2 - f.getWidth(m)/2 + 2, Game.getScreenHeight() - f.getHeight(m)/2 - 50 + 2);
		g.setColor(Color.white);
		g.drawString(m, Game.getScreenWidth()/2 - f.getWidth(m)/2, Game.getScreenHeight() - f.getHeight(m)/2 - 50);
		
	
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{		
		
		switch(step)
		{
			case 0:			
				message = "Loading Fonts";
	
				setProgress(.2f);
				break;
			case 1:
				Fonts.loadFonts();
				message = "Loading Images (World)";
				setProgress(.4f);
				break;
			case 2:
				Images.loadWorld();
				message = "Loading Images (Robots)";
				setProgress(.5f);
				break;
			case 3:
				Images.loadRobots();
				message = "Loading Images (Weapons)";
				setProgress(.6f);
				break;
			case 4:
				Images.loadWeapons();
				Images.loadEffects();
				message = "Loading Music";
				setProgress(.7f);
				break;
			case 5:
				Audio.loadSongList();
				message = "Loading SFX";
				setProgress(.8f);
				break;
			case 6:
				Audio.loadSFX();
				message = "Setting Game Music";
				break;
			case 7:
				Audio.setRandomGameMusic();
				Audio.loadGameMusicFile();
				message = "Ready!";
				setProgress(1f);
				ready = true;
				break;
		}
		
		step++;

		

	}
	
	public void enter()
	{
		message = "Loading Fonts";
	}
		
	public void keyReleased(int key, char c) 
	{
		if(ready)
		{			
			sbg.enterState(Engine.SELECT_ID, 
				new FadeOutTransition(Color.black, CoreValues.TRANSITION_FADE_TIME), 
				new FadeInTransition(Color.black, CoreValues.TRANSITION_FADE_TIME));

		}
	}

	public int getID() 
	{
		return id;
	}

}
