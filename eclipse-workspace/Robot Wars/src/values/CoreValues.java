package values;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Color;

public interface CoreValues 
{ 
	public final static int FRAMES_PER_SECOND = 60;
	
//  Auto Resolution Code - May Increase Load Time	
	
//	public final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//	public final static double ACTUAL_WIDTH = screenSize.getWidth();
//	public final static double ACTUAL_HEIGHT = screenSize.getHeight();
//	public final static int RESOLUTION_X = (int) (ACTUAL_WIDTH);
//	public final static int RESOLUTION_Y = (int) (ACTUAL_HEIGHT);
	
//  Manual Resolution Code

	
	final public static float CONCEALMENT_PARTIAL_PENALTY = .25f;	// 25% miss chance if they have partial concealment (2-3 corners)
	final public static float CONCEALMENT_TOTAL_PENALTY = .5f;		// 50% miss chance if they have total concealment (4 corners)
	final public static float COVER_PARTIAL_PENALTY = .5f;			// 50% miss chance if they have partial cover (2-3 corners)
	final public static float COVER_FULL_PENALTY = 1f;				// 100% miss chance if they have full cover  (4 corners)

	public final static float MUSIC_SPECIAL_CHANCE = .10f;
	public final static float MUSIC_HIDDEN_CHANCE = .01f;
	
	public final static int TRANSITION_FADE_TIME = 60;
	public final static int TRANSITION_FADE_TIME_SLOW = 240;
	
	public final static int BASE_CHOP_SKILL = 20;
	public final static int BASE_MINE_SKILL = 20;
	
	public final static int TICK_TIME = Math.max(1, 10 - Settings.gameSpeed);
	
	public final static int CELL_SIZE = 32;

	public static final int PLAYER_START_EDGE = 7;

	public static final int TEAM_STARTING_METAL = 100;
	public static final int TEAM_STARTING_STONE = 0;
	public static final int TEAM_STARTING_WOOD = 0;

	public final static int CONTROL_ZONE_MIN_DISTANCE = 25;
	public static final int CONTROL_ZONE_NUMBER = 3;
	public static final int CONTROL_ZONE_SIZE = 5;
	
	public static final int POINTS_FOR_KILL = 2;		// Friendly kill awards points to all opponents
	public static final int POINTS_TO_WIN = 100;
	public static final int POINT_GAIN_FREQUENCY = 120;
	public static final float POINT_GAIN_VALUE = .1f;
	public static final int FRAMES_TO_FLIP_ZONE = 500;



}
