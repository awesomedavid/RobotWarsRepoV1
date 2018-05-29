package values;

import world.MapSize;

public class Settings 
{	
	public final static int RESOLUTION_X = 1921; 	// We're using a weird resolution for the projector
	public final static int RESOLUTION_Y = 1081; 	// You can change this to fit your home system better
	
	// Gameplay
	public static int gameSpeed = 2;			
	public static MapSize mapSize = MapSize.SMALL;
	public static boolean tournamentMode = true;
	public static boolean teamSelection = true;

	// UI - Robot Data
	public static boolean showHealthbars = true;
	public static boolean showHealthValues = false;
	public static boolean showAction = false;
	public static boolean showGridLines = false;
	public static boolean showBiomeInfo = false;
	public static boolean showLatencyValue = false;
	
	// Audio
	public static boolean musicOn = true;
	public static boolean sfxOn = true;
	
	// UI - General
	public static boolean showCoverOverlay = false;
	public static boolean showConcealmentOverlay = false;
	public static boolean showFloatText = true;
	public static boolean showMiniMap = true;
	public static boolean showHUD = true;
	public static boolean showDebug = true;
	public static boolean showPlayerDisplay = false;
	
	// Graphics Settings
	public static boolean alphaBlending = true;
	
	// Scrolling Rate
	public static float mouseScrollSpeed = 1.0f;
	public static float keyScrollSpeed = 1.0f;
	public static boolean zoomEnabled = true;		// Zoom is not working right now

		

}
