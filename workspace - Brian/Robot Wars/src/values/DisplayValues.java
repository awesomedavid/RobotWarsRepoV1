package values;

import org.newdawn.slick.Color;

public interface DisplayValues 
{	
	public static final Color COLOR_PLATING = new Color(255, 200, 50);
	public static final Color COLOR_SHIELD = new Color(0, 100, 255);
	public static final Color COLOR_HEALTH = new Color(255, 50, 50);
	public static final Color COLOR_HEAT = new Color(150, 75, 0);
	public static final Color COLOR_OVERCLOCK = new Color(50, 75, 150);

	public static final int ROBOT_CARD_WIDTH = (int) (.089f * Settings.RESOLUTION_X);
	public static final int ROBOT_CARD_HEIGHT = (int) (.13f * Settings.RESOLUTION_Y);
	
	public static final int CARD_SPACING = (int) (.094f * Settings.RESOLUTION_X);
	public static final int CARD_INDENT = (int) (.007f * Settings.RESOLUTION_X);	
	
	public final static float TITLE_INDENT = 20;

	public final static int STATUS_BAR_INDENT = 10;
	
	public static final int STATUS_BAR_HORIZONTAL_WIDTH = (int) (.0655f * Settings.RESOLUTION_X);
	public static final int STATUS_BAR_HORIZONTAL_HEIGHT = (int) (.007f * Settings.RESOLUTION_Y);
	
	public static final int HEAT_BAR_WIDTH = (int) (.011f * Settings.RESOLUTION_X);
	public static final int HEAT_BAR_HEIGHT = (int) (.07f * Settings.RESOLUTION_Y);
	
	public static final int OVERCLOCK_BAR_WIDTH = (int) (.005f * Settings.RESOLUTION_X);
	public static final int OVERCLOCK_BAR_HEIGHT = (int) (.07f * Settings.RESOLUTION_Y);
	
	public static final int STATUS_ZONE_HEIGHT = STATUS_BAR_INDENT * 2 + STATUS_BAR_HORIZONTAL_HEIGHT * 3;

	public static final int HUD_HEIGHT = (int) (.14f * Settings.RESOLUTION_Y);

	
	public static final int CAPTURE_BAR_WIDTH = (int) (.11f * Settings.RESOLUTION_X);
	public static final int CAPTURE_BAR_HEIGHT = (int) (.015f * Settings.RESOLUTION_Y);

//	public static final int CAPTURE_BAR_WIDTH = (int) (.49f * CoreValues.RESOLUTION_X);
//	public static final int CAPTURE_BAR_HEIGHT = (int) (.013f * CoreValues.RESOLUTION_Y);
	
	public static final int COMBAT_FLOAT_TEXT_DURATION = 80;
}
