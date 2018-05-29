package core;

import java.util.ArrayList;

import objects.GameObject;
import objects.Point;
import objects.Unit;

public interface Utility {
	public static int random(int max) {
		return (int) (Math.random() * max);
	}

	public static int random(int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;

	}

	public static float random(double min, double max) {
		return (float) (min + (Math.random() * (max - min)));
	}
	
	public static int distance(GameObject a, GameObject b) {
		if (a == null || b == null)
			return -1;
		return distance(a.getX(), a.getY(), b.getX(), b.getY());
	}
	
	public static int distance(Point a, Point b) {
		if (a == null || b == null)
			return -1;
		return distance(a.getX(), a.getY(), b.getX(), b.getY());
	}

	public static int distance(float x1, float y1, float x2, float y2) {
		return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	
	public static float approxDistance(float x1, float y1, float x2, float y2) {
		float x = x1 -x2;
		x = x > 0 ? x : -x;
		float y = y1 - y2;
		y = y > 0 ? y : -y;
		return (int) (x + y);
//		return (float) (Math.pow(x1 - x2, 2) + Math.pow(y1- y2, 2));
	}


}
