package objects;

public class Point 
{
	int x;
	int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p)
	{
		this.x = p.getX();
		this.y = p.getY();
	}
	
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int value)
	{
		x = value;
	}
	
	public void setY(int value)
	{
		y = value;
	}
	
	public void set(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Point p)
	{
		return x == p.x && y == p.y;
	}
}
