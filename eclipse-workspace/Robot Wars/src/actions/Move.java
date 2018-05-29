package actions;

import objects.Direction;
import objects.Unit;
import world.cells.Cell;

public class Move extends Action 
{
	Cell destination;
	Direction direction;
	
	public Move(Unit actor)
	{
		super(actor, 0);
		
		destination = actor.look();
		
		if(destination != null && actor.canEnter(destination))
		{
			duration = (int) (actor.look().getDifficulty() / actor.getMoveSpeed());
			destination.setReservation(actor);
			
			if(duration < 0)
			{
				duration = Integer.MAX_VALUE;
			}
		}
		else
		{
			cancel();
		}
	}
	
	public void update()
	{
		super.update();
			
		//actor.turn(direction);
		
		if(!isValidMovement())
		{
			cancel();
		}
		
	}
	
	protected void complete()
	{		
		if(isValidMovement())
		{
			destination.clearReservation();
			actor.getCell().removeUnit();
			actor.setCell(destination);
			actor.getCell().addUnit(actor);
			actor.setLocation(actor.getCell().getX(), actor.getCell().getY());
		}
	}
	
	public boolean isValidMovement()
	{
		return actor.isAlive() && destination != null && actor.canEnter(destination);
	}
	
	public void cancel()
	{
		super.cancel();
		destination.clearReservation();
	}
}
