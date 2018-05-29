package values;

public interface WorldValues 
{
	// Terrain difficulty base values
	public static final int EASY = 10;
	public static final int AVERAGE = 20;
	public static final int HARD = 30;		
	public static final int IMPOSSIBLE = Integer.MAX_VALUE;
	
	// Easy Terrain
	public static final int TERRAIN_FLOOR_STONE_DIFFICULTY = EASY;
	public static final int TERRAIN_FLOOR_WOOD_DIFFICULTY = EASY;
	
	// Average Terrain
	public static final int TERRAIN_SOIL_DIFFICULTY = AVERAGE;
	public static final int TERRAIN_STONE_ROUGH_DIFFICULTY = AVERAGE;
	public static final int TERRAIN_SAND_DIFFICULTY = AVERAGE;
	
	// Hard Terrain
	public static final int TERRAIN_WATER_SHALLOW_DIFFICULTY = HARD;
	public static final int TERRAIN_MUD_DIFFICULTY = HARD;

	public static final float WALL_STONE_HEALTH = 300;
	public static final float WALL_WOOD_HEALTH = 150;
	public static final float TREE_HEALTH = 250;
	public static final float MOUNTAIN_HEALTH = 500;
	
}

