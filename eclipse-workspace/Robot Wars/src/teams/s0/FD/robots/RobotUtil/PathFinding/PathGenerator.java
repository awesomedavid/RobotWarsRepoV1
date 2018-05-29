package teams.s0.FD.robots.RobotUtil.PathFinding;

import java.util.ArrayList;

import objects.Point;
import objects.Robot;
import world.World;
import world.cells.Cell;

public class PathGenerator {

	static WrapperCell start;
	static WrapperCell end;
	static WrapperCell current;

	private static ArrayList<WrapperCell> openSet;
	private static ArrayList<WrapperCell> closedSet;

	private static WrapperCell[][] map;
	
	public PathGenerator() {
		
	}

	public static ArrayList<Cell> getCellPath(Point startPoint, Point endPoint) {

		ArrayList<Cell> tempCellPath = new ArrayList<>();

		ArrayList<WrapperCell> tempWrapperPath = getWrapperPath(startPoint, endPoint);

		for (int i = 0; i < tempWrapperPath.size(); i++) {
			tempCellPath.add(tempWrapperPath.get(i));
		}

		return tempCellPath;
	}

	public static ArrayList<WrapperCell> getWrapperPath(Point startPoint, Point endPoint) {
		
		start = (WrapperCell) World.getCell(startPoint);
		end = (WrapperCell) World.getCell(endPoint);

		openSet.add(start);

		map = getMap();

		addAllNeighbors();

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

					WrapperCell neighbor = neighbors.get(i);

					if (!closedSet.contains(neighbor) && !neighbor.hasImpassableTerrain()) {

						float tempG = current.getG() + 1;

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
		} while (!done);

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

	public static float heuristic(Cell a, Cell b) {
		return (float) Math.sqrt(Math.pow((a.getX() - b.getX()), 2) + Math.pow((a.getY() - b.getY()), 2));
	}

	public static void addAllNeighbors() {

		for (int i = 0; i < World.getWidth(); i++) {
			for (int j = 0; j < World.getHeight(); j++) {
				map[i][j].addNeighbors();
			}
		}
	}

	public static WrapperCell[][] getMap() {

		WrapperCell[][] tempMap = null;

		for (int i = 0; i < World.getWidth(); i++) {
			for (int j = 0; j < World.getHeight(); j++) {
				tempMap[i][j] = (WrapperCell) World.getCell(i, j);
			}
		}

		return tempMap;
	}
}
