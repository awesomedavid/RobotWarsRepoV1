package item.weapon;

import objects.Unit;
import values.CoreValues;
import objects.GameObject;
import world.cells.Cell;
import java.util.function.BiPredicate;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import world.World;

public class ShotCalculator implements CoreValues {
	
	public static float getCoverPenalty(int hitNum) 
	{
		// CLEAN SHOT
		if(hitNum == 4)
		{
		//System.out.println("No cover");
			return 0;
		}
		
		// COVER
		else if(hitNum == 2 || hitNum == 3)
		{
		//System.out.println("Partial cover");
			return COVER_PARTIAL_PENALTY;

		}
		
		// BLOCKED
		else
		{
	//	System.out.println("Full cover");
			return COVER_FULL_PENALTY;
		}
	}
	

	public static float getConcealPenalty(int hitNum)
	{
		// CLEAN SHOT
		if(hitNum == 4)
		{
		//System.out.println("No concealment");
			return 0;
		}
		
		// COVER
		else if(hitNum == 2 || hitNum == 3)
		{
		//System.out.println("Partial concealment");
			return CONCEALMENT_PARTIAL_PENALTY;

		}
		
		// BLOCKED
		else
		{
		//System.out.println("Full concealment");
			return CONCEALMENT_TOTAL_PENALTY;
		}
	}
	
	private static Point[] getUnitCorners(GameObject u) {
		Point[] corners = {
				new Point(u.getX(), u.getY()),
				new Point(u.getX() + 1, u.getY()),
				new Point(u.getX(), u.getY() + 1),
				new Point(u.getX() + 1, u.getY() + 1)
		};
		
		return corners;
	}
	
	private static boolean isCellObstructed(int x, int y) {
		if(!World.inBounds(x, y)) {
			if(World.inBounds(x - 1, y) || World.inBounds(x, y - 1))
				return false;
			return true;
		}
		return !World.inBounds(x, y) || World.getCell(x, y).isCover();
	}
	
	private static boolean isCellConcealed(int x, int y) {
		if(!World.inBounds(x, y)) {
			if(World.inBounds(x - 1, y) || World.inBounds(x, y - 1))
				return false;
			return true;
		}
		return !World.inBounds(x, y) || World.getCell(x, y).isObscured();
	}
	
	private static boolean doesLineFailTest(Line l, Cell start, Cell end, BiPredicate<Integer, Integer> func) {
		// Case for vertical line
		if(l.getStart().getX() == l.getEnd().getX()) {
			for(int y = (int) Math.min(l.getStart().getY(), l.getEnd().getY()); y < Math.max(l.getStart().getY(), l.getEnd().getY()); y++) {
				if(end.getY() == y) {
					return false;
				}
				if(func.test((int)l.getStart().getX(), y) && func.test((int)l.getStart().getX() - 1, y))
					return true;
			}
		}
		// Case for horizontal line
		else if(l.getStart().getY() == l.getEnd().getY()) {
			for(int x = (int) Math.min(l.getStart().getX(), l.getEnd().getX()); x < Math.max(l.getStart().getX(), l.getEnd().getX()); x++) {
				if(end.getX() == x) {
					return false;
				}
				if(func.test(x, (int)l.getStart().getY()) && func.test(x, (int)l.getStart().getY() - 1))
					return true;
			}
		}
		else {
			int x0 = (int) l.getStart().getX();
		    int y0 = (int) l.getStart().getY();
		    int x1 = (int) l.getEnd().getX();
		    int y1 = (int) l.getEnd().getY();

		    int dx = Math.abs(x1 - x0);
		    int dy = Math.abs(y1 - y0);
		    int x = x0;
		    int y = y0;
		    
		    if(x1 < x0)
		    	x--;
		    if(y1 < y0)
		    	y--;
		    
		    int n = 1 + dx + dy;
		    int x_inc = (x1 > x0) ? 1 : -1;
		    int y_inc = (y1 > y0) ? 1 : -1;
		    int error = dx - dy;

		    for (; n > 0; --n)
		    {
		    	if(n <= 2)
		    		return false;
		    	if(x == end.getX() && y == end.getY())
		    		return false;
		    	
		    	if(func.test(x, y))
					return true;

		        if (error > 0)
		        {
		            x += x_inc;
		            error -= dy;
		        }
		        else if (error < 0)
		        {
		            y += y_inc;
		            error += dx;
		        }
		        else if (error == 0) {
		        	if(Math.abs(x1 - x0) > Math.abs(y1 - y0)) {
		        		x += x_inc;
		        		error -= dy;
		        	}
		        	else if(Math.abs(x1 - x0) < Math.abs(y1 - y0)){
		        		y += y_inc;
		        		error += dx;
		        	}
		        	else {
		        		x += x_inc;
		            	y += y_inc;
		            	error -= dy;
		            	error += dx;
		            	--n;
		        	}
		        }
		    }
		}
		
		return false;
	}
	
	static public ShotData calcShot(Unit attacker, GameObject target) {
		Point p = null;
		float best = Float.POSITIVE_INFINITY;
		
		Point[] attackerCorners = getUnitCorners(attacker);
		Point[] targetCorners = getUnitCorners(target);
		
		for(Point p0 : attackerCorners) {
			
			int cover = 0;
			int conceal = 0;
			
			for(Point p1 : targetCorners) {
				
				Line l = new Line(p0.getX(), p0.getY(), p1.getX(), p1.getY());
				
				if(!doesLineFailTest(l, attacker.getCell(), target.getCell(), ShotCalculator::isCellObstructed))
					cover++;
				if(!doesLineFailTest(l, attacker.getCell(), target.getCell(), ShotCalculator::isCellConcealed))
					conceal++;
			}
			
			float t = getCoverPenalty(cover) + getConcealPenalty(conceal);
			
			if(t < best) {
				best = t;
				p = p0;
			}
			
			if(best == 0)
				break;
		}
		
		if(p != null)	return new ShotData(new objects.Point((int)p.getX(), (int)p.getY()), best);
		else	return new ShotData(null, best);
	}
}
