
package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import files.Fonts;
import files.Images;
import values.CoreValues;


public class End extends BasicGameState 
{
	private StateBasedGame sbg;
	private GameContainer gc;
	private int id;

	
	End(int id)
	{
		this.id = id;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		this.sbg = sbg;
		this.gc = gc;
		//Images.loadEndImages();

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{	
		String m = "WINNER";
		TrueTypeFont f = Fonts.calibri14Bold;
		g.setFont(f);	
		g.setColor(new Color(220, 220, 220, 255));
		g.drawString(m, Game.getScreenWidth()/2 - f.getWidth(m)/2, Game.getScreenHeight()/2 - f.getHeight(m)/2);
	}
		
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{

	}
	
	public void enter(GameContainer gc, StateBasedGame sbg)
	{

	}
	
	public void leave(GameContainer gc, StateBasedGame sbg)
	{
		
	}
		
	public void keyReleased(int key, char c) 
	{
		sbg.enterState(Engine.TITLE_ID, 
				new FadeOutTransition(Color.black, CoreValues.TRANSITION_FADE_TIME), 
				new FadeInTransition(Color.black, CoreValues.TRANSITION_FADE_TIME));
		
	}

	public int getID() 
	{
		return id;
	}

}
