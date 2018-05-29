package values;

public interface RobotValues 
{	
	// Maximum Game Values
	public static final int MAXIMUM_HEALTH_CAP = 100;
	public static final int MAXIMUM_PLATING_CAP = 100;
	public static final int MAXIMUM_SHIELD_CAP = 100;
	public static final float MAXIMUM_DODGE_CAP = .25f;
	public static final float MAXIMUM_ACCURACY_CAP = .5f;
	public static final int MAXIMUM_HEAT_CAP = 100;
	public static final int MAXIMUM_LATENCY = 1500;
	public final static int MAXIMUM_PROCESSERS = 3;
	
	public static final float ROBOT_BASE_SPEED_MULTIPLIER = .8f;
	
	// Frame Data
	public static final int FRAME_LIGHT_WEIGHT_MAX = 10;
	public static final float FRAME_LIGHT_SPEED_BASE = 1f;
	public static final int FRAME_LIGHT_HEALTH_BASE = 40;
	
	public static final int FRAME_MEDIUM_WEIGHT_MAX = 15;
	public static final float FRAME_MEDIUM_SPEED_BASE = .65f;
	public static final int FRAME_MEDIUM_HEALTH_BASE = 70;

	public static final int FRAME_HEAVY_WEIGHT_MAX = 20;
	public static final float FRAME_HEAVY_SPEED_BASE = .3f;
	public static final int FRAME_HEAVY_HEALTH_BASE = 100;
	
	// Respawn
	public static final int RESPAWN_TIME_BASE = 1200;
	public static final float RESPAWN_TIME_PENALTY_PER_DEATH = 1.2f;
	
	// Latency
	public final static int OVERCLOCK_LEVEL_PER_PROCESSOR = MAXIMUM_LATENCY/MAXIMUM_PROCESSERS;

	// Heat and Cooling
	public static final float ROBOT_HEAT_BASE = 100;
	public static final float ROBOT_COOL_RATE_BASE = .2f;
	public final static float ROBOT_OVERCLOCK_RATE = ROBOT_COOL_RATE_BASE * 4;

	// Shields
	public static final int SHIELD_REGEN_FREQUENCY = 120;			// Every 120 ticks  (2 seconds)
	public static final int SHIELD_RECOVERY_TIME = 120;			// Amount of time without taking damage for shield to recover
}
