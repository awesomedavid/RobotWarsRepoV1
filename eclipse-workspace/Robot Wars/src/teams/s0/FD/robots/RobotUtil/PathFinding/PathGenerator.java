package teams.s0.FD.robots.RobotUtil.PathFinding;

import java.util.ArrayList;

import objects.Point;
import objects.Robot;
import values.WorldValues;
import world.World;
import world.cells.Cell;
import world.cells.feature.Beacon;
import world.cells.terrain.WaterDeep;

public class PathGenerator {

	static WrapperCell start;
	static WrapperCell end;
	static WrapperCell current;

	private static ArrayList<WrapperCell> openSet;
	private static ArrayList<WrapperCell> closedSet;

	private static WrapperCell[][] map = new WrapperCell[World.getWidth()][World.getHeight()];;

	public PathGenerator() {

	}

	public static ArrayList<Cell> getCellPath(Point startPoint, Point endPoint) {

		ArrayList<Cell> tempCellPath = new ArrayList<>();

		ArrayList<WrapperCell> tempWrapperPath = getWrapperPath(startPoint, endPoint);

		if (tempWrapperPath == null) {
			return null;
		}

		for (int i = 0; i < tempWrapperPath.size(); i++) {
			tempCellPath.add(tempWrapperPath.get(i).getCell());
		}

		return tempCellPath;
	}

	public static ArrayList<WrapperCell> getWrapperPath(Point startPoint, Point endPoint) {

		map = getMap();

		openSet = new ArrayList<WrapperCell>();
		closedSet = new ArrayList<WrapperCell>();

		start = map[startPoint.getX()][startPoint.getY()];
		end = map[endPoint.getX()][endPoint.getY()];

		if (end.getCell().hasFeature(Beacon.class) || end.getCell().hasImpassableTerrain() || end.getCell().hasUnit()) {
			return null;
		}

		openSet.add(start);

		addAllNeighbors();

		int tries = 100000;

		boolean done = false;

		do {

			if (openSet.size() > 0) {

				int winner = 0;

				for (int i = 0; i < openSet.size(); i++) {

					if (openSet.get(i).getF() < openSet.get(winner).getF()) {
						winner = i;
					}
				}
				current = openSet.get(winner);

				if (current.equals(end)) {
					done = true;
					return generateWrapperPath();
				}

				openSet.remove(current);
				closedSet.add(current);

				ArrayList<WrapperCell> neighbors = current.getNeighbors();

				for (int i = 0; i < neighbors.size(); i++) {

					tries--;

					if (tries < 0) {
						System.out.println("lag");
						return null;
					}

					WrapperCell neighbor = neighbors.get(i);
					Cell c = neighbor.getCell();

					if (!closedSet.contains(neighbor) && !c.hasFeature(Beacon.class)
							&& !c.hasImpassableTerrain() ) {

						float tempG = current.getG() + c.getDifficulty();
						
						if(c.hasFeature()) {
							tempG += (c.getFeature().getCurHealth());
						}

						boolean newPath = false;

						if (openSet.contains(neighbor)) {
							if (tempG < neighbor.getG()) {
								neighbor.setG(tempG);
								newPath = true;
							}
						} else {
							neighbor.setG(tempG);
							newPath = true;
							openSet.add(neighbor);
						}
						if (newPath) {
							neighbor.setH(heuristic(neighbor, end));
							neighbor.setF(neighbor.getG() + neighbor.getH());
							neighbor.setPrevious(current);
						}
					}
				}
			}
		} while (!done && tries > 0);

		// System.out.println("shit");

		return null;
	}

	public static ArrayList<WrapperCell> generateWrapperPath() {

		ArrayList<WrapperCell> tempPath = new ArrayList<WrapperCell>();

		WrapperCell temp = current;
		tempPath.add(temp);
		if (temp != null) {
			while (temp.getPrevious() != null) {
				tempPath.add(temp);
				temp = temp.getPrevious();
			}
		}
		return tempPath;

	}

	public static float heuristic(WrapperCell cell1, WrapperCell cell2) {

		return (Math.abs(cell1.getCell().getX() - cell2.getCell().getX())
				+ Math.abs(cell1.getCell().getY() - cell2.getCell().getY())) * WorldValues.EASY;
	}

	public static void addAllNeighbors() {

		for (int i = 0; i < World.getWidth(); i++) {
			for (int j = 0; j < World.getHeight(); j++) {
				map[i][j].addNeighbors();
			}
		}
	}

	public static WrapperCell[][] getMap() {

		WrapperCell[][] tempMap = new WrapperCell[World.getWidth()][World.getHeight()];

		for (int i = 0; i < World.getWidth(); i++) {
			for (int j = 0; j < World.getHeight(); j++) {
				tempMap[i][j] = new WrapperCell(World.getCell(i, j), tempMap);
			}
		}

		map = tempMap;

		return tempMap;
	}
}
