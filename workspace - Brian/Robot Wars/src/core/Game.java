package core;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import animations.Animation;
import display.Camera;
import display.Display;
import display.duo.DisplayDuo;
import files.Fonts;
import objects.Robot;
import objects.Team;
import objects.Unit;
import sound.Audio;
import teams.starter.basic.BasicBalancedTeam;
import values.CoreValues;
import values.Settings;
import world.World;

public class Game extends BasicGameState implements CoreValues {
	private static List<Unit> units;

	private static List<Animation> animations;
	
	private static Camera camera;
	private static Display display;
	private static World world;
	private GameContainer gc;
	private StateBasedGame sbg;
	private static Team winner;
	
	private static boolean paused = false;
	
	int id;

	private static int timer = 0;

	Game(int id) {
		this.id = id;
	}
	
	public static int getScreenWidth()
	{
		return Settings.RESOLUTION_X;
	}
	
	public static int getScreenHeight()
	{
		return Settings.RESOLUTION_Y;
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// Overall audio volume modifiers
	
		this.gc = gc;
		this.sbg = sbg;
		
		gc.setSoundVolume(1f);
		gc.setMusicVolume(.7f);
		gc.setShowFPS(true);

	}
	
	public static List<Team> getTeams()
	{
		return Select.getTeams();
	}
	
	public static void addTeam(Class <? extends Team> clazz)
	{
		Select.addTeam(clazz);
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		gc.setMouseGrabbed(true);
		winner = null;
		paused = false;
		initMode();
		initObjects();
		initTeams();
	    initDisplay();		
	}

	public void initObjects()
	{
		camera = new Camera(gc);
		animations = new ArrayList<Animation>();
		units = new ArrayList<Unit>();
		world = new World();
		
		world.init();

		Audio.playGameMusic();
		
		for(Team t : getTeams())
		{
			t.init();
		}
	}
	public void initMode()
	{
		if(getTeams().size() == 0)
		{
			addTeam(BasicBalancedTeam.class);
			addTeam(BasicBalancedTeam.class);
	
		}
		else if(getTeams().size() > 2)
		{
			Settings.tournamentMode = false;
		}
	}
	public void initTeams()
	{
		if(Settings.tournamentMode)
		{
			getTeams().get(0).setOrigin(PLAYER_START_EDGE,  PLAYER_START_EDGE);
			getTeams().get(1).setOrigin(Settings.mapSize.getSize()-PLAYER_START_EDGE-1,Settings.mapSize.getSize()-PLAYER_START_EDGE-1);
	
			getTeams().get(0).spawnBase();
			getTeams().get(1).spawnBase();
			 
			getTeams().get(0).spawnRobots();
			getTeams().get(1).spawnRobots();		
		}
		else
		{
			for(Team t : getTeams())
			{
				int xPos = Utility.random(PLAYER_START_EDGE, World.getWidth()-PLAYER_START_EDGE);
				int yPos = Utility.random(PLAYER_START_EDGE, World.getWidth()-PLAYER_START_EDGE);
				t.setOrigin(xPos, yPos);
				t.spawnBase();
				t.spawnRobots();
			}
		}
	}

	public void initDisplay()
	{
		if(getTeams().size() == 2)
		{
			display = new DisplayDuo();
		}
		else
		{
			display = new Display();
		}
	}

	public void leave(GameContainer gc, StateBasedGame sbg) {


		
	}
	
	public void clear()
	{		
		units.clear();
		
		for(Team t : getTeams())
		{
			t.clear();
		}
	

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		g.setBackground(new Color(10, 20, 35));
		g.setFont(Fonts.arial16Bold);

		g.pushTransform();
		
		g.scale(Camera.getZoom(), Camera.getZoom());
		g.translate(-Camera.getX(), -Camera.getY());
		
		Game.world.render(g);
		

		for (Unit u : units) {
			u.render(g);
		}
	
		
		Game.world.renderAbove(g);
		
		renderAnimations(g);
		
		g.popTransform();

		g.translate(-Camera.getX(), -Camera.getY());

		display.render(g);

	}

	public void renderAnimations(Graphics g)
	{

		for(int i = animations.size() - 1; i >= 0; i--)
		{
			Animation a = animations.get(i);
			
			if(a.isDone())
			{
				animations.remove(a);
			}
			else
			{
				a.render(g);
			}
		}
	}
	
	public void keyPressed(int key, char c) {

	}

	public void keyReleased(int key, char c) 
	{
		if (key == Input.KEY_R && gc.getInput().isKeyPressed(Input.KEY_LSHIFT)) {
			
			clear();
			
			sbg.enterState(Engine.GAME_ID, new FadeOutTransition(Color.black, CoreValues.TRANSITION_FADE_TIME),
					new FadeInTransition(Color.black, CoreValues.TRANSITION_FADE_TIME));
		}
		
		if (key == Input.KEY_M) {
			Settings.showMiniMap = !Settings.showMiniMap;
		}
		
		if (key == Input.KEY_V) {
			Settings.alphaBlending = !Settings.alphaBlending;
		}
		
		if (key == Input.KEY_O) {
			Settings.showLatencyValue = !Settings.showLatencyValue;
		}
		
		if (key == Input.KEY_H) {
			Settings.showHUD = !Settings.showHUD;
		}
		
		if (key == Input.KEY_G) {
			Settings.showGridLines = !Settings.showGridLines;
		}
		
		if (key == Input.KEY_SPACE && !hasWinner()) {
			paused = !paused;
		}
		
		if (key == Input.KEY_1) {
			Settings.gameSpeed = 1;
		}
		
		if (key == Input.KEY_2) {
			Settings.gameSpeed = 2;
		}
		
		if (key == Input.KEY_3) {
			Settings.gameSpeed = 3;
		}
		
		if (key == Input.KEY_4) {
			Settings.gameSpeed = 4;
		}
		
		if (key == Input.KEY_5) {
			Settings.gameSpeed = 5;
		}
		
		if (key == Input.KEY_0) {
			Settings.gameSpeed = 30;
		}

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		timer++;

		camera.update(gc);
		
		if(!isPaused() && !hasWinner())
		{
			for(int i = 0; i < Settings.gameSpeed; i++)
			{
				updatePlayers();
				world.update();
				updateAnimations();
			}			
		}

		
		if(!isPaused() || hasWinner() )
		{
			display.update();
		}

	}
	
	public static Team getWinner()
	{
		return winner;
	}
	
	public static boolean hasWinner()
	{
		return winner != null;
	}
	
	public void updatePlayers()
	{


		
				for(Team t : getTeams())
				{
					long startTimeTeam = System.nanoTime();
					t.strategy();
					long durationTeam = (System.nanoTime() - startTimeTeam) / 1000;
					long durationTeamPerRobot = durationTeam / t.getRobots().size();

					if(t.getScorePercent() >= 1)
					{
						winner = t;
						paused = true;
					}
					
					//System.out.println((int) t.getScore());

					
					for (Unit u : t.getRobots()) 
					{												
						long startTime = System.nanoTime();
						u.update();
					
						long duration = (System.nanoTime() - startTime) / 1000;
						u.addLatency(duration + durationTeamPerRobot);
					}
				}
			
		
	}

	public void updateAnimations()
	{
		for(int i = animations.size() - 1; i >= 0; i--)
		{
			Animation a = animations.get(i);
			
			if(a.isDone())
			{
				animations.remove(a);
			}
			else
			{
				a.update();
			}
		}
	}

	public void cleanup() {

	}

	public int getID() {
		return id;
	}
	
	public static boolean isPaused()
	{
		return paused;
	}
	
	public static Team getTeam(int id)
	{
		if(id >= 0)
		{
			return  getTeams().get(id);
		}
		else
		{
			return null;
		}
	}
	
	public static int countTeams()
	{
		return getTeams().size();
	}

	public static List<Unit> getUnits()
	{
		return units;
	}
	
	public static void addUnit(Unit u)
	{
		units.add(u);
	}
	
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		
	//	if(gc.isMouseGrabbed())
	//	{
			Camera.shiftCamera(newX - oldX, newY - oldY);
	//	}

	}
	
	public void mousePressed(int button, int x, int y)
	{
	//	gc.setMouseGrabbed(true);

	}
	
	public void mouseReleased(int button, int x, int y)
	{
		//gc.setMouseGrabbed(false);

	}
	
	public static void addAnimation(Animation a)
	{
		animations.add(a);
	}
	
	
	public void updateWin()
	{
		
	}
	public void renderWin()
	{
		
	}


}
