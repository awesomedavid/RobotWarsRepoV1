package teams.s0.FD;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.PathFinder;

import core.Utility;
import item.Item;
import item.StoneBlock;
import objects.Direction;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;
import teams.s0.FD.FDTeam;
import teams.s0.FD.robots.RobotUtil.PathFinding.PathGenerator;
import values.CoreValues;
import values.WorldValues;
import world.World;
import world.cells.Cell;
import world.cells.feature.Beacon;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public abstract class FDRobot extends Robot {
	int abandonMoveCounter;
	Direction goal;
	int steps;
	int jitter;
	final int BOUNDARY = 5;
	final int JITTER_COOLDOWN_MAX = 20;
	final int JITTER_COOLDOWN_MIN = 10;
	private ArrayList<Cell> path;

	// PathGenerator pathGenerator = new PathGenerator();

	public FDRobot(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
		jitter = Utility.random(JITTER_COOLDOWN_MIN, JITTER_COOLDOWN_MAX);
		goal = Direction.getRandom();
		turn(goal);
	}

	// Fire primary
	public void shoot(GameObject target) {
		if (getPrimary() != null && getPrimary().canUse(target)) {
			usePrimary(target);
		} else if (getSecondary() != null && getSecondary().canUse(target)) {
			useSecondary(target);
		}
	}

	// Reload primary
	public void reloadIfNeeded() {
		if (hasPrimary() && getPrimary().canReload() && getPrimary().needsReload()) {
			reload(getPrimary());
		}
	}

	// Pickup items
	public void pickupIfAble() {
		if (getCell().hasItem(Item.class)) {
			pickup();
		}
	}

	// Stop and turn left when capturing
	public void spinToWin() {
		if (inZoneHostile() || inZoneNeutral()) {
			stop();
			if (getTimer() % 10 == 0) {
				turnLeft();

			}
		}
	}

	// This method is called when the robot dies
	public void onDeath() {

	}

	// Move forward if possible, if not go random. Only try 10 times.
	public void attemptMove() {
		if (abandonMoveCounter == 10) {
			return;
		} else if (frontIsClear()) {
			move();
		} else {
			abandonMoveCounter++;
			// turnRandom();
			// attemptMove();
		}
	}

	// This method makes the robot wander around the map, avoiding edges
	public void wander() {
		abandonMoveCounter = 0;

		if (getTimer() % 4 == 0) {

			shiftGoal();
			turn(goal);
			attemptMove();

		}

		// if (jitter > 0) {
		// jitter--;
		// }
		//
		// if (jitter == 0) {
		// jitter = Utility.random(JITTER_COOLDOWN_MIN, JITTER_COOLDOWN_MAX);
		// goal = Direction.getRandom();
		// turn(goal);
		// }

	}

	public void shiftGoal() {

		// ArrayList<Cell> tempPath = PathGenerator.getCellPath(getPoint(),
		// World.getCell(World.getWidth() - 1, World.getHeight() - 1).getPoint());

		if (getTimer() % 60 == 0) {
			ArrayList<Cell> tempPath = PathGenerator.getCellPath(getPoint(),
					World.getCell(World.getWidth() - 1, World.getHeight() - 1).getPoint());

			if (tempPath != null) {
				path = tempPath;
			}
		}

		// ArrayList<Cell> path = PathGenerator.getCellPath(getPoint(),
		// getNearestUncappedBeacon());

		if (path == null) {
			return;
		}

		if (path.size() > 1) {

			Cell c = path.get(path.size() - 1);

			if (getX() < c.getX()) {
				goal = Direction.EAST;
			} else if (getX() > c.getX()) {
				goal = Direction.WEST;
			} else if (getY() < c.getY()) {
				goal = Direction.SOUTH;
			} else if (getY() > c.getY()) {
				goal = Direction.NORTH;
			}

			path.remove(c);
		}
		// System.out.println(c.getX() + " " + c.getY());
	}

	// This method makes the robot move - awkwardly - toward the target
	public void seek(GameObject target) {
		abandonMoveCounter = 0;
		if (target == null) {
			return;
		} else if (target.getX() > getX()) {
			turnEast();
			attemptMove();
		} else if (target.getX() < getX()) {
			turnWest();
			attemptMove();
		} else if (target.getY() > getY()) {
			turnSouth();
			attemptMove();
		} else if (target.getY() < getY()) {
			turnNorth();
			attemptMove();
		} else {
			turnRandom();
			attemptMove();
		}
	}

	// A supporting method for the basic robot
	public void turnRandom() {
		int r = Utility.random(3);

		switch (r) {
		case 0:
			turnLeft();
			break;
		case 1:
			turnRight();
			break;
		case 2:
			turnAround();
			break;
		}
	}

	public void display(Graphics g) {

		if (getTimer() % 1 == 0) {

			// ArrayList<Cell> path = PathGenerator.getCellPath(getPoint(),
			// World.getCell(45, 50).getPoint());
			// ArrayList<Cell> path = PathGenerator.getCellPath(getPoint(),
			// getNearestUncappedBeacon());

			if (path == null) {
				return;
			}

			for (int i = 0; i < path.size(); i++) {
				Cell c = path.get(i);
				g.setColor(new Color(0, 0, 200, 70));
				g.fillRect(c.getXPixel(), c.getYPixel(), CoreValues.CELL_SIZE, CoreValues.CELL_SIZE);
			}
		}
	}

	public Point getNearestUncappedBeacon() {
		ArrayList<Point> beacons = (ArrayList<Point>) World.getControlPoints();

		Point nearest = new Point(0, 0);
		Unit nearestEnemy;
		float dist = Float.MAX_VALUE;

		for (Point p : beacons) {
			Beacon beacon = (Beacon) World.getCell(p).getFeature();

			if (Utility.distance(p.getX(), p.getY(), x, y) < dist && beacon.getZone().getOwnerID() != getTeam().getID())
				nearest = p;

		}
		return new Point(nearest.getX(), nearest.getY());
	}

	public Point getNearestControlPoint() {

		int dist = Integer.MAX_VALUE;
		Point result = null;

		for (Point p : World.getControlPoints()) {

			if (FDTeam.getDistance(this.getPoint(), p) < dist) {
				dist = FDTeam.getDistance(this.getPoint(), p);
				result = p;
			}
		}
		return result;
	}
}
