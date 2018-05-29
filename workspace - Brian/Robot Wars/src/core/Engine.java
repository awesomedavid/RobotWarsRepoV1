
package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import values.CoreValues;

public class Engine extends StateBasedGame 
{
    public static final int TITLE_ID = 0;
    public static final int SELECT_ID = 1;
    public static final int GAME_ID = 2;
    public static final int END_ID = 3;

    private BasicGameState title;
    private BasicGameState game;
    private BasicGameState select;

    private BasicGameState end;

	public Engine(String name) 
	{
		super(name);
		
		select = new Select(SELECT_ID);
		title = new Splash(TITLE_ID);
		game = new Game(GAME_ID);
		end = new End(END_ID);
	}

	public void initStatesList(GameContainer gc) throws SlickException 
	{
		addState(title);
		addState(select);
		addState(game);
		addState(end);
	}

	public static void main(String[] args) 
	{

		
		try {
			AppGameContainer appgc = new AppGameContainer(new Engine("Robot Wars"));
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

		
			appgc.setDisplayMode(Game.getScreenWidth(), Game.getScreenHeight(), false);
			appgc.setTargetFrameRate(CoreValues.FRAMES_PER_SECOND);
		//	appgc.setVSync(true);
		//	System.getProperties().list(System.out);
			appgc.start();



		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}