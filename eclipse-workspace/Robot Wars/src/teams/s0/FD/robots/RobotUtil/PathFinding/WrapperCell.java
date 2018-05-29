package teams.s0.FD.robots.RobotUtil.PathFinding;

import java.util.ArrayList;

import world.World;
import world.cells.Cell;

public class WrapperCell extends Cell {

	private float f;
	private float g;
	private float h;

	private ArrayList<WrapperCell> neighbors;
	private WrapperCell previous;

	public WrapperCell(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void addNeighbors() {

		if (inbounds(getX() + 1, getY())) {
			neighbors.add((WrapperCell) World.getCell(getX() + 1, getY()));
		}
		if (inbounds(getX() - 1, getY())) {
			neighbors.add((WrapperCell) World.getCell(getX() - 1, getY()));
		}
		if (inbounds(getX(), getY() + 1)) {
			neighbors.add((WrapperCell) World.getCell(getX(), getY() + 1));
		}
		if (inbounds(getX(), getY() - 1)) {
			neighbors.add((WrapperCell) World.getCell(getX() + 1, getY() - 1));
		}

	}

	public ArrayList<WrapperCell> getNeighbors() {
		return neighbors;
	}

	public boolean inbounds(int a, int b) {

		return a >= 0 && a < World.getWidth() && b >= 0 && b < World.getHeight();
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
