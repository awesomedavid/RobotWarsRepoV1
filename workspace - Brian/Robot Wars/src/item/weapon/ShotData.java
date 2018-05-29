package item.weapon;

import objects.Point;

public class ShotData {
	private Point p;
	private float penalty;
	
	public ShotData(Point p, float penalty) {
		this .p = p;
		this.penalty = penalty;
	}
	
	public Point getCorner() {
		return p;
	}
	
	public float getPenalty() {
		return penalty;
	}
}
