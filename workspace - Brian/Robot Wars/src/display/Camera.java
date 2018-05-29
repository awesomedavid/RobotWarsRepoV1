
package display;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import core.Game;
import values.CoreValues;
import values.Settings;
import world.World;


public class Camera implements CoreValues
{	
	// Basic Zooming and Scrolling
	public static final float ZOOM_RATE = .02f;
	public static final float ZOOM_MAX = 2f;
	public static final float ZOOM_MIN = 1;
	public static final float ZOOM_BASE = 1;

	public static final float SCROLL_RATE_BASE_MOUSE = 2;
	public static final float SCROLL_RATE_BASE_KEY = 10;
	
	private static float x;
	private static float y;

	private static float zoom;	

	public Camera(GameContainer gc) 
	{
		x=0;
		y=0;

		zoom = ZOOM_BASE;
	}
	
	public void cameraControl(GameContainer gc)
	{
		float scroll_rate = SCROLL_RATE_BASE_KEY * 1 / zoom;

		if (gc.getInput().isKeyDown(Input.KEY_W))
			y -= scroll_rate;
		if (gc.getInput().isKeyDown(Input.KEY_S))
			y += scroll_rate;
		if (gc.getInput().isKeyDown(Input.KEY_A))
			x -= scroll_rate;
		if (gc.getInput().isKeyDown(Input.KEY_D))
			x += scroll_rate;
		
		
		if (gc.getInput().isKeyDown(Input.KEY_HOME)) {
			x = 0;
			y = 0;
		}

		if(Settings.zoomEnabled)
		{
			if (gc.getInput().isMouseButtonDown(0))
				zoomIn();
			if (gc.getInput().isMouseButtonDown(1))
				zoomOut();
			if (gc.getInput().isMouseButtonDown(2)) {
				x = 0;
				y = 0;
			}
		}

	}
	
	public void update(GameContainer gc) 
	{
			cameraControl(gc);
	}

	public static float getX() {
		return x;
	}

	public static float getY() {
		return y;
	}

	public static float getViewWidth() {
		
		return (int) (Game.getScreenWidth() * 1 / getZoom());
		
	}

	public static float getViewHeight() {
		return (int) (Game.getScreenHeight() * 1 / getZoom());
	}

	public static void shiftCamera(float deltaX, float deltaY) 
	{		
		x+= deltaX * SCROLL_RATE_BASE_MOUSE * Settings.mouseScrollSpeed;
		y+= deltaY * SCROLL_RATE_BASE_MOUSE * Settings.mouseScrollSpeed;		
	}

	public static void centerView(float xPos, float yPos) {
		x = xPos;
		y = yPos;
	}
	
	public static void zoomIn(float amount) {
		float oldWidth = getViewWidth();
		float oldHeight = getViewHeight();
		
		zoom += zoom * amount;
	
		if (zoom < ZOOM_MIN)
			zoom = ZOOM_MIN;
		if (zoom > ZOOM_MAX)
			zoom = ZOOM_MAX;
		
		x += (oldWidth-getViewWidth())/2;
		y += (oldHeight-getViewHeight())/2;
	}
	
	public static void zoomOut(float amount) {
		float oldWidth = getViewWidth();
		float oldHeight = getViewHeight();
		
		zoom -= zoom * amount;
		
		if (zoom < ZOOM_MIN)
			zoom = ZOOM_MIN;
		if (zoom > ZOOM_MAX)
			zoom = ZOOM_MAX;
		
		x += (oldWidth-getViewWidth())/2;
		y += (oldHeight-getViewHeight())/2;
	}
	
	
	public static void zoomIn() {
		zoomIn(ZOOM_RATE);
	}

	public static void zoomOut() {
		zoomOut(ZOOM_RATE);
	}
	
	public static float getZoom() {
		return zoom;
	}
	
	public static int tileFirstX()
	{
		return Math.max(0, (int) (getX() / CoreValues.CELL_SIZE));
	}
	
	public static int tileFirstY()
	{
		return Math.max(0, (int) (getY() / CoreValues.CELL_SIZE));
	}
	
	public static int tileLastX()
	{
		return Math.min(World.getWidth(), (int) ((getX() + getViewWidth()) / CoreValues.CELL_SIZE + 1));
	}
	
	public static int tileLastY()
	{
		return Math.min(World.getHeight(), (int) ((getY() + getViewHeight()) / CoreValues.CELL_SIZE + 1));
	}

}


//package display;
//
//import org.newdawn.slick.GameContainer;
//import org.newdawn.slick.Input;
//
//import values.CoreValues;
//import values.Settings;
//import world.World;
//
//public class Camera 
//{	
//	public static final float SCROLL_RATE_BASE_AUTO = 2;
//	public static final float SCROLL_RATE_BASE_MOUSE = 1.2f;
//	public static final float SCROLL_RATE_BASE_KEY = 18;
//	
//	private static float x;
//	private static float y;
//
//	public Camera(GameContainer gc) 
//	{
//		x = 0;
//		y = 0;
//	}
//	
//	public void cameraControl(GameContainer gc)
//	{
//		float scroll_rate = SCROLL_RATE_BASE_KEY * Settings.keyScrollSpeed ;
//
//		if (gc.getInput().isKeyDown(Input.KEY_W))
//			y -= scroll_rate;
//		if (gc.getInput().isKeyDown(Input.KEY_S))
//			y += scroll_rate;
//		if (gc.getInput().isKeyDown(Input.KEY_A))
//			x -= scroll_rate;
//		if (gc.getInput().isKeyDown(Input.KEY_D))
//			x += scroll_rate;
//		
//		
//		if (gc.getInput().isKeyDown(Input.KEY_HOME)) {
//			x = 0;
//			y = 0;
//		}
//
//
//		if (gc.getInput().isMouseButtonDown(2)) {
//			x = 0;
//			y = 0;
//		}
//
//	}
//	
//	public void update(GameContainer gc) 
//	{
//			cameraControl(gc);
//	}
//
//	public static int getX() {
//		return (int) x;
//	}
//
//	public static int getY() {
//		return (int) y;
//	}
//
//	public static float getViewWidth() {
//		return CoreValues.RESOLUTION_X;
//	}
//
//	public static float getViewHeight() {
//		return CoreValues.RESOLUTION_Y;
//	}
//
//	public static void shiftCamera(float deltaX, float deltaY) 
//	{		
//		
//		x+= deltaX * SCROLL_RATE_BASE_MOUSE * Settings.mouseScrollSpeed;
//		y+= deltaY * SCROLL_RATE_BASE_MOUSE * Settings.mouseScrollSpeed;
////		final float DELTA_X = deltaX;
////		final float DELTA_Y = deltaY;
////
////		
////		
////		// West
////		if (DELTA_X < 0 && x + DELTA_X > 0) {
////			x += DELTA_X * SCROLL_RATE_BASE_MOUSE;
////		} else if (DELTA_X < 0) {
////			x = 0;
////		}
////
////		// East
////		if (DELTA_X > 0 && x + getViewWidth() + DELTA_X < Values.WORLD_WIDTH_PIXEL ) {
////			x += DELTA_X * SCROLL_RATE_BASE_MOUSE;
////		} else if (DELTA_X > 0) {
////			x = Values.WORLD_WIDTH_PIXEL - getViewWidth();
////		}
////
////		// North
////		if (DELTA_Y < 0 && y + DELTA_Y > 0) {
////			y += DELTA_Y * SCROLL_RATE_BASE_MOUSE;
////		} else if (DELTA_Y < 0) {
////			y = 0;
////		}
////
////		// South
////		if (DELTA_Y > 0 && y + getViewHeight() + DELTA_Y < Values.WORLD_HEIGHT_PIXEL) {
////			y += DELTA_Y * SCROLL_RATE_BASE_MOUSE;
////		} else if (DELTA_Y > 0) {
////			y = Values.WORLD_HEIGHT_PIXEL - getViewHeight();
////		}
//
//	}
//
//	public static void centerView(float xPos, float yPos) {
//		x = xPos;
//		y = yPos;
//	}
//	
//	public static int tileFirstX()
//	{
//		return Math.max(0, (int) (getX() / CoreValues.CELL_SIZE));
//	}
//	
//	public static int tileFirstY()
//	{
//		return Math.max(0, (int) (getY() / CoreValues.CELL_SIZE));
//	}
//	
//	public static int tileLastX()
//	{
//		return Math.min(World.getWidth(), (int) ((getX() + getViewWidth()) / CoreValues.CELL_SIZE + 1));
//	}
//	
//	public static int tileLastY()
//	{
//		return Math.min(World.getHeight(), (int) ((getY() + getViewHeight()) / CoreValues.CELL_SIZE + 1));
//	}
//	
//
//	
//	
//}
