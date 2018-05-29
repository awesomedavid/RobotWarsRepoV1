package teams.s0.FD.robots.RobotUtil.PathFinding;

import java.util.ArrayList;

import world.World;
import world.cells.Cell;

public class WrapperCell {

	private float f;
	private float g;
	private float h;

	private Cell cell;

	private WrapperCell[][] map;

	private ArrayList<WrapperCell> neighbors;
	private WrapperCell previous;

	public WrapperCell(Cell cell, WrapperCell[][] map) {
		this.cell = cell;
		this.map = map;
		
		neighbors = new ArrayList<WrapperCell>();

		if (map == null) {
			System.out.println("sheeeeeit");
		}
		// TODO Auto-generated constructor stub
	}

	public void addNeighbors() {
		
		if(cell == null) {
			System.out.println("cell");
		}
		if(map == null) {
			System.out.println("map");
		}
		

		if (inbounds(cell.getX() + 1, cell.getY())) {
			neighbors.add(map[cell.getX() + 1][cell.getY()]);
		}
		if (inbounds(cell.getX() - 1, cell.getY())) {
			neighbors.add(map[cell.getX() - 1][cell.getY()]);
		}
		if (inbounds(cell.getX(), cell.getY() + 1)) {
			neighbors.add(map[cell.getX()][cell.getY() + 1]);
		}
		if (inbounds(cell.getX(), cell.getY() - 1)) {
			neighbors.add(map[cell.getX()][cell.getY() - 1]);
		}

	}

	public ArrayList<WrapperCell> getNeighbors() {
		
		if(neighbors == null) {
			System.out.println("III");
			addNeighbors();
		}
		return neighbors;
	}

	public boolean inbounds(int a, int b) {

		return a >= 0 && a < World.getWidth() && b >= 0 && b < World.getHeight();
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public WrapperCell[][] getMap() {
		return map;
	}

	public void setMap(WrapperCell[][] map) {
		this.map = map;
	}

	public void setNeighbors(ArrayList<WrapperCell> neighbors) {
		this.neighbors = neighbors;
	}

	public WrapperCell getPrevious() {
		return previous;
	}

	public void setPrevious(WrapperCell previous) {
		this.previous = previous;
	}

	public float getF() {
		return f;
	}

	public void setF(float f) {
		this.f = f;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}
}
